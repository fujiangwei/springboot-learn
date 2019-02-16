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
public class SysUser implements Serializable {

    private static final long serialVersionUID = 5088697673359856350L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date createTime;
    private Date updateTime;
    private String username;
    private String password;
    private String nickname;
    private String mobile;
    private String email;
    private String qq;
    private Date birthday;
    private Integer gender;
    private String avatar;
    private String userType;
    private String regIp;
    private String lastLoginIp;
    private Date lastLoginTime;
    private Integer loginCount;
    private String remark;
    private Integer status;
}
