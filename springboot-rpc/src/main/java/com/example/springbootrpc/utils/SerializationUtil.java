package com.example.springbootrpc.utils;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * descripiton:
 *
 * @author: kinson(2219945910@qq.com)
 * @date: 2019/8/15
 * @time: 23:16
 * @modifier:
 * @since:
 */
public class SerializationUtil {

    private static Map<Class<?>, Schema<?>> cachedSchemaMap = new ConcurrentHashMap<>();

    private static Objenesis objenesis = new ObjenesisStd(true);

    public static <T>T deserialize(byte[] data, Class<?> genericClass) {
        try {
            T tMsg = (T)objenesis.newInstance(genericClass);
            Schema<T> schema = (Schema<T>)getSchema(genericClass);
            ProtobufIOUtil.mergeFrom(data, tMsg, schema);

            return tMsg;
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    public static <T> byte[] serialize(T obj) {
        Class<T> aClass = (Class<T>)obj.getClass();
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            Schema<T> schema = getSchema(aClass);
            return ProtobufIOUtil.toByteArray(obj, schema, buffer);
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        } finally {
            buffer.clear();
        }
    }

    private static <T> Schema<T> getSchema(Class<T> clazz) {
        return (Schema<T>)cachedSchemaMap.computeIfAbsent(clazz, RuntimeSchema::createFrom);
    }
}
