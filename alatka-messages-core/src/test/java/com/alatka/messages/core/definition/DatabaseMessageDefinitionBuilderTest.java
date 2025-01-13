package com.alatka.messages.core.definition;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.IsoFieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;
import com.alatka.messages.core.context.MessageDefinitionContext;
import com.alatka.messages.core.holder.MessageHolder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import javax.sql.DataSource;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mockStatic;

public class DatabaseMessageDefinitionBuilderTest {

    @Test
    @DisplayName("getSources()")
    void test01() throws SQLException {
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        doReturn(true, true, false).when(resultSet).next();
        doReturn(2, 1).when(resultSet).getInt("M_ID");
        doReturn("fixed", "iso").when(resultSet).getString("M_TYPE");
        doReturn("0901", "visa").when(resultSet).getString("M_GROUP");
        doReturn("3006", "common").when(resultSet).getString("M_CODE");
        doReturn("request", "payload").when(resultSet).getString("M_KIND");
        doReturn(null, null).when(resultSet).getString("M_DOMAIN");
        doReturn(null, null).when(resultSet).getString("M_USAGE");
        doReturn("NONE", "NONE").when(resultSet).getString("M_DOMAIN_TYPE");
        doReturn(null, "com.alatka.messages.core.holder.MessageHolder").when(resultSet).getString("M_HOLDER");
        doReturn(null, "GB18030").when(resultSet).getString("M_CHARSET");
        doReturn("fixed testing", "8583 testing").when(resultSet).getString("M_REMARK");

        PreparedStatement statement = Mockito.mock(PreparedStatement.class);
        doReturn(resultSet).when(statement).executeQuery();
        Connection connection = Mockito.mock(Connection.class);
        doReturn(statement).when(connection).prepareStatement(ArgumentMatchers.any());
        DataSource dataSource = Mockito.mock(DataSource.class);
        doReturn(connection).when(dataSource).getConnection();

        FixedDatabaseMessageDefinitionBuilder builder = Mockito.spy(new FixedDatabaseMessageDefinitionBuilder(dataSource));

        List<Map<String, Object>> sources = builder.getSources();

        Assertions.assertEquals(2, sources.size());

        Assertions.assertEquals(1, sources.get(0).get("id"));
        Assertions.assertEquals("iso", sources.get(0).get("type"));
        Assertions.assertEquals("visa", sources.get(0).get("group"));
        Assertions.assertEquals("common", sources.get(0).get("code"));
        Assertions.assertEquals("payload", sources.get(0).get("kind"));
        Assertions.assertNull(sources.get(0).get("domain"));
        Assertions.assertNull(sources.get(0).get("usage"));
        Assertions.assertEquals("NONE", sources.get(0).get("domainType"));
        Assertions.assertEquals("com.alatka.messages.core.holder.MessageHolder", sources.get(0).get("holder"));
        Assertions.assertEquals("GB18030", sources.get(0).get("charset"));
        Assertions.assertEquals("8583 testing", sources.get(0).get("remark"));

        Assertions.assertEquals(2, sources.get(1).get("id"));
        Assertions.assertEquals("fixed", sources.get(1).get("type"));
        Assertions.assertEquals("0901", sources.get(1).get("group"));
        Assertions.assertEquals("3006", sources.get(1).get("code"));
        Assertions.assertEquals("request", sources.get(1).get("kind"));
        Assertions.assertNull(sources.get(1).get("domain"));
        Assertions.assertNull(sources.get(1).get("usage"));
        Assertions.assertEquals("NONE", sources.get(1).get("domainType"));
        Assertions.assertNull(sources.get(1).get("holder"));
        Assertions.assertNull(sources.get(1).get("charset"));
        Assertions.assertEquals("fixed testing", sources.get(1).get("remark"));
    }

    @Test
    @DisplayName("getSources() exception")
    void test02() {
        DataSource dataSource = Mockito.mock(DataSource.class);
        try {
            Mockito.doThrow(SQLException.class).when(dataSource).getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        FixedDatabaseMessageDefinitionBuilder builder = Mockito.spy(new FixedDatabaseMessageDefinitionBuilder(dataSource));

        Assertions.assertThrows(RuntimeException.class, () -> builder.getSources());
    }

    @Test
    @DisplayName("buildMessageDefinitions()")
    void test03() {
        Map<String, Object> source = Mockito.mock(HashMap.class);
        doReturn("fixed", "iso").when(source).get("type");
        doReturn("9001", "visa").when(source).get("group");
        doReturn("3004", "common").when(source).get("code");
        doReturn("request", "subPayload").when(source).get("kind");
        doReturn(null, "F13").when(source).get("domain");
        doReturn(null, "AB").when(source).get("usage");
        doReturn(null, "UV").when(source).get("domainType");
        doReturn(null, "com.alatka.messages.core.holder.MessageHolder").when(source).get("holder");
        doReturn(null, "GBK").when(source).get("charset");
        doReturn("fixed testing", "8583 testing").when(source).get("remark");

        FixedDatabaseMessageDefinitionBuilder builder = new FixedDatabaseMessageDefinitionBuilder(null);
        List<MessageDefinition> list1 = builder.buildMessageDefinitions(source);
        Assertions.assertEquals(1, list1.size());
        Assertions.assertEquals(MessageDefinition.Type.fixed, list1.get(0).getType());
        Assertions.assertEquals("9001", list1.get(0).getGroup());
        Assertions.assertEquals("3004", list1.get(0).getCode());
        Assertions.assertEquals(MessageDefinition.Kind.request, list1.get(0).getKind());
        Assertions.assertEquals("", list1.get(0).getDomain());
        Assertions.assertEquals("", list1.get(0).getUsage());
        Assertions.assertEquals(MessageDefinition.DomainType.NONE, list1.get(0).getDomainType());
        Assertions.assertEquals(MessageHolder.class, list1.get(0).getHolder());
        Assertions.assertEquals(Charset.forName("GB18030"), list1.get(0).getCharset());
        Assertions.assertEquals("fixed testing", list1.get(0).getRemark());

        List<MessageDefinition> list2 = builder.buildMessageDefinitions(source);
        Assertions.assertEquals(1, list2.size());
        Assertions.assertEquals(MessageDefinition.Type.iso, list2.get(0).getType());
        Assertions.assertEquals("visa", list2.get(0).getGroup());
        Assertions.assertEquals("common", list2.get(0).getCode());
        Assertions.assertEquals(MessageDefinition.Kind.subPayload, list2.get(0).getKind());
        Assertions.assertEquals("F13", list2.get(0).getDomain());
        Assertions.assertEquals("AB", list2.get(0).getUsage());
        Assertions.assertEquals(MessageDefinition.DomainType.UV, list2.get(0).getDomainType());
        Assertions.assertEquals(MessageHolder.class, list2.get(0).getHolder());
        Assertions.assertEquals(Charset.forName("GBK"), list2.get(0).getCharset());
        Assertions.assertEquals("8583 testing", list2.get(0).getRemark());
    }

    @Test
    @DisplayName("buildFieldDefinitions()")
    void test04() throws SQLException {
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        doReturn(true, true, false).when(resultSet).next();
        doReturn(1, 2).when(resultSet).getInt("F_DOMAIN_NO");
        doReturn("test1", "test2").when(resultSet).getString("F_NAME");
        doReturn("java.lang.String", "java.time.LocalDate").when(resultSet).getString("F_CLAZZ");
        doReturn(null, "yyyyMMdd").when(resultSet).getString("F_PATTERN");
        doReturn(true, false).when(resultSet).getBoolean("F_FIXED");
        doReturn(20, 8).when(resultSet).getInt("F_LENGTH");
        doReturn("fixed testing", "8583 testing").when(resultSet).getString("F_REMARK");
        doReturn("OPEN", "RAW").when(resultSet).getString("F_STATUS");
        doReturn(null, "counts").when(resultSet).getString("F_PAGE_SIZE_NAME");
        doReturn("ASCII", "BCD").when(resultSet).getString("F_PARSE_TYPE");
        doReturn(null, "BCD").when(resultSet).getString("F_LEN_PARSE_TYPE");
        doReturn(false, true).when(resultSet).getBoolean("F_EXIST_SUBDOMAIN");
        doReturn("FIXED", "PAGE").when(resultSet).getString("F_SUBDOMAIN_TYPE");
        doReturn(null, "alias").when(resultSet).getString("F_ALIAS_NAME");
        doReturn(20, 10).when(resultSet).getInt("F_MAX_LENGTH");
        doReturn(false, true).when(resultSet).getBoolean("F_NON_SUBDOMAIN_EXCEPTION");

        PreparedStatement statement = Mockito.mock(PreparedStatement.class);
        doReturn(resultSet).when(statement).executeQuery();
        Connection connection = Mockito.mock(Connection.class);
        doReturn(statement).when(connection).prepareStatement(ArgumentMatchers.any());
        DataSource dataSource = Mockito.mock(DataSource.class);
        doReturn(connection).when(dataSource).getConnection();

        FixedDatabaseMessageDefinitionBuilder builder = Mockito.spy(new FixedDatabaseMessageDefinitionBuilder(dataSource));

        MessageDefinition subdomain = new MessageDefinition();
        subdomain.setUsage("");

        List<MessageDefinition> definitions = Collections.singletonList(subdomain);
        MessageDefinitionContext mock = Mockito.mock(MessageDefinitionContext.class);
        doReturn(definitions).when(mock).childrenMessageDefinitions(any(), any());
        MockedStatic<MessageDefinitionContext> mockedStatic = mockStatic(MessageDefinitionContext.class);
        mockedStatic.when(MessageDefinitionContext::getInstance).thenReturn(mock);

        MessageDefinition definition = Mockito.mock(MessageDefinition.class);
        doReturn(MessageDefinition.Type.fixed, MessageDefinition.Type.iso).when(definition).getType();

        List<FieldDefinition> list = builder.buildFieldDefinitions(definition, Collections.singletonMap("id", 1));

        Assertions.assertEquals(2, list.size());

        Assertions.assertEquals(1, list.get(0).getDomainNo());
        Assertions.assertEquals("test1", list.get(0).getName());
        Assertions.assertEquals(String.class.getName(), list.get(0).getClassName());
        Assertions.assertNull(list.get(0).getPattern());
        Assertions.assertTrue(list.get(0).getFixed());
        Assertions.assertEquals(20, list.get(0).getLength());
        Assertions.assertEquals("fixed testing", list.get(0).getRemark());
        Assertions.assertEquals(FieldDefinition.Status.OPEN, list.get(0).getStatus());
        Assertions.assertNull(list.get(0).getPageSizeName());
        Assertions.assertEquals(FieldDefinition.ParseType.ASCII, list.get(0).getParseType());
        Assertions.assertFalse(list.get(0).getExistSubdomain());
        Assertions.assertEquals(MessageDefinition.DomainType.FIXED, list.get(0).getSubdomainType());

        Assertions.assertEquals(2, list.get(1).getDomainNo());
        Assertions.assertEquals("test2", list.get(1).getName());
        Assertions.assertEquals(LocalDate.class.getName(), list.get(1).getClassName());
        Assertions.assertEquals("yyyyMMdd", list.get(1).getPattern());
        Assertions.assertFalse(list.get(1).getFixed());
        Assertions.assertEquals(8, list.get(1).getLength());
        Assertions.assertEquals("8583 testing", list.get(1).getRemark());
        Assertions.assertEquals(FieldDefinition.Status.RAW, list.get(1).getStatus());
        Assertions.assertEquals("counts", list.get(1).getPageSizeName());
        Assertions.assertEquals(FieldDefinition.ParseType.BCD, list.get(1).getParseType());
        Assertions.assertEquals(FieldDefinition.ParseType.BCD, ((IsoFieldDefinition) list.get(1)).getLenParseType());
        Assertions.assertTrue(list.get(1).getExistSubdomain());
        Assertions.assertEquals(MessageDefinition.DomainType.PAGE, list.get(1).getSubdomainType());
        Assertions.assertEquals("alias", ((IsoFieldDefinition) list.get(1)).getAliasName());
        Assertions.assertEquals(10, ((IsoFieldDefinition) list.get(1)).getMaxLength());
        Assertions.assertTrue(((IsoFieldDefinition) list.get(1)).getNonSubdomainException());
        Map<String, MessageDefinition> messageDefinitionMap = list.get(1).getMessageDefinitionMap();
        Assertions.assertEquals(1, messageDefinitionMap.size());
        Assertions.assertEquals(subdomain, messageDefinitionMap.get(FieldDefinition.SUBFIELD_KEY_DEFAULT));

        mockedStatic.close();
    }

    @Test
    @DisplayName("buildFieldDefinitions() exception")
    void test05() {
        DataSource dataSource = Mockito.mock(DataSource.class);
        try {
            Mockito.doThrow(SQLException.class).when(dataSource).getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        FixedDatabaseMessageDefinitionBuilder builder = Mockito.spy(new FixedDatabaseMessageDefinitionBuilder(dataSource));

        Assertions.assertThrows(RuntimeException.class, () -> builder.buildFieldDefinitions(null, null));
    }
}
