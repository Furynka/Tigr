package com.dreamteam.service.config;

import com.dreamteam.TigrAppContext;
import com.dreamteam.dto.EnvironmentDTO;
import com.dreamteam.entity.Environment;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Eva Ambrusova
 */
@Configuration
@Import(TigrAppContext.class)
//@ComponentScan(basePackageClasses={EnvironmentServiceImpl.clas})
public class ServiceConfig {
    @Bean
    public Mapper dozer() {
        DozerBeanMapper dozer = new DozerBeanMapper();
        dozer.addMapping(new TigrDozerConfig());
        return dozer;
    }

    /**
     * Custom config for Dozer if needed
     * @author nguyen
     *
     */
    public class TigrDozerConfig extends BeanMappingBuilder {
        @Override
        protected void configure() {
            mapping(Environment.class, EnvironmentDTO.class);
        }
    }

}
