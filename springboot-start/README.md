#新建springboot项目示例
1. 项目结构
    应用启动类（此处为SpringbootStarApplication文件）放在顶层目录下，其他java文件放在其同目录下的目录中，适应框架所需
2. 启动原理
  SpringbootStarApplication文件为应用启动类，启动原理可参考：[spring boot应用启动原理分析](https://blog.csdn.net/hengyunabc/article/details/50120001)
3. 知识讲解
   
   > 常见注解使用
   
      - @SpringBootApplication，springboot的核心注解，用于开启自动配置，等效于@Configuraion、@ComponentScan和@EnableAutoConfiguration三个注解一起使用。
      - @RestController,表示是servlet控制器，是@Controller和ResponseBody的注解合集，支持restful风格的写法，请求的返回的数据格式为json，适合专门做后台服务接口开发，在4.0版本前不支持该注解。
      - @RequestMapping：提供路由信息，负责URL到Controller中的具体函数的映射。
      - @ResponseBody: 将请求返回的结果为json形式。
      - @Controller：用于定义控制器类。
      - @Service: 表示Service层。
      - @Repository: 表示数据关系映射层。
      - @ComponentScan: 将该类自动发现扫描组件。
      - @Bean: 用在方法上来生成一个bean示例，交给Spring容器托管。
      - @Component: 表示组件。
      - @Configuration: 相当于传统的xml配置文件，如果有些第三方库需要用到xml文件，建议仍然通过@Configuration类作为项目的配置主类——可以使用@ImportResource注解加载xml配置文件。
      - @EnableAutoConfiguration: Spring Boot自动配置（auto-configuration）：尝试根据你添加的jar依赖自动配置你的Spring应用。
      - @ConfigurationProperties: 将配置文件的内容封装成实体类对应的属性。可以把@ConfigurationProperties直接定义在@Bean的注解上，此时bean实体类就不用@Component和@ConfigurationProperties注解了。
      - @Profiles: 提供了一种隔离应用程序配置的方式，并让这些配置只能在特定的环境下生效。
      - @Autowired: 自动导入依赖的bean。
      - @Qualifier: 当有多个同一类型的Bean时，可以用@Qualifier(“name”)来指定。与@Autowired配合使用。@Qualifier限定描述符除了能根据名字进行注入。
      - @Resource: 默认byName，与@Autowired干类似的事，找不到再根据类型找，使用@Resource(name=”name”,type=”type”)
      - @Import: 用来导入其他配置类。
      - @ImportResource：用来加载xml配置文件。
      - @RequestBody: 处理post、put等请求时数据解析。
      - @RequestParam: 获取请求参数，用在方法的参数前。
      - @PathVariable: 路径变量。
      - @Value: 获取配置application文件的内容。
      - @ControllerAdvice：包含@Component。可以被扫描到。统一处理异常。
      - @ExceptionHandler（Exception.class）：用在方法上面表示遇到这个异常就执行以下方法。