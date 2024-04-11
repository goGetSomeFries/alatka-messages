package com.alatka.messages.support;

import com.alatka.messages.util.BytesUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.ByteArraySerializer;

import java.io.IOException;

/**
 * 自定义byte[]序列化器，以16进制字符串展示
 *
 * @author ybliu
 */
public class CustomByteArraySerializer extends ByteArraySerializer {

    @Override
    public void serialize(byte[] value, JsonGenerator g, SerializerProvider provider)
            throws IOException {
        g.writeString(BytesUtil.bytesToHex(value));
    }

    @Override
    public void serializeWithType(byte[] value, JsonGenerator g, SerializerProvider provider,
                                  TypeSerializer typeSer) throws IOException {
        WritableTypeId typeIdDef = typeSer.writeTypePrefix(g, typeSer.typeId(value, JsonToken.VALUE_EMBEDDED_OBJECT));
        g.writeString(BytesUtil.bytesToHex(value));
        typeSer.writeTypeSuffix(g, typeIdDef);
    }
}
