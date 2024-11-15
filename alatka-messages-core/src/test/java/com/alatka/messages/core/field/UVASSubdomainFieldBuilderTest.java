package com.alatka.messages.core.field;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;
import com.alatka.messages.core.context.MessageDefinitionContext;
import com.alatka.messages.core.definition.IsoYamlMessageDefinitionBuilder;
import com.alatka.messages.core.holder.MessageHolder;
import com.alatka.messages.core.holder.UsageSubdomain;
import com.alatka.messages.core.util.BytesUtil;
import org.junit.jupiter.api.*;

import java.util.Collections;
import java.util.Map;

public class UVASSubdomainFieldBuilderTest {

    private UVASSubdomainFieldBuilder fieldBuilder = new UVASSubdomainFieldBuilder();

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
        fieldDefinition.setSubdomainType(MessageDefinition.DomainType.UVAS);
        Assertions.assertTrue(fieldBuilder.matched(null, fieldDefinition));
    }

    @Disabled
    @Test
    @DisplayName("pack()")
    void test03() {
        String key = "iso:cups:common:subPayload:F48:AA";
        MessageHolder instance = MessageHolder.newInstance(key);
        instance.put(1, "DJIEJ47824781IFQIJFJOEJKLSJE");
        UsageSubdomain<Object> usageSubdomain = new UsageSubdomain<>();
        usageSubdomain.put(instance);

        MessageDefinition definition = MessageDefinitionContext.getInstance().messageDefinition(key);
        Map<String, MessageDefinition> usageMap = Collections.singletonMap(definition.getUsage(), definition);

        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setMessageDefinitionMap(usageMap);
        byte[] pack = fieldBuilder.pack(usageSubdomain, fieldDefinition, usageMap);
        Assertions.assertEquals("41534141303238444A49454A3437383234373831494651494A464A4F454A4B4C534A45", BytesUtil.bytesToHex(pack));
    }


    @Test
    @DisplayName("unpack() AS")
    void test04() {
        String key = "iso:cups:common:subPayload:F48:AA";
        String hex = "41534141303238444A49454A3437383234373831494651494A464A4F454A4B4C534A45";
        MessageDefinition definition = MessageDefinitionContext.getInstance().messageDefinition(key);
        Map<String, MessageDefinition> usageMap = Collections.singletonMap(definition.getUsage(), definition);

        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setMessageDefinitionMap(usageMap);
        UsageSubdomain<Object> usageSubdomain = fieldBuilder.unpack(BytesUtil.hexToBytes(hex), fieldDefinition, usageMap);
        Map<String, Object> map = usageSubdomain.getHolder();
        MessageHolder messageHolder = (MessageHolder) map.get(definition.getUsage());
        Assertions.assertEquals("DJIEJ47824781IFQIJFJOEJKLSJE", messageHolder.getByDomainNo(1));
    }

    @Test
    @DisplayName("unpack() !AS")
    void test05() {
        String key = "iso:cups:common:subPayload:F48:AA";
        String str = "AADJIEJ47824781IFQIJFJOEJKLSJE";
        MessageDefinition definition = MessageDefinitionContext.getInstance().messageDefinition(key);
        Map<String, MessageDefinition> usageMap = Collections.singletonMap(definition.getUsage(), definition);

        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setMessageDefinitionMap(usageMap);
        UsageSubdomain<Object> usageSubdomain = fieldBuilder.unpack(str.getBytes(), fieldDefinition, usageMap);
        Map<String, Object> map = usageSubdomain.getHolder();
        MessageHolder messageHolder = (MessageHolder) map.get(definition.getUsage());
        Assertions.assertEquals("DJIEJ47824781IFQIJFJOEJKLSJE", messageHolder.getByDomainNo(1));
    }
}
