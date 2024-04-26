package com.alatka.messages.field;

import com.alatka.messages.context.FieldDefinition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.YearMonth;

// TODO
public class YearMonthFieldBuilderTest {

    private YearMonthFieldBuilder fieldBuilder = new YearMonthFieldBuilder();

    @Test
    @DisplayName("order() == 85")
    void test01() {
        int order = fieldBuilder.getOrder();
        Assertions.assertEquals(85, order);
    }

    @Test
    @DisplayName("matched()")
    void test02() {
        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setOriginClass(YearMonth.class);
        Assertions.assertTrue(fieldBuilder.matched(null, fieldDefinition));
    }

    // TODO
    @Test
    @DisplayName("fromObjectToAscii()")
    void test03() {
    }

    @Test
    @DisplayName("fromObjectToBcd()")
    void test04() {
    }

    @Test
    @DisplayName("toObjectWithAscii()")
    void test05() {
    }

    @Test
    @DisplayName("toObjectWithBcd()")
    void test06() {
    }

}
