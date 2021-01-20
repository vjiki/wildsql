package com.github.vjiki.wildsql;

import com.github.vjiki.wildsql.constants.AnimalClassEnum;
import com.github.vjiki.wildsql.constants.GroupOfPopulationEnum;
import com.github.vjiki.wildsql.controller.DtoConverter;
import com.github.vjiki.wildsql.model.AnimalType;
import com.github.vjiki.wildsql.model.Area;
import com.github.vjiki.wildsql.model.repositories.AbstractRepository;
import com.github.vjiki.wildsql.model.repositories.AnimalTypeRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
//is a meta-annotation that pulls in component scanning, autoconfiguration, and property support.
public class WildsqlApplication {

	public static void main(String[] args) {

		SpringApplication.run(WildsqlApplication.class, args);

	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration()
				.setMatchingStrategy(MatchingStrategies.LOOSE)
				.setAmbiguityIgnored(true);

		return modelMapper;
	}

	@Bean
	public DtoConverter dtoConverter() {
		return new DtoConverter();
	}


}
