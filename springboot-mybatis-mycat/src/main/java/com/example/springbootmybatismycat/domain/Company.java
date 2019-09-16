package com.example.springbootmybatismycat.domain;

import java.io.Serializable;

/**
 * descripiton:
 *
 * @author: kinson(2219945910 @ qq.com)
 * @date: 2019/1/31
 * @time: 21:42
 * @modifier:
 * @since:
 */
public class Company implements Serializable {

    private static final long serialVersionUID = 5088697673359856350L;

    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
