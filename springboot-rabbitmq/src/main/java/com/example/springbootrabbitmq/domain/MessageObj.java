package com.example.springbootrabbitmq.domain;

import java.io.Serializable;

/**
 * descripiton:
 *
 * @author: kinson(2219945910 @ qq.com)
 * @date: 2019/3/26
 * @time: 12:12
 * @modifier:
 * @since:
 */
public class MessageObj implements Serializable {

    private static final long serialVersionUID = 5088697673359856350L;

    private Integer id;

    private String name;

    private boolean isAck;

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

    public boolean isAck() {
        return isAck;
    }

    public void setAck(boolean ack) {
        isAck = ack;
    }

    @Override
    public String toString() {
        return String.format("id=%d,name=%s", id, name);
    }
}
