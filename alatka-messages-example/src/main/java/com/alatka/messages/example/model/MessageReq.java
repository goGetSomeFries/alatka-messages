package com.alatka.messages.example.model;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotEmpty;

@Schema(description = "报文解包实体")
public class MessageReq {

    @Schema(description = "报文hex字符串", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "key不能为空")
    private String key;

    @Schema(description = "报文hex字符串", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "message不能为空")
    private String message;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
