package com.alatka.messages.core.field;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;
import com.alatka.messages.core.context.MessageDefinitionContext;
import com.alatka.messages.core.holder.MessageHolderAware;
import com.alatka.messages.core.holder.MessageHolderUtil;
import com.alatka.messages.core.util.BytesUtil;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * bitmap报文域解析器
 *
 * @author ybliu
 * @see AbstractFieldBuilder
 * @see FieldBuilder
 */
public class BitmapFieldBuilder extends AbstractFieldBuilder<Map<Integer, Boolean>> implements MessageHolderAware {

    private final ThreadLocal<Object> messageHolder = new ThreadLocal<>();

    @Override
    protected boolean returnIfNull() {
        return false;
    }

    @Override
    protected Map<Integer, Boolean> toObjectWithBinary(byte[] bytes, FieldDefinition fieldDefinition) {
        return generate(bytes);
    }

    public static Map<Integer, Boolean> generate(byte[] bytes) {
        int length = bytes.length * 8;
        String bitmapStr = BytesUtil.bytesToBinary(bytes);
        Map<Integer, Boolean> bitmap = new HashMap<>(length);
        IntStream.range(0, length).forEach(i -> bitmap.put(i + 1, bitmapStr.charAt(i) == '1'));
        return bitmap;
    }

    @Override
    protected byte[] fromObjectToBinary(Map<Integer, Boolean> value, FieldDefinition fieldDefinition) {
        if (value == null) {
            try {
                List<FieldDefinition> list = MessageDefinitionContext.getInstance().fieldDefinitions(messageDefinition);
                Map<Integer, Boolean> bitmap = list.stream()
                        .filter(definition -> definition.getDomainNo() > 1)
                        .collect(Collectors.toMap(
                                FieldDefinition::getDomainNo,
                                definition -> MessageHolderUtil.getByName(messageHolder.get(), definition.getName()) != null));

                int length = fieldDefinition.getFixed() ? fieldDefinition.getLength() :
                        (list.stream()
                                .sorted(Comparator.comparing(FieldDefinition::getDomainNo).reversed())
                                .filter(definition -> MessageHolderUtil.getByName(messageHolder.get(), definition.getName()) != null)
                                .map(FieldDefinition::getDomainNo)
                                .findFirst()
                                .orElseThrow(() -> new RuntimeException("bitmap is 0")) <= 64 ? 8 : 16);
                // 1域
                bitmap.put(1, length == 16);
                // 2-64/128域
                if (length == 8) {
                    bitmap.keySet().removeIf(key -> key > 64);
                }
                IntStream.range(2, length * 8 + 1)
                        .boxed()
                        .filter(i -> !bitmap.containsKey(i))
                        .forEach(i -> bitmap.put(i, false));
                value = bitmap;
            } finally {
                messageHolder.remove();
            }
        }

        String str = value.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .map(flag -> flag ? "1" : "0")
                .collect(Collectors.joining(""));
        return BytesUtil.binaryToBytes(str);
    }

    @Override
    public int getOrder() {
        return super.getOrder() + 60;
    }

    @Override
    public boolean matched(MessageDefinition messageDefinition, FieldDefinition definition) {
        return MessageDefinition.Type.iso == messageDefinition.getType()
                && MessageDefinition.Kind.payload == messageDefinition.getKind()
                && definition.getDomainNo() == 1
                && Map.class.isAssignableFrom(definition.getClassType());
    }

    @Override
    public void setMessageHolder(Object instance) {
        this.messageHolder.set(instance);
    }
}
