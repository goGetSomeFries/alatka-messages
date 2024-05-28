package com.alatka.messages.message;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.context.IsoFieldDefinition;
import com.alatka.messages.context.MessageDefinitionContext;
import org.junit.jupiter.api.Assertions;
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
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
public class RebuildSubdomainMessageBuilderTest {

    @Spy
    private RebuildSubdomainMessageBuilder messageBuilder;

    @Test
    @DisplayName("buildFieldDefinitions")
    void test01() {
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

        List<String> tags = list.stream()
                .filter(d -> d.getDomainNo() % 2 == 0)
                .map(IsoFieldDefinition::getAliasName)
                .collect(Collectors.toList());
        doReturn(tags).when(messageBuilder).doBuildFieldDefinitions(any(), any());

        List<FieldDefinition> result = messageBuilder.buildFieldDefinitions(null);
        Assertions.assertIterableEquals(list.stream().filter(d -> d.getDomainNo() % 2 == 0).collect(Collectors.toList()), result);

        mockedStatic.close();
    }

}
