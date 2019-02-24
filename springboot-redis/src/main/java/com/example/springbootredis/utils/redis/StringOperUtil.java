package com.example.springbootredis.utils.redis;

import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * descripiton: String 字符串操作
 *
 * @author: kinson(2219945910@qq.com)
 * @date: 2019/2/22
 * @time: 22:01
 * @modifier:
 * @since:
 */
public class StringOperUtil {

    public static void oper(Jedis jedis) {
        System.out.println("*************************************");
        System.out.println("**********String 字符串操作***********");
        //1、设置key的值为redis
        String set = jedis.set("hello", "redis");
        System.out.println("设置key的值为redis:" + set + "---" + jedis.get("hello"));

        //2、设置key的值为hello, redis覆盖原先的值
        String hello = jedis.set("hello", "hello");
        System.out.println("设置key的值为hello, redis覆盖原先的值:" + hello + "---" + jedis.get("hello"));

        //3、setex 存活时间为10s
        String setex = jedis.setex("hi", 10, "hi");
        Long ttls = jedis.ttl("hi");
        System.out.println("设置key的值为redis，存活时间为10s:" + setex + "---" + jedis.get("hi") + ", ttl:" + ttls);

        //4、psetex存活时间为10000ms
        String psetex = jedis.psetex("hei", 10000L, "hei");
        Long ttlp = jedis.ttl("hei");
        System.out.println("设置key的值为redis，存活时间为10s:" + setex + "---" + jedis.get("hei") + ", ttl:" + ttlp);

        /*4、setnx只在键 key 不存在的情况下， 将键 key 的值设置为 value 。
        若键 key 已经存在， 则 SETNX 命令不做任何动作。设置成功时返回 1 ， 设置失败时返回 0
		SETNX 是『SET if Not eXists』(如果不存在，则 SET)的简写。在分布式锁中可以使用到。*/
        Long setnx = jedis.setnx("setnx", "fjw");
        System.out.println("setnx :" + setnx + "---" + jedis.get("setnx"));
        Long setnx2 = jedis.setnx("setnx", "gmx");
        System.out.println("setnx2 :" + setnx2 + "---" + jedis.get("setnx"));

        /**
         * getSet将键 key 的值设为 value ， 并返回键 key 在被设置之前的旧值。

         返回值
         返回给定键 key 的旧值。

         如果键 key 没有旧值， 也即是说， 键 key 在被设置之前并不存在， 那么命令返回 nil 。

         当键 key 存在但不是字符串类型时， 命令返回一个错误。
         */
        String getSet = jedis.getSet("getSet", "getSet");
        System.out.println("getSet :" + getSet + "---" + jedis.get("getSet"));
        String getSet2 = jedis.getSet("setnx", "getSet");
        System.out.println("getSet2 :" + getSet2 + "---" + jedis.get("setnx"));

        //strlen字符串长度
        Long strlen = jedis.strlen("setnx");
        System.out.println("strlen :" + strlen);

        /**
         * 如果键 key 已经存在并且它的值是一个字符串， APPEND 命令将把 value 追加到键 key 现有值的末尾。

         如果 key 不存在， APPEND 就简单地将键 key 的值设为 value ， 就像执行 SET key value 一样。

         返回值
         追加 value 之后， 键 key 的值的长度。
         */
        Long append = jedis.append("append", "append");
        System.out.println("append :" + append);
        Long appendR = jedis.append("append", " redis");
        System.out.println("appendR :" + appendR);

        //从第几个位置开始替换
        Long setrange = jedis.setrange("append", 7, "hello");
        System.out.println("setrange :" + setrange);

        //获取指定区间内的子串
        String getrange = jedis.getrange("append", 7, -1);
        System.out.println("getrange :" + getrange);

        //设置多个key/value
        String mset = jedis.mset("k1", "v1", "k2", "v2");
        System.out.println("mset:" + mset);
        //获取多个key的value
        List<String> mget = jedis.mget("k1", "k2", "k3");
        System.out.println("mget:" + mget);
        List<String> mget2 = jedis.mget("k1", "remain_num_key");
        System.out.println("mget2:" + mget2);

        /**
         * 当且仅当所有给定键都不存在时， 为所有给定键设置值。

         即使只有一个给定键已经存在， MSETNX 命令也会拒绝执行对所有键的设置操作。

         MSETNX 是一个原子性(atomic)操作， 所有给定键要么就全部都被设置， 要么就全部都不设置， 不可能出现第三种状态。

         返回值
         当所有给定键都设置成功时， 命令返回 1 ； 如果因为某个给定键已经存在而导致设置未能成功执行， 那么命令返回 0 。
         */
        Long msetnx = jedis.msetnx("k1", "vk1", "k3", "v3");
        System.out.println("msetnx：" + msetnx);
        Long msetnx2 = jedis.msetnx("k4", "vk1", "k3", "v3");
        System.out.println("msetnx2：" + msetnx2);

        /**
         * 为键 key 储存的数字值加上一。

         如果键 key 不存在， 那么它的值会先被初始化为 0 ， 然后再执行 INCR 命令。

         如果键 key 储存的值不能被解释为数字， 那么 INCR 命令将返回一个错误。

         本操作的值限制在 64 位(bit)有符号数字表示之内。
         返回键 key 在执行加一操作之后的值。
         */
        Long incr = jedis.incr("incr");
        System.out.println("incr:" + incr);

        /**
         * 为键 key 储存的数字值加上增量 increment 。

         如果键 key 不存在， 那么键 key 的值会先被初始化为 0 ， 然后再执行 INCRBY 命令。

         如果键 key 储存的值不能被解释为数字， 那么 INCRBY 命令将返回一个错误。
         返回在加上增量 increment 之后， 键 key 当前的值。
         */
        Long incrBy = jedis.incrBy("incr", 5);
        System.out.println("incrBy:" + incrBy);

        //与incr相反
        Long decr = jedis.decr("decr");
        System.out.println("decr:" + decr);
        Long decr2 = jedis.decr("incr");
        System.out.println("decr2:" + decr2);
        Long decrBy = jedis.decrBy("incr", 5);
        System.out.println("decrBy:" + decrBy);

        //浮点数据增长
        Double incrByFloat = jedis.incrByFloat("incr", 3.1415926);
        System.out.println("incrByFloat:" + incrByFloat);
    }
}
