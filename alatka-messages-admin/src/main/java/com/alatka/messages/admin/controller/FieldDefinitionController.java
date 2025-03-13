package com.alatka.messages.admin.controller;

import com.alatka.messages.admin.entity.FieldDefinition;
import com.alatka.messages.admin.model.PageResMessage;
import com.alatka.messages.admin.model.ResMessage;
import com.alatka.messages.admin.model.field.FieldDefinitionPageReq;
import com.alatka.messages.admin.model.field.FieldDefinitionRes;
import com.alatka.messages.admin.model.message.MessageDefinitionReq;
import com.alatka.messages.admin.service.FieldDefinitionService;
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
import java.util.stream.Collectors;

@Tag(name = "报文域")
@RestController
@RequestMapping("/field")
public class FieldDefinitionController {

    private FieldDefinitionService fieldDefinitionService;

    @Operation(summary = "创建报文域")
    @PostMapping("/create")
    public ResMessage<Void> create(@Valid @RequestBody MessageDefinitionReq req) {
        return ResMessage.success(() -> {
        });
    }

    @Operation(summary = "删除报文域")
    @Parameter(name = "ids", description = "编号", required = true)
    @DeleteMapping("/delete")
    public ResMessage<Void> delete(@RequestParam String ids) {
        List<Long> list = Arrays.stream(ids.split(","))
                .map(Long::parseLong).collect(Collectors.toList());
        return ResMessage.success(() -> fieldDefinitionService.delete(list));
    }

    @Operation(summary = "修改报文域")
    @PutMapping("/update")
    public ResMessage<Void> update(@Valid @RequestBody MessageDefinitionReq req) {
        return ResMessage.success(() -> {
        });
    }

    @Operation(summary = "查询报文子域")
    @GetMapping("/list")
    public ResMessage<List<FieldDefinitionRes>> queryList(@RequestParam Long messageId) {
        List<FieldDefinitionRes> list = fieldDefinitionService.queryByMessageId(messageId)
                .stream()
                .map(entity -> {
                    FieldDefinitionRes res = new FieldDefinitionRes();
                    BeanUtils.copyProperties(entity, res);
                    return res;
                })
                .collect(Collectors.toList());

        return ResMessage.success(list);
    }

    @Operation(summary = "分页查询报文域")
    @GetMapping("/page")
    public PageResMessage<FieldDefinitionRes> queryPage(@Valid FieldDefinitionPageReq req) {
        FieldDefinition condition = new FieldDefinition();
        BeanUtils.copyProperties(req, condition);
        Page<FieldDefinitionRes> page = fieldDefinitionService.queryPage(condition, req.build())
                .map(entity -> {
                    FieldDefinitionRes res = new FieldDefinitionRes();
                    BeanUtils.copyProperties(entity, res);
                    return res;
                });
        return PageResMessage.success(page);
    }

    @Autowired
    public void setFieldDefinitionService(FieldDefinitionService fieldDefinitionService) {
        this.fieldDefinitionService = fieldDefinitionService;
    }

}
