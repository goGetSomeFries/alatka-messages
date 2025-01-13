package com.alatka.messages.core.definition;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;
import com.alatka.messages.core.context.MessageDefinitionContext;
import com.alatka.messages.core.util.FileUtil;
import com.alatka.messages.core.util.JsonUtil;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 文件报文定义解析器
 *
 * @author ybliu
 * @see AbstractMessageDefinitionBuilder
 */
public abstract class FileMessageDefinitionBuilder<S extends FieldDefinition> extends AbstractMessageDefinitionBuilder<Path, S> {

    private final String classpath;

    public FileMessageDefinitionBuilder(String classpath) {
        this.classpath = classpath;
    }

    @Override
    protected List<S> buildFieldDefinitions(MessageDefinition definition, Path source) {
        List<Map<String, Object>> list = this.doBuildFieldDefinitions(definition, source);

        return list.stream()
                .map(map -> JsonUtil.mapToObject(map, fieldDefinitionClass()))
                .peek(fieldDefinition -> this.buildFieldDefinition(definition, fieldDefinition))
                .peek(fieldDefinition -> this.postBuildFieldDefinition(definition, fieldDefinition))
                .collect(Collectors.toList());
    }

    private void buildFieldDefinition(MessageDefinition definition, FieldDefinition fieldDefinition) {
        if (fieldDefinition.getFixed() == null) {
            fieldDefinition.setFixed(Boolean.TRUE);
        }
        if (fieldDefinition.getStatus() == null) {
            fieldDefinition.setStatus(FieldDefinition.Status.OPEN);
        }
        if (fieldDefinition.getParseType() == null) {
            FieldDefinition.ParseType parseType =
                    fieldDefinition.getExistSubdomain() || fieldDefinition.getClassType() == byte[].class ?
                            FieldDefinition.ParseType.NONE : FieldDefinition.ParseType.ASCII;
            fieldDefinition.setParseType(parseType);
        }
        if (fieldDefinition.getExistSubdomain()) {
            List<MessageDefinition> list = MessageDefinitionContext.getInstance()
                    .childrenMessageDefinitions(definition, fieldDefinition);
            Map<String, MessageDefinition> messageDefinitionMap =
                    list.stream().collect(Collectors.toMap(d ->
                            d.getUsage().isEmpty() ? FieldDefinition.SUBFIELD_KEY_DEFAULT : d.getUsage(), Function.identity()));
            fieldDefinition.setMessageDefinitionMap(messageDefinitionMap);
        }
    }

    @SuppressWarnings("unchecked")
    protected <T> T getValueWithMap(Map<String, Object> map, String key) {
        return (T) map.get(key);
    }

    @Override
    protected List<Path> getSources() {
        return FileUtil.getClasspathFiles(this.classpath, "*." + this.type() + this.fileSuffix());
    }

    /**
     * 文件后缀
     *
     * @return 文件后缀
     */
    protected abstract String fileSuffix();

    protected abstract List<Map<String, Object>> doBuildFieldDefinitions(MessageDefinition definition, Path source);

    /**
     * {@link FieldDefinition}后处理器
     *
     * @param definition      {@link MessageDefinition}
     * @param fieldDefinition {@link FieldDefinition}
     */
    protected abstract void postBuildFieldDefinition(MessageDefinition definition, FieldDefinition fieldDefinition);

    /**
     * get {@link FieldDefinition} type
     *
     * @return {@link FieldDefinition} type
     */
    protected abstract Class<S> fieldDefinitionClass();

}
