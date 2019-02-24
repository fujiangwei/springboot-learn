## Redis介绍
[Redis 介绍](https://www.cnblogs.com/kingsonfu/p/10407413.html)

## Jedis常用API使用
> 字符串
 
SET/GET：设置和获取key/value

    String set = jedis.set("hello", "redis");
    System.out.println("设置key的值为redis:" + set + "---" + jedis.get("hello"));
    
SETEX：设置存活时间为10（s）

    String setex = jedis.setex("hi", 10, "hi");
    Long ttls = jedis.ttl("hi");
    System.out.println("设置key的值为redis，存活时间为10s:" + setex + "---" + jedis.get("hi") + ", ttl:" + ttls);

SETNX：SETNX 是『SET if Not eXists』(如果不存在，则 SET)的简写。只在键key不存在的情况下，将键key的值设置为value。若键key已经存在，则SETNX命令不做任何动作。
设置成功时返回1，设置失败时返回0，方方法可用在分布式锁中。

    Long setnx = jedis.setnx("setnx", "fjw");
    System.out.println("setnx :" + setnx + "---" + jedis.get("setnx"));
    Long setnx2 = jedis.setnx("setnx", "gmx");
    System.out.println("setnx2 :" + setnx2 + "---" + jedis.get("setnx"));
    
PSETEX：设置存活时间为10000（ms）

    String psetex = jedis.psetex("hei", 10000L, "hei");
    Long ttlp = jedis.ttl("hei");
    System.out.println("设置key的值为redis，存活时间为10s:" + setex + "---" + jedis.get("hei") + ", ttl:" + ttlp);

GETSET：将键key的值设为value，并返回键key在被设置之前的旧值，若key没有旧值则返回null。

    String getSet = jedis.getSet("getSet", "getSet");
    System.out.println("getSet :" + getSet + "---" + jedis.get("getSet"));
    String getSet2 = jedis.getSet("setnx", "getSet");
    System.out.println("getSet2 :" + getSet2 + "---" + jedis.get("setnx"));
    
STRLEN：字符串长度

    Long strlen = jedis.strlen("setnx");
    System.out.println("strlen :" + strlen);

APPEND：给指定key的值追加值，若key不存在则如同SET操作

    Long append = jedis.append("append", "append");
    System.out.println("append :" + append);
    Long appendR = jedis.append("append", " redis");
    System.out.println("appendR :" + appendR);

SETRANGE：从指定位置给key对应的值替换给定内容

    Long setrange = jedis.setrange("append", 7, "hello");
    System.out.println("setrange :" + setrange);

GETRANGE：从指定位置获取去key对应的子串内容

    String getrange = jedis.getrange("append", 7, -1);
    System.out.println("getrange :" + getrange);

INCR：++1操作

    Long incr = jedis.incr("incr");
    System.out.println("incr:" + incr);

INCRBY：按给定的增量幅度递增
    
    Long incrBy = jedis.incrBy("incr", 5);
    System.out.println("incrBy:" + incrBy);

INCRBYFLOAT：按给定的浮点数据类型增量幅度递增

    Double incrByFloat = jedis.incrByFloat("incr", 3.1415926);
    System.out.println("incrByFloat:" + incrByFloat);

DECR：--1操作

    //与incr相反
    Long decr = jedis.decr("decr");
    System.out.println("decr:" + decr);
    Long decr2 = jedis.decr("incr");
    System.out.println("decr2:" + decr2);

DECRBY：按给定的减量幅度递减

    Long decrBy = jedis.decrBy("incr", 5);
    System.out.println("decrBy:" + decrBy);

MSET：同时设置多个key/value数据

    String mset = jedis.mset("k1", "v1", "k2", "v2");
    System.out.println("mset:" + mset);

MGET：同时获取多个key对应的值

     List<String> mget = jedis.mget("k1", "k2", "k3");
     System.out.println("mget:" + mget);
     List<String> mget2 = jedis.mget("k1", "remain_num_key");
     System.out.println("mget2:" + mget2);

MSETNX：当且仅当所有给定键都不存在时， 为所有给定键设置值。即使只有一个给定键已经存在， MSETNX 命令也会拒绝执行对所有键的设置操作。

    Long msetnx = jedis.msetnx("k1", "vk1", "k3", "v3");
    System.out.println("msetnx：" + msetnx);
    Long msetnx2 = jedis.msetnx("k4", "vk1", "k3", "v3");
    System.out.println("msetnx2：" + msetnx2);

    