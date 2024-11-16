package com.alatka.messages.core.field;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;
import com.alatka.messages.core.context.MessageDefinitionContext;
import com.alatka.messages.core.definition.IsoYamlMessageDefinitionBuilder;
import com.alatka.messages.core.holder.MessageHolder;
import com.alatka.messages.core.util.BytesUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;

public class FixedSubdomainFieldBuilderTest {

    private FixedSubdomainFieldBuilder fieldBuilder = new FixedSubdomainFieldBuilder();

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
        fieldDefinition.setSubdomainType(MessageDefinition.DomainType.FIXED);
        Assertions.assertTrue(fieldBuilder.matched(null, fieldDefinition));
    }

    @Test
    @DisplayName("pack() fixed=true")
    void test03() {
        String key = "iso:cups:common:subPayload:F53";
        MessageHolder value = MessageHolder.newInstance(key);
        value.put(1, "D");
        value.put(2, "1");
        value.put(3, null);

        MessageDefinition definition = MessageDefinitionContext.getInstance().messageDefinition(key);
        Map<String, MessageDefinition> usageMap = Collections.singletonMap(FieldDefinition.SUBFIELD_KEY_DEFAULT, definition);

        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setFixed(true);

        byte[] pack = fieldBuilder.pack(value, fieldDefinition, usageMap);
        Assertions.assertEquals("44312020202020202020202020202020", BytesUtil.bytesToHex(pack));
    }

    @Test
    @DisplayName("pack() fixed=false")
    void test04() {
        String key = "iso:cups:common:subPayload:F54";
        MessageHolder value = MessageHolder.newInstance(key);
        value.put(1, "AD");
        value.put(2, "11");
        value.put(3, "156");
        value.put(4, "-");
        value.put(5, new BigDecimal("12229"));

        MessageDefinition definition = MessageDefinitionContext.getInstance().messageDefinition(key);
        Map<String, MessageDefinition> usageMap = Collections.singletonMap(FieldDefinition.SUBFIELD_KEY_DEFAULT, definition);

        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setFixed(false);

        byte[] pack = fieldBuilder.pack(value, fieldDefinition, usageMap);
        Assertions.assertEquals("414431313135362D303030303030303132323239", BytesUtil.bytesToHex(pack));
    }

    @Test
    @DisplayName("unpack() fixed=true")
    void test05() {
        byte[] bytes = BytesUtil.hexToBytes("44312020202020202020202020202020");
        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setFixed(true);
        String key = "iso:cups:common:subPayload:F53";
        MessageDefinition definition = MessageDefinitionContext.getInstance().messageDefinition(key);
        Map<String, MessageDefinition> usageMap = Collections.singletonMap(FieldDefinition.SUBFIELD_KEY_DEFAULT, definition);

        MessageHolder messageHolder = (MessageHolder) fieldBuilder.unpack(bytes, fieldDefinition, usageMap);
        Assertions.assertEquals("D", messageHolder.getByDomainNo(1));
        Assertions.assertEquals("1", messageHolder.getByDomainNo(2));
        Assertions.assertNull(messageHolder.getByDomainNo(3));
    }

    @Test
    @DisplayName("unpack() fixed=false")
    void test06() {
        byte[] bytes = BytesUtil.hexToBytes("414431313135362D303030303030303132323239");
        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setFixed(false);
        String key = "iso:cups:common:subPayload:F54";
        MessageDefinition definition = MessageDefinitionContext.getInstance().messageDefinition(key);
        Map<String, MessageDefinition> usageMap = Collections.singletonMap(FieldDefinition.SUBFIELD_KEY_DEFAULT, definition);

        MessageHolder messageHolder = (MessageHolder) fieldBuilder.unpack(bytes, fieldDefinition, usageMap);
        Assertions.assertEquals("AD", messageHolder.getByDomainNo(1));
        Assertions.assertEquals("11", messageHolder.getByDomainNo(2));
        Assertions.assertEquals("156", messageHolder.getByDomainNo(3));
        Assertions.assertEquals("-", messageHolder.getByDomainNo(4));
        Assertions.assertEquals(new BigDecimal("12229"), messageHolder.getByDomainNo(5));
    }
}
