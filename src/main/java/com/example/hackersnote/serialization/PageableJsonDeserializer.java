package com.example.hackersnote.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.io.IOException;

/**
 * Pageable 反序列化器类.
 */
public class PageableJsonDeserializer extends JsonDeserializer<Pageable> {
    /**
     * 将 JSON 数据反序列化为 {@link Pageable} 对象.
     * <p>
     * 从 JSON 中提取 "page" 和 "size" 字段，并使用它们构建 {@link PageRequest}.
     * </p>
     *
     * @param jsonParser             用于解析 JSON 内容的 {@link JsonParser} 实例
     * @param deserializationContext 反序列化过程中的上下文信息
     * @return 包含分页信息的 {@link Pageable} 实例
     * @throws IOException 如果在读取 JSON 内容时发生 I/O 错误
     */
    @Override
    public Pageable deserialize(
            final JsonParser jsonParser,
            final DeserializationContext deserializationContext)
            throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        int page = node.get("page").asInt();
        int size = node.get("size").asInt();

        return PageRequest.of(page, size);
    }
}
