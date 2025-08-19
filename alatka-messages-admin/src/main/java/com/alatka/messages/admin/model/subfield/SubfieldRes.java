package com.alatka.messages.admin.model.subfield;

import com.alatka.messages.admin.model.field.FieldRes;
import com.alatka.messages.admin.model.message.MessageRes;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Schema(description = "报文子域响应实体")
public class SubfieldRes {

    @Valid
    @Schema(description = "子域集合", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "subfields 不能为空")
    private List<FieldRes> subfields;

    @Valid
    @Schema(description = "子域报文", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "subMessage 不能为空")
    private MessageRes subMessage;

    public List<FieldRes> getSubfields() {
        return subfields;
    }

    public void setSubfields(List<FieldRes> subfields) {
        this.subfields = subfields;
    }

    public MessageRes getSubMessage() {
        return subMessage;
    }

    public void setSubMessage(MessageRes subMessage) {
        this.subMessage = subMessage;
    }
}
