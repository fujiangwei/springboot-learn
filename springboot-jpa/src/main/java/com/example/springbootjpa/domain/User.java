package com.example.springbootjpa.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * descripiton:
 *
 * @author: kinson(2219945910@qq.com)
 * @date: 2019/2/17
 * @time: 14:02
 * @modifier:
 * @since:
 */
@Entity
@Data
@Table(name = "user")
public class User {

    @Id
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "user_name")
    private String username;

    @Override
    public String toString() {
        return String.format(
                "User[userId=%d, username='%s']",
                userId, username);
    }
}
