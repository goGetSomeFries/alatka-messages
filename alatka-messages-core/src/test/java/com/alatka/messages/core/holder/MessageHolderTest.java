package com.alatka.messages.core.holder;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.IsoFieldDefinition;
import com.alatka.messages.core.util.BytesUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.ReflectionMemberAccessor;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class MessageHolderTest {

    @Test
    @DisplayName("toMapByName()/toMapByDomainNo()")
    void test01() throws NoSuchFieldException, IllegalAccessException {
        ReflectionMemberAccessor reflectionMemberAccessor = new ReflectionMemberAccessor();

        IsoFieldDefinition fd1 = new IsoFieldDefinition();
        fd1.setName("name1");
        fd1.setDomainNo(1);
        IsoFieldDefinition fd2 = new IsoFieldDefinition();
        fd2.setName("name2");
        fd2.setDomainNo(2);
        IsoFieldDefinition fd3 = new IsoFieldDefinition();
        fd3.setName("name3");
        fd3.setDomainNo(3);
        IsoFieldDefinition fd4 = new IsoFieldDefinition();
        fd4.setName("name4");
        fd4.setDomainNo(4);
        IsoFieldDefinition fd5 = new IsoFieldDefinition();
        fd5.setName("name5");
        fd5.setDomainNo(5);

        IsoFieldDefinition fd41 = new IsoFieldDefinition();
        fd41.setName("name41");
        fd41.setDomainNo(1);
        Map<FieldDefinition, Object> map41 = new HashMap<>();
        map41.put(fd1, "AMMNT");
        MessageHolder messageHolder41 = Mockito.spy(MessageHolder.class);
        reflectionMemberAccessor.set(MessageHolder.class.getDeclaredField("valueMap"), messageHolder41, map41);

        IsoFieldDefinition fd51 = new IsoFieldDefinition();
        fd51.setName("name51");
        fd51.setDomainNo(1);
        Map<FieldDefinition, Object> map51 = new HashMap<>();
        map51.put(fd1, LocalDate.now());
        MessageHolder messageHolder51 = Mockito.spy(MessageHolder.class);
        reflectionMemberAccessor.set(MessageHolder.class.getDeclaredField("valueMap"), messageHolder51, map51);

        UsageSubdomain<Object> usageSubdomain = new UsageSubdomain<>();
        usageSubdomain.put("AC", messageHolder51);
        usageSubdomain.put("ZR", BytesUtil.hexToBytes("AB11"));

        Map<FieldDefinition, Object> map = new HashMap<>();
        map.put(fd1, "15901334567");
        map.put(fd2, null);
        map.put(fd3, 300.00);
        map.put(fd4, messageHolder41);
        map.put(fd5, usageSubdomain);

        MessageHolder messageHolder = Mockito.spy(MessageHolder.class);
        reflectionMemberAccessor.set(MessageHolder.class.getDeclaredField("valueMap"), messageHolder, map);

        Map<String, Object> result = messageHolder.toMapByName();
        System.out.println(result);
        result = messageHolder.toMapByDomainNo();
        System.out.println(result);
    }
}
