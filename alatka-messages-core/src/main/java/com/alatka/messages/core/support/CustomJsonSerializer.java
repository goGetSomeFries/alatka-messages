package com.alatka.messages.core.support;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * 使用 {@link #toString()} 方法返回作为 JSON 格式化结果
 *
 * @author ybliu
 */
public class CustomJsonSerializer extends JsonSerializer<Object> {

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        String result = value.toString();
        gen.writeRawValue(result);
    }
}
