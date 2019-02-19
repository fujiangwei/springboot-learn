package com.example.springbootjpa.repository.impl;

import com.example.springbootjpa.domain.User;
import com.example.springbootjpa.repository.BaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

/**
 * descripiton:
 *
 * @author: kinson(2219945910@qq.com)
 * @date: 2019/2/18
 * @time: 22:58
 * @modifier:
 * @since:
 */
@Repository
public class BaseRepositoryImpl<T, ID extends Serializable> implements BaseRepository<T, ID> {

    private static final Logger logger = LoggerFactory.getLogger(BaseRepositoryImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public T findOneBySql(String sql, Class<T> tClass) {
        logger.info(">>>>>>> sql : " + sql);
        Query query = entityManager.createNativeQuery(sql);
        entityManager.close();

        return (T) query.getSingleResult();
    }

    @Override
    public List<T> findAllBySql(String sql, Class<T> clazz) {
        logger.info(">>>>>>> sql : " + sql);

        Query query = entityManager.createNativeQuery(sql);
        entityManager.close();

        return query.getResultList();

    }

    @Override
    public List<Object[]> listBySQL(String sql) {
        return entityManager.createNativeQuery(sql).getResultList();
    }

    @Override
    public List<Object[]> findUserBySql(String sql) {
        List<Object[]> list = entityManager
                .createNativeQuery(sql)
                .getResultList();

        return list;
    }

    @Override
    public List<Object[]> findUserByHql(String sql) {
        sql = "select user_name from User where user_id = 1";

        List<Object[]> list = entityManager
                .createQuery(sql)
                .getResultList();

        return list;
    }

    @Override
    public List<Object[]> findUserBySpecification() {

        //根据地址分组查询，并且学生数量大于3的所有地址
//        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
//        Root<User> root = query.from(User.class);
//        //select address,count(id) as idCount from student group by address having idCount > 3
//        query.multiselect(root.get("address"),builder.count(root.get("id")))
//                .groupBy(root.get("address")).having(builder.gt(builder.count(root.get("id")),3));

//        return entityManager.createQuery(query).getResultList();

        //根据地址分组查询，并且学生数量大于3的所有地址
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root<User> root = query.from(User.class);
        query.multiselect(root.get("user_name"))
                .where(root.get("address")).having(builder.gt(builder.count(root.get("id")), 3));

        return entityManager.createQuery(query).getResultList();
    }
}
