#shiro简介

```
Apache Shiro是一个强大易用的 Java 安全框架，提供了认证、授权、加密和会话管理功能，可为任何应用提供安全保障 - 从命令行应用、移动应用到大型网络及企业应用。

Shiro 为解决下列问题（我喜欢称它们为应用安全的四要素）提供了保护应用的 API：

认证 - 用户身份识别，常被称为用户“登录”；
授权 - 访问控制；
密码加密 - 保护或隐藏数据防止被偷窥；
会话管理 - 每用户相关的时间敏感的状态。
Shiro 还支持一些辅助特性，如 Web 应用安全、单元测试和多线程，它们的存在强化了上面提到的四个要素。
```

#shiro特点
```$xslt
1、易于使用 - 易用性是这个项目的最终目标。应用安全有可能会非常让人糊涂，令人沮丧，并被认为是“必要之恶”【译注：比喻应用安全方面的编程。】。若是能让它简化到新手都能很快上手，那它将不再是一种痛苦了。
2、广泛性 - 没有其他安全框架可以达到 Apache Shiro 宣称的广度，它可以为你的安全需求提供“一站式”服务。
3、灵活性 - Apache Shiro 可以工作在任何应用环境中。虽然它工作在 Web、EJB 和 IoC 环境中，但它并不依赖这些环境。Shiro 既不强加任何规范，也无需过多依赖。
4、Web 能力 - Apache Shiro 对 Web 应用的支持很神奇，允许你基于应用 URL 和 Web 协议（如 REST）创建灵活的安全策略，同时还提供了一套控制页面输出的 JSP 标签库。
5、可插拔 - Shiro 干净的 API 和设计模式使它可以方便地与许多的其他框架和应用进行集成。你将看到 Shiro 可以与诸如 Spring、Grails、Wicket、Tapestry、Mule、Apache Camel、Vaadin 这类第三方框架无缝集成。
6、支持 - Apache Shiro 是Apache 软件基金会成员，这是一个公认为了社区利益最大化而行动的组织。项目开发和用户组都有随时愿意提供帮助的友善成员。像Katasoft这类商业公司，还可以给你提供需要的专业支持和服务。
```

#shiro核心概念Subject，SecurityManager 和 Realms
1、 Subject
```
Subject一词是一个安全术语，其基本意思是“当前的操作用户”。称之为“用户”并不准确，因为“用户”一词通常跟人相关。在安全领域，术语“Subject”可以是人，也可以是第三方进程、后台帐户（Daemon Account）或其他类似事物。它仅仅意味着“当前跟软件交互的东西”。但考虑到大多数目的和用途，你可以把它认为是 Shiro 的“用户”概念。
```     
2、 SecurityManager
```$xslt
Subject 代表了当前用户的安全操作，SecurityManager 则管理所有用户的安全操作。它是 Shiro 框架的核心，充当“保护伞”，引用了多个内部嵌套安全组件，它们形成了对象图。
```
3、 Realms
```$xslt
Realm 充当了 Shiro 与应用安全数据间的“桥梁”或者“连接器”。也就是说，当切实与像用户帐户这类安全相关数据进行交互，执行认证（登录）和授权（访问控制）时，Shiro 会从应用配置的 Realm 中查找很多内容。

从这个意义上讲，Realm 实质上是一个安全相关的DAO：它封装了数据源的连接细节，并在需要时将相关数据提供给 Shiro。当配置 Shiro 时，你必须至少指定一个 Realm，用于认证和（或）授权。配置多个 Realm 是可以的，但是至少需要一个。

Shiro 内置了可以连接大量安全数据源（又名目录）的 Realm，如 LDAP、关系数据库（JDBC）、类似 INI 的文本配置资源以及属性文件等。如果缺省的 Realm 不能满足需求，你还可以插入代表自定义数据源的自己的 Realm 实现。下面的清单 4 是通过 INI 配置 Shiro 使用 LDAP 目录作为应用 Realm 的示例。
```

###shiro认证
```$xslt
认证是核实用户身份的过程。也就是说，当用户使用应用进行认证时，他们就在证明他们就是自己所说的那个人。有时这也理解为“登录”。

这个过程的常见例子是大家都熟悉的“用户 / 密码”组合。多数用户在登录软件系统时，通常提供自己的用户名（当事人）和支持他们的密码（证书）。如果存储在系统中的密码（或密码表示）与用户提供的匹配，他们就被认为通过认证。

```
###shiro授权
```$xslt
授权实质上就是访问控制 - 控制用户能够访问应用中的哪些内容，比如资源、Web 页面等等。多数用户执行访问控制是通过使用诸如角色和权限这类概念完成的。也就是说，通常用户允许或不允许做的事情是根据分配给他们的角色或权限决定的。那么，通过检查这些角色和权限，你的应用程序就可以控制哪些功能是可以暴露的。
```
###shiro会话管理
```$xslt
在安全框架领域，Apache Shiro 提供了一些独特的东西：可在任何应用或架构层一致地使用 Session API。即，Shiro 为任何应用提供了一个会话编程范式 - 从小型后台独立应用到大型集群 Web 应用。这意味着，那些希望使用会话的应用开发者，不必被迫使用 Servlet 或 EJB 容器了。或者，如果正在使用这些容器，开发者现在也可以选择使用在任何层统一一致的会话 API，取代 Servlet 或 EJB 机制。

但 Shiro 会话最重要的一个好处或许就是它们是独立于容器的。这具有微妙但非常强大的影响。例如，让我们考虑一下会话集群。对集群会话来讲，支持容错和故障转移有多少种容器特定的方式？Tomcat 的方式与 Jetty 的不同，而 Jetty 又和 Websphere 不一样，等等。但通过 Shiro 会话，你可以获得一个容器无关的集群解决方案。Shiro 的架构允许可插拔的会话数据存储，如企业缓存、关系数据库、NoSQL 系统等。这意味着，只要配置会话集群一次，它就会以相同的方式工作，跟部署环境无关 - Tomcat、Jetty、JEE 服务器或者独立应用。不管如何部署应用，毋须重新配置应用。

Shiro 会话的另一好处就是，如果需要，会话数据可以跨客户端技术进行共享。例如，Swing 桌面客户端在需要时可以参与相同的 Web 应用会话中
```
###shiro加密
```$xslt
加密是隐藏或混淆数据以避免被偷窥的过程。在加密方面，Shiro 的目标是简化并让 JDK 的加密支持可用。

一般情况下，加密不是特定于 Subject 的，所以它是 Shiro API 的一部分，但并不特定于 Subject。你可以在任何地方使用 Shiro 的加密支持，甚至在不使用 Subject 的情况下。对于加密支持，Shiro 真正关注的两个领域是加密哈希（又名消息摘要）和加密密码。
```

###shiro Filter Chain定义说明：

```
1、一个URL可以配置多个Filter，使用逗号分隔
2、当设置多个过滤器时，全部验证通过，才视为通过
3、部分过滤器可指定参数，如perms，roles
```
> Shiro内置的FilterChain

Filter Name|Class
:-------:|:------:
anon|org.apache.shiro.web.filter.authc.AnonymousFilter
authc|org.apache.shiro.web.filter.authc.FormAuthenticationFilter
authcBasic|org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter
perms|org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter
port|org.apache.shiro.web.filter.authz.PortFilter
rest|org.apache.shiro.web.filter.authz.HttpMethodPermissionFilter
roles|org.apache.shiro.web.filter.authz.RolesAuthorizationFilter
ssl|org.apache.shiro.web.filter.authz.SslFilter
user|org.apache.shiro.web.filter.authc.UserFilter

* anon:所有url都都可以匿名访问
* authc: 需要认证才能进行访问
* user:配置记住我或认证通过可以访问

##shiro权限注解：
 
 > @RequiresAuthentication  
 ```
 表示当前Subject已经通过login进行了身份验证；即Subject. isAuthenticated()返回true。
 ```
  
 > @RequiresUser 
 ```$xslt
 表示当前Subject已经身份验证或者通过记住我登录的。
 ```
 
 > @RequiresGuest 
 ```$xslt
 表示当前Subject没有身份验证或通过记住我登录过，即是游客身份。
 ``` 
  
 > @RequiresRoles(value={“admin”, “user”}, logical= Logical.AND)  
 ```$xslt
 表示当前Subject需要角色admin和user。
 ```
 
 > @RequiresPermissions (value={“user:a”, “user:b”}, logical= Logical.OR)  
 ```$xslt
 表示当前Subject需要权限user:a或user:b。
 ```

## 问题
> 使用tk.mybatis的XXMapper不要和数据库实体类映射的XXMapper放在同一个包下，不然扫描会出问题

参照：

[让 Apache Shiro保护你的应用](https://www.infoq.cn/article/apache-shiro)
[Apache Shiro指南和参考手册](http://shiro.apache.org/documentation.html)