package com.alatka.messages.admin.model.subfield;

import com.alatka.messages.admin.model.field.FieldReq;
import com.alatka.messages.admin.model.message.MessageReq;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Schema(description = "报文子域请求实体")
public class SubfieldReq {

    @Valid
    @Schema(description = "子域集合", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "subfields 不能为空")
    private List<FieldReq> subfields;

    @Valid
    @Schema(description = "子域报文", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "subMessage 不能为空")
    private MessageReq subMessage;

    public List<FieldReq> getSubfields() {
        return subfields;
    }

    public void setSubfields(List<FieldReq> subfields) {
        this.subfields = subfields;
    }

    public MessageReq getSubMessage() {
        return subMessage;
    }

    public void setSubMessage(MessageReq subMessage) {
        this.subMessage = subMessage;
    }
}
