package com.alatka.messages.core.message;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;
import com.alatka.messages.core.holder.Bitmap;

/**
 * bitmap类型子域报文打包/解包器
 *
 * @author ybliu
 */
public class BitmapSubdomainMessageBuilder extends MessageBuilder {

    private final ThreadLocal<Bitmap> bitmap = new ThreadLocal<>();

    public BitmapSubdomainMessageBuilder(MessageDefinition definition) {
        super.definition = definition;
    }

    @Override
    protected boolean filter(FieldDefinition fieldDefinition) {
        return this.bitmap.get() == null || this.bitmap.get().exist(fieldDefinition.getDomainNo());
    }

    @Override
    protected void postProcess(FieldDefinition fieldDefinition, Object value, boolean packed) {
        if (fieldDefinition.getClassType() == Bitmap.class) {
            if (packed) {
                String offset = fieldDefinition.getPattern();
                byte[] bytes = (byte[]) value;
                this.bitmap.set(offset == null || offset.isEmpty() ? new Bitmap(bytes) : new Bitmap(bytes, Integer.parseInt(offset)));
            } else {
                this.bitmap.set((Bitmap) value);
            }
        }
    }

    @Override
    protected void postProcess() {
        this.bitmap.remove();
    }
}
