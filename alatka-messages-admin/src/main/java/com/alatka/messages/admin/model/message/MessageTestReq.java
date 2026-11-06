package com.alatka.messages.admin.model.message;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Schema(description = "报文本地测试请求实体")
public class MessageTestReq {

    @Schema(description = "主键", required = true)
    @NotNull(message = "id 不能为空")
    private Long id;

    @Schema(description = "打包解包标识", required = true)
    @NotNull(message = "packed 不能为空")
    private Boolean packed;

    @Schema(description = "报文", required = true)
    @NotEmpty(message = "content 不能为空")
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getPacked() {
        return packed;
    }

    public void setPacked(Boolean packed) {
        this.packed = packed;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
