package com.alatka.messages.admin.controller;

import com.alatka.messages.admin.entity.MessageDefinition;
import com.alatka.messages.admin.model.PageResMessage;
import com.alatka.messages.admin.model.ResMessage;
import com.alatka.messages.admin.model.message.MessageBuildReq;
import com.alatka.messages.admin.model.message.MessagePageReq;
import com.alatka.messages.admin.model.message.MessageReq;
import com.alatka.messages.admin.model.message.MessageRes;
import com.alatka.messages.admin.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Tag(name = "报文")
@RestController
@RequestMapping("/message")
public class MessageController {

    private MessageService messageService;

    @Operation(summary = "部署报文")
    @PostMapping("/build")
    public ResMessage<Map<String, String>> build(@Valid @RequestBody MessageBuildReq req) {
        return ResMessage.success(messageService.build(req.getUris(), req.getPath()));
    }

    @Operation(summary = "创建报文")
    @PostMapping("/create")
    public ResMessage<Long> create(@Valid @RequestBody MessageReq req) {
        MessageDefinition entity = new MessageDefinition();
        BeanUtils.copyProperties(req, entity);
        return ResMessage.success(messageService.create(entity));
    }

    @Operation(summary = "删除报文")
    @Parameter(name = "ids", description = "编号", required = true)
    @DeleteMapping("/delete")
    public ResMessage<Void> delete(@RequestParam String ids) {
        List<Long> list = Arrays.stream(ids.split(","))
                .map(Long::parseLong).collect(Collectors.toList());
        return ResMessage.success(() -> messageService.delete(list));
    }

    @Operation(summary = "修改报文")
    @PutMapping("/update")
    public ResMessage<Void> update(@Valid @RequestBody MessageReq req) {
        MessageDefinition entity = new MessageDefinition();
        BeanUtils.copyProperties(req, entity);
        return ResMessage.success(() -> messageService.update(entity));
    }

    @Operation(summary = "查询子域报文列表")
    @GetMapping("/listSub")
    public ResMessage<List<MessageRes>> listSub(@RequestParam Long fieldId) {
        List<MessageRes> list = messageService.queryByFieldId(fieldId).stream()
                .map(entity -> {
                    MessageRes res = new MessageRes();
                    BeanUtils.copyProperties(entity, res);
                    return res;
                }).collect(Collectors.toList());
        return ResMessage.success(list);
    }

    @Operation(summary = "分页查询报文")
    @GetMapping("/page")
    public PageResMessage<MessageRes> queryPage(@Valid MessagePageReq req) {
        MessageDefinition condition = new MessageDefinition();
        BeanUtils.copyProperties(req, condition);
        Page<MessageRes> page = messageService.queryPage(condition, req.build())
                .map(entity -> {
                    MessageRes res = new MessageRes();
                    BeanUtils.copyProperties(entity, res);
                    return res;
                });
        return PageResMessage.success(page);
    }

    @Autowired
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

}
