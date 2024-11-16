package com.alatka.messages.core.field;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.IsoFieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;
import com.alatka.messages.core.context.MessageDefinitionContext;
import com.alatka.messages.core.definition.IsoYamlMessageDefinitionBuilder;
import com.alatka.messages.core.holder.MessageHolder;
import com.alatka.messages.core.holder.UsageSubdomain;
import com.alatka.messages.core.message.DefaultMessageBuilder;
import com.alatka.messages.core.message.TLV2SubdomainMessageBuilder;
import com.alatka.messages.core.message.TVSubdomainMessageBuilder;
import com.alatka.messages.core.util.BytesUtil;
import com.alatka.messages.core.util.ClassUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;

public class UVSubdomainFieldBuilderTest {

    private UVSubdomainFieldBuilder fieldBuilder = new UVSubdomainFieldBuilder();

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
        fieldDefinition.setSubdomainType(MessageDefinition.DomainType.UV);
        Assertions.assertTrue(fieldBuilder.matched(null, fieldDefinition));
    }

    @Test
    @DisplayName("generateMessageBuilder()")
    void test03() {
        MessageDefinition definition = new MessageDefinition();
        definition.setDomainType(MessageDefinition.DomainType.TV);
        Assertions.assertTrue(ClassUtil.invoke(fieldBuilder, "generateMessageBuilder",
                new Class[]{MessageDefinition.class}, new Object[]{definition}) instanceof TVSubdomainMessageBuilder);

        definition.setDomainType(MessageDefinition.DomainType.TLV2);
        Assertions.assertTrue(ClassUtil.invoke(fieldBuilder, "generateMessageBuilder",
                new Class[]{MessageDefinition.class}, new Object[]{definition}) instanceof TLV2SubdomainMessageBuilder);

        definition.setDomainType(MessageDefinition.DomainType.TLV);
        Assertions.assertTrue(ClassUtil.invoke(fieldBuilder, "generateMessageBuilder",
                new Class[]{MessageDefinition.class}, new Object[]{definition}) instanceof DefaultMessageBuilder);
    }

    @Test
    @DisplayName("pack() validate=true")
    void test04() {
        String key = "iso:cups:common:subPayload:F59:QL";
        MessageHolder instance = MessageHolder.newInstance(key);
        instance.put(1, 1);
        UsageSubdomain<Object> usageSubdomain = new UsageSubdomain<>();
        usageSubdomain.put(instance);

        MessageDefinition definition = MessageDefinitionContext.getInstance().messageDefinition(key);
        Map<String, MessageDefinition> usageMap = Collections.singletonMap(definition.getUsage(), definition);

        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setMessageDefinitionMap(usageMap);

        byte[] pack = fieldBuilder.pack(usageSubdomain, fieldDefinition, usageMap);
        Assertions.assertEquals("514C303031", BytesUtil.bytesToHex(pack));
    }

    @Test
    @DisplayName("pack() validate=false")
    void test05() {
        String key = "iso:cups:common:subPayload:F59:QL";
        UsageSubdomain<Object> usageSubdomain = new UsageSubdomain<>();
        usageSubdomain.put("AA", BytesUtil.hexToBytes("30303234"));

        MessageDefinition definition = MessageDefinitionContext.getInstance().messageDefinition(key);
        Map<String, MessageDefinition> usageMap = Collections.singletonMap(definition.getUsage(), definition);

        IsoFieldDefinition fieldDefinition = new IsoFieldDefinition();
        fieldDefinition.setMessageDefinitionMap(usageMap);
        fieldDefinition.setNonSubdomainException(false);

        byte[] pack = fieldBuilder.pack(usageSubdomain, fieldDefinition, usageMap);
        Assertions.assertEquals("414130303234", BytesUtil.bytesToHex(pack));
    }

    @Test
    @DisplayName("unpack() validate=true")
    void test06() {
        String key = "iso:cups:common:subPayload:F59:QL";
        MessageDefinition definition = MessageDefinitionContext.getInstance().messageDefinition(key);
        Map<String, MessageDefinition> usageMap = Collections.singletonMap(definition.getUsage(), definition);

        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setMessageDefinitionMap(usageMap);

        String hex = "514C303031";
        UsageSubdomain<Object> usageSubdomain = fieldBuilder.unpack(BytesUtil.hexToBytes(hex), fieldDefinition, usageMap);
        Map<String, Object> holder = usageSubdomain.getHolder();
        MessageHolder messageHolder = (MessageHolder) holder.get(definition.getUsage());
        Assertions.assertEquals(1, (int) messageHolder.getByDomainNo(1));
    }

    @Test
    @DisplayName("unpack() validate=false")
    void test07() {
        String key = "iso:cups:common:subPayload:F59:QL";
        MessageDefinition definition = MessageDefinitionContext.getInstance().messageDefinition(key);
        Map<String, MessageDefinition> usageMap = Collections.singletonMap(definition.getUsage(), definition);

        IsoFieldDefinition fieldDefinition = new IsoFieldDefinition();
        fieldDefinition.setMessageDefinitionMap(usageMap);
        fieldDefinition.setNonSubdomainException(false);

        String hex = "5150323331";
        UsageSubdomain<Object> usageSubdomain = fieldBuilder.unpack(BytesUtil.hexToBytes(hex), fieldDefinition, usageMap);
        Map<String, Object> holder = usageSubdomain.getHolder();
        byte[] bytes = (byte[]) holder.get("QP");
        Assertions.assertEquals("323331", BytesUtil.bytesToHex(bytes));
    }

}
