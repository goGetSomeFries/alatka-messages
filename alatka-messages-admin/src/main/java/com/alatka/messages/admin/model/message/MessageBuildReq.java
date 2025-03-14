package com.alatka.messages.admin.model.message;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Schema(description = "报文部署请求实体")
public class MessageBuildReq {

    @Schema(description = "uri集合", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty
    private List<String> uris;

    @Schema(description = "路径", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty
    private String path;

    public List<String> getUris() {
        return uris;
    }

    public void setUris(List<String> uris) {
        this.uris = uris;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
