package com.project.irctc.irctc_trains.controllers.admin;

import com.project.irctc.irctc_trains.config.AppConstants;
import com.project.irctc.irctc_trains.dto.PagedResponse;
import com.project.irctc.irctc_trains.dto.StationDto;
import com.project.irctc.irctc_trains.service.StationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/stations")
public class StationController {
    private final StationService stationService;

    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @PostMapping
    public ResponseEntity<StationDto> createStation(@Valid @RequestBody StationDto stationDto) {
        StationDto dto = stationService.createStation(stationDto);
        return new ResponseEntity<StationDto>(dto, HttpStatus.CREATED);
    }

    @GetMapping
    public PagedResponse<StationDto> getAllStations(@RequestBody StationDto stationDto,
                                                    @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE) int page,
                                                    @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size,
                                                    @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
                                                    @RequestParam(value = "sortDir", defaultValue = "name") String sortDir
    ) {
        PagedResponse<StationDto> stations = stationService.getAllStation(page, size, sortBy, sortDir);
        return stations;
    }

    @GetMapping("/{id}")
    public ResponseEntity<StationDto> getSingleStation(Long id) {
        StationDto station = stationService.getSingleStation(id);
        return new ResponseEntity<StationDto>(station, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public StationDto updateStation(@PathVariable Long id, @RequestBody StationDto dto) {
        return stationService.updateStation(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        stationService.deleteStation(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

}
