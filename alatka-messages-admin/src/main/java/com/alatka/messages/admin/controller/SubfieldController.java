package com.alatka.messages.admin.controller;

import com.alatka.messages.admin.entity.FieldDefinition;
import com.alatka.messages.admin.entity.MessageDefinition;
import com.alatka.messages.admin.model.ResMessage;
import com.alatka.messages.admin.model.field.FieldRes;
import com.alatka.messages.admin.model.message.MessageRes;
import com.alatka.messages.admin.model.subfield.SubfieldReq;
import com.alatka.messages.admin.service.SubfieldService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Tag(name = "报文子域")
@RestController
@RequestMapping("/subfield")
public class SubfieldController {

    private SubfieldService subfieldService;

    @Operation(summary = "创建报文子域")
    @PostMapping("/create")
    public ResMessage<Void> create(@Valid @RequestBody SubfieldReq req) {
        MessageDefinition subMessage = new MessageDefinition();
        BeanUtils.copyProperties(req.getSubMessage(), subMessage);
        List<FieldDefinition> subfields = req.getSubfields().stream().map(element -> {
            FieldDefinition subfield = new FieldDefinition();
            BeanUtils.copyProperties(element, subfield);
            return subfield;
        }).collect(Collectors.toList());
        BeanUtils.copyProperties(req, subMessage);
        return ResMessage.success(() -> subfieldService.create(subMessage, subfields));
    }

    @Operation(summary = "查询报文子域")
    @GetMapping("/list")
    // TODO
    public ResMessage<Map<MessageRes, List<FieldRes>>> queryList(@RequestParam Long fieldId) {
        Map<MessageRes, List<FieldRes>> result = subfieldService.queryByFieldId(fieldId).entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> {
                            MessageRes res = new MessageRes();
                            BeanUtils.copyProperties(entry.getKey(), res);
                            return res;
                        },
                        entry -> entry.getValue().stream().map(e -> {
                            FieldRes res = new FieldRes();
                            BeanUtils.copyProperties(e, res);
                            return res;
                        }).collect(Collectors.toList())));

        return ResMessage.success(result);
    }

    @Autowired
    public void setSubfieldService(SubfieldService subfieldService) {
        this.subfieldService = subfieldService;
    }

}
