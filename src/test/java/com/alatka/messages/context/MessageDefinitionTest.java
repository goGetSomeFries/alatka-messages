package com.alatka.messages.context;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

    @Test
    @DisplayName("compareTo()")
    void test05() {
        // TODO
    }
}
