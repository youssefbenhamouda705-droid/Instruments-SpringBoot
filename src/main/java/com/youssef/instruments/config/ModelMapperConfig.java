package com.youssef.instruments.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration Spring pour le Bean ModelMapper.
 * Permet l'injection de ModelMapper dans les services pour
 * la conversion automatique entité <-> DTO.
 */
@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
