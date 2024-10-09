package com.example.hackersnote.config;

import com.example.hackersnote.serialization.PageableJsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * 自定义反序列化器配置类.
 */
@Configuration
public class JacksonConfig {
    @Bean
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();

        SimpleModule module = new SimpleModule();
        module.addDeserializer(Pageable.class, new PageableJsonDeserializer());
        objectMapper.registerModule(module);

        return objectMapper;
    } // end objectMapper()
} // end class JacksonConfig
