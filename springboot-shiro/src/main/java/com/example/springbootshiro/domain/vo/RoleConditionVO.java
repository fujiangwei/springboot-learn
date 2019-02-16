package com.example.springbootshiro.domain.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class RoleConditionVO extends BaseConditionVO {
    private Role role;
}

