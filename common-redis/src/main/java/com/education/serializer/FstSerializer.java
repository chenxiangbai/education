package com.education.serializer;

import io.jsonwebtoken.lang.Assert;
import org.nustaq.serialization.FSTConfiguration;

import java.nio.charset.Charset;

/**
 * @author xiangb.chen
 * @Version
 * @Date 2019/8/28
 * @Description 所有的自定义Bean对象都需要实现Serializable
 */
public class FstSerializer implements org.springframework.data.redis.serializer.RedisSerializer<Object> {
    private static FSTConfiguration configuration = FSTConfiguration.createDefaultConfiguration();
    static {
        configuration.setStructMode(true);
        configuration.clearCaches();
        FSTConfiguration.clearGlobalCaches();
    }
    private final Charset charset;

    private final String target = "\"";

    private final String replacement = "";

    public FstSerializer() {
        this(Charset.forName("UTF8"));
    }

    public FstSerializer(Charset charset) {
        Assert.notNull(charset, "Charset must not be null!");
        this.charset = charset;
    }

    @Override
    public Object deserialize(byte[] bytes) {
        if (bytes == null){
            return null;
        }
        return configuration.asObject(bytes);
    }

    @Override
    public byte[] serialize(Object object){
        if (object == null) {
            return null;
        }
        return configuration.asByteArray(object);
    }
}
