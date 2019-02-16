package com.example.springbootshiro.domain.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ResourceConditionVO extends BaseConditionVO {
    private String type;
}

