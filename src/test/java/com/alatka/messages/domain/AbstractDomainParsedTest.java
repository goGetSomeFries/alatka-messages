package com.alatka.messages.domain;

import com.alatka.messages.context.FieldDefinition;
import com.alatka.messages.util.ClassUtil;
import org.junit.jupiter.api.Test;

class AbstractDomainParsedTest {

    @Test
    void padding() {
        byte[] bytes = ClassUtil.invoke(AbstractDomainParsed.class,
                "padding",
                new Class[]{byte[].class, int.class, FieldDefinition.FieldType.class, FieldDefinition.class},
                new Object[]{});
    }


}