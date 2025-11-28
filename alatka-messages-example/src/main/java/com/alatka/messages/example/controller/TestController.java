package com.alatka.messages.example.controller;

import com.alatka.messages.admin.model.ResMessage;
import com.alatka.messages.core.definition.MessageDefinitionBuilder;
import com.alatka.messages.core.holder.MessageHolder;
import com.alatka.messages.core.message.MessageBuilder;
import com.alatka.messages.example.model.MessageReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "测试")
@RestController
@RequestMapping("/")
public class TestController {

    private MessageDefinitionBuilder messageDefinitionBuilder;


    @Operation(summary = "执行解包")
    @PostMapping("/message/unpack")
    public ResMessage<MessageHolder> unpack(@Valid @RequestBody MessageReq param) {
        MessageHolder messageHolder = MessageBuilder.init(param.getKey()).unpack(param.getMessage());
        return ResMessage.success(messageHolder);
    }

    @Operation(summary = "执行构建")
    @PostMapping("/message/deploy")
    public String execute() {
        messageDefinitionBuilder.refresh();
        return "ok";
    }

    @Autowired
    public void setMessageDefinitionBuilder(MessageDefinitionBuilder messageDefinitionBuilder) {
        this.messageDefinitionBuilder = messageDefinitionBuilder;
    }
}
