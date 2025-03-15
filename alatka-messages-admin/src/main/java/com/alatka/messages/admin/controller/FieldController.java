package com.alatka.messages.admin.controller;

import com.alatka.messages.admin.entity.FieldDefinition;
import com.alatka.messages.admin.model.PageResMessage;
import com.alatka.messages.admin.model.ResMessage;
import com.alatka.messages.admin.model.field.FieldPageReq;
import com.alatka.messages.admin.model.field.FieldReq;
import com.alatka.messages.admin.model.field.FieldRes;
import com.alatka.messages.admin.service.FieldService;
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
public class FieldController {

    private FieldService fieldService;

    @Operation(summary = "创建报文域")
    @PostMapping("/create")
    public ResMessage<Long> create(@Valid @RequestBody FieldReq req) {
        FieldDefinition condition = new FieldDefinition();
        BeanUtils.copyProperties(req, condition);
        return ResMessage.success(fieldService.create(condition));
    }

    @Operation(summary = "删除报文域")
    @Parameter(name = "ids", description = "编号", required = true)
    @DeleteMapping("/delete")
    public ResMessage<Void> delete(@RequestParam String ids) {
        List<Long> list = Arrays.stream(ids.split(","))
                .map(Long::parseLong).collect(Collectors.toList());
        return ResMessage.success(() -> fieldService.delete(list));
    }

    @Operation(summary = "修改报文域")
    @PutMapping("/update")
    public ResMessage<Void> update(@Valid @RequestBody FieldReq req) {
        FieldDefinition condition = new FieldDefinition();
        BeanUtils.copyProperties(req, condition);
        return ResMessage.success(() -> fieldService.update(condition));
    }

    @Operation(summary = "分页查询报文域")
    @GetMapping("/page")
    public PageResMessage<FieldRes> queryPage(@Valid FieldPageReq req) {
        FieldDefinition condition = new FieldDefinition();
        BeanUtils.copyProperties(req, condition);
        Page<FieldRes> page = fieldService.queryPage(condition, req.build())
                .map(entity -> {
                    FieldRes res = new FieldRes();
                    BeanUtils.copyProperties(entity, res);
                    return res;
                });
        return PageResMessage.success(page);
    }

    @Autowired
    public void setFieldService(FieldService fieldService) {
        this.fieldService = fieldService;
    }

}
