package com.alatka.messages.core.field;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.IsoFieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;
import com.alatka.messages.core.context.MessageDefinitionContext;
import com.alatka.messages.core.definition.IsoYamlMessageDefinitionBuilder;
import com.alatka.messages.core.holder.MessageHolder;
import com.alatka.messages.core.holder.UsageSubdomain;
import com.alatka.messages.core.util.BytesUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;

public class AbstractULVSubdomainFieldBuilderTest {

    private AbstractULVSubdomainFieldBuilder fieldBuilder = new ULVSubdomainFieldBuilder();

    @BeforeAll
    public static void beforeAll() {
        new IsoYamlMessageDefinitionBuilder("").build();
    }

    @Test
    @DisplayName("pack() validate=true")
    void test02() {
        String key = "iso:cups:common:subPayload:F63:SM";
        MessageHolder instance = MessageHolder.newInstance(key);
        instance.put(1, BytesUtil.hexToBytes("12EB34592309129023908978"));
        UsageSubdomain<Object> usageSubdomain = new UsageSubdomain<>();
        usageSubdomain.put(instance);

        MessageDefinition definition = MessageDefinitionContext.getInstance().messageDefinition(key);
        Map<String, MessageDefinition> usageMap = Collections.singletonMap(definition.getUsage(), definition);

        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setMessageDefinitionMap(usageMap);

        byte[] pack = fieldBuilder.pack(usageSubdomain, fieldDefinition, usageMap);
        Assertions.assertEquals("534D30313612EB3459230912902390897820202020", BytesUtil.bytesToHex(pack));
    }

    @Test
    @DisplayName("pack() validate=false")
    void test03() {
        String key = "iso:cups:common:subPayload:F63:SM";
        UsageSubdomain<Object> usageSubdomain = new UsageSubdomain<>();
        usageSubdomain.put("AA", BytesUtil.hexToBytes("30303234"));

        MessageDefinition definition = MessageDefinitionContext.getInstance().messageDefinition(key);
        Map<String, MessageDefinition> usageMap = Collections.singletonMap(definition.getUsage(), definition);

        IsoFieldDefinition fieldDefinition = new IsoFieldDefinition();
        fieldDefinition.setMessageDefinitionMap(usageMap);
        fieldDefinition.setNonSubdomainException(false);

        byte[] pack = fieldBuilder.pack(usageSubdomain, fieldDefinition, usageMap);
        Assertions.assertEquals("414130303430303234", BytesUtil.bytesToHex(pack));
    }

    @Test
    @DisplayName("unpack() validate=true")
    void test04() {
        String key = "iso:cups:common:subPayload:F63:SM";
        MessageDefinition definition = MessageDefinitionContext.getInstance().messageDefinition(key);
        Map<String, MessageDefinition> usageMap = Collections.singletonMap(definition.getUsage(), definition);

        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setMessageDefinitionMap(usageMap);

        String hex = "534D30313612EB3459230912902390897820202020";
        UsageSubdomain<Object> usageSubdomain = fieldBuilder.unpack(BytesUtil.hexToBytes(hex), fieldDefinition, usageMap);
        Map<String, Object> holder = usageSubdomain.getHolder();
        MessageHolder messageHolder = (MessageHolder) holder.get(definition.getUsage());
        Assertions.assertArrayEquals(BytesUtil.hexToBytes("12EB3459230912902390897820202020"), messageHolder.getByDomainNo(1));
    }

    @Test
    @DisplayName("unpack() validate=false")
    void test05() {
        String key = "iso:cups:common:subPayload:F63:SM";
        MessageDefinition definition = MessageDefinitionContext.getInstance().messageDefinition(key);
        Map<String, MessageDefinition> usageMap = Collections.singletonMap(definition.getUsage(), definition);

        IsoFieldDefinition fieldDefinition = new IsoFieldDefinition();
        fieldDefinition.setMessageDefinitionMap(usageMap);
        fieldDefinition.setNonSubdomainException(false);

        String hex = "5150303033323331";
        UsageSubdomain<Object> usageSubdomain = fieldBuilder.unpack(BytesUtil.hexToBytes(hex), fieldDefinition, usageMap);
        Map<String, Object> holder = usageSubdomain.getHolder();
        byte[] bytes = (byte[]) holder.get("QP");
        Assertions.assertEquals("323331", BytesUtil.bytesToHex(bytes));
    }

}
