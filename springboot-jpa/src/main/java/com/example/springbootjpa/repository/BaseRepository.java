package com.example.springbootjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * descripiton: 通用
 *
 * @author: kinson(2219945910 @ qq.com)
 * @date: 2019/2/18
 * @time: 22:56
 * @modifier:
 * @since:
 */

//在BaseRepository上添加@NoRepositoryBean标注，这样Spring Data Jpa在启动时就不会去实例化BaseRepository这个接口
//@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> {

    T findOneBySql(String sql, Class<T> tClass);

    List<T> findAllBySql(String sql, Class<T> clazz);

    List<Object[]> listBySQL(final String sql);

    //基于原生态的sql进行查询
    List<Object[]> findUserBySql(final String sql);

    //基于Hibernate的HQL进行查询
    List<Object[]> findUserByHql(final String sql);

    //基于Specification的方式进行查询，使用的是CriteriaQuery进行查询
    List<Object[]> findUserBySpecification();
}
