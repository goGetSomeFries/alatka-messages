package com.alatka.messages.admin.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "响应报文")
public class ResMessage<T> {

    @Schema(description = "响应码", requiredMode = Schema.RequiredMode.REQUIRED)
    private String code;

    @Schema(description = "响应数据")
    private T data;

    @Schema(description = "错误信息")
    private String msg;

    public ResMessage() {
    }

    protected ResMessage(String code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public static ResMessage<Void> success(Executor executor) {
        executor.execute();
        return new ResMessage<>("0000", null, "success");
    }

    public static <T> ResMessage<T> success(T data) {
        return new ResMessage<>("0000", data, "success");
    }

    public static <T> ResMessage<T> error(String msg) {
        return new ResMessage<>("9999", null, msg);
    }

    public interface Executor {
        void execute();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
