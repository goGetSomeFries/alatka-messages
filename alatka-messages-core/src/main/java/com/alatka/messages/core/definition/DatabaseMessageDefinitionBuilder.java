package com.alatka.messages.core.definition;

import com.alatka.messages.core.context.*;
import com.alatka.messages.core.holder.MessageHolder;
import com.alatka.messages.core.support.Constant;
import com.alatka.messages.core.util.ClassUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * database报文定义解析器
 *
 * @author ybliu
 * @see AbstractMessageDefinitionBuilder
 */
public abstract class DatabaseMessageDefinitionBuilder extends AbstractMessageDefinitionBuilder<Map<String, Object>, FieldDefinition> {

    private final DataSource dataSource;

    public DatabaseMessageDefinitionBuilder(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    protected List<MessageDefinition> buildMessageDefinitions(Map<String, Object> source) {
        MessageDefinition definition = this.buildMessageDefinition(source);
        return Collections.singletonList(definition);
    }

    @Override
    protected List<FieldDefinition> buildFieldDefinitions(MessageDefinition definition, Map<String, Object> source) {
        List<Map<String, Object>> list = new ArrayList<>();
        String sql = "select * from ALK_FIELD_DEFINITION WHERE M_ID = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, Integer.parseInt(source.get("id").toString()));
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Map<String, Object> result = new HashMap<>();
                    result.put("domainNo", resultSet.getInt("F_DOMAIN_NO"));
                    result.put("name", resultSet.getString("F_NAME"));
                    result.put("className", resultSet.getString("F_CLAZZ"));
                    result.put("pattern", resultSet.getString("F_PATTERN"));
                    result.put("fixed", resultSet.getBoolean("F_FIXED"));
                    result.put("length", resultSet.getInt("F_LENGTH"));
                    result.put("remark", resultSet.getString("F_REMARK"));
                    result.put("status", resultSet.getString("F_STATUS"));
                    result.put("pageSizeName", resultSet.getString("F_PAGE_SIZE_NAME"));
                    result.put("parseType", resultSet.getString("F_PARSE_TYPE"));
                    result.put("lenParseType", resultSet.getString("F_LEN_PARSE_TYPE"));
                    result.put("existSubdomain", resultSet.getBoolean("F_EXIST_SUBDOMAIN"));
                    result.put("subdomainType", resultSet.getString("F_SUBDOMAIN_TYPE"));
                    result.put("aliasName", resultSet.getString("F_ALIAS_NAME"));
                    result.put("maxLength", resultSet.getInt("F_MAX_LENGTH"));
                    boolean nonSubdomainException = resultSet.getString("F_NON_SUBDOMAIN_EXCEPTION") == null
                            || resultSet.getBoolean("F_NON_SUBDOMAIN_EXCEPTION");
                    result.put("nonSubdomainException", nonSubdomainException);
                    list.add(result);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("查询ALK_FIELD_DEFINITION失败", e);
        }

        return list.stream()
                .map(map -> this.buildFieldDefinition(map, definition))
                .sorted()
                .collect(Collectors.toList());
    }

    private FieldDefinition buildFieldDefinition(Map<String, Object> result, MessageDefinition definition) {
        FieldDefinition fieldDefinition = definition.getType() == MessageDefinition.Type.fixed ?
                new FixedFieldDefinition() : new IsoFieldDefinition();

        fieldDefinition.setDomainNo((Integer) result.get("domainNo"));
        fieldDefinition.setName(result.get("name").toString());
        fieldDefinition.setClassName(result.get("className") == null ? null : result.get("className").toString());
        fieldDefinition.setPattern(result.get("pattern") == null ? null : result.get("pattern").toString());
        fieldDefinition.setFixed((boolean) result.get("fixed"));
        fieldDefinition.setLength((Integer) result.get("length"));
        fieldDefinition.setRemark(result.get("remark").toString());
        fieldDefinition.setStatus(FieldDefinition.Status.valueOf(result.get("status").toString()));
        fieldDefinition.setPageSizeName(result.get("pageSizeName") == null ? null : result.get("pageSizeName").toString());
        fieldDefinition.setParseType(FieldDefinition.ParseType.valueOf(result.get("parseType").toString()));
        fieldDefinition.setExistSubdomain((boolean) result.get("existSubdomain"));
        fieldDefinition.setSubdomainType(result.get("subdomainType") == null ?
                null : MessageDefinition.DomainType.valueOf(result.get("subdomainType").toString()));
        if (fieldDefinition instanceof IsoFieldDefinition) {
            ((IsoFieldDefinition) fieldDefinition).setLenParseType(result.get("lenParseType") == null ?
                    null : FieldDefinition.ParseType.valueOf(result.get("lenParseType").toString()));
            ((IsoFieldDefinition) fieldDefinition).setMaxLength((Integer) result.get("maxLength"));
            ((IsoFieldDefinition) fieldDefinition).setAliasName(result.get("aliasName") == null ? null : result.get("aliasName").toString());
            ((IsoFieldDefinition) fieldDefinition).setNonSubdomainException((boolean) result.get("nonSubdomainException"));
        }
        if (fieldDefinition.getExistSubdomain()) {
            List<MessageDefinition> list = MessageDefinitionContext.getInstance()
                    .childrenMessageDefinitions(definition, fieldDefinition);
            Map<String, MessageDefinition> messageDefinitionMap =
                    list.stream().collect(Collectors.toMap(d ->
                            d.getUsage().isEmpty() ? FieldDefinition.SUBFIELD_KEY_DEFAULT : d.getUsage(), Function.identity()));
            fieldDefinition.setMessageDefinitionMap(messageDefinitionMap);
        }
        return fieldDefinition;
    }

    @Override
    protected List<Map<String, Object>> getSources() {
        List<Map<String, Object>> list = new ArrayList<>();
        String sql = "select * from ALK_MESSAGE_DEFINITION WHERE M_TYPE = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, this.type().name());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Map<String, Object> result = new HashMap<>();
                    result.put("id", resultSet.getInt("M_ID"));
                    result.put("type", resultSet.getString("M_TYPE"));
                    result.put("group", resultSet.getString("M_GROUP"));
                    result.put("code", resultSet.getString("M_CODE"));
                    result.put("kind", resultSet.getString("M_KIND"));
                    result.put("domain", resultSet.getString("M_DOMAIN"));
                    result.put("usage", resultSet.getString("M_USAGE"));
                    result.put("domainType", resultSet.getString("M_DOMAIN_TYPE"));
                    result.put("holder", resultSet.getString("M_HOLDER"));
                    result.put("charset", resultSet.getString("M_CHARSET"));
                    result.put("remark", resultSet.getString("M_REMARK"));
                    list.add(result);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("查询ALK_MESSAGE_DEFINITION失败", e);
        }

        return list.stream()
                .sorted(Comparator.comparing(this::buildMessageDefinition))
                .collect(Collectors.toList());
    }

    private MessageDefinition buildMessageDefinition(Map<String, Object> source) {
        MessageDefinition definition = new MessageDefinition();
        definition.setType(MessageDefinition.Type.valueOf(source.get("type").toString()));
        definition.setGroup(source.get("group").toString());
        definition.setCode(source.get("code").toString());
        definition.setKind(MessageDefinition.Kind.valueOf(source.get("kind").toString()));
        definition.setDomain(source.get("domain") == null ?
                "" : source.get("domain").toString());
        definition.setUsage(source.get("usage") == null ?
                "" : source.get("usage").toString());
        definition.setDomainType(source.get("domainType") == null ?
                MessageDefinition.DomainType.NONE : MessageDefinition.DomainType.valueOf(source.get("domainType").toString()));
        definition.setHolder(source.get("holder") == null ?
                MessageHolder.class : ClassUtil.forName(source.get("holder").toString()));
        definition.setCharset(source.get("charset") == null ?
                Constant.DEFAULT_CHARSET : source.get("charset").toString());
        definition.setRemark(source.get("remark").toString());
        return definition;
    }

}
