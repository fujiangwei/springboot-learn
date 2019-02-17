package com.example.springbootjpa.repository;

import com.example.springbootjpa.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * descripiton:
 *
 * @author: kinson(2219945910@qq.com)
 * @date: 2019/2/17
 * @time: 14:06
 * @modifier:
 * @since:
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     *  Spring Data JPA 在后台为持久层接口创建代理对象时，会解析方法名字，并实现相应的功能。除了通过方法名字以外，它还可以通过如下两种方式指定查询语句：
     *  Spring Data JPA 可以访问 JPA 命名查询语句。开发者只需要在定义命名查询语句时，为其指定一个符合给定格式的名字，Spring Data JPA 便会在创建代理对象时，使用该命名查询语句来实现其功能。
     *  开发者还可以直接在声明的方法上面使用
     * @return
     */

    List<User> findAllBy();

    User findByUserId(Integer userId);

    //@Query 注解，并提供一个查询语句作为参数，Spring Data JPA 在创建代理对象时，便以提供的查询语句来实现其功能。
    @Query("from User where user_name = :username")
    User findUser(@Param("username") String name);
}
