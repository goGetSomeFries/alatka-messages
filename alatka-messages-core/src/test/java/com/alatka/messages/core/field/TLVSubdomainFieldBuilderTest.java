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

public class TLVSubdomainFieldBuilderTest {

    private TLVSubdomainFieldBuilder fieldBuilder = new TLVSubdomainFieldBuilder();

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
        fieldDefinition.setSubdomainType(MessageDefinition.DomainType.TLV);
        Assertions.assertTrue(fieldBuilder.matched(null, fieldDefinition));
    }

    @Test
    @DisplayName("pack()")
    void test03() {
        String key = "iso:cups:common:subPayload:F55";
        MessageHolder value = MessageHolder.newInstance(key);
        value.put(7, LocalDate.of(2024, 5, 14));
        value.put(8, 1);

        MessageDefinition definition = MessageDefinitionContext.getInstance().messageDefinition(key);
        Map<String, MessageDefinition> usageMap = Collections.singletonMap(FieldDefinition.SUBFIELD_KEY_DEFAULT, definition);

        byte[] pack = fieldBuilder.pack(value, null, usageMap);
        Assertions.assertEquals("9A032405149C0101", BytesUtil.bytesToHex(pack));
    }


    @Test
    @DisplayName("unpack()")
    void test04() {
        byte[] bytes = BytesUtil.hexToBytes("9A032405149C0101");
        String key = "iso:cups:common:subPayload:F55";
        MessageDefinition definition = MessageDefinitionContext.getInstance().messageDefinition(key);
        Map<String, MessageDefinition> usageMap = Collections.singletonMap(FieldDefinition.SUBFIELD_KEY_DEFAULT, definition);

        MessageHolder messageHolder = (MessageHolder) fieldBuilder.unpack(bytes, null, usageMap);
        Assertions.assertEquals(LocalDate.of(2024, 5, 14), messageHolder.getByDomainNo(7));
        Assertions.assertEquals(1, (int) messageHolder.getByDomainNo(8));
    }

}
