package com.alatka.messages.admin.model.field;

import com.alatka.messages.admin.model.PageReqMessage;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "报文域分页请求实体")
public class FieldDefinitionPageReq extends PageReqMessage {

    @Schema(description = "报文id")
    private Long messageId;

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }
}
