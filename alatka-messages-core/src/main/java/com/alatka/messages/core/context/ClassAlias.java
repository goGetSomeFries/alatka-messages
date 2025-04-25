package com.alatka.messages.core.context;

import com.alatka.messages.core.holder.Bitmap;

public enum ClassAlias {
    BitMap(Bitmap.class),
    LocalDateTime(java.time.LocalDateTime.class),
    LocalDate(java.time.LocalDate.class),
    LocalTime(java.time.LocalTime.class),
    Date(java.util.Date.class),
    YearMonth(java.time.YearMonth.class),
    MonthDay(java.time.MonthDay.class),
    Bytes(byte[].class),
    Integer(java.lang.Integer.class),
    BigDecimal(java.math.BigDecimal.class),
    BigInteger(java.math.BigInteger.class),
    Long(java.lang.Long.class),
    String(java.lang.String.class),
    Boolean(java.lang.Boolean.class),
    List(java.util.ArrayList.class),
    MessageHolder(com.alatka.messages.core.holder.MessageHolder.class),
    UsageSubdomain(com.alatka.messages.core.holder.UsageSubdomain.class);

    private final Class<?> clazz;

    ClassAlias(Class<?> clazz) {
        this.clazz = clazz;
    }

    public Class<?> getClazz() {
        return clazz;
    }
}
