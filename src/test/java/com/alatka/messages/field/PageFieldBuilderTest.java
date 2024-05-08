package com.alatka.messages.field;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.MessageDefinition;
import com.alatka.messages.definition.IsoYamlMessageDefinitionBuilder;
import org.junit.jupiter.api.*;

public class PageFieldBuilderTest {

    private PageFieldBuilder fieldBuilder = new PageFieldBuilder();

    @BeforeAll
    public static void beforeAll() {
        new IsoYamlMessageDefinitionBuilder("").build();
    }

    @Test
    @DisplayName("order() == 100")
    void test01() {
        int order = fieldBuilder.getOrder();
        Assertions.assertEquals(100, order);
    }

    @Test
    @DisplayName("matched()")
    void test02() {
        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setExistSubdomain(true);
        fieldDefinition.setSubdomainType(MessageDefinition.DomainType.PAGE);
        Assertions.assertTrue(fieldBuilder.matched(null, fieldDefinition));
    }

    // TODO
    @Disabled
    @Test
    @DisplayName("pack()")
    void test03() {
    }

    // TODO
    @Disabled
    @Test
    @DisplayName("unpack()")
    void test04() {
    }
}
