//package com.example.springbootdruid.config;
//
//import com.alibaba.druid.filter.Filter;
//import com.alibaba.druid.filter.stat.StatFilter;
//import com.alibaba.druid.pool.DruidDataSource;
//import com.alibaba.druid.support.http.StatViewServlet;
//import com.alibaba.druid.support.http.WebStatFilter;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.boot.web.servlet.ServletRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//
///**
// * descripiton:
// *
// * @author: kinson(2219945910@qq.com)
// * @date: 2019/1/31
// * @time: 21:29
// * @modifier:
// * @since:
// */
//@Configuration
//public class DruidConfig {
//
//    @Bean
//    @ConfigurationProperties(prefix="spring.datasource")
//    public DataSource druidDataSource() {
//        return new DruidDataSource();
//    }
//
//    @Bean
//    public ServletRegistrationBean DruidStatViewServle2(){
//
//        //org.springframework.boot.context.embedded.ServletRegistrationBean提供类的进行注册.
//
//        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
//
//
//
//        //添加初始化参数：initParams
//
//
//
//        //白名单：
//
//        servletRegistrationBean.addInitParameter("allow","127.0.0.1");
//
//        //IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
//
//        servletRegistrationBean.addInitParameter("deny","192.168.1.73");
//
//        //登录查看信息的账号密码.
//
////        servletRegistrationBean.addInitParameter("loginUsername","admin");
////
////        servletRegistrationBean.addInitParameter("loginPassword","123456");
//
//        //是否能够重置数据.
//
//        servletRegistrationBean.addInitParameter("resetEnable","false");
//
//        return servletRegistrationBean;
//
//    }
//
//
//
//    /**
//
//     * 注册一个：filterRegistrationBean
//
//     * @return
//
//     */
//
//    @Bean
//
//    public FilterRegistrationBean druidStatFilter2(){
//
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
//
//        //添加过滤规则.
//
//        filterRegistrationBean.addUrlPatterns("/*");
//
//        //添加不需要忽略的格式信息.
//
//        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
//
//        return filterRegistrationBean;
//    }
//
//    @Bean
//    public Filter statFilter(){
//        StatFilter statFilter=new StatFilter();
//        //多长时间定义为慢sql，这里定义为5s
//        statFilter.setSlowSqlMillis(1);
//        //是否打印出慢日志
//        statFilter.setLogSlowSql(true);
//        //是否将日志合并起来
//        statFilter.setMergeSql(true);
//        return  statFilter;
//    }
//}
