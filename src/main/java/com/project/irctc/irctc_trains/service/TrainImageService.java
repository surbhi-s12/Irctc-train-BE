package com.project.irctc.irctc_trains.service;

import com.project.irctc.irctc_trains.config.AppConstants;
import com.project.irctc.irctc_trains.dto.TrainImageDataWithResource;
import com.project.irctc.irctc_trains.dto.TrainImageResponse;
import com.project.irctc.irctc_trains.entities.Train;
import com.project.irctc.irctc_trains.entities.TrainImage;
import com.project.irctc.irctc_trains.exception.ResourceNotFoundException;
import com.project.irctc.irctc_trains.respositories.TrainImageRepository;
import com.project.irctc.irctc_trains.respositories.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

public class TrainImageService {
    @Value("${train.image.folder.path}")
    private String folderPath;
    @Autowired
    private TrainRepository trainRepository;

    @Autowired
    private TrainImageRepository trainImageRepository;

    public TrainImageResponse upload(MultipartFile file, Long trainNo) throws IOException {
        Train train = trainRepository.findById(trainNo).orElseThrow(() -> new ResourceNotFoundException("train not found !!"));

        if (!Files.exists(Paths.get(folderPath))) {
            System.out.println("creating folder");
            Files.createDirectories(Paths.get(folderPath));
        }
        String filePath = folderPath + UUID.randomUUID() + "_" + file.getOriginalFilename();
        Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
        System.out.println("File Uploaded");
        TrainImage trainImage = new TrainImage();
        trainImage.setFileName(filePath);
        trainImage.setFileType(file.getContentType());
        trainImage.setSize(file.getSize());
        trainImage.setTrain(train);
        Train savedTrain = trainRepository.save(train);

        return TrainImageResponse.from(savedTrain.getTrainImage(), AppConstants.BASE_URL, trainNo);
    }


    public TrainImageDataWithResource loadImageByTrain(Long trainId) throws MalformedURLException {
        Train train = trainRepository.findById(trainId).orElseThrow(() -> new ResourceNotFoundException("Train nit found!!"));
        TrainImage trainImage = train.getTrainImage();
        if(trainImage == null){
            throw new ResourceNotFoundException("Image not found!!");
        }

        Path path = Paths.get(trainImage.getFileName());

        if(!Files.exists(path)){
            throw new ResourceNotFoundException("Image not found!!");
        }
        UrlResource urlResource = new UrlResource(path.toUri());
        TrainImageDataWithResource trainImageDataWithResource = new TrainImageDataWithResource(trainImage, urlResource );
        return  trainImageDataWithResource;
    }
}
