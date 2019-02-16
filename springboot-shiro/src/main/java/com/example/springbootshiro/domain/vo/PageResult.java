package com.example.springbootshiro.domain.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class PageResult {
    private Long total;
    private List rows;

    public PageResult(Long total, List rows) {
        this.total = total;
        this.rows = rows;
    }

    public PageResult() {
    }
}
