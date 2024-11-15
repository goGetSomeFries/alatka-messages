package com.alatka.messages.core.domain;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.IsoFieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;
import com.alatka.messages.core.util.BytesUtil;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * TLV（tag-length-value）域解析器<br>
 * e.g. F55域
 * <p>
 * tag标签的属性为bit，由16进制表示，占1～2个字节长度。例如，“9F33”为一个占用两个字节的
 * tag标签。而“95”为一个占用一个字节的tag标签。若tag标签的第一个字节（注：字节排序方向为从
 * 左往右数，第一个字节即为最左边的字节。bit排序规则同理。）的后五个bit为“11111”，则说明该
 * tag占两个字节，例如“9F33”；否则占一个字节，例如“95”<br>
 * <p>
 * 子域长度（即L本身）的属性也为bit，占1～3个字节长度。具体编码规则如下：<br>
 * a) 当 L 字段最左边字节的最左 bit 位（即 bit8）为 0，表示该 L 字段占一个字节，它的后续 7 个
 * bit 位（即 bit7～bit1）表示子域取值的长度，采用二进制数表示子域取值长度的十进制数。
 * 例如，某个域取值占 3 个字节，那么其子域取值长度表示为“00000011”。所以，若子域取值
 * 的长度在 1～127 字节之间，那么该 L 字段本身仅占一个字节。<br>
 * b) 当 L 字段最左边字节的最左 bit 位（即 bit8）为 1，表示该 L 字段不止占一个字节，那么它到
 * 底占几个字节由该最左字节的后续 7 个 bit 位（即 bit7～bit1）的十进制取值表示。例如，若最
 * 左字节为 10000010，表示 L 字段除该字节外，后面还有两个字节。其后续字节的十进制取值
 * 表示子域取值的长度。例如，若 L 字段为“1000 0001 1111 1111”，表示该子域取值占 255 个
 * 字节。所以，若子域取值的长度在 128～255 字节之间，那么该 L 字段本身需占两个字节。<br>
 * c) L 可以取值为“0”，此时，V 值不出现，系统必须能够正确解析这种子域。<br>
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
        if (bytes.length == 0) {
            return bytes;
        }
        byte[] tagBytes = BytesUtil.hexToBytes(((IsoFieldDefinition) fieldDefinition).getAliasName());

        byte[] valueBytes = fieldDefinition.getFixed() ? this.padding(bytes, fieldDefinition) : bytes;

        int valueLength = valueBytes.length;
        byte[] tempLenBytes = BytesUtil.intToBytes(valueLength);
        byte[] lenBytes = valueLength < 128 ? tempLenBytes :
                BytesUtil.concat(new byte[]{(byte) (tempLenBytes.length | 128)}, tempLenBytes);

        return BytesUtil.concat(tagBytes, lenBytes, valueBytes);
    }

    @Override
    public byte[] unpack(byte[] bytes, FieldDefinition fieldDefinition, AtomicInteger counter) {
        // tag
        int tagLength = (bytes[counter.get()] & 0x1F) == 0x1F ? 2 : 1;

        byte flagByte = bytes[counter.addAndGet(tagLength)];
        byte[] lenBytes = (flagByte & 128) == 0 ?
                Arrays.copyOfRange(bytes, counter.get(), counter.addAndGet(1)) :
                Arrays.copyOfRange(bytes, counter.addAndGet(1), counter.addAndGet(flagByte & 127));

        int valueLength = BytesUtil.bytesToInt(lenBytes);
        return valueLength == 0 ? new byte[0] : Arrays.copyOfRange(bytes, counter.get(), counter.addAndGet(valueLength));
    }

}
