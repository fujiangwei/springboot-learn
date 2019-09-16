# RPC远程调用示例学习

## springboot commandRunner和ApplicationRunner
```text
应用服务器启动时，加载一些数据和执行一些初始化动作，如读取配置文件或者读取数据库连接等，当有多个类实现这些接口时可通过加@Order注解来控制执行的先后顺序，值越小优先级越高
  
两者的区别在于：
    ApplicationRunner中run方法的参数为ApplicationArguments，而CommandLineRunner接口中run方法的参数为String数组。
    想要更详细地获取命令行参数，那就使用ApplicationRunner接口。
    ApplicationRunner是针对main方法的参数args做了一层封装，存在形式为key=value模式，可以通过key来获取对应的value值。而CommandLineRunner只是以值的形式存在着。
    
```




