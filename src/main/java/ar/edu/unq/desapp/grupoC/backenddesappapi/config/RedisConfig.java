package ar.edu.unq.desapp.grupoC.backenddesappapi.config;

import ar.edu.unq.desapp.grupoC.backenddesappapi.model.Review;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
public class RedisConfig {
    @Bean
    public ReactiveRedisOperations<String, Review> reviewTemplate(LettuceConnectionFactory lettuceConnectionFactory){
        RedisSerializer<Review> valueSerializer = new Jackson2JsonRedisSerializer<>(Review.class);
        RedisSerializationContext<String, Review> serializationContext = RedisSerializationContext.<String, Review>newSerializationContext(RedisSerializer.string())
                .value(valueSerializer)
                .build();
        return new ReactiveRedisTemplate<>(lettuceConnectionFactory, serializationContext);
    }
}
