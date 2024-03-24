package com.alatka.messages;

import com.alatka.messages.message.MessageBuilder;
import com.alatka.messages.definition.FixedYamlMessageDefinitionBuilder;
import com.alatka.messages.holder.MessageHolder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FixedYamlMessageBuilderTest {

    @BeforeAll
    public static void beforeAll() {
        new FixedYamlMessageDefinitionBuilder().build();
    }

    @Test
    public void test01() {
        String key = "fixed:0305:4118:request";
        MessageHolder instance = MessageHolder.newInstance(key);
        instance.put(1, "4118");
        instance.put(2, null);
        instance.put(3, "0305");
        instance.put(4, "PR");
        instance.put(5, "999990");
        instance.put(6, "000000");
        instance.put(7, new Random().nextInt(1000000));
        instance.put(8, "C1");
        instance.put(9, "220881199438273288");
        instance.put(10, null);
        instance.put(11, "CUTAG");
        instance.put(12, null);
        instance.put(13, null);
        instance.put(14, null);

        byte[] expected = MessageBuilder.init(key).pack(instance);
        MessageHolder object = MessageBuilder.init(key).unpack(expected);
        byte[] actual = MessageBuilder.init(key).pack(object);

        Assertions.assertEquals(object, instance);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void test02() {
        List<MessageHolder> elements = new ArrayList<>();
        String pageKey = "fixed:0305:3006:subPayload:F13";
        MessageHolder instance1 = MessageHolder.newInstance(pageKey);
        instance1.put(1, 1);
        instance1.put(2, LocalDate.now());
        instance1.put(3, LocalDate.now());
        instance1.put(4, null);
        instance1.put(5, 39392);
        instance1.put(6, "DDAC");
        instance1.put(7, new BigDecimal("200.01"));
        MessageHolder instance2 = MessageHolder.newInstance(pageKey);
        instance2.put(1, 2);
        instance2.put(2, LocalDate.now());
        instance2.put(3, LocalDate.now());
        instance2.put(4, null);
        instance2.put(5, 39392);
        instance2.put(6, "DDAC");
        instance2.put(7, new BigDecimal("200.03"));
        elements.add(instance1);
        elements.add(instance2);

        String key = "fixed:0305:3006:response";
        MessageHolder instance = MessageHolder.newInstance(key);
        instance.put(1, "3006");
        instance.put(2, null);
        instance.put(3, "0305");
        instance.put(4, "PR");
        instance.put(5, "999990");
        instance.put(6, "000000");
        instance.put(7, new Random().nextInt(1000000));
        instance.put(8, "622544495959234");
        instance.put(9, YearMonth.now());
        instance.put(10, "1");
        instance.put(11, 2);
        instance.put(12, "1");
        instance.put(13, elements);

        byte[] expected = MessageBuilder.init(key).pack(instance);
        MessageHolder object = MessageBuilder.init(key).unpack(expected);
        byte[] actual = MessageBuilder.init(key).pack(object);

        Assertions.assertEquals(object, instance);
        Assertions.assertArrayEquals(expected, actual);
    }

}
