#1、mysql-connector-java 6.0以上版本连接数据库问题

1、Loading class `com.mysql.jdbc.Driver'. This is deprecated. The new driver class is `com.mysql.cj.jdbc.Driver'. The driver is automatically registered via the SPI and manual loading of the driver class is generally unnecessary.

> 解决：将 jdbc.driverClassName=com.mysql.jdbc.Driver 修改为 jdbc.driverClassName=com.mysql.cj.jdbc.Driver

2、com.mysql.cj.exceptions.InvalidConnectionAttributeException: The server time zone value 'ÖÐ¹ú±ê×¼Ê±¼ä' is unrecognized or represents more than one time zone. You must configure either the server or JDBC driver (via the serverTimezone configuration property) to use a more specifc time zone value if you want to utilize time zone support.

> 解决：在jdbc连接url中加入属性serverTimezone来指定时区

#2、mybatis配置及包扫描检查

> 检查配置文件是否写对：
  
  * 在springboot的配置文件添加，mybatis的配置如下所示：
  
  
    mybatis:
      type-aliases-package: com.xxx.xxx.domain
      mapper-locations: classpath:/mybatis/*.xml
      
  * 是否加上相应的注解
  
在启动类上加上@MapperScan或者@ComponentScan注解，手动指定application类要扫描哪些包下的注解，如下所示： 
    
    @SpringBootApplication
    @MappertScan(basePackages = {"com.xxx.xxx.mapper"})
  　　或者在接口上添加@Mapper注解。

     @Mapper
     public interface UserMapper {
     
     }
  注意检查涉及到的包路径是否写正确。