package com.alatka.messages.core.context;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ClassAliasTest {

    @Test
    void test01() {
        ClassAlias[] values = ClassAlias.values();
        String[] array = {"BitMap", "LocalDateTime", "LocalDate", "LocalTime",
                "Date", "YearMonth", "MonthDay", "Bytes", "Integer", "BigDecimal", "BigInteger",
                "Long", "String", "Boolean", "Subdomain"};
        Assertions.assertEquals(array.length, values.length);
        for (int i = 0; i < array.length; i++) {
            Assertions.assertEquals(array[i], values[i].name());
        }
    }
}
