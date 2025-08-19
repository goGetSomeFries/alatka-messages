package com.alatka.messages.admin.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


@Schema(description = "分页参数")
public class PageReqMessage {

    @Schema(description = "当前页数", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "pageNo不能为空")
    @Min(value = 1)
    private Integer pageNo;

    @Schema(description = "每页记录数", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "pageSize不能为空")
    @Min(value = 1)
    private Integer pageSize;

    @Schema(description = "正序/倒序")
    @Pattern(regexp = "(asc|desc)", message = "[direction] must be 'asc|desc'")
    private String direction;

    @Schema(description = "排序字段", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "orderBy不能为空")
    private String orderBy;

    public Pageable build() {
        return PageRequest.of(pageNo - 1, pageSize,
                Sort.Direction.fromOptionalString(this.direction).orElse(Sort.Direction.DESC),
                orderBy);
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
