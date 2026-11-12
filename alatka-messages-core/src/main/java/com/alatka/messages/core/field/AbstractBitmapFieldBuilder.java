package com.alatka.messages.core.field;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.MessageDefinitionContext;
import com.alatka.messages.core.holder.Bitmap;
import com.alatka.messages.core.holder.MessageHolderUtil;
import com.alatka.messages.core.support.ExecutionContext;
import com.alatka.messages.core.util.BytesUtil;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 抽象bitmap报文域解析器
 *
 * @author ybliu
 * @see AbstractFieldBuilder
 * @see FieldBuilder
 */
public abstract class AbstractBitmapFieldBuilder extends AbstractFieldBuilder<Bitmap> {

    @Override
    protected boolean returnIfNull() {
        return false;
    }

    @Override
    protected Bitmap toObjectWithBinary(byte[] bytes, FieldDefinition fieldDefinition) {
        int offset = this.getOffset(fieldDefinition);
        return new Bitmap(bytes, offset);
    }

    @Override
    protected byte[] fromObjectToBinary(Bitmap value, FieldDefinition fieldDefinition) {
        if (value != null) {
            return value.getBytes();
        }

        List<FieldDefinition> list = MessageDefinitionContext.getInstance()
                .fieldDefinitions(ExecutionContext.instance().getMessageDefinition());
        Object instance = ExecutionContext.instance().getMessageHolder();

        int length = this.calculateLength(instance, list, fieldDefinition);

        int offset = this.getOffset(fieldDefinition);

        byte[] bytes = new byte[length];
        list.stream()
                .filter(definition -> definition.getDomainNo() - offset > 0)
                .filter(definition -> definition.getDomainNo() - offset <= length * 8)
                .filter(definition -> MessageHolderUtil.getByName(instance, definition.getName()) != null)
                .map(FieldDefinition::getDomainNo)
                .forEach(domainNo -> bytes[(domainNo - offset - 1) / 8] |= (byte) (0x80 >>> ((domainNo - offset - 1) % 8)));

        this.postBitmap(bytes);

        return bytes;

    }

    @Override
    public Bitmap normalizeType(Object value, FieldDefinition fieldDefinition) {
        throw new UnsupportedOperationException(fieldDefinition + "无需指定，程序自动赋值");
    }

    @Override
    protected Object doDenormalizeType(Bitmap value, FieldDefinition fieldDefinition) {
        byte[] bytes = value.getBytes();
        int offset = this.getOffset(fieldDefinition);

        Map<String, String> map = new LinkedHashMap<>(bytes.length);
        for (int i = 0; i < bytes.length; i++) {
            String key = "F" + (i * 8 + offset + 1) + "~F" + (i * 8 + 8 + offset);
            String val = BytesUtil.bytesToBinary(Arrays.copyOfRange(bytes, i, i + 1));
            map.put(key, val);
        }
        return map.entrySet().stream()
                .map(entry -> entry.getKey() + ":" + entry.getValue())
                .collect(Collectors.joining(", "));
    }

    @Override
    public int getOrder() {
        return super.getOrder() + 60;
    }

    protected abstract int calculateLength(Object instance, List<FieldDefinition> list, FieldDefinition fieldDefinition);

    protected void postBitmap(byte[] bytes) {
    }

    private int getOffset(FieldDefinition fieldDefinition) {
        return fieldDefinition.getPattern() == null || fieldDefinition.getPattern().isEmpty() ?
                0 : Integer.parseInt(fieldDefinition.getPattern());
    }

}
