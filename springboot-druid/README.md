# Druid配置 
[详细配置表格链接](https://www.cnblogs.com/kingsonfu/p/10343947.html)

>  这4个参数key里不带druid也可以，即可以还用上面的这个4个参数
 spring.datasource.druid.url=jdbc:mysql://localhost:3306/springboot?useUnicode=true&characterEncoding=utf-8&useSSL=false
 spring.datasource.druid.username=root
 spring.datasource.druid.password=root
 spring.datasource.druid.driver-class-name=com.mysql.jdbc.Driver
>  初始化时建立物理连接的个数
 spring.datasource.druid.initial-size=5
>  最大连接池数量
 spring.datasource.druid.max-active=30
> 最小连接池数量
 spring.datasource.druid.min-idle=5
> 获取连接时最大等待时间，单位毫秒
 spring.datasource.druid.max-wait=60000
> 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
 spring.datasource.druid.time-between-eviction-runs-millis=60000
> 连接保持空闲而不被驱逐的最小时间
 spring.datasource.druid.min-evictable-idle-time-millis=300000
>  用来检测连接是否有效的sql，要求是一个查询语句
 spring.datasource.druid.validation-query=SELECT 1 FROM DUAL
>  建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。
 spring.datasource.druid.test-while-idle=true
>  申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。
 spring.datasource.druid.test-on-borrow=false
>  归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。
 spring.datasource.druid.test-on-return=false
>  是否缓存preparedStatement，也就是PSCache。PSCache对支持游标的数据库性能提升巨大，比如说oracle。在mysql下建议关闭。
 spring.datasource.druid.pool-prepared-statements=true
>  要启用PSCache，必须配置大于0，当大于0时，poolPreparedStatements自动触发修改为true。
 spring.datasource.druid.max-pool-prepared-statement-per-connection-size=50
>  配置监控统计拦截的filters，去掉后监控界面sql无法统计
 spring.datasource.druid.filters=stat,wall
>  通过connectProperties属性来打开mergeSql功能；慢SQL记录
 spring.datasource.druid.connection-properties=druid.stat.mergeSql=true;druid.stat.slowSqlMillis=500
>  合并多个DruidDataSource的监控数据
 spring.datasource.druid.use-global-data-source-stat=true
 
#druid依赖引入问题
当使用springboot管理druid相关配置时，使用依赖如下：
    
    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>druid-spring-boot-starter</artifactId>
        <version>1.1.9</version>
    </dependency>

.properties文件如上所示，.yml文件如application.yml文件所示，此时是其内部已进行相关配置的解析，druid监控各项数据正常展示
若使用以下依赖时,项目启动后访问druid监控页404。

    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>druid</artifactId>
        <version>${druid.version}</version>
    </dependency>
   
此时需要自己手动代码解析相关的配置项，否则druid监控的SQL监控将无数据