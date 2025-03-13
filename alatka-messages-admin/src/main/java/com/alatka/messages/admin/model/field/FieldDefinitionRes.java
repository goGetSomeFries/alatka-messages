package com.alatka.messages.admin.model.field;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "报文域响应实体")
public class FieldDefinitionRes {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

}
