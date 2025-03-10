package com.alatka.messages.admin.model;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.domain.Page;

import java.util.List;

public class PageResMessage<T> extends ResMessage<List<T>> {

    @Schema(description = "总页数", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer totalPages;

    @Schema(description = "总记录数", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long totalRecords;

    @Schema(description = "每页记录数", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer pageSize;

    public PageResMessage(List<T> list, Integer totalPages, Long totalRecords, Integer pageSize) {
        super("0000", list, "success");
        this.totalPages = totalPages;
        this.totalRecords = totalRecords;
        this.pageSize = pageSize;
    }

    public static <T> PageResMessage<T> success(Page<T> page) {
        return new PageResMessage<>(page.getContent(), page.getTotalPages(), page.getTotalElements(), page.getSize());
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(Long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
