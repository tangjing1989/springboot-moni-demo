package com.cn.web.util;

import org.apache.ibatis.cache.CacheException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
/**
 * Describe:
 * User:tangjing
 * Date:17/1/13
 * Time:下午5:46
 */

public class SerializeUtil {
    public static byte[] serialize(Object object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            final byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (final Exception e) {
            throw new CacheException(e);
        }
    }

    public static Object unserialize(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        ByteArrayInputStream bais = null;
        try {
            bais = new ByteArrayInputStream(bytes);
            final ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (final Exception e) {
            throw new CacheException(e);
        }
    }


}
