package com.alatka.messages.message;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.IsoFieldDefinition;

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
                .map(s -> (IsoFieldDefinition) s)
                .collect(Collectors.toMap(IsoFieldDefinition::getAliasName, Function.identity()));

        List<String> list = doBuildFieldDefinitions(bytes, tagMap);
        return list.stream().filter(tagMap::containsKey).map(tagMap::get).collect(Collectors.toList());
    }

    protected abstract List<String> doBuildFieldDefinitions(byte[] bytes, Map<String, IsoFieldDefinition> tagMap);

}
