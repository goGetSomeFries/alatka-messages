package com.alatka.messages;

import com.alatka.messages.definition.IsoYamlMessageDefinitionBuilder;
import com.alatka.messages.holder.MessageHolder;
import com.alatka.messages.message.MessageBuilder;
import com.alatka.messages.util.BytesUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Arrays;

public class MessageTest {

    @BeforeAll
    public static void beforeAll() {
        new IsoYamlMessageDefinitionBuilder().build();
    }

    @Test
    @DisplayName("jcb-1")
    public void test01() {
        String key = "iso:jcb:common:payload";
        String hex = "F0F1F0F0767D448188E1A008103568560090000176000000000000000176000000000176122913412700000001000172134127122925041229639981000008885280000888518000F0F0F0F1F7F2F1F2F1F3F2F7F3F9F4F5F3F34040F3F2F5F6F2F2F0F340404040404040D1C3C240E3C5E2E340D4C5D9C3C8C1D5E3404040404040E3C5E2E340C3C9E3E84040404040D1D7D54AF0F2F4F71122334455667788990011223344556677889900000201094725454745445395202545900000000006404040404040F0F3F1F4F9F6F5F8F7F4404040F1F4404040F0F4F0F1F0F8F4F0F8F4F006F2F2F2F3F9F2";
        MessageHolder holder = MessageBuilder.init(key).unpack(hex);
        System.out.println(holder);
        byte[] pack = MessageBuilder.init(key).pack(holder);
        MessageHolder holder1 = MessageBuilder.init(key).unpack(pack);
        Assertions.assertEquals(holder, holder1);
        Assertions.assertEquals(hex.toUpperCase(), BytesUtil.bytesToHex(pack));
    }

    @Test
    @DisplayName("jcb-2")
    public void test02() {
        String key = "iso:jcb:common:payload";
        String hex = "F0F1F0F0723C4481A8C180081035601234567890120000000000000261190923072729468214151228092398125399022004088802000008880200002503560123456789012D98121010000033300000F9F4F0F9F2F3F4F6F8F2F1F47B94F2F386F891F4F1F1F7F5F1F8404040404040404040C1C3D8E4C9D9C5D940D5C1D4C540404040404040404040C3C9E3E840D5C1D4C54040404040E4E2C107F0F1F0F3F1F2F3F3F4F406F2F2F2F3F4F4";
        MessageHolder holder = MessageBuilder.init(key).unpack(hex);
        System.out.println(holder);
        byte[] pack = MessageBuilder.init(key).pack(holder);
        MessageHolder holder1 = MessageBuilder.init(key).unpack(pack);
        Assertions.assertEquals(holder, holder1);
        Assertions.assertEquals(hex.toUpperCase(), BytesUtil.bytesToHex(pack));
    }


    @Test
    public void test99() {
        System.out.println(BytesUtil.bytesToBinary(BytesUtil.hexToBytes("723C4481A8C18008")));
    }
}
