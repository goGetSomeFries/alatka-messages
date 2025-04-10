package com.alatka.messages.core.message;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.IsoFieldDefinition;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 8583 aliasName类型子域 报文打包/解包器
 *
 * @author ybliu
 */
public abstract class RebuildSubdomainMessageBuilder extends MessageBuilder {

    @Override
    protected List<FieldDefinition> buildFieldDefinitions(byte[] bytes) {
        Map<String, IsoFieldDefinition> tagMap = super.buildFieldDefinitions(bytes).stream()
                .map(IsoFieldDefinition.class::cast)
                .collect(Collectors.toMap(IsoFieldDefinition::getAliasName, Function.identity()));

        List<String> list = doBuildFieldDefinitions(bytes, tagMap);
        return list.stream()
                .peek(s -> {
                    if (!tagMap.containsKey(s)) {
                        throw new IllegalArgumentException("Unknown tag: " + s);
                    }
                })
                .map(tagMap::get)
                .collect(Collectors.toList());
    }

    protected abstract List<String> doBuildFieldDefinitions(byte[] bytes, Map<String, IsoFieldDefinition> tagMap);

}
