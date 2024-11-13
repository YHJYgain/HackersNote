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
    /**
     * 配置自定义的 {@link ObjectMapper}，用于 JSON 反序列化.
     * <p>
     * 该方法创建并配置 {@link ObjectMapper}，注册自定义的 {@link Pageable} 反序列化器，
     * 使 {@link Pageable} 对象能够从 JSON 数据正确反序列化.
     * </p>
     *
     * @param builder 用于构建 {@link ObjectMapper} 的 {@link Jackson2ObjectMapperBuilder} 实例
     * @return 配置完成的 {@link ObjectMapper} 实例
     */
    @Bean
    public ObjectMapper objectMapper(final Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();

        SimpleModule module = new SimpleModule();
        module.addDeserializer(Pageable.class, new PageableJsonDeserializer());
        objectMapper.registerModule(module);

        return objectMapper;
    }
}
