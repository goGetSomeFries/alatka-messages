package com.alatka.messages.domain;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.util.BytesUtil;
import com.alatka.messages.context.MessageDefinition;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * TLV（tag-length-value）域解析器
 *
 * @author ybliu
 * @see AbstractDomainParsed
 * @see DomainParsed
 */
public class TLVDomainParsed extends AbstractDomainParsed {

    @Override
    public int getOrder() {
        return 80;
    }

    @Override
    public boolean matched(MessageDefinition messageDefinition, FieldDefinition fieldDefinition) {
        return MessageDefinition.Type.iso == messageDefinition.getType() &&
                messageDefinition.getDomainType() == MessageDefinition.DomainType.TLV;
    }

    @Override
    public byte[] pack(byte[] bytes, FieldDefinition fieldDefinition) {
        byte[] tagBytes = BytesUtil.toBytes(fieldDefinition.getDomainNo());
        int valueLength = bytes.length;
        byte[] tempLenBytes = BytesUtil.toBytes(valueLength);
        byte[] lenBytes = valueLength < 128 ? tempLenBytes :
                BytesUtil.concat(new byte[]{(byte) (tempLenBytes.length | 128)}, tempLenBytes);
        return BytesUtil.concat(tagBytes, lenBytes, bytes);
    }

    @Override
    public byte[] unpack(byte[] bytes, FieldDefinition fieldDefinition, AtomicInteger counter) {
        // tag
        int tagLength = (bytes[counter.get()] & 0x1F) == 0x1F ? 2 : 1;

        byte flagByte = bytes[counter.addAndGet(tagLength)];
        byte[] lenBytes = (flagByte & 128) == 0 ?
                Arrays.copyOfRange(bytes, counter.get(), counter.addAndGet(1)) :
                Arrays.copyOfRange(bytes, counter.addAndGet(1), counter.addAndGet(flagByte & 127));

        int valueLength = BytesUtil.toInt(lenBytes);
        return valueLength == 0 ? new byte[0] : Arrays.copyOfRange(bytes, counter.get(), counter.addAndGet(valueLength));
    }

}
