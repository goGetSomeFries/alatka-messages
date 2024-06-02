package com.alatka.messages.definition;

import com.alatka.messages.context.MessageDefinition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.nio.file.Path;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;

public class FileMessageDefinitionBuilderTest {

    @Test
    @DisplayName("getValueWithMap()")
    void test01() throws SQLException {
        FileMessageDefinitionBuilder builder = Mockito.spy(new FixedXmlMessageDefinitionBuilder());

        Map<String, Object> map = new HashMap<>();
        map.put("123", 123);
        Assertions.assertEquals(123, (int) builder.getValueWithMap(map, "123"));
        Assertions.assertNull(builder.getValueWithMap(map, "1"));
    }

    @Test
    @DisplayName("getSources()")
    void test02() {
        FileMessageDefinitionBuilder builder = Mockito.spy(new FixedXmlMessageDefinitionBuilder("test"));
        Mockito.doReturn(MessageDefinition.Type.fixed).when(builder).type();
        Mockito.doReturn(".xml").when(builder).fileSuffix();

        List<Path> list = builder.getSources();
        Assertions.assertEquals(1, list.size());
        Assertions.assertEquals("9001.3006.fixed.xml", list.get(0).toFile().getName());
    }

    @Disabled
    @Test
    @DisplayName("buildFieldDefinitions()")
    void test03() {
        FileMessageDefinitionBuilder builder = Mockito.spy(new FixedXmlMessageDefinitionBuilder());
        // TODO
        Mockito.doReturn(null).when(builder).doBuildFieldDefinitions(any(), any());
    }
}
