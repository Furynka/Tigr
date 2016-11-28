package com.dreamteam.service.config;

import com.dreamteam.TigrAppContext;
import com.dreamteam.dto.AnimalDTO;
import com.dreamteam.entity.Animal;
import com.dreamteam.service.AnimalServiceImpl;
import com.dreamteam.service.WorkerServiceImpl;
import com.dreamteam.service.facade.AnimalFacadeImpl;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Eva Ambrusova
 */
@Configuration
@Import(TigrAppContext.class)
@ComponentScan(basePackageClasses={AnimalServiceImpl.class, AnimalFacadeImpl.class, 
    WorkerServiceImpl.class})
public class ServiceConfig {
    @Bean
    public Mapper dozer() {
        DozerBeanMapper dozer = new DozerBeanMapper();
        dozer.addMapping(new TigrDozerConfig());
        return dozer;
    }

    public class TigrDozerConfig extends BeanMappingBuilder {
        @Override
        protected void configure() {
            mapping(Animal.class, AnimalDTO.class);
        }
    }

}
