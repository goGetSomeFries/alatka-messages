package com.alatka.messages.core.definition;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link MessageDefinitionBuilder}组合
 *
 * @author ybliu
 */
public class CompositeMessageDefinitionBuilder implements MessageDefinitionBuilder {

    private final List<MessageDefinitionBuilder> builders = new ArrayList<>();

    @Override
    public void build() {
        this.builders.forEach(MessageDefinitionBuilder::build);
    }

    @Override
    public void refresh() {
        this.builders.forEach(MessageDefinitionBuilder::refresh);
    }

    public void addBuilder(MessageDefinitionBuilder builder) {
        this.builders.add(builder);
    }
}
