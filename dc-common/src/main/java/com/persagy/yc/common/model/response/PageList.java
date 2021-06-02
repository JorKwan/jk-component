package com.persagy.yc.common.model.response;

import lombok.Data;

import java.util.List;

/**
 * 分页列表
 * @author Charlie Yu
 * @data 2021-03-25
 */
@Data
public class PageList<T> {

    private List<T> records;
    private long total;

    public PageList(final List<T> records, final long total) {
        this.records = records;
        this.total = total;
    }
}
