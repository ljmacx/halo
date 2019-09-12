package run.halo.app.cache;

import org.springframework.beans.factory.annotation.Autowired;
import run.halo.app.service.RedisService;

import java.util.Optional;
import java.util.Timer;

public class RedisCacheStore extends StringCacheStore {

    private final static long PERIOD = 60 * 1000 * 100;
    private final Timer timer;

    @Autowired
    RedisService redisService;

    public RedisCacheStore(){
        this.timer = new Timer();
    }

    @Override
    Optional<CacheWrapper<String>> getInternal(String key) {
        Object value = redisService.get(key);
            return Optional.ofNullable((CacheWrapper<String>)value);
    }

    @Override
    void putInternal(String key, CacheWrapper<String> cacheWrapper) {
        redisService.set(key, cacheWrapper);
    }

    @Override
    Boolean putInternalIfAbsent(String key, CacheWrapper<String> cacheWrapper) {

            Optional<String> valueOp = get(key);
            if (valueOp.isPresent()){
                return false;
            }
            putInternal(key, cacheWrapper);

            return true;

    }

    @Override
    public void delete(String key) {
        redisService.del(key);
    }
}
