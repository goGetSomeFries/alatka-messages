package com.alatka.messages.core.context;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;
import java.util.Objects;

public class MessageDefinitionTest {

    @Test
    @DisplayName("equals()")
    void test01() {
        MessageDefinition definition1 = new MessageDefinition();
        definition1.setType(MessageDefinition.Type.iso);
        definition1.setGroup("cups");
        definition1.setCode("test");
        definition1.setKind(MessageDefinition.Kind.subPayload);
        definition1.setDomain("F11");
        definition1.setUsage(null);
        MessageDefinition definition2 = new MessageDefinition();
        definition2.setType(MessageDefinition.Type.iso);
        definition2.setGroup("cups");
        definition2.setCode("test");
        definition2.setKind(MessageDefinition.Kind.subPayload);
        definition2.setDomain("F11");
        definition2.setUsage(null);
        Assertions.assertTrue(definition1.equals(definition1));
        Assertions.assertNotNull(definition1);
        Assertions.assertFalse(definition1.equals("123"));
        Assertions.assertTrue(definition1.equals(definition2));
    }

    @Test
    @DisplayName("hashcode()")
    void test02() {
        MessageDefinition definition1 = new MessageDefinition();
        definition1.setType(MessageDefinition.Type.iso);
        definition1.setGroup("cups");
        definition1.setCode("test");
        definition1.setKind(MessageDefinition.Kind.subPayload);
        definition1.setDomain("F11");
        definition1.setUsage(null);

        int actual = Objects.hash(definition1.getType(), definition1.getGroup(), definition1.getCode(), definition1.getKind(), definition1.getDomain(), definition1.getUsage());
        Assertions.assertEquals(definition1.hashCode(), actual);
    }

    @Test
    @DisplayName("identity()")
    void test03() {
        MessageDefinition definition1 = new MessageDefinition();
        definition1.setType(MessageDefinition.Type.iso);
        definition1.setGroup("cups");
        definition1.setCode("test");
        definition1.setKind(MessageDefinition.Kind.subPayload);
        definition1.setDomain(null);
        definition1.setUsage("AA");

        String actual = "iso:cups:test:subPayload:AA";
        Assertions.assertEquals(definition1.identity(), actual);
    }

    @Test
    @DisplayName("copy()")
    void test04() {
        MessageDefinition definition1 = new MessageDefinition();
        definition1.setType(MessageDefinition.Type.iso);
        definition1.setGroup("cups");
        definition1.setCode("test");
        definition1.setKind(MessageDefinition.Kind.subPayload);
        definition1.setDomain(null);
        definition1.setUsage("AA");
        MessageDefinition definition2 = definition1.copy();
        Assertions.assertTrue(Objects.equals(definition1.getType(), definition2.getType()));
        Assertions.assertTrue(Objects.equals(definition1.getGroup(), definition2.getGroup()));
        Assertions.assertTrue(Objects.equals(definition1.getCode(), definition2.getCode()));
        Assertions.assertTrue(Objects.equals(definition1.getKind(), definition2.getKind()));
        Assertions.assertTrue(Objects.equals(definition1.getDomain(), definition2.getDomain()));
        Assertions.assertTrue(Objects.equals(definition1.getUsage(), definition2.getUsage()));
    }

    @Disabled
    @Test
    @DisplayName("compareTo()")
    void test05() {
        // TODO
    }

    @Test
    @DisplayName("toString()")
    void test06() {
        MessageDefinition definition = new MessageDefinition();
        definition.setType(MessageDefinition.Type.iso);
        definition.setGroup("cups");
        definition.setCode("test");
        definition.setKind(MessageDefinition.Kind.subPayload);
        definition.setDomain(null);
        definition.setUsage("AA");
        definition.setDomainType(MessageDefinition.DomainType.NONE);
        definition.setRemark("testing....");

        Assertions.assertEquals("MessageDefinition{iso:cups:test:subPayload:AA:NONE:testing....}", definition.toString());
    }

    @Test
    @DisplayName("getter() setter()")
    void test07() {
        MessageDefinition definition = new MessageDefinition();
        definition.setType(MessageDefinition.Type.iso);
        definition.setGroup("cups");
        definition.setCode("test");
        definition.setKind(MessageDefinition.Kind.subPayload);
        definition.setDomain("F11");
        definition.setUsage("AA");
        definition.setDomainType(MessageDefinition.DomainType.NONE);
        definition.setHolder(String.class);
        definition.setCharset("GBK");
        definition.setRemark("testing....");

        Assertions.assertEquals(MessageDefinition.Type.iso, definition.getType());
        Assertions.assertEquals("cups", definition.getGroup());
        Assertions.assertEquals("test", definition.getCode());
        Assertions.assertEquals(MessageDefinition.Kind.subPayload, definition.getKind());
        Assertions.assertEquals("F11", definition.getDomain());
        Assertions.assertEquals("AA", definition.getUsage());
        Assertions.assertEquals(MessageDefinition.DomainType.NONE, definition.getDomainType());
        Assertions.assertEquals(String.class, definition.getHolder());
        Assertions.assertEquals(Charset.forName("GBK"), definition.getCharset());
        Assertions.assertEquals("testing....", definition.getRemark());
    }


    @Test
    @DisplayName("MessageDefinition.Type")
    void test08() {
        MessageDefinition.Type[] values = MessageDefinition.Type.values();
        String[] array = {"iso", "fixed"};
        Assertions.assertEquals(array.length, values.length);
        for (int i = 0; i < array.length; i++) {
            Assertions.assertEquals(array[i], values[i].name());
        }
    }

    @Test
    @DisplayName("MessageDefinition.Kind")
    void test09() {
        MessageDefinition.Kind[] values = MessageDefinition.Kind.values();
        String[] array = {"header", "subPayload", "payload", "request", "response", "none"};
        Assertions.assertEquals(array.length, values.length);
        for (int i = 0; i < array.length; i++) {
            Assertions.assertEquals(array[i], values[i].name());
        }
    }

    @Test
    @DisplayName("MessageDefinition.DomainType")
    void test10() {
        MessageDefinition.DomainType[] values = MessageDefinition.DomainType.values();
        String[] array = {"TLV", "TLV2", "TV", "ULV", "ULV2", "UV", "UVAS", "PAGE", "FIXED", "BITMAP", "NONE"};
        Assertions.assertEquals(array.length, values.length);
        for (int i = 0; i < array.length; i++) {
            Assertions.assertEquals(array[i], values[i].name());
        }
    }
}
