## 分页插件参数介绍

> 分页插件可选参数如下：

* dialect：默认情况下会使用 PageHelper 方式进行分页，如果想要实现自己的分页逻辑，可以实现 Dialect(com.github.pagehelper.Dialect) 接口，然后配置该属性为实现类的全限定名称。
下面几个参数都是针对默认 dialect 情况下的参数。使用自定义 dialect 实现时，下面的参数没有任何作用。

* helperDialect：分页插件会自动检测当前的数据库链接，自动选择合适的分页方式。 你可以配置helperDialect属性来指定分页插件使用哪种方言。配置时，可以使用下面的缩写值：
oracle,mysql,mariadb,sqlite,hsqldb,postgresql,db2,sqlserver,informix,h2,sqlserver2012,derby
特别注意：使用 SqlServer2012 数据库时，需要手动指定为 sqlserver2012，否则会使用 SqlServer2005 的方式进行分页。
你也可以实现 AbstractHelperDialect，然后配置该属性为实现类的全限定名称即可使用自定义的实现方法。

* offsetAsPageNum：默认值为 false，该参数对使用 RowBounds 作为分页参数时有效。 当该参数设置为 true 时，会将 RowBounds 中的 offset 参数当成 pageNum 使用，可以用页码和页面大小两个参数进行分页。

* rowBoundsWithCount：默认值为false，该参数对使用 RowBounds 作为分页参数时有效。 当该参数设置为true时，使用 RowBounds 分页会进行 count 查询。

* pageSizeZero：默认值为 false，当该参数设置为 true 时，如果 pageSize=0 或者 RowBounds.limit = 0 就会查询出全部的结果（相当于没有执行分页查询，但是返回结果仍然是 Page 类型）。

* reasonable：分页合理化参数，默认值为false。当该参数设置为 true 时，pageNum<=0 时会查询第一页， pageNum>pages（超过总数时），会查询最后一页。默认false 时，直接根据参数进行查询。

* params：为了支持startPage(Object params)方法，增加了该参数来配置参数映射，用于从对象中根据属性名取值， 可以配置 pageNum,pageSize,count,pageSizeZero,reasonable，不配置映射的用默认值， 默认值为pageNum=pageNum;pageSize=pageSize;count=countSql;reasonable=reasonable;pageSizeZero=pageSizeZero。

* supportMethodsArguments：支持通过 Mapper 接口参数来传递分页参数，默认值false，分页插件会从查询方法的参数值中，自动根据上面 params 配置的字段中取值，查找到合适的值时就会自动分页。 使用方法可以参考测试代码中的 com.github.pagehelper.test.basic 包下的 ArgumentsMapTest 和 ArgumentsObjTest。

* autoRuntimeDialect：默认值为 false。设置为 true 时，允许在运行时根据多数据源自动识别对应方言的分页 （不支持自动选择sqlserver2012，只能使用sqlserver），用法和注意事项参考下面的场景五。

* closeConn：默认值为 true。当使用运行时动态数据源或没有设置 helperDialect 属性自动获取数据库类型时，会自动获取一个数据库连接， 通过该属性来设置是否关闭获取的这个连接，默认true关闭，设置为 false 后，不会关闭获取的连接，这个参数的设置要根据自己选择的数据源来决定。

* aggregateFunctions(5.1.5+)：默认为所有常见数据库的聚合函数，允许手动添加聚合函数（影响行数），所有以聚合函数开头的函数，在进行 count 转换时，会套一层。其他函数和列会被替换为 count(0)，其中count列可以自己配置。

> 重要提示：

当 offsetAsPageNum=false 的时候，由于 PageNum 问题，RowBounds查询的时候 reasonable 会强制为 false。使用 PageHelper.startPage 方法不受影响。

## 如何选择配置这些参数
这里列举一些可能会用到某些参数的情况。

* 场景一
如果你仍然在用类似ibatis式的命名空间调用方式，你也许会用到rowBoundsWithCount， 分页插件对RowBounds支持和 MyBatis 默认的方式是一致，默认情况下不会进行 count 查询，如果你想在分页查询时进行 count 查询， 以及使用更强大的 PageInfo 类，你需要设置该参数为 true。

注： PageRowBounds 想要查询总数也需要配置该属性为 true。

* 场景二
如果你仍然在用类似ibatis式的命名空间调用方式，你觉得 RowBounds 中的两个参数 offset,limit 不如 pageNum,pageSize 容易理解， 你可以使用 offsetAsPageNum 参数，将该参数设置为 true 后，offset会当成 pageNum 使用，limit 和 pageSize 含义相同。

* 场景三
如果觉得某个地方使用分页后，你仍然想通过控制参数查询全部的结果，你可以配置 pageSizeZero 为 true， 配置后，当 pageSize=0 或者 RowBounds.limit = 0 就会查询出全部的结果。

* 场景四
如果你分页插件使用于类似分页查看列表式的数据，如新闻列表，软件列表， 你希望用户输入的页数不在合法范围（第一页到最后一页之外）时能够正确的响应到正确的结果页面， 那么你可以配置 reasonable 为 true，这时如果 pageNum<=0 会查询第一页，如果 pageNum>总页数 会查询最后一页。

* 场景五
如果你在 Spring 中配置了动态数据源，并且连接不同类型的数据库，这时你可以配置 autoRuntimeDialect 为 true，这样在使用不同数据源时，会使用匹配的分页进行查询。 这种情况下，你还需要特别注意 closeConn 参数，由于获取数据源类型会获取一个数据库连接，所以需要通过这个参数来控制获取连接后，是否关闭该连接。 默认为 true，有些数据库连接关闭后就没法进行后续的数据库操作。而有些数据库连接不关闭就会很快由于连接数用完而导致数据库无响应。所以在使用该功能时，特别需要注意你使用的数据源是否需要关闭数据库连接。

当不使用动态数据源而只是自动获取 helperDialect 时，数据库连接只会获取一次，所以不需要担心占用的这一个连接是否会导致数据库出错，但是最好也根据数据源的特性选择是否关闭连接。

## 相关说明及API

1、PageHelper的优点是，分页和Mapper.xml完全解耦。实现方式是以插件的形式，对Mybatis执行的流程进行了强化，添加了总数count和limit查询。属于物理分页。

2、Page page = PageHelper.startPage(pageNum, pageSize, true); - true表示需要统计总数，这样会多进行一次请求select count(0); 省略掉true参数只返回分页数据。 

   1)统计总数，（将SQL语句变为 select count(0) from xxx,只对简单SQL语句其效果，复杂SQL语句需要自己写）

    Page<?> page = PageHelper.startPage(1,-1);

    long count = page.getTotal();

   2)分页，pageNum - 第N页， pageSize - 每页M条数

   > A、只分页不统计(每次只执行分页语句)

    PageHelper.startPage([pageNum],[pageSize]);

    List<?> pagelist = queryForList( xxx.class, "queryAll" , param);

    //pagelist就是分页之后的结果

   > B、分页并统计（每次执行2条语句，一条select count语句，一条分页语句）适用于查询分页时数据发生变动，需要将实时的变动信息反映到分页结果上

    Page<?> page = PageHelper.startPage([pageNum],[pageSize],[iscount]);

    List<?> pagelist = queryForList( xxx.class , "queryAll" , param);

    long count = page.getTotal();

    //也可以 List<?> pagelist = page.getList();  获取分页后的结果集

   3)使用PageHelper查全部（不分页）

    PageHelper.startPage(1,0);

    List<?> alllist = queryForList( xxx.class , "queryAll" , param);

   4)PageHelper的其他API

    String orderBy = PageHelper.getOrderBy();    //获取orderBy语句

    Page<?> page = PageHelper.startPage(Object params);

    Page<?> page = PageHelper.startPage(int pageNum, int pageSize);

    Page<?> page = PageHelper.startPage(int pageNum, int pageSize, boolean isCount);

    Page<?> page = PageHelper.startPage(pageNum, pageSize, orderBy);

    Page<?> page = PageHelper.startPage(pageNum, pageSize, isCount, isReasonable);    //isReasonable分页合理化,null时用默认配置

    Page<?> page = PageHelper.startPage(pageNum, pageSize, isCount, isReasonable, isPageSizeZero);    //isPageSizeZero是否支持PageSize为0，true且pageSize=0时返回全部结果，false时分页,null时用默认配置

   5)、默认值

    //RowBounds参数offset作为PageNum使用 - 默认不使用

    private boolean offsetAsPageNum = false;

    //RowBounds是否进行count查询 - 默认不查询

    private boolean rowBoundsWithCount = false;

    //当设置为true的时候，如果pagesize设置为0（或RowBounds的limit=0），就不执行分页，返回全部结果

    private boolean pageSizeZero = false;

    //分页合理化

    private boolean reasonable = false;

    //是否支持接口参数来传递分页参数，默认false

    private boolean supportMethodsArguments = false;  

 
3、分页安全性问题
 
PageHelper 方法使用了静态的 ThreadLocal 参数，分页参数和线程是绑定的。
 
只要你可以保证在 PageHelper 方法调用后紧跟 MyBatis 查询方法，这就是安全的。因为 PageHelper 在 finally 代码段中自动清除了 ThreadLocal 存储的对象。
 
如果代码在进入 Executor 前发生异常，就会导致线程不可用，这属于人为的 Bug（例如接口方法和 XML 中的不匹配，导致找不到 MappedStatement 时）， 这种情况由于线程不可用，也不会导致 ThreadLocal 参数被错误的使用。
 
> 下面这样的代码是不安全的用法：
 
    PageHelper.startPage(1, 10);
    List<Country> list;
    if(param1 != null){
        list = countryMapper.selectIf(param1);
    } else {
        list = new ArrayList<Country>();
    }
这种情况下由于 param1 存在 null 的情况，就会导致 PageHelper 生产了一个分页参数，但是没有被消费，这个参数就会一直保留在这个线程上。当这个线程再次被使用时，就可能导致不该分页的方法去消费这个分页参数，这就产生了错误的分页。
 
> 应该写成下面这个样子：
 
    List<Country> list;
    if(param1 != null){
        PageHelper.startPage(1, 10);
        list = countryMapper.selectIf(param1);
    } else {
        list = new ArrayList<Country>();
    }
这种写法就能保证安全。
 
> 或者可以手动清理 ThreadLocal 存储的分页参数，如下所示：
 
    List<Country> list;
    if(param1 != null){
        PageHelper.startPage(1, 10);
        try{
            list = countryMapper.selectAll();
        } finally {
            PageHelper.clearPage();
        }
    } else {
        list = new ArrayList<Country>();
    }

[Mybatis-PageHelper](https://github.com/pagehelper/Mybatis-PageHelper/blob/master/wikis/zh/HowToUse.md)