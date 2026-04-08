package com.srping_mvc_json_view_practicle.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Configuration
public class JacksonConfig {

	 @Bean
	    public ObjectMapper objectMapper() {
	        ObjectMapper mapper = new ObjectMapper();

	        mapper.findAndRegisterModules(); // for Java 8 date
	        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

	        return mapper;
	    }
	
}
