package com.alatka.messages.core.domain;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.util.BytesUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;

class AbstractDomainParsedTest {

    private AbstractDomainParsed domainParsed = new FixedDomainParsed();

    @Test
    @DisplayName("域值超长")
    void test01() {
        byte[] bytes = "123".getBytes();
        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setLength(2);
        Assertions.assertThrows(IllegalArgumentException.class, () -> domainParsed.padding(bytes, fieldDefinition));
    }

    @Test
    @DisplayName("域值与配置长度相等")
    void test02() {
        byte[] bytes = "123".getBytes();
        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setLength(3);
        Assertions.assertSame(domainParsed.padding(bytes, fieldDefinition), bytes);
    }

    @Test
    @DisplayName("ascii左补0")
    void test03() {
        String str = "123";
        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setLength(4);
        fieldDefinition.setClassType(Integer.class);
        fieldDefinition.setParseType(FieldDefinition.ParseType.ASCII);

        byte[] padding = domainParsed.padding(str.getBytes(), fieldDefinition);
        Assertions.assertEquals(new String(padding), "0" + str);
    }

    @Test
    @DisplayName("ascii右补空格")
    void test04() {
        String str = "123";
        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setLength(4);
        fieldDefinition.setClassType(String.class);
        fieldDefinition.setParseType(FieldDefinition.ParseType.ASCII);

        byte[] padding = domainParsed.padding(str.getBytes(), fieldDefinition);
        Assertions.assertEquals(new String(padding), str + " ");
    }

    @Test
    @DisplayName("ebcdic左补0")
    void test05() {
        String hex = "F1F2F3";
        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setLength(4);
        fieldDefinition.setClassType(LocalDate.class);
        fieldDefinition.setParseType(FieldDefinition.ParseType.EBCDIC);

        byte[] padding = domainParsed.padding(BytesUtil.hexToBytes(hex), fieldDefinition);
        String actual = BytesUtil.bytesToHex(BytesUtil.toEBCDIC("0")) + hex;
        Assertions.assertEquals(BytesUtil.bytesToHex(padding), actual);
    }

    @Test
    @DisplayName("ebcdic右补空格")
    void test06() {
        String hex = "F1F2F3";
        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setLength(4);
        fieldDefinition.setClassType(String.class);
        fieldDefinition.setParseType(FieldDefinition.ParseType.EBCDIC);

        byte[] padding = domainParsed.padding(BytesUtil.hexToBytes(hex), fieldDefinition);
        String actual = hex + BytesUtil.bytesToHex(BytesUtil.toEBCDIC(" "));
        Assertions.assertEquals(BytesUtil.bytesToHex(padding), actual);
    }

    @Test
    @DisplayName("bcd左补0")
    void test07() {
        String hex = "112233";
        FieldDefinition fieldDefinition = new FieldDefinition();
        fieldDefinition.setLength(4);
        fieldDefinition.setClassType(Date.class);
        fieldDefinition.setParseType(FieldDefinition.ParseType.BCD);

        byte[] padding = domainParsed.padding(BytesUtil.hexToBytes(hex), fieldDefinition);
        byte[] actual = BytesUtil.concat(BytesUtil.intToBytes(0), BytesUtil.hexToBytes(hex));
        Assertions.assertArrayEquals(padding, actual);
    }
}