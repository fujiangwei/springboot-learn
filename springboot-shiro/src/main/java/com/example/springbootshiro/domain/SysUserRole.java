package com.example.springbootshiro.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class SysUserRole implements Serializable {

    private static final long serialVersionUID = 5088697673359856350L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date createTime;
    private Date updateTime;
    private Long userId;
    private Long roleId;
}
