# springboot + mybatis + mycat整合
[springboot + mybatis + mycat整合](https://github.com/fujiangwei/springboot-learn/tree/master/springboot-mybatis-mycat)

# 相关文章
[windows安装Mycat并测试](https://www.cnblogs.com/kingsonfu/p/10626481.html)
[Mycat 简介](https://www.cnblogs.com/kingsonfu/p/10627802.html)
[Mycat 配置文件schema.xml](https://www.cnblogs.com/kingsonfu/p/10626544.html)
[Mycat 配置文件server.xml](https://www.cnblogs.com/kingsonfu/p/10627285.html)
[Mycat 配置文件rule.xml](https://www.cnblogs.com/kingsonfu/p/10627423.html)

# 问题

> 在新增用户时报错：partition table, insert must provide ColumnList错误

    将insert into tb_user VALUES (#{userId}, #{userName},#{companyId});
    改为
    insert into tb_user(id, name, company_id) VALUES (#{userId}, #{userName},#{companyId});
    即加上所有的列字段