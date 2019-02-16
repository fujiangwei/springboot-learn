package com.example.springbootshiro.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class SysResources implements Serializable {

    private static final long serialVersionUID = 5088697673359856350L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;
    private String url;
    private String permission;
    private Long parentId;
    private Integer sort;
    private Boolean external;
    private Boolean available;
    private String icon;
    private Date createTime;
    private Date updateTime;

    @Transient
    private String checked;
    @Transient
    private SysResources parent;
    @Transient
    private List<SysResources> nodes;
}
