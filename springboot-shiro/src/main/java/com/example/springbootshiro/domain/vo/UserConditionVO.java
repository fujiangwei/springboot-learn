package com.example.springbootshiro.domain.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserConditionVO extends BaseConditionVO {
    private User user;
}
