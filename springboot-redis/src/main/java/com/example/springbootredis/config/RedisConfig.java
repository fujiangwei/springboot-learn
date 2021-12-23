package com.example.springbootredis.config;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * descripiton: 读取redis.properties配置
 *
 * @author: kinson(2219945910 @ qq.com)
 * @date: 2018/6/26
 * @time: 23:03
 * @modifier:
 * @since:
 */
@Configuration
//redis前缀
@ConfigurationProperties(prefix = "redis")
//配置文件内容
@PropertySource("classpath:/config/redis.properties")
@Data
public class RedisConfig {

    private Logger logger = LoggerFactory.getLogger(RedisConfig.class);
    /**
     * Matser的ip地址
     */
    private String host;
    /**
     * 端口号
     */
    private Integer port;
    /**
     * 密码
     */
    private String password;
    /**
     * 客户端连接超时时间，单位毫秒，默认是2000
     */
    private Integer timeout;
    /**
     * 最大空闲数
     */
    private Integer maxIdle;
    /**
     * 控制一个pool可分配多少个jedis实例（jedis 2.4以后用该属性,2.4之前用的redis.maxActive）
     */
    private Long maxTotal;
    /**
     * 最大建立连接等待时间。如果超过此时间将接到异常。设为-1表示无限制。
     */
    private Long maxWaitMillis;
    /**
     * 连接的最小空闲时间 默认1800000毫秒(30分钟)
     */
    private Long minEvictableIdleTimeMillis;
    /**
     * 每次释放连接的最大数目,默认3
     */
    private Long numTestsPerEvictionRun;
    /**
     * 逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
     */
    private Long timeBetweenEvictionRunsMillis;
    /**
     * 是否在从池中取出连接前进行检验,如果检验失败,则从池中去除连接并尝试取出另一个
     */
    private boolean testOnBorrow;
    /**
     * 在空闲时检查有效性, 默认false
     */
    private boolean testWhileIdle;

    /**
     * testOnReturn属性设置为true，归还连接时，会进行检查，检查不通过则销毁
     */
    private boolean testOnReturn;

    /**
     * 连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
     */
    private boolean blockWhenExhausted;

    @Override
    public String toString() {

        return String.format("RedisConfig[host=%s,port=%s,password=%s,timeout=%s]",
                host, port, password, timeout);
    }

}
