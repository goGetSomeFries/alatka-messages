package com.alatka.messages.core.field;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;
import com.alatka.messages.core.holder.MessageHolderUtil;

import java.util.Comparator;
import java.util.List;

/**
 * bitmap报文域解析器
 *
 * @author ybliu
 * @see AbstractFieldBuilder
 * @see FieldBuilder
 */
public class BitmapFieldBuilder extends AbstractBitmapFieldBuilder {

    @Override
    public boolean matched(MessageDefinition messageDefinition, FieldDefinition definition) {
        return MessageDefinition.Type.iso == messageDefinition.getType()
                && MessageDefinition.Kind.payload == messageDefinition.getKind()
                && definition.getDomainNo() == 1;
    }

    @Override
    protected int calculateLength(Object instance, List<FieldDefinition> list, FieldDefinition fieldDefinition) {
        if (fieldDefinition.getFixed()) {
            return fieldDefinition.getLength();
        }
        Integer lastDomainNo = list.stream()
                .sorted(Comparator.comparing(FieldDefinition::getDomainNo).reversed())
                .filter(definition -> MessageHolderUtil.getByName(instance, definition.getName()) != null)
                .map(FieldDefinition::getDomainNo)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("bitmap is 0"));
        return this.doCalculateLength(lastDomainNo, 1);
    }

    private int doCalculateLength(Integer lastDomainNo, int times) {
        if (lastDomainNo <= 64 * times) {
            return 8 * times;
        }
        return this.doCalculateLength(lastDomainNo, times + 1);
    }

    @Override
    protected void postBitmap(byte[] bytes) {
        this.doPostBitmap(bytes, bytes.length, 1);
    }

    private void doPostBitmap(byte[] bytes, int length, int times) {
        if (8 * times < length) {
            bytes[times - 1] |= (byte) 0x80;
            this.doPostBitmap(bytes, length, times + 1);
        } else {
            bytes[times - 1] &= 0x7F;
        }
    }
}
