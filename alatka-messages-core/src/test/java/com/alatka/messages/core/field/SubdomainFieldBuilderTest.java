package com.alatka.messages.core.field;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.IsoFieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class SubdomainFieldBuilderTest {

    @Spy
    private SubdomainFieldBuilder fieldBuilder;

    @BeforeEach
    public void beforeAll() {
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
        Assertions.assertTrue(fieldBuilder.matched(null, fieldDefinition));
    }

    @Test
    @DisplayName("validate() return true")
    void test03() {
        Map<String, MessageDefinition> usageMap = Collections.singletonMap("AA", null);
        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setMessageDefinitionMap(usageMap);
        Assertions.assertTrue(fieldBuilder.validate(fieldDefinition, "AA"));
    }


    @Test
    @DisplayName("validate() return false")
    void test04() {
        Map<String, MessageDefinition> usageMap = Collections.singletonMap("AA", null);
        IsoFieldDefinition fieldDefinition = new IsoFieldDefinition();
        fieldDefinition.setNonSubdomainException(false);
        fieldDefinition.setMessageDefinitionMap(usageMap);
        Assertions.assertFalse(fieldBuilder.validate(fieldDefinition, "BB"));
    }

    @Test
    @DisplayName("validate() exception")
    void test05() {
        Map<String, MessageDefinition> usageMap = Collections.singletonMap("AA", null);
        IsoFieldDefinition fieldDefinition = new IsoFieldDefinition();
        fieldDefinition.setNonSubdomainException(true);
        fieldDefinition.setMessageDefinitionMap(usageMap);
        Assertions.assertThrows(RuntimeException.class, () -> fieldBuilder.validate(fieldDefinition, "BB"));
    }

    @Test
    @DisplayName("fromObjectToNone")
    void test06() {
        doReturn("123".getBytes()).when(fieldBuilder).pack(any(), any(), any());

        Map<String, MessageDefinition> usageMap = Collections.EMPTY_MAP;
        IsoFieldDefinition fieldDefinition = new IsoFieldDefinition();
        fieldDefinition.setMessageDefinitionMap(usageMap);
        Assertions.assertArrayEquals("123".getBytes(), fieldBuilder.fromObjectToNone(null, fieldDefinition));
    }

    @Test
    @DisplayName("toObjectWithNone exception")
    void test07() {
        Map<String, MessageDefinition> usageMap = Collections.EMPTY_MAP;
        IsoFieldDefinition fieldDefinition = new IsoFieldDefinition();
        fieldDefinition.setMessageDefinitionMap(usageMap);
        Assertions.assertThrows(RuntimeException.class, () -> fieldBuilder.toObjectWithNone(null, fieldDefinition));
    }

    @Test
    @DisplayName("toObjectWithNone")
    void test08() {
        doReturn(100).when(fieldBuilder).unpack(any(), any(), any());

        Map<String, MessageDefinition> usageMap = Collections.singletonMap("AA", null);
        IsoFieldDefinition fieldDefinition = new IsoFieldDefinition();
        fieldDefinition.setMessageDefinitionMap(usageMap);
        Assertions.assertEquals(100, fieldBuilder.toObjectWithNone(null, fieldDefinition));
    }

}
