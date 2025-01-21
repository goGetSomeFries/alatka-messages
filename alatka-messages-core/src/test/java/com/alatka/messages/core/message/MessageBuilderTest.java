package com.alatka.messages.core.message;

import com.alatka.messages.core.context.FieldDefinition;
import com.alatka.messages.core.context.IsoFieldDefinition;
import com.alatka.messages.core.context.MessageDefinition;
import com.alatka.messages.core.context.MessageDefinitionContext;
import com.alatka.messages.core.domain.DomainParsed;
import com.alatka.messages.core.domain.DomainParsedFactory;
import com.alatka.messages.core.field.FieldBuilder;
import com.alatka.messages.core.field.FieldBuilderFactory;
import com.alatka.messages.core.holder.MessageHolder;
import com.alatka.messages.core.util.BytesUtil;
import com.alatka.messages.core.util.ClassUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MessageBuilderTest {

    @Spy
    private MessageBuilder messageBuilder;

    @Test
    @DisplayName("concatBytes()")
    void test01() {
        byte[] bytes1 = "1".getBytes();
        byte[] bytes2 = "2".getBytes();
        byte[] bytes = messageBuilder.concatBytes(bytes1, bytes2);
        Assertions.assertArrayEquals("12".getBytes(), bytes);
    }

    @Test
    @DisplayName("filter()")
    void test02() {
        Assertions.assertTrue(messageBuilder.filter(null));
    }

    @Test
    @DisplayName("postProcess(FieldDefinition, Object, Object, boolean)")
    void test03() {
        // TODO
        messageBuilder.postProcess(null, null, false);
    }

    @Test
    @DisplayName("postProcess()")
    void test04() {
        // TODO
        messageBuilder.postProcess();
    }

    @Test
    @DisplayName("buildFieldDefinitions()")
    void test05() {
        List<IsoFieldDefinition> list = IntStream.range(0, 5)
                .mapToObj(i -> {
                    IsoFieldDefinition definition = new IsoFieldDefinition();
                    definition.setDomainNo(i);
                    definition.setName("test" + i);
                    definition.setAliasName("aliasName" + i);
                    return definition;
                }).collect(Collectors.toList());


        MessageDefinitionContext mock = Mockito.mock(MessageDefinitionContext.class);
        doReturn(list).when(mock).fieldDefinitions(any());

        MockedStatic<MessageDefinitionContext> mockedStatic = mockStatic(MessageDefinitionContext.class);
        mockedStatic.when(MessageDefinitionContext::getInstance).thenReturn(mock);

        List<FieldDefinition> result = messageBuilder.buildFieldDefinitions();
        Assertions.assertIterableEquals(list, result);

        mockedStatic.close();
    }

    @Test
    @DisplayName("buildFieldDefinitions(byte[])")
    void test06() {
        List<IsoFieldDefinition> list = IntStream.range(0, 5)
                .mapToObj(i -> {
                    IsoFieldDefinition definition = new IsoFieldDefinition();
                    definition.setDomainNo(i);
                    definition.setName("test" + i);
                    definition.setAliasName("aliasName" + i);
                    return definition;
                }).collect(Collectors.toList());


        MessageDefinitionContext mock = Mockito.mock(MessageDefinitionContext.class);
        doReturn(list).when(mock).fieldDefinitions(any());

        MockedStatic<MessageDefinitionContext> mockedStatic = mockStatic(MessageDefinitionContext.class);
        mockedStatic.when(MessageDefinitionContext::getInstance).thenReturn(mock);

        List<FieldDefinition> result = messageBuilder.buildFieldDefinitions(null);
        Assertions.assertIterableEquals(list, result);

        mockedStatic.close();
    }

    @Test
    @DisplayName("doUnpack()")
    void test07() {
        DomainParsed mockDomainParsed = Mockito.mock(DomainParsed.class);
        doReturn(new byte[0]).when(mockDomainParsed).unpack(any(), any(), any());

        MockedStatic<DomainParsedFactory> mockedDomainParsedFactory = mockStatic(DomainParsedFactory.class);
        mockedDomainParsedFactory.when(() -> DomainParsedFactory.getInstance(any(), any(), any())).thenReturn(mockDomainParsed);

        FieldBuilder mockFieldBuilder = Mockito.mock(FieldBuilder.class);
        doReturn("TEST").when(mockFieldBuilder).deserialize(any(), any(), any(), any());

        MockedStatic<FieldBuilderFactory> mockedFieldBuilderFactory = mockStatic(FieldBuilderFactory.class);
        mockedFieldBuilderFactory.when(() -> FieldBuilderFactory.getInstance(any(), any(), any())).thenReturn(mockFieldBuilder);

        FieldDefinition fieldDefinition = new FieldDefinition();
        MessageBuilder.Wrapper wrapper = messageBuilder.doUnpack(null, null, fieldDefinition, null, null);

        Assertions.assertEquals("TEST", ClassUtil.getFieldValue(wrapper, "value"));
        Assertions.assertSame(fieldDefinition, ClassUtil.getFieldValue(wrapper, "fieldDefinition"));

        mockedDomainParsedFactory.close();
        mockedFieldBuilderFactory.close();
    }

    @Test
    @DisplayName("unpack(byte[]) MessageHolder")
    void test08() {
        List<IsoFieldDefinition> list = IntStream.range(0, 5)
                .mapToObj(i -> {
                    IsoFieldDefinition definition = new IsoFieldDefinition();
                    definition.setDomainNo(i);
                    definition.setName("test" + i);
                    definition.setAliasName("aliasName" + i);
                    return definition;
                }).collect(Collectors.toList());

        doReturn(list).when(messageBuilder).buildFieldDefinitions(any());
        doReturn(messageBuilder.new Wrapper("1", null)).when(messageBuilder).doUnpack(any(), any(), any(), any(), any());

        MockedStatic<MessageHolder> mockedStatic = mockStatic(MessageHolder.class);
        MessageHolder messageHolder = mock(MessageHolder.class);
        mockedStatic.when(() -> MessageHolder.newInstance(any())).thenReturn(messageHolder);

        MessageDefinition definition = mock(MessageDefinition.class);
        doReturn("1").when(definition).identity();
        doReturn(MessageHolder.class).when(definition).getHolder();

        messageBuilder.definition = definition;
        Object unpack = messageBuilder.unpack(new byte[0]);
        Assertions.assertSame(unpack, messageHolder);

        mockedStatic.close();
    }

    @Test
    @DisplayName("unpack(byte[]) POJO")
    void test09() {
        List<IsoFieldDefinition> list = IntStream.range(0, 5)
                .mapToObj(i -> {
                    IsoFieldDefinition definition = new IsoFieldDefinition();
                    definition.setDomainNo(i);
                    definition.setName("test" + i);
                    definition.setAliasName("aliasName" + i);
                    return definition;
                }).collect(Collectors.toList());

        doReturn(list).when(messageBuilder).buildFieldDefinitions(any());
        doReturn(messageBuilder.new Wrapper("1", null)).when(messageBuilder).doUnpack(any(), any(), any(), any(), any());

        MockedStatic<ClassUtil> mockedStatic = mockStatic(ClassUtil.class);
        Object instance = new Object();
        mockedStatic.when(() -> ClassUtil.newInstance(any())).thenReturn(instance);

        MessageDefinition definition = mock(MessageDefinition.class);
        doReturn(Object.class).when(definition).getHolder();

        messageBuilder.definition = definition;
        Object unpack = messageBuilder.unpack(new byte[0]);
        Assertions.assertSame(unpack, instance);

        mockedStatic.close();
    }

    @Test
    @DisplayName("unpack(byte[]) exception")
    void test10() {
        List<IsoFieldDefinition> list = IntStream.range(0, 5)
                .mapToObj(i -> {
                    IsoFieldDefinition definition = new IsoFieldDefinition();
                    definition.setDomainNo(i);
                    definition.setName("test" + i);
                    definition.setAliasName("aliasName" + i);
                    return definition;
                }).collect(Collectors.toList());

        doReturn(list).when(messageBuilder).buildFieldDefinitions(any());
        doThrow(IllegalArgumentException.class).when(messageBuilder).doUnpack(any(), any(), any(), any(), any());

        MockedStatic<ClassUtil> mockedStatic = mockStatic(ClassUtil.class);
        Object instance = new Object();
        mockedStatic.when(() -> ClassUtil.newInstance(any())).thenReturn(instance);

        MessageDefinition definition = mock(MessageDefinition.class);
        doReturn(Object.class).when(definition).getHolder();

        messageBuilder.definition = definition;
        Assertions.assertThrows(RuntimeException.class, () -> messageBuilder.unpack(new byte[0]));

        mockedStatic.close();
    }

    @Test
    @DisplayName("unpack(String)")
    void test11() {
        Object instance = new Object();
        String hex = "12";
        doReturn(instance).when(messageBuilder).unpack(BytesUtil.hexToBytes(hex));
        Object unpack = messageBuilder.unpack(hex);

        Assertions.assertSame(instance, unpack);
    }

    @Test
    @DisplayName("doPack()")
    void test12() {
        FieldBuilder mockFieldBuilder = Mockito.mock(FieldBuilder.class);
        doReturn(new byte[0]).when(mockFieldBuilder).serialize(any(), any());

        MockedStatic<FieldBuilderFactory> mockedFieldBuilderFactory = mockStatic(FieldBuilderFactory.class);
        mockedFieldBuilderFactory.when(() -> FieldBuilderFactory.getInstance(any(), any(), any())).thenReturn(mockFieldBuilder);

        DomainParsed mockDomainParsed = Mockito.mock(DomainParsed.class);
        doReturn("TEST".getBytes()).when(mockDomainParsed).pack(any(), any());

        MockedStatic<DomainParsedFactory> mockedDomainParsedFactory = mockStatic(DomainParsedFactory.class);
        mockedDomainParsedFactory.when(() -> DomainParsedFactory.getInstance(any(), any(), any())).thenReturn(mockDomainParsed);

        FieldDefinition fieldDefinition = new FieldDefinition();
        MessageBuilder.Wrapper wrapper = messageBuilder.doPack(null, null, fieldDefinition);

        Assertions.assertArrayEquals("TEST".getBytes(), (byte[]) ClassUtil.getFieldValue(wrapper, "value"));
        Assertions.assertSame(fieldDefinition, ClassUtil.getFieldValue(wrapper, "fieldDefinition"));

        mockedDomainParsedFactory.close();
        mockedFieldBuilderFactory.close();
    }

    @Test
    @DisplayName("pack() exception")
    void test13() {
        List<IsoFieldDefinition> list = IntStream.range(0, 5)
                .mapToObj(i -> {
                    IsoFieldDefinition definition = new IsoFieldDefinition();
                    definition.setDomainNo(i);
                    definition.setName("test" + i);
                    definition.setAliasName("aliasName" + i);
                    return definition;
                }).collect(Collectors.toList());

        doReturn(list).when(messageBuilder).buildFieldDefinitions();
        doThrow(IllegalArgumentException.class).when(messageBuilder).doPack(any(), any(), any());

        Assertions.assertThrows(RuntimeException.class, () -> messageBuilder.pack(null));
    }

    @Test
    @DisplayName("pack()")
    void test14() {
        List<IsoFieldDefinition> list = IntStream.range(0, 5)
                .mapToObj(i -> {
                    IsoFieldDefinition definition = new IsoFieldDefinition();
                    definition.setDomainNo(i);
                    definition.setName("test" + i);
                    definition.setAliasName("aliasName" + i);
                    return definition;
                }).collect(Collectors.toList());

        doReturn(list).when(messageBuilder).buildFieldDefinitions();
        doReturn(messageBuilder.new Wrapper("123".getBytes(), null)).when(messageBuilder).doPack(any(), any(), any());

        Assertions.assertArrayEquals("123123123123123".getBytes(), messageBuilder.pack(null));
    }

    @Test
    @DisplayName("init(MessageDefinition)")
    void test15() {
        MessageDefinition definition = new MessageDefinition();
        definition.setKind(MessageDefinition.Kind.payload);
        definition.setType(MessageDefinition.Type.iso);
        Assertions.assertInstanceOf(IsoMessageBuilder.class, MessageBuilder.init(definition));

        definition.setKind(MessageDefinition.Kind.subPayload);
        Assertions.assertInstanceOf(DefaultMessageBuilder.class, MessageBuilder.init(definition));

        definition.setDomainType(MessageDefinition.DomainType.TLV);
        Assertions.assertInstanceOf(TLVSubdomainMessageBuilder.class, MessageBuilder.init(definition));

        definition.setDomainType(MessageDefinition.DomainType.TLV2);
        Assertions.assertInstanceOf(TLV2SubdomainMessageBuilder.class, MessageBuilder.init(definition));

        definition.setDomainType(MessageDefinition.DomainType.TV);
        Assertions.assertInstanceOf(TVSubdomainMessageBuilder.class, MessageBuilder.init(definition));

        definition.setDomainType(MessageDefinition.DomainType.BITMAP);
        Assertions.assertInstanceOf(BitmapSubdomainMessageBuilder.class, MessageBuilder.init(definition));
    }

    @Test
    @DisplayName("init(String) exception")
    void test16() {
        MessageDefinitionContext mock = Mockito.mock(MessageDefinitionContext.class);
        doReturn(null).when(mock).messageDefinition(anyString());

        MockedStatic<MessageDefinitionContext> mockedStatic = mockStatic(MessageDefinitionContext.class);
        mockedStatic.when(MessageDefinitionContext::getInstance).thenReturn(mock);

        Assertions.assertThrows(IllegalArgumentException.class, () -> MessageBuilder.init(""));

        mockedStatic.close();
    }

    @Disabled
    @Test
    @DisplayName("init(String)")
    void test17() {
        MessageDefinition definition = new MessageDefinition();

        MessageDefinitionContext messageDefinitionContext = Mockito.mock(MessageDefinitionContext.class);
        doReturn(definition).when(messageDefinitionContext).messageDefinition(anyString());

        MockedStatic<MessageDefinitionContext> mockedStatic1 = mockStatic(MessageDefinitionContext.class);
        mockedStatic1.when(MessageDefinitionContext::getInstance).thenReturn(messageDefinitionContext);

        MessageBuilder messageBuilder = mock(MessageBuilder.class);
        MockedStatic<MessageBuilder> mockedStatic2 = mockStatic(MessageBuilder.class);
        mockedStatic2.when(() -> MessageBuilder.init(any(MessageDefinition.class))).thenReturn(messageBuilder);

        // TODO
        Assertions.assertSame(messageBuilder, MessageBuilder.init("1"));

        mockedStatic1.close();
        mockedStatic2.close();
    }

    @Disabled
    @Test
    @DisplayName("init(Class<>)")
    void test18() {

    }
}
