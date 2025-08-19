package com.alatka.messages.admin.model.field;

import com.alatka.messages.admin.model.PageReqMessage;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "报文域分页请求实体")
public class FieldPageReq extends PageReqMessage {

    @Schema(description = "报文id", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "messageId 不能为空")
    private Long messageId;

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }
}
