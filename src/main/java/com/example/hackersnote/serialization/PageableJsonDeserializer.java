package com.example.hackersnote.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import org.springframework.data.domain.*;

import java.io.IOException;

/**
 * Pageable 反序列化器类
 */
public class PageableJsonDeserializer extends JsonDeserializer<Pageable> {
    @Override
    public Pageable deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        int page = node.get("page").asInt();
        int size = node.get("size").asInt();

        return PageRequest.of(page, size);
    } // end deserialize()

} // end class PageableJsonDeserializer
