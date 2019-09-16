package com.example.springbootrediscluster.config.client;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.*;

/**
 * descripiton: 客户端
 *
 * @author: kinson(2219945910 @ qq.com)
 * @date: 2018/10/21
 * @time: 22:58
 * @modifier:
 * @since:
 */
@Component
public class RedisClient {

    private static final Logger logger = LoggerFactory.getLogger(RedisClient.class);

    @Autowired
    private JedisSentinelPool jedisPool;

    public Jedis getJedis() {
        return jedisPool.getResource();
    }

    /**
     * 读取缓存,若不存在则设置读取
     *
     * @param key
     * @param initValue
     * @return
     */
    public void setOrElse(final String key, final String initValue) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();

            if (jedis.exists(key)) {
                logger.info("{}={}已存在", key, jedis.get(key));
            } else {
                jedis.set(key, initValue);
                logger.info("{}不存在,已成功设置初始值{}", key, jedis.get(key));
            }
        } catch (Exception e) {
            logger.error("[RedisClient] setOrElse e,", e);
        } finally {
            close(jedis);
        }
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return Boolean
     */
    public String set(final String key, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.set(key, String.valueOf(value));
        } catch (Exception e) {
            logger.error("[RedisClient] set e,", e);
            return "";
        } finally {
            close(jedis);
        }
    }

    /**
     * 写入缓存设置时效时间
     *
     * @param key
     * @param value
     * @param expireTime
     * @return
     */
    public Long setExpire(final String key, Object value, Integer expireTime) {
        try {
            Jedis jedis = jedisPool.getResource();
            return jedis.expire(key, expireTime);
        } catch (Exception e) {
            logger.error("[RedisClient] setExpire 异常", e);
        }
        return -1L;
    }

    /**
     * 批量删除对应的value
     *
     * @param keys
     */
    public void removeBatch(final String... keys) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            for (String key : keys) {
                if (exists(key)) {
                    jedis.del(key);
                }
            }
        } catch (Exception e) {
            logger.error("[RedisClient] removeBatch 异常", e);
        } finally {
            close(jedis);
        }

    }

    /**
     * 批量删除key
     *
     * @param pattern
     */
    public void removePattern(final String pattern) {
        Jedis jedis = jedisPool.getResource();
        Set<String> keys = jedis.keys(pattern);
        if (keys.size() <= 0) {
            return;
        }
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 删除对应的value
     *
     * @param key
     */
    public void remove(final String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            if (jedis.exists(key)) {
                jedis.del(key);
            }
        } catch (Exception e) {
            logger.error("[RedisClient] remove e,", e);
        } finally {
            close(jedis);
        }
    }

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.exists(key);
        } catch (Exception e) {
            logger.error("[RedisClient] exists e,", e);
            return false;
        } finally {
            close(jedis);
        }
    }

    public Long incr(final String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.incr(key);
        } catch (Exception e) {
            logger.error("[RedisClient] incr e,", e);
            throw new IllegalArgumentException("加1异常");
        } finally {
            close(jedis);
        }
    }

    public Long decr(final String key, final Integer by) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.decrBy(key, by);
        } catch (Exception e) {
            logger.error("[RedisClient] decr e,", e);
            throw new IllegalArgumentException("减1异常");
        } finally {
            close(jedis);
        }
    }


    /**
     * 判断缓存中是否有对应的value
     *
     * @param keys
     * @return
     */
    public List<String> exists(String... keys) {
        Jedis jedis = null;
        try {
            List<String> containKeys = Lists.newArrayList();
            jedis = getJedis();
            for (String key : keys) {
                if (jedis.exists(key)) {
                    containKeys.add(key);
                }
            }
            return containKeys;
        } catch (Exception e) {
            logger.error("[RedisClient] exists e,", e);
            return Collections.emptyList();
        } finally {
            close(jedis);
        }
    }

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public Optional<String> get(final String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return Optional.ofNullable(jedis.get(key));
        } catch (Exception e) {
            logger.error("[RedisClient] get exception,", e);
            return Optional.empty();
        } finally {
            close(jedis);
        }
    }

    /**
     * 读取缓存,若不存在则设置读取
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public Optional<String> getOrElse(final String key, final String defaultValue) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String result = jedis.get(key);
            if (StringUtils.isNotEmpty(result)) {
                return Optional.ofNullable(result);
            }
            jedis.set(key, defaultValue);
            return Optional.ofNullable(jedis.get(key));
        } catch (Exception e) {
            logger.error("[RedisClient] getOrElse e,", e);
            return Optional.empty();
        } finally {
            close(jedis);
        }
    }

    /**
     * 读取缓存,若不存在则设置读取
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public Optional<String> getAndSet(final String key, final String defaultValue) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String result = jedis.get(key);
            if (StringUtils.isNotEmpty(result)) {
                if (StringUtils.equals("1", result)) {
                    jedis.set(key, "2");
                } else {
                    jedis.set(key, "1");
                }
                return Optional.ofNullable(result);
            }
            jedis.set(key, defaultValue);
            return Optional.ofNullable(jedis.get(key));
        } catch (Exception e) {
            logger.error("[RedisClient] getAndSet e,", e);
            return Optional.of("1");
        } finally {
            close(jedis);
        }
    }

    /**
     * 哈希 添加
     *
     * @param key
     * @param hashKey
     * @param value
     */
    public void hmSet(String key, String hashKey, String value) {
        Jedis jedis = jedisPool.getResource();
        jedis.hset(key, hashKey, value);
    }

    /**
     * 哈希获取数据
     *
     * @param key
     * @param hashKey
     * @return
     */
    public Object hmGet(String key, String hashKey) {
        Jedis jedis = jedisPool.getResource();
        return jedis.hget(key, hashKey);
    }

    /**
     * 列表添加
     *
     * @param key
     * @param values
     */
    public void lPush(String key, String... values) {
        Jedis jedis = jedisPool.getResource();
        jedis.lpush(key, values);
    }

    /**
     * 列表获取
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public List<String> lRange(String key, long start, long end) {
        Jedis jedis = jedisPool.getResource();
        return jedis.lrange(key, start, end);
    }

    /**
     * 列表获取长度
     *
     * @param key
     * @return
     */
    public Long llen(String key) {
        Jedis jedis = jedisPool.getResource();
        return jedis.llen(key);
    }

    public boolean set(final String key, final Object value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, JSON.toJSONString(value));
            return true;
        } catch (Exception e) {
            logger.error("[RedisClient] set e,", e);
            return false;
        } finally {
            close(jedis);
        }
    }

    public <T> T getObject(final String key, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String value = jedis.get(key);
            return JSON.parseObject(value, clazz);
        } catch (Exception e) {
            logger.error("[RedisClient] getObject e,", e);
            return null;
        } finally {
            close(jedis);
        }
    }

    public <T> List<T> getList(final String key, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String value = jedis.get(key);
            return JSON.parseArray(value, clazz);
        } catch (Exception e) {
            logger.error("[RedisClient] getList e,", e);
            return new ArrayList<>();
        } finally {
            close(jedis);
        }
    }

    /**
     * 集合添加
     *
     * @param key
     * @param members
     */
    public void add(String key, String... members) {
        Jedis jedis = jedisPool.getResource();
        jedis.sadd(key, members);
    }

    /**
     * 集合获取
     *
     * @param key
     * @return
     */
    public Set<String> setMembers(String key) {
        Jedis jedis = jedisPool.getResource();
        return jedis.smembers(key);
    }

    /**
     * 有序集合添加
     *
     * @param key
     * @param score
     * @param member
     */
    public void zAdd(String key, double score, String member) {
        Jedis jedis = jedisPool.getResource();
        jedis.zadd(key, score, member);
    }

    /**
     * 有序集合获取
     *
     * @param key
     * @param min
     * @param max
     * @return
     */
    public Set<String> rangeByScore(String key, double min, double max) {
        Jedis jedis = jedisPool.getResource();
        return jedis.zrangeByScore(key, min, max);
    }

    public void close(final Jedis jedis) {
        if (null != jedis) {
            jedis.close();
        }
    }
}
