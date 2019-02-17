## 启动报错问题
> Error creating bean with name 'entityManagerFactory' defined in class path resource [org/springframework/boot/autoconfigure/orm/jpa/HibernateJpaConfiguration.class]: Invocation of init method failed; nested exception is org.hibernate.AnnotationException: No identifier specified for entity: com.example.springbootjpa.domain.User
```$xslt
表映射的实体类缺少标识主键Id注解 @Id      
```
> org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'userController': Unsatisfied dependency expressed through field 'userService'; nested exception is org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'userServiceImpl': Unsatisfied dependency expressed through field 'userRepository';
```$xslt

```

## JPA各项注解
   1、@Entity(name="EntityName") 
   
   必须,name为可选,对应数据库中一的个表 
   
       @Entity  //标识这个pojo是一个jpa实体     
       public class Users implements Serializable {     
       } 
   
   
   2、@Table(name="",catalog="",schema="") 
   
   可选,通常和@Entity配合使用,只能标注在实体的class定义处,表示实体对应的数据库表的信息 
   
   name:可选,表示表的名称.默认地,表名和实体名称一致,只有在不一致的情况下才需要指定表名 
   
   catalog:可选,表示Catalog名称,默认为Catalog(""). 
   
   schema:可选,表示Schema名称,默认为Schema(""). 
   
       @Entity      
       @Table(name = "users") //指定表名为users     
       public class Users implements Serializable {     
       }  
   
   3、@id 
   
   必须,@id定义了映射到数据库表的主键的属性,一个实体只能有一个属性被映射为主键.置于getXxxx()前. 
   
       @Id     
       private String userCode;  
   
   4、@GeneratedValue(strategy=GenerationType,generator="") 
   
   可选 
   
   * strategy:表示主键生成策略,有AUTO,INDENTITY,SEQUENCE 和 TABLE 4种,分别表示让ORM框架自动选择, 
   
   根据数据库的Identity字段生成,根据数据库表的Sequence字段生成,以有根据一个额外的表生成主键,默认为AUTO 
   
   * generator:表示主键生成器的名称,这个属性通常和ORM框架相关,例如,Hibernate可以指定uuid等主键生成方式. 
   
   
       public class Users implements Serializable {     
       @Id     
       @GeneratedValue(strategy=GenerationType.IDENTITY)//主键自增，注意，这种方式依赖于具体的数据库，如果数据库不支持自增主键，那么这个类型是没法用的     
       @Column(name = "user_id", nullable = false)     
       private int userId;     
      
      
       public class Users implements Serializable {     
       @Id     
       @GeneratedValue(strategy=GenerationType.TABLE)//通过一个表来实现主键id的自增，这种方式不依赖于具体的数据库，可以解决数据迁移的问题     
       @Column(name = "user_code", nullable = false)     
       private String userCode;     
      
      
       public class Users implements Serializable {     
       @Id     
       @GeneratedValue(strategy=GenerationType.SEQUENCE)//通过Sequence来实现表主键自增，这种方式依赖于数据库是否有SEQUENCE，如果没有就不能用     
       @SequenceGenerator(name="seq_user")     
       @Column(name = "user_id", nullable = false)     
       private int userId;    
   
   
   5、@Basic(fetch=FetchType,optional=true) 
   
   可选 
   
   @Basic表示一个简单的属性到数据库表的字段的映射,对于没有任何标注的getXxxx()方法,默认即为@Basic 
   
   * fetch: 表示该属性的读取策略,有EAGER和LAZY两种,分别表示主支抓取和延迟加载,默认为EAGER. 
   
   * optional:表示该属性是否允许为null,默认为true 
   
   
       @Basic(optional=false) 
       
       public String getAddress() { 
       
         return address; 
       
       } 
   
   
   6、@Column 
   
   可选 
   
   @Column描述了数据库表中该字段的详细定义,这对于根据JPA注解生成数据库表结构的工具非常有作用. 
   
   * name:表示数据库表中该字段的名称,默认情形属性名称一致 
   
   * nullable:表示该字段是否允许为null,默认为true 
   
   * unique:表示该字段是否是唯一标识,默认为false 
   
   * length:表示该字段的大小,仅对String类型的字段有效 
   
   * insertable:表示在ORM框架执行插入操作时,该字段是否应出现INSETRT语句中,默认为true 
   
   * updateable:表示在ORM框架执行更新操作时,该字段是否应该出现在UPDATE语句中,默认为true.对于一经创建就不可以更改的字段,该属性非常有用,如对于birthday字段. 
   
   * columnDefinition:表示该字段在数据库中的实际类型.通常ORM框架可以根据属性类型自动判断数据库中字段的类型,但是对于Date类型仍无法确定数据库中字段类型究竟是DATE,TIME还是TIMESTAMP.此外,String的默认映射类型为VARCHAR,如果要将String类型映射到特定数据库的BLOB或TEXT字段类型,该属性非常有用. 
   
       
       @Column(name = "user_code", nullable = false, length=32)//设置属性userCode对应的字段为user_code，长度为32，非空     
       private String userCode;     
       @Column(name = "user_wages", nullable = true, precision=12, scale=2)//设置属性wages对应的字段为user_wages，12位数字可保留两位小数，可以为空     
       private double wages;     
       @Temporal(TemporalType.DATE)//设置为时间类型     
       private Date joinDate; 
   
   7、@Transient 
   
   可选 
   
   @Transient表示该属性并非一个到数据库表的字段的映射,ORM框架将忽略该属性. 
   
   如果一个属性并非数据库表的字段映射,就务必将其标示为@Transient ,否则,ORM框架默认其注解为@Basic 
   
       @Transient      
       private int tempValue;     
          
       public int getTempValue(){     
           return tempValue;     
       }     
      
       public void setTempValue(int value){     
           this.tempValue = value;     
       }    
   
   8、@ManyToOne(fetch=FetchType,cascade=CascadeType) 
   
   可选 
   
   @ManyToOne表示一个多对一的映射,该注解标注的属性通常是数据库表的外键 
   
   * optional:是否允许该字段为null,该属性应该根据数据库表的外键约束来确定,默认为true 
   * fetch:表示抓取策略,默认为FetchType.EAGER 
   * cascade:表示默认的级联操作策略,可以指定为ALL,PERSIST,MERGE,REFRESH和REMOVE中的若干组合,默认为无级联操作 
   * targetEntity:表示该属性关联的实体类型.该属性通常不必指定,ORM框架根据属性类型自动判断targetEntity. 
   
   9、@OneToMany(fetch=FetchType,cascade=CascadeType) 
   
   可选 
   
   @OneToMany描述一个一对多的关联,该属性应该为集体类型,在数据库中并没有实际字段. 
   
   * fetch:表示抓取策略,默认为FetchType.LAZY,因为关联的多个对象通常不必从数据库预先读取到内存 
   * cascade:表示级联操作策略,对于OneToMany类型的关联非常重要,通常该实体更新或删除时,其关联的实体也应当被更新或删除 
   
   例如:实体User和Order是OneToMany的关系,则实体User被删除时,其关联的实体Order也应该被全部删除 
   
   有T_One和T_Many两个表，他们是一对多的关系，注解范例如下 
   > 主Pojo 
   
        @Entity     
        @Table(name = "T_ONE")     
        public class One implements Serializable {     
        private static final long serialVersionUID = 1L;     
        @Id     
        @Column(name = "ONE_ID", nullable = false)     
        private String oneId;     
        @Column(name = "DESCRIPTION")     
        private String description;     
        @OneToMany(cascade = CascadeType.ALL, mappedBy = "oneId")//指向多的那方的pojo的关联外键字段     
        private Collection<Many> manyCollection;     
   
   
   > 子Pojo 
   
       @Entity     
       @Table(name = "T_MANY")     
       public class Many implements Serializable {     
       private static final long serialVersionUID = 1L;     
       @Id     
       @Column(name = "MANY_ID", nullable = false)     
       private String manyId;     
       @Column(name = "DESCRIPTION")     
       private String description;     
          
       @JoinColumn(name = "ONE_ID", referencedColumnName = "ONE_ID")//设置对应数据表的列名和引用的数据表的列名     
       @ManyToOne//设置在“一方”pojo的外键字段上     
       private One oneId;    
   
   10、@JoinColumn 
   
   可选 
   
   @JoinColumn和@Column类似,介量描述的不是一个简单字段,而一一个关联字段,例如.描述一个@ManyToOne的字段. 
   
   name:该字段的名称.由于@JoinColumn描述的是一个关联字段,如ManyToOne,则默认的名称由其关联的实体决定. 
   
   例如,实体Order有一个user属性来关联实体User,则Order的user属性为一个外键, 
   
   其默认的名称为实体User的名称+下划线+实体User的主键名称 
   
   示例: 
   
       见@ManyToOne 
   
   11、@OneToOne(fetch=FetchType,cascade=CascadeType) 
   
   可选 
   
   @OneToOne描述一个一对一的关联 
   
   * fetch:表示抓取策略,默认为FetchType.LAZY 
   * cascade:表示级联操作策略 
   
   > 主Pojo 
   
       @Entity     
       @Table(name = "T_ONEA")     
       public class OneA implements Serializable {     
       private static final long serialVersionUID = 1L;     
       @Id     
       @Column(name = "ONEA_ID", nullable = false)     
       private String oneaId;     
       @Column(name = "DESCRIPTION")     
       private String description;     
       @OneToOne(cascade = CascadeType.ALL, mappedBy = "oneA")//主Pojo这方的设置比较简单，只要设置好级联和映射到从Pojo的外键就可以了。     
       private OneB oneB;    
   
   > 从Pojo 
   
       @Entity     
       @Table(name = "T_ONEB")     
       public class OneB implements Serializable {     
       private static final long serialVersionUID = 1L;     
       @Id     
       @Column(name = "ONEA_ID", nullable = false)     
       private String oneaId;     
       @Column(name = "DESCRIPTION")     
       private String description;     
       @JoinColumn(name = "ONEA_ID", referencedColumnName = "ONEA_ID", insertable = false, updatable =false)//设置从方指向主方的关联外键，这个ONEA_ID其实是表T_ONEA的主键     
       @OneToOne     
       private OneA oneA;     
   
   12、@ManyToMany 
   
   可选 
   
   @ManyToMany 描述一个多对多的关联.多对多关联上是两个一对多关联,但是在ManyToMany描述中,中间表是由ORM框架自动处理 
   
   * targetEntity:表示多对多关联的另一个实体类的全名,例如:package.Book.class 
   * mappedBy:表示多对多关联的另一个实体类的对应集合属性名称 
   
   > 第一个Pojo 
   
       @Entity     
       @Table(name = "T_MANYA")     
       public class ManyA implements Serializable {     
       private static final long serialVersionUID = 1L;     
       @Id     
       @Column(name = "MANYA_ID", nullable = false)     
       private String manyaId;     
       @Column(name = "DESCRIPTION")     
       private String description;     
       @ManyToMany     
       @JoinTable(name = "TMANY1_TMANY2", joinColumns = {@JoinColumn(name = "MANYA_ID", referencedColumnName = "MANYA_ID")}, inverseJoinColumns = {@JoinColumn(name = "MANYB_ID", referencedColumnName = "MANYB_ID")})     
       private Collection<ManyB> manybIdCollection;     
   
   
   > 第二个Pojo 
   
       @Entity     
       @Table(name = "T_MANYB")     
       public class ManyB implements Serializable {     
       private static final long serialVersionUID = 1L;     
       @Id     
       @Column(name = "MANYB_ID", nullable = false)     
       private String manybId;     
       @Column(name = "DESCRIPTION")     
       private String description;     
       @ManyToMany(mappedBy = "manybIdCollection")     
       private Collection<ManyA> manyaIdCollection;    
   
   两个实体间相互关联的属性必须标记为@ManyToMany,并相互指定targetEntity属性, 
   
   需要注意的是,有且只有一个实体的@ManyToMany注解需要指定mappedBy属性,指向targetEntity的集合属性名称 
   
   利用ORM工具自动生成的表除了T_MANYA和T_MANYB表外,还自动生成了一个TMANY1_TMANY2表,用于实现多对多关联 
   
   
   13、@MappedSuperclass 
   
   可选 
   
   @MappedSuperclass可以将超类的JPA注解传递给子类,使子类能够继承超类的JPA注解 
   
       @MappedSuperclass 
   
       public class Employee() { 
          .... 
       }  
   
       @Entity 
   
       public class Engineer extends Employee { 
          ..... 
       } 
   
       @Entity 
   
       public class Manager extends Employee { 
          ..... 
       } 
   
   
   14、@Embedded 
   
   可选 
   
   @Embedded将几个字段组合成一个类,并作为整个Entity的一个属性. 
   
   例如User包括id,name,city,street,zip属性. 
   
   我们希望city,street,zip属性映射为Address对象.这样,User对象将具有id,name和address这三个属性. 
   
   Address对象必须定义为@Embededable 
   
      @Embeddable 
   
       public class Address {city,street,zip} 
   
       @Entity 
   
       public class User { 
   
          @Embedded 
   
          public Address getAddress() { 
              .......... 
          } 
   
       } 
   
   
   15、@OrderBy 
   
   在加载数据的时候可以为其指定顺序，使用@OrderBy注解实现 
   
       @Table(name = "USERS")     
       public class User {     
           @OrderBy(name = "group_name ASC, name DESC")     
           private List books = new ArrayList();     
       }    
   
   
   16、@Lob 
   
   大字段 
   
       @Lob //对应Blob字段类型     
       @Column(name = "PHOTO")     
       private Serializable photo;     
       @Lob //对应Clob字段类型     
       @Column(name = "DESCRIPTION")     
       private String description;    
   
   
   Hibernate验证注解 
   
注解|适用类型|说明|示例 
:------:|:------:|:------:|:------:
@Pattern|String|通过正则表达式来验证字符串|@attern(regex=”[a-z]{6}”) 
@Length|String|验证字符串的长度|@length(min=3,max=20) 
@Email|String|验证一个Email地址是否有效|@email 
@Range|Long|验证一个整型是否在有效的范围内|@Range(min=0,max=100) 
@Min|Long|验证一个整型必须不小于指定值|@Min(value=10) 
@Max|Long|验证一个整型必须不大于指定值|@Max(value=20) 
@Size|集合或数组|集合或数组的大小是否在指定范围内|@Size(min=1,max=255) 
   
## jpa配置

    jpa:
        database: mysql
        show-sql: false #是否打印sql
        hibernate:
            ddl-auto: update
        properties:
            hibernate.format_sql: true
            hibernate.naming.physical-strategy: org.hibernate.config.model.naming.PhysicalNamingStrategyStandardImpl
            hibernate.cache.use_second_level_cache: false
            hibernate.search.default.directory_provider: filesystem
            hibernate.search.default.indexBase: ${user.dir}/indexes

hibernate.hbm2ddl.auto参数的作用主要用于：自动创建|更新|验证数据库表结构。如果不是此方面的需求建议set value="none"。
create：
每次加载hibernate时都会删除上一次的生成的表，然后根据你的model类再重新来生成新表，哪怕两次没有任何改变也要这样执行，这就是导致数据库表数据丢失的一个重要原因。
create-drop ：
每次加载hibernate时根据model类生成表，但是sessionFactory一关闭,表就自动删除。
update：
最常用的属性，第一次加载hibernate时根据model类会自动建立起表的结构（前提是先建立好数据库），以后加载hibernate时根据 model类自动更新表结构，即使表结构改变了但表中的行仍然存在不会删除以前的行。要注意的是当部署到服务器后，表结构是不会被马上建立起来的，是要等 应用第一次运行起来后才会。
validate ：
每次加载hibernate时，验证创建数据库表结构，只会和数据库中的表进行比较，不会创建新表，但是会插入新值。

## 查询使用方式
Spring Data JPA 在后台为持久层接口创建代理对象时，会解析方法名字，并实现相应的功能。除了通过方法名字以外，它还可以通过如下两种方式指定查询语句：

Spring Data JPA 可以访问 JPA 命名查询语句。开发者只需要在定义命名查询语句时，为其指定一个符合给定格式的名字，Spring Data JPA 便会在创建代理对象时，使用该命名查询语句来实现其功能。
开发者还可以直接在声明的方法上面使用 @Query 注解，并提供一个查询语句作为参数，Spring Data JPA 在创建代理对象时，便以提供的查询语句来实现其功能。
JP QL 语句中通过": 变量"的格式来指定参数，同时在方法的参数前面使用 @Param 将方法参数与 JP QL 中的命名参数对应

> 在查询时，通常需要同时根据多个属性进行查询，且查询的条件也格式各样（大于某个值、在某个范围等等），Spring Data JPA 为此提供了一些表达条件查询的关键字，大致如下：

* And --- 等价于 SQL 中的 and 关键字，比如 findByUsernameAndPassword(String user, Striang pwd)；
* Or --- 等价于 SQL 中的 or 关键字，比如 findByUsernameOrAddress(String user, String addr)；
* Between --- 等价于 SQL 中的 between 关键字，比如 findBySalaryBetween(int max, int min)；
* LessThan --- 等价于 SQL 中的 "<"，比如 findBySalaryLessThan(int max)；
* GreaterThan --- 等价于 SQL 中的">"，比如 findBySalaryGreaterThan(int min)；
* IsNull --- 等价于 SQL 中的 "is null"，比如 findByUsernameIsNull()；
* IsNotNull --- 等价于 SQL 中的 "is not null"，比如 findByUsernameIsNotNull()；
* NotNull --- 与 IsNotNull 等价；
* Like --- 等价于 SQL 中的 "like"，比如 findByUsernameLike(String user)；
* NotLike --- 等价于 SQL 中的 "not like"，比如 findByUsernameNotLike(String user)；
* OrderBy --- 等价于 SQL 中的 "order by"，比如 findByUsernameOrderBySalaryAsc(String user)；
* Not --- 等价于 SQL 中的 "！ ="，比如 findByUsernameNot(String user)；
* In --- 等价于 SQL 中的 "in"，比如 findByUsernameIn(Collection<String> userList) ，方法的参数可以是 Collection 类型，也可以是数组或者不定长参数；
* NotIn --- 等价于 SQL 中的 "not in"，比如 findByUsernameNotIn(Collection<String> userList) ，方法的参数可以是 Collection 类型，也可以是数组或者不定长参数；

## Spring Data JPA 对事务的支持
   默认情况下，Spring Data JPA 实现的方法都是使用事务的。针对查询类型的方法，其等价于 @Transactional(readOnly=true)；增删改类型的方法，等价于 @Transactional。可以看出，除了将查询的方法设为只读事务外，其他事务属性均采用默认值。
   
   如果用户觉得有必要，可以在接口方法上使用 @Transactional 显式指定事务属性，该值覆盖 Spring Data JPA 提供的默认值。同时，开发者也可以在业务层方法上使用 @Transactional 指定事务属性，这主要针对一个业务层方法多次调用持久层方法的情况。持久层的事务会根据设置的事务传播行为来决定是挂起业务层事务还是加入业务层的事务.
   
   
   
[优雅使用Spring Data JPA 让一切近乎完美](https://www.ibm.com/developerworks/cn/opensource/os-cn-spring-jpa/index.html)