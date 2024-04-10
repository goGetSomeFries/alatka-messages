package com.alatka.messages.support;

import com.alatka.messages.util.BytesUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @author ybliu
 */
public class CustomJsonSerializer extends JsonSerializer<Object> {

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        String result = value instanceof byte[] ? BytesUtil.bytesToHex((byte[]) value) : value.toString();
        gen.writeRawValue(result);
    }
}
