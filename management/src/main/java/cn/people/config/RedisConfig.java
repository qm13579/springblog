package cn.people.config;

import cn.hutool.core.lang.Assert;
import cn.hutool.crypto.digest.DigestUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;

/**
 * @author : FENGZHI
 * create at:  2020/3/14  下午9:08
 * @description:
 */
@Slf4j
@EnableCaching
@Configuration
@ConditionalOnClass(RedisOperations.class)
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfig extends CachingConfigurerSupport {
    /**
     * 设置redis数据默认过期时间，默认2小时
     * 设置cacheable 序列化方式
     * @return
     */
    @Bean
    public RedisCacheConfiguration redisCacheConfiguration(){

        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();
        configuration = configuration.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(
                fastJsonRedisSerializer)).entryTtl(Duration.ofHours(2));
        return configuration;
    }

    @Bean(name = "redisTemplate")
    @ConditionalOnMissingBean(name = "redisTemplate")
    public RedisTemplate<Object,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){

        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        //序列化方式
        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
        //value值采用fastJsonRedisSerializer序列化方式
        template.setValueSerializer(fastJsonRedisSerializer);
        template.setHashValueSerializer(fastJsonRedisSerializer);
        //开启全局autotype--方便开发
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        //建议使用小范围白名单
        ParserConfig.getGlobalInstance().addAccept("cn.people.domain");
        //key 的序列化采用StringRedisSerializer
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setConnectionFactory(redisConnectionFactory);
        //替换默认的序列化
//        template.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }
    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return (target,method,params) ->{
            HashMap<Object, Object> container = new HashMap<>(3);
            Class<?> targetClass = target.getClass();
            //类地址
            container.put("class",targetClass.toGenericString());
            //方法名称
            container.put("method",targetClass.getPackage());
            //包名称
            container.put("package",targetClass.getPackage());
            //转为json字符串
            for(int i = 0;i<params.length;i++){
                container.put(String.valueOf(i),params[i]);
            }
            String jsonString = JSON.toJSONString(container);
            //做sha4⃣️6 hash计算 得到一个key
            return DigestUtil.sha256(jsonString);
        };
    }

    /**
     * 自定义异常
     * @return
     */
    @Bean
    @Override
    public CacheErrorHandler errorHandler() {
        log.info("初始化 ->[{}]","redis CacheErrorHandle");

        return new CacheErrorHandler() {
            @Override
            public void handleCacheGetError(RuntimeException e, Cache cache, Object o) {
                log.error("Redis occur handleCacheGetError：key -> [{}]", o, e);
            }

            @Override
            public void handleCachePutError(RuntimeException e, Cache cache, Object o, Object o1) {
                log.error("Redis occur handleCachePutError：key -> [{}]；value -> [{}]", o, o1, e);
            }

            @Override
            public void handleCacheEvictError(RuntimeException e, Cache cache, Object o) {
                log.error("Redis occur handleCacheGetError：key -> [{}]", o, e);
            }

            @Override
            public void handleCacheClearError(RuntimeException e, Cache cache) {
                log.error("Redis occur handleCacheGetError:", e);
            }
        };
    }
}

/**
 * 定义序列化工具
 * @param <T>
 */
class FastJsonRedisSerializer<T> implements RedisSerializer<T>{

    private Class<T> clazz;


    public FastJsonRedisSerializer(Class<T> clazz) {
        super();
        this.clazz = clazz;
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (t == null){
            return new byte[0];
        }
        return JSON.toJSONString(t, SerializerFeature.WriteClassName).getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length <= 0){
            return null;
        }
        String str = new String(bytes, StandardCharsets.UTF_8);
        return JSON.parseObject(str,clazz);
    }
}

/**
 * 重写序列化器
 */
class StringRedisSerializer implements RedisSerializer<Object>{

    private final Charset charset;

    StringRedisSerializer(Charset charset) {
        Assert.notNull(charset,"chart must not be null");
        this.charset = charset;
    }

    public StringRedisSerializer() {
        this(StandardCharsets.UTF_8);
    }

    @Override
    public byte[] serialize(Object o) throws SerializationException {
        String string = JSON.toJSONString(o);

        if (StringUtils.isBlank(string)){
            return null;
        }
        string = string.replaceAll("/","");

        return string.getBytes(charset);
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        return (bytes == null ? null : new String(bytes,charset));
    }
}
