package com.alatka.messages.core.field;

/**
 * 数值类型报文域解析器
 *
 * @author ybliu
 * @see AbstractFieldBuilder
 * @see FieldBuilder
 */
public abstract class NumberFieldBuilder<T> extends AbstractFieldBuilder<T> {

    @Override
    public int getOrder() {
        return super.getOrder() + 20;
    }
}
