package run.halo.app.service;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RedisService {

    boolean expire(String key, long time);

    long getExpire(String key);

    boolean hasKey(String key);
    boolean hHasKey(String key, String item);
    boolean sHasKey(String key, Object value);

    void del(String key);
    void del(Collection<String> keys);
    void hdel(String key, Object... item);
    long setRemove(String key, Object... values);
    long lRemove(String key, long count, Object value);

    Object get(String key);
    Object hget(String key, String item);
    Map<Object, Object> hmget(String key);
    Set<Object> sGet(String key);
    List<Object> lGet(String key, long start, long end);
    Object lGetIndex(String key, long index);

    void set(String key, Object value);
    void set(String key, Object value, long time);
    void hmset(String key, Map<String, Object> map);
    void hmset(String key, Map<String, Object> map, long time);
    void hset(String key, String item, Object value);
    void hset(String key, String item, Object value, long time);
    long sSet(String key, Object... values);
    long sSetAndTime(String key, long time, Object... values);
    void lSet(String key, Object value);
    void lSet(String key, Object value, long time);
    void lSet(String key, List<Object> value);
    void lSet(String key, List<Object> value, long time);
    void lUpdateIndex(String key, long index, Object value);


    long sGetSetSize(String key);
    long lGetListSize(String key);



}
