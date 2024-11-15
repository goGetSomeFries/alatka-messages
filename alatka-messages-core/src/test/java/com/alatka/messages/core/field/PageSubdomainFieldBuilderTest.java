package com.alatka.messages.core.field;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;
import com.alatka.messages.core.context.MessageDefinitionContext;
import com.alatka.messages.core.definition.FixedYamlMessageDefinitionBuilder;
import com.alatka.messages.core.holder.MessageHolder;
import com.alatka.messages.core.util.BytesUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PageSubdomainFieldBuilderTest {

    private PageSubdomainFieldBuilder fieldBuilder = new PageSubdomainFieldBuilder();

    @BeforeAll
    public static void beforeAll() {
        new FixedYamlMessageDefinitionBuilder("").build();
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

    @Test
    @DisplayName("pack()")
    void test03() {
        String key = "fixed:9001:3006:subPayload:F13";
        MessageHolder instance1 = MessageHolder.newInstance(key);
        instance1.put(1, 1);
        instance1.put(2, LocalDate.of(2024, 5, 14));
        instance1.put(3, LocalDate.of(2024, 5, 14));
        instance1.put(5, 23423);
        MessageHolder instance2 = MessageHolder.newInstance(key);
        instance2.put(1, 2);
        instance2.put(2, LocalDate.of(2024, 5, 14));
        instance2.put(3, LocalDate.of(2024, 5, 14));
        instance2.put(5, 23425);
        List<MessageHolder> value = Stream.of(instance1, instance2).collect(Collectors.toList());

        MessageDefinition definition = MessageDefinitionContext.getInstance().messageDefinition(key);
        Map<String, MessageDefinition> usageMap = Collections.singletonMap(FieldDefinition.SUBFIELD_KEY_DEFAULT, definition);

        byte[] pack = fieldBuilder.pack(value, null, usageMap);
        Assertions.assertEquals("303031323032343035313432303234303531343030303030303030303233343233202020203030303030303030303030302020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202030303030202020202020303032323032343035313432303234303531343030303030303030303233343235202020203030303030303030303030302020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202030303030202020202020", BytesUtil.bytesToHex(pack));
    }

    @Test
    @DisplayName("unpack()")
    void test04() {
        byte[] bytes = BytesUtil.hexToBytes("303031323032343035313432303234303531343030303030303030303233343233202020203030303030303030303030302020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202030303030202020202020303032323032343035313432303234303531343030303030303030303233343235202020203030303030303030303030302020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202030303030202020202020");
        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setLength(141);
        String key = "fixed:9001:3006:subPayload:F13";
        MessageDefinition definition = MessageDefinitionContext.getInstance().messageDefinition(key);
        Map<String, MessageDefinition> usageMap = Collections.singletonMap(FieldDefinition.SUBFIELD_KEY_DEFAULT, definition);

        List<MessageHolder> list = (List<MessageHolder>) fieldBuilder.unpack(bytes, fieldDefinition, usageMap);
        Assertions.assertEquals(2, list.size());
    }
}
