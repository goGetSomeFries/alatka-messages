package com.alatka.messages.core;

import com.alatka.messages.core.definition.IsoAnnotationMessageDefinitionBuilder;
import com.alatka.messages.core.holder.MessageHolder;
import com.alatka.messages.core.message.MessageBuilder;
import com.alatka.messages.core.model.iso.cups.b0810.Cups0810;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("cups0810报文")
public class MessageCups0810AnnotationTest {

    @BeforeAll
    public static void beforeAll() {
        new IsoAnnotationMessageDefinitionBuilder("com.alatka.messages.core.model.iso.cups.b0810").build();
    }

    @Test
    public void test01() {
        String hex = "0810003800010AC0001400348506370904010803200000313231323132313231323132303030353631303033333330393139313034393030303030340011000001860030004024EDB7BEEC305B69648E03B4BC3A5A86A895281C37BB7BC79C51BA740000000000000000719C22F9";
        String key = "iso:0001:0810:payload";
        Cups0810 holder = MessageBuilder.init(key).unpack(hex);
        System.out.println(MessageHolder.fromPojo(holder));
    }
}
