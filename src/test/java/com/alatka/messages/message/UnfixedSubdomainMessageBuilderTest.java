package com.alatka.messages.message;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.MessageDefinition;
import com.alatka.messages.context.MessageDefinitionContext;
import com.alatka.messages.domain.DomainParsed;
import com.alatka.messages.domain.DomainParsedFactory;
import com.alatka.messages.field.FieldBuilder;
import com.alatka.messages.field.FieldBuilderFactory;
import com.alatka.messages.util.ClassUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mockStatic;

public class UnfixedSubdomainMessageBuilderTest {

    @Test
    @DisplayName("构造器")
    void test01() {
        MessageDefinition definition = new MessageDefinition();
        Assertions.assertDoesNotThrow(() -> new UnfixedSubdomainMessageBuilder(definition));
    }

    @Test
    @DisplayName("concatBytes()")
    void test02() {
        MessageDefinition definition = new MessageDefinition();
        UnfixedSubdomainMessageBuilder messageBuilder = new UnfixedSubdomainMessageBuilder(definition);
        byte[] bytes1 = "1".getBytes();
        byte[] bytes2 = "2".getBytes();
        byte[] bytes = messageBuilder.concatBytes(bytes1, bytes2);
        Assertions.assertArrayEquals("21".getBytes(), bytes);
    }

    @Test
    @DisplayName("buildFieldDefinitions()")
    void test03() {
        List<FieldDefinition> list = IntStream.range(0, 5)
                .mapToObj(i -> {
                    FieldDefinition definition = new FieldDefinition();
                    definition.setDomainNo(i);
                    definition.setName("test" + i);
                    return definition;
                }).collect(Collectors.toList());

        MessageDefinitionContext mock = Mockito.mock(MessageDefinitionContext.class);
        doReturn(list).when(mock).fieldDefinitions(any());

        MockedStatic<MessageDefinitionContext> mockedStatic = mockStatic(MessageDefinitionContext.class);
        mockedStatic.when(MessageDefinitionContext::getInstance).thenReturn(mock);

        MessageDefinition definition = new MessageDefinition();
        UnfixedSubdomainMessageBuilder messageBuilder = new UnfixedSubdomainMessageBuilder(definition);
        List<FieldDefinition> result = messageBuilder.buildFieldDefinitions();

        Assertions.assertIterableEquals(list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList()), result);

        mockedStatic.close();
    }

    @Test
    @DisplayName("doUnpack() counter >= bytes.length")
    void test04() {
        MessageDefinition definition = new MessageDefinition();
        UnfixedSubdomainMessageBuilder messageBuilder = new UnfixedSubdomainMessageBuilder(definition);

        AtomicInteger counter = new AtomicInteger(20);
        byte[] bytes = new byte[10];
        FieldDefinition fieldDefinition = new FieldDefinition();
        MessageBuilder.Wrapper wrapper = messageBuilder.doUnpack(null, null, fieldDefinition, bytes, counter);

        Assertions.assertNull(ClassUtil.getFieldValue(wrapper, "value"));
        Assertions.assertSame(fieldDefinition, ClassUtil.getFieldValue(wrapper, "fieldDefinition"));
    }

    @Test
    @DisplayName("doUnpack() counter < bytes.length")
    void test05() {
        DomainParsed mockDomainParsed = Mockito.mock(DomainParsed.class);
        doReturn(new byte[0]).when(mockDomainParsed).unpack(any(), any(), any());

        MockedStatic<DomainParsedFactory> mockedDomainParsedFactory = mockStatic(DomainParsedFactory.class);
        mockedDomainParsedFactory.when(() -> DomainParsedFactory.getInstance(any(), any(), any())).thenReturn(mockDomainParsed);

        FieldBuilder mockFieldBuilder = Mockito.mock(FieldBuilder.class);
        doReturn("TEST").when(mockFieldBuilder).deserialize(any(), any(), any(), any());

        MockedStatic<FieldBuilderFactory> mockedFieldBuilderFactory = mockStatic(FieldBuilderFactory.class);
        mockedFieldBuilderFactory.when(() -> FieldBuilderFactory.getInstance(any(), any(), any())).thenReturn(mockFieldBuilder);

        MessageDefinition definition = new MessageDefinition();
        UnfixedSubdomainMessageBuilder messageBuilder = new UnfixedSubdomainMessageBuilder(definition);

        AtomicInteger counter = new AtomicInteger(5);
        byte[] bytes = new byte[10];
        FieldDefinition fieldDefinition = new FieldDefinition();
        MessageBuilder.Wrapper wrapper = messageBuilder.doUnpack(null, null, fieldDefinition, bytes, counter);

        Assertions.assertEquals("TEST", ClassUtil.getFieldValue(wrapper, "value"));
        Assertions.assertSame(fieldDefinition, ClassUtil.getFieldValue(wrapper, "fieldDefinition"));

        mockedDomainParsedFactory.close();
        mockedFieldBuilderFactory.close();
    }

    @Test
    @DisplayName("doPack() bytes.length=0 isNull=null")
    void test06() {
        FieldBuilder mockFieldBuilder = Mockito.mock(FieldBuilder.class);
        doReturn(new byte[0]).when(mockFieldBuilder).serialize(any(), any());

        MockedStatic<FieldBuilderFactory> mockedFieldBuilderFactory = mockStatic(FieldBuilderFactory.class);
        mockedFieldBuilderFactory.when(() -> FieldBuilderFactory.getInstance(any(), any(), any())).thenReturn(mockFieldBuilder);

        MessageDefinition definition = new MessageDefinition();
        UnfixedSubdomainMessageBuilder messageBuilder = new UnfixedSubdomainMessageBuilder(definition);

        FieldDefinition fieldDefinition = new FieldDefinition();
        MessageBuilder.Wrapper wrapper = messageBuilder.doPack(null, null, fieldDefinition);

        Assertions.assertArrayEquals(new byte[0], (byte[]) ClassUtil.getFieldValue(wrapper, "value"));
        Assertions.assertSame(fieldDefinition, ClassUtil.getFieldValue(wrapper, "fieldDefinition"));

        mockedFieldBuilderFactory.close();
    }

    @Test
    @DisplayName("doPack() bytes.length=0 isNull=true")
    void test07() {
        FieldBuilder mockFieldBuilder = Mockito.mock(FieldBuilder.class);
        doReturn(new byte[0]).when(mockFieldBuilder).serialize(any(), any());

        MockedStatic<FieldBuilderFactory> mockedFieldBuilderFactory = mockStatic(FieldBuilderFactory.class);
        mockedFieldBuilderFactory.when(() -> FieldBuilderFactory.getInstance(any(), any(), any())).thenReturn(mockFieldBuilder);

        MessageDefinition definition = new MessageDefinition();
        UnfixedSubdomainMessageBuilder messageBuilder = new UnfixedSubdomainMessageBuilder(definition);

        ClassUtil.setFieldValue(messageBuilder, "isNull", Boolean.TRUE);

        FieldDefinition fieldDefinition = new FieldDefinition();
        MessageBuilder.Wrapper wrapper = messageBuilder.doPack(null, null, fieldDefinition);

        Assertions.assertArrayEquals(new byte[0], (byte[]) ClassUtil.getFieldValue(wrapper, "value"));
        Assertions.assertSame(fieldDefinition, ClassUtil.getFieldValue(wrapper, "fieldDefinition"));

        mockedFieldBuilderFactory.close();
    }

    @Test
    @DisplayName("doPack() bytes.length!=0")
    void test08() {
        FieldBuilder mockFieldBuilder = Mockito.mock(FieldBuilder.class);
        doReturn("1".getBytes()).when(mockFieldBuilder).serialize(any(), any());

        MockedStatic<FieldBuilderFactory> mockedFieldBuilderFactory = mockStatic(FieldBuilderFactory.class);
        mockedFieldBuilderFactory.when(() -> FieldBuilderFactory.getInstance(any(), any(), any())).thenReturn(mockFieldBuilder);

        DomainParsed mockDomainParsed = Mockito.mock(DomainParsed.class);
        doReturn("2".getBytes()).when(mockDomainParsed).pack(any(), any());

        MockedStatic<DomainParsedFactory> mockedDomainParsedFactory = mockStatic(DomainParsedFactory.class);
        mockedDomainParsedFactory.when(() -> DomainParsedFactory.getInstance(any(), any(), any())).thenReturn(mockDomainParsed);

        MessageDefinition definition = new MessageDefinition();
        UnfixedSubdomainMessageBuilder messageBuilder = new UnfixedSubdomainMessageBuilder(definition);

        FieldDefinition fieldDefinition = new FieldDefinition();
        MessageBuilder.Wrapper wrapper = messageBuilder.doPack(null, null, fieldDefinition);

        Assertions.assertArrayEquals("2".getBytes(), (byte[]) ClassUtil.getFieldValue(wrapper, "value"));
        Assertions.assertSame(fieldDefinition, ClassUtil.getFieldValue(wrapper, "fieldDefinition"));

        mockedFieldBuilderFactory.close();
        mockedDomainParsedFactory.close();
    }
}
