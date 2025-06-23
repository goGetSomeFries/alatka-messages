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

import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;

public class TLV2SubdomainFieldBuilderTest {

    private TLV2SubdomainFieldBuilder fieldBuilder = new TLV2SubdomainFieldBuilder();

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
        fieldDefinition.setSubdomainType(MessageDefinition.DomainType.TLV2);
        Assertions.assertTrue(fieldBuilder.matched(null, fieldDefinition));
    }

    @Test
    @DisplayName("pack()")
    void test03() {
        String key = "iso:cupd:common:subPayload:F62:DA";
        MessageHolder value = MessageHolder.newInstance(key);
        value.put(1, "999999");
        value.put(2, "0000");

        MessageDefinition definition = MessageDefinitionContext.getInstance().messageDefinition(key);
        Map<String, MessageDefinition> usageMap = Collections.singletonMap(FieldDefinition.SUBFIELD_KEY_DEFAULT, definition);

        byte[] pack = fieldBuilder.pack(value, null, usageMap);
        Assertions.assertEquals("54303030303639393939393954303130303430303030", BytesUtil.bytesToHex(pack));
    }


    @Test
    @DisplayName("unpack()")
    void test04() {
        byte[] bytes = BytesUtil.hexToBytes("54303030303639393939393954303130303430303030");
        String key = "iso:cupd:common:subPayload:F62:DA";
        MessageDefinition definition = MessageDefinitionContext.getInstance().messageDefinition(key);
        Map<String, MessageDefinition> usageMap = Collections.singletonMap(FieldDefinition.SUBFIELD_KEY_DEFAULT, definition);

        MessageHolder messageHolder = (MessageHolder) fieldBuilder.unpack(bytes, null, usageMap);
        Assertions.assertEquals("999999", messageHolder.getByDomainNo(1));
        Assertions.assertEquals("0000", messageHolder.getByDomainNo(2));
    }

}
