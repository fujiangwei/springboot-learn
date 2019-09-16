一 AOP的概念
    AOP(Aspect Oriented Programming)，即为面向切面编程。在软件开发中，散布于应用中多处的

功能被称为横切关注点(cross-cutting concern)，通常来说，这些横切关注点从概念上是与应用的业务

逻辑分离的。比如，声明式事务、日志、安全、缓存等等，都与业务逻辑无关，可以将这些东西抽象成

为模块，采用面向切面编程的方式，通过声明方式定义这些功能用于何处，通过预编译方式和运行期动

态代理实现这些模块化横切关注点程序功能进行统一维护，从而将横切关注点与它们所影响的对象之

间分离出来，就是实现解耦。

横切关注点可以被模块化为特殊的类，这些类被称为切面(aspect)。这样做有两个优点:

1）每个关注点都集中于一个地方，而不是分散到多处代码中;

2）服务模块更简洁，因为它们只包含主要的关注点的代码（核心业务逻辑），

而次要关注点的代码（日志，事务，安全等）都被转移到切面中。

二 AOP术语
1、通知(Advice)
切面类有自己要完成的工作，切面类的工作就称为通知。通知定义了切面是做什么以及何时使用。

"做什么"，即切面类中定义的方法是干什么的；

"何时使用"，即5种通知类型，是在目标方法执行前，还是目标方法执行后等等；

"何处做"，即通知定义了做什么，何时使用，但是不知道用在何处，而切点定义的就是告诉通知应该用在

哪个类的哪个目标方法上，从而完美的完成横切点功能。

Spring切面定义了5种类型通知：

1）前置通知(Before)：在目标方法被调用之前调用通知功能。

2）后置通知(After)：在目标方法完成之后调用通知，不会关心方法的输出是什么。

3）返回通知(After-returning)： 在目标方法成功执行之后调用通知。

4）异常通知(After-throwing)：在目标方法抛出异常后调用通知。

5）环绕通知(Around)：通知包裹了被通知的方法，在被通知的方法调用之前和之后执行自定义的行为。

2、连接点(Join point)
在我们的应用程序中有可能有数以万计的时机可以应用通知，而这些时机就被称为连接点。

连接点是在应用执行过程中能够插入切面的一个点。这个点可以是调用方法时、抛出异常时、

甚至修改一个字段时。切面代码可以利用这些点插入到应用的正常流程之中，并添加新的行为。

连接点是一个虚概念，可以把连接点看成是切点的集合。

下面我们看看切点是神马鬼?

3、切点(Poincut)
连接点谈的是一个飘渺的大范围，而切点是一个具体的位置，用于缩小切面所通知的连接点的范围。

前面说过，通知定义的是切面的"要做什么"和"在何时做"，是不是没有去哪里做，而切点就定义了"去何处做"。

切点的定义会匹配通知所要织入的一个或多个连接点。我们通常使用明确的类和方法名称，或者是使用

正则表达式定义所匹配的类和方法名称来指定切点。说白了，切点就是让通知找到"发泄的地方"。

4、切面(Aspect)
切面是通知和切点的结合，通知和切点共同定义了切面的全部内容。因为通知定义的是切面的

"要做什么"和"在何时做"，而切点定义的是切面的"在何地做"。将两者结合在一起，就可以完美的

展现切面在何时，何地，做什么(功能)。

5、引入(Introduction)
引入这个概念就比较高大尚，引入允许我们向现有的类添加新方法或属性。

主要目的是想在无需修改A的情况下，引入B的行为和状态。

6、织入(Weaving)
织入是把切面应用到目标对象并创建新的代理对象的过程。切面在指定的连接点被织入到目标对象中。

在目标对象的生命周期里有多个点可以进行织入:

编译期: 

    切面在目标类编译时被织入。需要特殊的编译器，是AspectJ的方式，不是spring的菜。

类加载期: 

    切面在目标类加载到JVM时被织入。这种方式需要特殊的类加载器，它可以在目标类被引入应用之前

    增强该目标类的字节码。AspectJ5支持这种方式。

运行期:  

     切面在应用运行的某个时刻被织入。一般情况下，在织入切面时，AOP容器会为目标对象动态的创建

     一个代理对象。而这正是Spring AOP的织入切面的方式。

三 AOP实战
    举个开会的例子，在家开家庭会议，上学开班会，上班了开工作会议，人死了开追悼会。

人的一生都在开会中出生，然后在开会中死去。每个人都离不开开会。而上学时候开会，

开会前老师都说: 静一下，现在通讯发达了，网络时代，老师开会估计都说: 把手机收起来。

    而工作了，咱们最常听见的是: 把手机调成静音，或者关机。其实在开会之前的这些都不是开会要将的核心，

而开会后搞不好还要写个总结报告，所有开会前开会后做的这些事情，都与核心业务逻辑开会都是独立开来的，

我们用开会前，开会，开会后来举例分析一下AOP的使用。AOP的实现可以通过注解方式或XML方式，

这里主要分析注解方式，下一篇有机会再讨论下XML方式。

1、创建切面类
切面类包含通知和切入点，在创建切面类之前，我们需要了解下AspectJ的切点表达式，因为我们需要通过

切点表达式定义切点，用于准确的定位应该在什么地方应用切面的通知。

直接上一个图，解释下切点表达式的元素:



对切点表达式有所了解后，我们通过@Aspect注解标注创建切面类。

package com.lanhuigu.spring;
 
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
 
/**
 *  注解@Aspect标识该类为切面类
 */
@Component
@Aspect
public class PersonAspect {
 
    /**
     * 开会之前--找个位置坐下
     */
    @Before("execution(* com.lanhuigu.spring.ConferenceServiceImpl.conference(..))")
    public void takeSeats() {
        System.out.println("找位置坐");
    }
 
    /**
     * 开会之前--手机调成静音
     */
    @Before("execution(* com.lanhuigu.spring.ConferenceServiceImpl.conference(..))")
    public void silenceCellPhones() {
        System.out.println("手机调成静音");
    }
 
    /**
     * 开会之后--写会议总结报告
     */
    @After("execution(* com.lanhuigu.spring.ConferenceServiceImpl.conference(..))")
    public void summary() {
        System.out.println("写会议总结报告");
    }
 
}
在定义完这个切面类之后，有没有发现3个方法通知类型之后的execution表达式内容完全一致，

如果你有代码强迫症，一定想把他们提成一个公用的，别的地方只需要引用一下就行，

这个地方使用@Pointcut满足你的强迫症。

优化后的切面类:

package com.lanhuigu.spring;
 
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
 
/**
 *  注解@Aspect标识该类为切面类
 */
@Component
@Aspect
public class PersonAspect {
 
//    /**
//     * 开会之前--找个位置坐下
//     */
//    @Before("execution(* com.lanhuigu.spring.ConferenceServiceImpl.conference(..))")
//    public void takeSeats() {
//        System.out.println("找位置坐");
//    }
//
//    /**
//     * 开会之前--手机调成静音
//     */
//    @Before("execution(* com.lanhuigu.spring.ConferenceServiceImpl.conference(..))")
//    public void silenceCellPhones() {
//        System.out.println("手机调成静音");
//    }
//
//    /**
//     * 开会之后--写会议总结报告
//     */
//    @After("execution(* com.lanhuigu.spring.ConferenceServiceImpl.conference(..))")
//    public void summary() {
//        System.out.println("写会议总结报告");
//    }
    /**
     * =========================================================================
     * 从上面的执行代码可以看出切点execution表达式内容都是一样，
     * 我们可以通过@Pointcut进行优化。
     * =========================================================================
     */
 
    /**
     * 通过注解@Pointcut定义切点，conference()只是一个标识，无所谓是什么，
     * 方法中内容本身也是空的，使用该切点的地方直接通过标识conference()引用切点表达式。
     */
    @Pointcut("execution(* com.lanhuigu.spring.ConferenceServiceImpl.conference(..))")
    public void conference() {}
 
    /**
     * 开会之前--找个位置坐下
     */
    @Before("conference()")
    public void takeSeats() {
        System.out.println("找位置坐");
    }
 
    /**
     * 开会之前--手机调成静音
     */
    @Before("conference()")
    public void silenceCellPhones() {
        System.out.println("手机调成静音");
    }
 
    /**
     * 开会之后--写会议总结报告
     */
    @After("conference()")
    public void summary() {
        System.out.println("写会议总结报告");
    }
 
}
咱们解释一下切点表达式的含义:



通过execution指示器，选择ConferenceServiceImpl类中的conference()方法。

方法表达式以“*”号开始，表明了我们不关心方法的返回值是神马鬼。

对于方法参数列表通过两个点表示，表示我们不在乎conference的参数。

在执行表达式的时候，我们可以通过逻辑运算符&&(and) , ||(or) , !(not)对表达式进行搭配。比如:

execution(* com.lanhuigu.spring.ConferenceServiceImpl.conference(..)
          && within(com.lanhuigu.spring.*))

增加了一个限制就是我们只管com.lanhuigu.spring下的包，这里的&&可以使用and来替代，

同理|| , !都是一样的用法，灵活多变，只能根据实际情况看着办。

2、创建目标类，定义目标方法
目标类就是我们的核心，开会，开会前和开会后，中间休息等等地方都是插入切面通知的地方，

这些地方的集合就是连接点，而某一个具体的位置就是切点，连接点就是切点的集合。

我们创建一个目标类，作为切面插入的目标。

创建一个开会的接口：

package com.lanhuigu.spring;
 
public interface ConferenceService {
    void conference();
}
开会接口的实现:

package com.lanhuigu.spring;
 
import org.springframework.stereotype.Component;
 
@Component
public class ConferenceServiceImpl implements ConferenceService {
 
    @Override
    public void conference() {
        System.out.println("开会......");
    }
 
}

3、 编写配置类，启动AOP代理功能
切面类，目标方法都创建完了，但是我们的切面类在启动时也不会被转化为代理的。

现在处于完事具备，只欠东风的状态。需要一阵风，打一场赤壁之战。

古有诸葛亮借东风，今有Spring通过@EnableAspectJAutoProxy注解启动AspectJ自动代理。

JavaConfg配置：

package com.lanhuigu.spring;
 
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
 
/**
 * Jdk代理：基于接口的代理，一定是基于接口，会生成目标对象的接口的子对象。
 * Cglib代理：基于类的代理，不需要基于接口，会生成目标对象的子对象。
 *
 * 1. 注解@EnableAspectJAutoProxy开启代理;
 *
 * 2. 如果属性proxyTargetClass默认为false, 表示使用jdk动态代理织入增强;
 *
 * 3. 如果属性proxyTargetClass设置为true，表示使用Cglib动态代理技术织入增强;
 *
 * 4. 如果属性proxyTargetClass设置为false，但是目标类没有声明接口，
 *    Spring aop还是会使用Cglib动态代理，也就是说非接口的类要生成代理都用Cglib。
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan("com.lanhuigu")
public class AppConfig {
 
}
4、 测试类
package com.lanhuigu.spring;
 
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
 
/**
 * 基于@ContextConfiguration构建Spring环境
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class AopTest {
 
    @Autowired
    private ConferenceServiceImpl conferenceService;
 
    @Test
    public void testAopAnnotation() {
        conferenceService.conference();
    }
 
}

5、程序运行结果
手机调成静音
找位置坐
开会......
写会议总结报告

程序输出的结果一样，到此，我们基本上了解了基于注解的AOP实战。

四  环绕通知
关于通知类型，需要单独分析的是环绕通知，他跟其他通知类型不一样，环绕通知也是最为强大的一种通知方式，

所谓的环绕通知，顾名思义，它能够让你所编写的逻辑将被通知的目标方法全部包装起来。

实际上就像我们前面写的开会前，开会后干的哪些事情，对于环绕通知来说，一个方法就搞定了，因为他包围了目标方法，

等同于在一个通知方法中同时编写了前置通知和后置通知，环绕通知都会为执行开会前，开会后等等逻辑。

把前面的Person类重构为如下内容:

package com.lanhuigu.spring;
 
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
 
/**
 *  注解@Aspect标识该类为切面类
 */
@Component
@Aspect
public class PersonAspect {
 
    /**
     * 通过注解@Pointcut定义切点，conference()只是一个标识，无所谓是什么，
     * 方法中内容本身也是空的，使用该切点的地方直接通过标识conference()引用切点表达式。
     */
    @Pointcut("execution(* com.lanhuigu.spring.ConferenceServiceImpl.conference(..))")
    public void conference() {}
 
    @Around("conference()")
    public void testAround(ProceedingJoinPoint jp) {
        try {
            System.out.println("1111111111111");
            System.out.println("2222222222222");
            jp.proceed();
            System.out.println("3333333333333");
        } catch (Throwable e) {
            System.out.println("开会不爽，打起来了");
        }
    }
 
}
重构完后再运行下TestAopAnnotation测试类，输出如下结果:

1111111111111
2222222222222
开会......
3333333333333