package com.alatka.messages.message;

import com.alatka.messages.context.IsoFieldDefinition;
import com.alatka.messages.context.MessageDefinition;
import com.alatka.messages.util.BytesUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 8583 TLV报文打包/解包器
 *
 * @author ybliu
 */
public class TLVSubdomainMessageBuilder extends RebuildSubdomainMessageBuilder {

    public TLVSubdomainMessageBuilder(MessageDefinition definition) {
        super.definition = definition;
    }

    @Override
    protected List<String> doBuildFieldDefinitions(byte[] bytes, Map<String, IsoFieldDefinition> tagMap) {
        List<String> list = new ArrayList<>();
        AtomicInteger counter = new AtomicInteger(0);
        while (counter.get() < bytes.length) {
            int tagLength = (bytes[counter.get()] & 0x1F) == 0x1F ? 2 : 1;
            byte[] tagBytes = Arrays.copyOfRange(bytes, counter.get(), counter.addAndGet(tagLength));
            String tag = BytesUtil.bytesToHex(tagBytes);
            list.add(tag);

            byte flagByte = bytes[counter.get()];
            byte[] lenBytes = (flagByte & 128) == 0 ?
                    Arrays.copyOfRange(bytes, counter.get(), counter.addAndGet(1)) :
                    Arrays.copyOfRange(bytes, counter.addAndGet(1), counter.addAndGet(flagByte & 127));

            int valueLength = BytesUtil.bytesToInt(lenBytes);
            counter.addAndGet(valueLength);
        }
        return list;
    }
}
