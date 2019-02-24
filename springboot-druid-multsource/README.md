## springboot-mybatis-druid整合多数据源
> pom文件

    <dependency>
        <groupId>org.mybatis.spring.boot</groupId>
        <artifactId>mybatis-spring-boot-starter</artifactId>
        <version>2.0.0</version>
    </dependency>
    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>druid-spring-boot-starter</artifactId>
        <version>1.1.9</version>
    </dependency>

    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <scope>runtime</scope>
    </dependency>
    
> 配置文件

    spring:
        datasource:
            type: com.alibaba.druid.pool.DruidDataSource
            driverClassName: com.mysql.cj.jdbc.Driver
            druid:
                # 主库数据源
                master:
                    url: jdbc:mysql://localhost:3306/springboot?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false
                    username: root
                    password: root
                # 从库数据源
                slave:
                    url: jdbc:mysql://ip:3306/springboot?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false
                    username: root
                    password: root
                # 初始连接数
                initial-size: 10
                # 最大连接池数量
                max-active: 100
                # 最小连接池数量
                min-idle: 10
                # 配置获取连接等待超时的时间
                max-wait: 60000
                # 打开PSCache，并且指定每个连接上PSCache的大小
                pool-prepared-statements: true
                max-pool-prepared-statement-per-connection-size: 20
                # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
                timeBetweenEvictionRunsMillis: 60000
                # 配置一个连接在池中最小生存的时间，单位是毫秒
                min-evictable-idle-time-millis: 300000
                validation-query: SELECT 1 FROM DUAL
                test-while-idle: true
                test-on-borrow: false
                test-on-return: false
                stat-view-servlet:
                    enabled: true
                    url-pattern: /monitor/druid/*
                filter:
                    stat:
                        log-slow-sql: true
                        slow-sql-millis: 1000
                        merge-sql: false
                    wall:
                        config:
                            multi-statement-allow: true
                            
> 主从数据源配置

MasterDataSourcesConfig，SlaveDataSourcesConfig配置类。
其他的和单独配置mybatis差不多，只不过无需在application文件中指定包路径和mapper.xml文件路径
    
    
## 事物问题

> 主数据库
直接使用@Transactional即可

    @Override
    @Transactional
    public void update(SysUser sysUser) {
        sysUserMapper.update(sysUser);
        int i = 10 / 0;
    }
    
> 从数据库
使用配置的事务管理器，即SlaveDataSourcesConfig类中的slaveTransactionManager

    @Override
    @Transactional(value = "slaveTransactionManager")
    public void update(User user) {
        userMapper.update(user);
        int i = 10 / 0;
    }

## 问题

1、druid的配置文件需放在application文件中，放在application-druid中没读取到