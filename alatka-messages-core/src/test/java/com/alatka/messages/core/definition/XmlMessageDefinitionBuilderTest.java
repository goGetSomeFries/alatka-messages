package com.alatka.messages.core.definition;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class XmlMessageDefinitionBuilderTest {

    @Test
    @DisplayName("fileSuffix()")
    void test01() {
        FixedXmlMessageDefinitionBuilder definitionBuilder = new FixedXmlMessageDefinitionBuilder();
        Assertions.assertEquals(".xml", definitionBuilder.fileSuffix());
    }

    @Disabled
    @Test
    @DisplayName("()")
    void test02() {
        // TODO

    }
}
