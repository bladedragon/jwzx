package team.redrock.jwzxspider.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import team.redrock.jwzxspider.entitiy.EmptyRoom;

import java.net.UnknownHostException;
import java.util.List;

@Configuration
public class RedisConfig {  //构造属于自己的序列化器
    @Bean
    public RedisTemplate<Object, Object> objectRedisTemplate(
            RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
        template.setConnectionFactory(redisConnectionFactory);
        //使用json的序列化器
        Jackson2JsonRedisSerializer ser = new Jackson2JsonRedisSerializer<Object>(Object.class);
//        JdkSerializationRedisSerializer ser = new JdkSerializationRedisSerializer();
        template.setDefaultSerializer(ser);                 //相当于key的序列化类型和value的序列化类型
        return template;
    }

    @Bean
    public RedisTemplate<String, EmptyRoom> EmptyredisTemplate(
            RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<String, EmptyRoom> template = new RedisTemplate<String, EmptyRoom>();
        template.setConnectionFactory(redisConnectionFactory);
        //使用json的序列化器
        Jackson2JsonRedisSerializer ser = new Jackson2JsonRedisSerializer<EmptyRoom>(EmptyRoom.class);
//       JdkSerializationRedisSerializer ser = new JdkSerializationRedisSerializer();
        template.setDefaultSerializer(ser);                 //相当于key的序列化类型和value的序列化类型
        return template;
    }
}




