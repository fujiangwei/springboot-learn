## springboot集成thymeleaf
> 简版
pom引入thymeleaf依赖,其他无需配置

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>

## thymeleaf的配置文件说明
* spring.thymeleaf.cache = true ＃启用模板缓存。
* spring.thymeleaf.check-template = true ＃在呈现模板之前检查模板是否存在。
* spring.thymeleaf.check-template-location = true ＃检查模板位置是否存在。
* spring.thymeleaf.content-type = text / html ＃Content-Type值。
* spring.thymeleaf.enabled = true ＃启用MVC Thymeleaf视图分辨率。
* spring.thymeleaf.encoding = UTF-8 ＃模板编码。
* spring.thymeleaf.excluded-view-names = ＃应该从解决方案中排除的视图名称的逗号分隔列表。
* spring.thymeleaf.mode = HTML5 ＃应用于模板的模板模式。另请参见StandardTemplateModeHandlers。
* spring.thymeleaf.prefix = classpath：/ templates / ＃在构建URL时预先查看名称的前缀。
* spring.thymeleaf.suffix = .html ＃构建URL时附加到查看名称的后缀。
* spring.thymeleaf.template-resolver-order = ＃链中模板解析器的顺序。
* spring.thymeleaf.view-names = ＃可以解析的视图名称的逗号分隔列表。/ templates / ＃在构建URL时先查看名称的前缀。
* spring.thymeleaf.suffix = .html ＃构建URL时附加到查看名称的后缀。
* spring.thymeleaf.template-resolver-order = ＃链中模板解析器的顺序。
* spring.thymeleaf.view-names = ＃可以解析的视图名称的逗号分隔列表。/ templates / ＃在构建URL时先查看名称的前缀。
* spring.thymeleaf.suffix = .html ＃构建URL时附加到查看名称的后缀。
* spring.thymeleaf.template-resolver-order = ＃链中模板解析器的顺序。
* spring.thymeleaf.view-names = ＃可以解析的视图名称的逗号分隔列表。

## thymeleaf的基础知识介绍
1.1  标准表达式介绍
   它们分为四类：
   
   1.变量表达式
   2. 选择表达式（星号表达式）
   3. 消息表达式（井号表达式，资源表达式）通常做国际化
   4.URL表达式
1.2  标准表达式详解
   1.变量表达式
     变量表达式用于访问容器上下文环境中的变量，功能和JSTL中的${}相同。比如我要在Controllar中用model.addAttribute向前端传入一个对象，那么前端如何接受呢？ 以下是例子。
   
   //向前端写入一个UL的对象和ID属性
   @RequestMapping("/userPower")
   public String userPower(@RequestParam("id") Integer id,Model model){
   	UserPower UL = us.selectUserByID(id);
       model.addAttribute("ID","10081");
   	model.addAttribute("UL", UL);
   	return "userPower";
   	}
    前端接收代码
   
   <input th:text="${ID}" ></input >
   <input th:if="${UL.Power} == 1" >管理员</input >
     访问此页面，效果如下
   
   
   
   JAVA代码就不解释了，很简单。解释下HTML部分，th:text=" " 和 th:if=" " 是thymeleaf的一个属性，先不做解释。其$(ID)，$(UL.Power)中的"UL"是Java代码中model传来的Key值，".Power"是UserPower对象中的一个属性。
   
   2.星号表达式
     择表达式（星号表达式）。只要是没有选择对象，选择表达式与变量表达式的语法是完全一样的。使用th:object对象属性来绑定对象。 代码如下。
   
   //向前端写入一个UL的参数
   @RequestMapping("/userPower")
   public String userPower(@RequestParam("id") Integer id,Model model){
   	UserPower UL = us.selectUserByID(id);
   	model.addAttribute("UL", UL);
   	return "userPower";
   	}
              前端接收代码
   
   <div th:object=" ${UL}" >
   
   <p>Name: <span th: text=" *{Name}" >张</span>. </p>
   
   <p>LastName: <span th: text=" *{lastName}" >三</span>. </p>
   
   <p>Address: <span th: text=" *{addr}" >北京</span>. </p>
   
   </div>
             Html解释：首先使用th:object来邦定后台传来的UserPower对象，然后使用*来代表这个对象，后面{}中的值是此对象中的属性。
   
  3.消息表达式
    消息表达式（井号表达式，资源表达式）。通常与th:text属性一起使用，指明声明了th:text的标签的文本是#{}中的key所对应的value，而标签内的文本将不会显示。就是说我们可以从.properties或yml文件中，用Key索引Value.
   
   例子如下：HTML部分
   
   <p th:text="#{home.language}" >This text will not be show! </p>
   properties中我们自定义一个home.language的属性
   
   home.language=这段文字将会被显示！！
   访问页面，效果是英文不会被展示，展示的是属性文件中的中文。
   
  4.URL表达式
    超链接url表达式。
   
   例如：
   
   <script th:src="@{/static/js/jquery-2.4.min.js}"></script>
    表达式简介到此为止，之后我会在写表达式在工作中使用，出现的一些问题，比如URL表达式如何代入参数，字符串的拼接等等。
   
1.3  thymeleaf属性的介绍
     上面如th:src、th:text等字眼，这些就是thymeleaf的属性，下面进行讲解。
   
   * th:action
   * th:each
   * th:field
   * th:href
   * th:id
   * th:if
   * th:include
   * th:fragment
   * th:object
   * th:src
   * th:replace
   * th:text
   * th:value
   
   1、th:action
      定义后台控制器的路径，类似<form>标签的action属性。 例子如下。
   
    <form id="login" th:action="@{/login}">......</form>
   2、th:each
      这个属性非常常用，比如从后台传来一个对象集合那么我就可以使用此属性遍历输出，和jstl中的<c: forEach>类似，而且这个属性不仅可以循环集合，还可以循环数组及Map，例子如下。
   
       <ol>  
               <li>List类型的循环：  
                   <table >  
                     <tr>  
                       <th>用户名</th>  
                       <th>用户邮箱</th>  
                       <th>超级管理员</th>  
                       <th>状态变量值：index</th>  
                       <th>状态变量值：count</th>  
                       <th>状态变量值：size</th>  
                       <th>状态变量值：current.userName</th>  
                       <th>状态变量值：even</th>  
                       <th>状态变量值：odd</th>  
                       <th>状态变量值：first</th>  
                       <th>状态变量值：last</th>  
                     </tr>  
                     <tr  th:each="user,iterStat : ${list}">  
                       <td th:text="${user.userName}">Onions</td>  
                       <td th:text="${user.email}">test@test.com.cn</td>  
                       <td th:text="${user.isAdmin}">yes</td>  
                        <th th:text="${iterStat .index}">index</th>  
                       <th th:text="${iterStat .count}">count</th>  
                       <th th:text="${iterStat .size}">size</th>  
                       <th th:text="${iterStat .current.userName}">current</th>  
                       <th th:text="${iterStat .even}">even</th>  
                       <th th:text="${iterStat .odd}">odd</th>  
                       <th th:text="${iterStat .first}">first</th>  
                       <th th:text="${iterStat .last}">last</th>  
                     </tr>  
                   </table>  
               </li>  
               <li>Map类型的循环：  
                   <div th:each="mapS:${map}">  
                   <div th:text="${mapS}"></div>  
                   </div>  
               </li>  
               <li>数组的循环：  
                   <div th:each="arrayS:${arrays}">  
                   <div th:text="${arrayS}"></div>  
                   </div>  
               </li>  
               </ol>  
           解释一下每个参数的意义，首先th:each="user,iterStat : ${list}"中的 ${list}，是后台传来的Key，user 作为接受参数接收，使用iterStat作为list下标值 （这个东西我当时好久都不知道是啥），其中user及iterStat自己可以随便写。
   
   例子中的${iterStat .index}、${iterStat .count}是iterStat的属性，其属性的含义为：
       index:当前迭代对象的index（从0开始计算）
       count: 当前迭代对象的index(从1开始计算)
       size:被迭代对象的大小
       current:当前迭代变量
       even/odd:布尔值，当前循环是否是偶数/奇数（从0开始计算）
       first:布尔值，当前循环是否是第一个
       last:布尔值，当前循环是否是最后一个
   
   3、th:href、th:src、th:id
      定义超链接，类似<a>标签的href 属性。value形式为@{/login}
   
    <a  class="login" th:href="@{/login}"></a>
    
   用于外部资源引入，类似<script>标签的src属性，常与@{}表达式结合使用。
   
    <script th:src="@{/static/js/jquery-2.4.min.js}"></script>
   类似html标签中的id属性。
   
    <div class="user" th:id = "(${index})"></div>
   4、th:if
      这个属性使用也非常频繁，比如后台传来一个key，判断value的值，1为男，2为女。
   
       <span th:if="${Sex} == 1" >
                 <input type="redio" name="se"  th:value="男" />
       </span>
       <span th:if="${Sex} == 2">
                 <input type="redio" name="se"  th:value="女"  />
       </span>
   5、th:value
      类似html中的value属性，能对某元素的value属性进行赋值。
   
    <input type="hidden" id="StartNo" name="StartNo" th:value="${StartNo}">
   6、th:text
      用于文本的显示
   
    <input type="text" id="RealName" name="ReaName" th:text="${RealName}">
   7、th:attr
    用于给HTML中某元素的某属性赋值。比如例子5还可以写成如下形式.
   
    <input type="hidden" id="StartNo" name="StartNo" th:attr="value=${StartNo}" >
    
   7、th:field
      常用于表单字段绑定。通常与th:object一起使用。 属性绑定、集合绑定。
     <form id="login-form" th:action="@{/login}" th:object="${loginBean}">...
     
     <input type="text" value="" th:field="*{username}"></input>
     <input type="text" value="" th:field="*{user[0].username}"></input>
     </form>