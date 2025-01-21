package com.alatka.messages.core.field;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;
import com.alatka.messages.core.holder.Bitmap;

import java.util.List;

/**
 * 子域bitmap报文域解析器
 * 参考visa 62.0域
 *
 * @author ybliu
 * @see AbstractFieldBuilder
 * @see FieldBuilder
 */
public class SubdomainBitmapFieldBuilder extends AbstractBitmapFieldBuilder {

    @Override
    public boolean matched(MessageDefinition messageDefinition, FieldDefinition definition) {
        return MessageDefinition.Type.iso == messageDefinition.getType()
                && definition.getClassType() == Bitmap.class
                && (MessageDefinition.Kind.subPayload == messageDefinition.getKind() || MessageDefinition.Kind.header == messageDefinition.getKind());
    }

    @Override
    protected int calculateLength(Object instance, List<FieldDefinition> list, FieldDefinition fieldDefinition) {
        return fieldDefinition.getLength();
    }

}
