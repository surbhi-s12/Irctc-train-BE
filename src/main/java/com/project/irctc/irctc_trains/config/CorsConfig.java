package com.project.irctc.irctc_trains.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

public class CorsConfig {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
