package com.alatka.messages;

import com.alatka.messages.definition.FixedAnnotationMessageDefinitionBuilder;
import com.alatka.messages.message.MessageBuilder;
import com.alatka.messages.template.Fixed3006Page;
import com.alatka.messages.template.Fixed3006Res;
import com.alatka.messages.template.Fixed4118Req;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FixedAnnotationMessageBuilderTest {

    @BeforeAll
    public static void beforeAll() {
        new FixedAnnotationMessageDefinitionBuilder("com.alatka.messages.template").build();
    }

    @Test
    public void test01() {
        String key = "fixed:0305:4118:request";
        Fixed4118Req instance = new Fixed4118Req();
        instance.setTrxType("4118");
        instance.setRetCode(null);
        instance.setBnkNbr("0305");
        instance.setSource("PR");
        instance.setBrnNo("999990");
        instance.setOpeNo("000000");
        instance.setSeqNo(new Random().nextInt(1000000));
        instance.setOption("C1");
        instance.setCustNbr("220881199438273288");
        instance.setCardNbr(null);
        instance.setTagType("CUTAG");
        instance.setTagCode(null);
        instance.setTagInfo(null);
        instance.setResvd(null);

        byte[] expected = MessageBuilder.init(key).pack(instance);
        Fixed4118Req object = MessageBuilder.init(key).unpack(expected);
        byte[] actual = MessageBuilder.init(key).pack(object);

        Assertions.assertEquals(object, instance);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void test02() {
        List<Fixed3006Page> elements = new ArrayList<>();
        Fixed3006Page instance1 = new Fixed3006Page();
        instance1.setMonthNbr(1);
        instance1.setValDate(LocalDate.now());
        instance1.setPurDate(LocalDate.now());
        instance1.setPurTime(LocalTime.now());
        instance1.setTranNo(39392);
        instance1.setTranType("DDAC");
        instance1.setAmount(new BigDecimal("200.01"));
        Fixed3006Page instance2 = new Fixed3006Page();
        instance2.setMonthNbr(2);
        instance2.setValDate(LocalDate.now());
        instance2.setPurDate(LocalDate.now());
        instance2.setPurTime(LocalTime.now());
        instance2.setTranNo(39392);
        instance2.setTranType("DDAC");
        instance2.setAmount(new BigDecimal("200.03"));
        elements.add(instance1);
        elements.add(instance2);

        String key = "fixed:0305:3006:response";
        Fixed3006Res instance = new Fixed3006Res();
        instance.setTrxType("3006");
        instance.setRetCode(null);
        instance.setBnkNbr("0305");
        instance.setSource("PR");
        instance.setBrnNo("999990");
        instance.setOpeNo("000000");
        instance.setSeqNo(new Random().nextInt(1000000));
        instance.setCardNbr("622544495959234");
        instance.setTranYM(YearMonth.now());
        instance.setOption("1");
        instance.setCounts(2);
        instance.setRtnInd("1");
        instance.setElements(elements);

        byte[] expected = MessageBuilder.init(key).pack(instance);
        Fixed3006Res object = MessageBuilder.init(key).unpack(expected);
        byte[] actual = MessageBuilder.init(key).pack(object);

        Assertions.assertEquals(object, instance);
        Assertions.assertArrayEquals(expected, actual);
    }

}
