package com.alatka.messages.admin.controller;

import com.alatka.messages.admin.entity.MessageDefinition;
import com.alatka.messages.admin.model.PageResMessage;
import com.alatka.messages.admin.model.ResMessage;
import com.alatka.messages.admin.model.message.MessageDefinitionPageReq;
import com.alatka.messages.admin.model.message.MessageDefinitionReq;
import com.alatka.messages.admin.model.message.MessageDefinitionRes;
import com.alatka.messages.admin.service.MessageDefinitionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "报文")
@RestController
@RequestMapping("/message")
public class MessageDefinitionController {

    private MessageDefinitionService messageDefinitionService;

    @Operation(summary = "创建报文")
    @PostMapping("/create")
    public ResMessage<Long> create(@Valid @RequestBody MessageDefinitionReq req) {
        MessageDefinition entity = new MessageDefinition();
        BeanUtils.copyProperties(req, entity);
        return ResMessage.success(messageDefinitionService.create(entity));
    }

    @Operation(summary = "删除报文")
    @Parameter(name = "id", description = "编号", required = true)
    @DeleteMapping("/delete")
    public ResMessage<Void> delete(@RequestParam Long id) {
        return ResMessage.success(() -> messageDefinitionService.delete(id));
    }

    @Operation(summary = "修改规则组")
    @PutMapping("/update")
    public ResMessage<Void> update(@Valid @RequestBody MessageDefinitionReq req) {
        MessageDefinition entity = new MessageDefinition();
        BeanUtils.copyProperties(req, entity);
        return ResMessage.success(() -> messageDefinitionService.update(entity));
    }

    @Operation(summary = "分页查询报文")
    @GetMapping("/page")
    public PageResMessage<MessageDefinitionRes> queryPage(@Valid MessageDefinitionPageReq req) {
        MessageDefinition condition = new MessageDefinition();
        BeanUtils.copyProperties(req, condition);
        Page<MessageDefinitionRes> page = messageDefinitionService.queryPage(condition, req.build())
                .map(entity -> {
                    MessageDefinitionRes res = new MessageDefinitionRes();
                    BeanUtils.copyProperties(entity, res);
                    return res;
                });
        return PageResMessage.success(page);
    }

    @Autowired
    public void setMessageDefinitionService(MessageDefinitionService messageDefinitionService) {
        this.messageDefinitionService = messageDefinitionService;
    }

}
