## 事务管理
事务管理是应用系统开发中必不可少的一部分。Spring为事务管理提供了丰富的功能支持。
Spring 事务管理分为编码式和声明式的两种方式:
    编程式事务指的是通过编码方式实现事务；
    声明式事务基于 AOP,将具体业务逻辑与事务处理解耦。声明式事务管理使业务代码逻辑不受污染, 因此在实际使用中声明式事务用的比较多。声明式事务有两种方式，一种是在配置文件（xml）中做相关的事务规则声明，另一种是基于@Transactional 注解的方式，也是目前流行的使用方式，

## @Transactional 注解的属性信息
   * name	当在配置文件中有多个 TransactionManager , 可以用该属性指定选择哪个事务管理器。
   * propagation	事务的传播行为，默认值为 REQUIRED。
   * isolation	事务的隔离度，默认值采用 DEFAULT。
   * timeout	事务的超时时间，默认值为-1。如果超过该时间限制但事务还没有完成，则自动回滚事务。
   * read-only	指定事务是否为只读事务，默认值为 false；为了忽略那些不需要事务的方法，比如读取数据，可以设置 read-only 为 true。
   * rollback-for	用于指定能够触发事务回滚的异常类型，如果有多个异常类型需要指定，各类型之间可以通过逗号分隔。
   * no-rollback- for	抛出 no-rollback-for 指定的异常类型，不回滚事务。
   
  > propagation 属性
  
  需要注意下面三种 propagation 可以不启动事务。本来期望目标方法进行事务管理，但若是错误的配置这三种 propagation，事务将不会发生回滚。
  
  TransactionDefinition.PROPAGATION_SUPPORTS：如果当前存在事务，则加入该事务；如果当前没有事务，则以非事务的方式继续运行。
  TransactionDefinition.PROPAGATION_NOT_SUPPORTED：以非事务方式运行，如果当前存在事务，则把当前事务挂起。
  TransactionDefinition.PROPAGATION_NEVER：以非事务方式运行，如果当前存在事务，则抛出异常。
   
  > rollbackFor 属性
  
  默认情况下，如果在事务中抛出了未检查异常（继承自 RuntimeException 的异常）或者 Error，则 Spring 将回滚事务；除此之外，Spring 不会回滚事务。
    
  如果在事务中抛出其他类型的异常，并期望 Spring 能够回滚事务，可以指定 rollbackFor。例：
    
    @Transactional(propagation= Propagation.REQUIRED,rollbackFor= MyException.class)
    
  若在目标方法中抛出的异常是 rollbackFor 指定的异常的子类，事务同样会回滚。

## 使用
@Transactional 可以加在方法上，表示对当前方法配置事务也可以添加到类级别上。
也可以添加到类级别上。当把@Transactional 注解放在类级别时，表示所有该类的公共方法都配置相同的事务属性信息。 
当类级别配置了@Transactional，方法级别也配置了@Transactional，应用程序会以方法级别的事务属性信息来管理事务，即方法级别的事务属性信息会覆盖类级别的相关配置信息。

## 注意
> @Transactional 只能应用到 public 方法才有效

只有@Transactional 注解应用到 public 方法，才能进行事务管理。这是因为在使用 Spring AOP 代理时，Spring 在调用 
TransactionInterceptor 在目标方法执行前后进行拦截之前，DynamicAdvisedInterceptor（CglibAopProxy 的内部类）的 
intercept 方法或 JdkDynamicAopProxy 的 invoke 方法会间接调用 AbstractFallbackTransactionAttributeSource（Spring 通过这个类获取表 1. @Transactional 注解的事务属性配置属性信息）的 computeTransactionAttribute 方法。

    //AbstractFallbackTransactionAttributeSource类
  
    protected TransactionAttribute computeTransactionAttribute(Method method,
        Class<?> targetClass) {
            // Don't allow no-public methods as required.
            if (allowPublicMethodsOnly() && !Modifier.isPublic(method.getModifiers())) {
    return null;}