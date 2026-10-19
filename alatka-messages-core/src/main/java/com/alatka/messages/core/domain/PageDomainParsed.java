package com.alatka.messages.core.domain;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;
import com.alatka.messages.core.holder.MessageHolderUtil;
import com.alatka.messages.core.support.ExecutionContext;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 分页域解析器
 *
 * @author ybliu
 * @see AbstractDomainParsed
 * @see DomainParsed
 */
public class PageDomainParsed extends AbstractDomainParsed {

    @Override
    public int getOrder() {
        return 10;
    }

    @Override
    public boolean matched(MessageDefinition messageDefinition, FieldDefinition fieldDefinition) {
        return !fieldDefinition.getFixed() && fieldDefinition.getSubdomainType() == MessageDefinition.DomainType.PAGE;
    }

    @Override
    public byte[] pack(byte[] bytes, FieldDefinition fieldDefinition) {
        return bytes;
    }

    @Override
    public byte[] unpack(byte[] bytes, FieldDefinition fieldDefinition, AtomicInteger counter) {
        int elementLength = fieldDefinition.getLength();
        int pageSize = MessageHolderUtil.getByName(ExecutionContext.instance().getMessageHolder(), fieldDefinition.getPageSizeName());
        return Arrays.copyOfRange(bytes, counter.get(), counter.addAndGet(elementLength * pageSize));
    }

}
