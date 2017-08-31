package com.zy.study.springboot.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.zy.study.springboot.config.util.JSR310DateTimeSerializer;
import com.zy.study.springboot.config.util.JSR310LocalDateDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.*;

/** 
* @ClassName: RedisConfiguration 
* @Description: Redis Config
* @author mengfanzhu
* @date 2017年2月21日 下午2:03:26 
*/

@Configuration
public class RedisConfiguration {
	
	@Bean(name = "redisTemplate")
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory redisFactory){
		StringRedisTemplate template = new StringRedisTemplate(redisFactory);
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
		
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//		JavaTimeModule module = new JavaTimeModule();
//		module.addSerializer(OffsetDateTime.class, JSR310DateTimeSerializer.INSTANCE);
//		module.addSerializer(ZonedDateTime.class, JSR310DateTimeSerializer.INSTANCE);
//		module.addSerializer(LocalDateTime.class, JSR310DateTimeSerializer.INSTANCE);
//		module.addSerializer(Instant.class, JSR310DateTimeSerializer.INSTANCE);
//		module.addDeserializer(LocalDate.class, JSR310LocalDateDeserializer.INSTANCE);
//		jackson2JsonRedisSerializer.setObjectMapper(om);
//		om.registerModule(module);

		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(jackson2JsonRedisSerializer);
		template.afterPropertiesSet();
		return template;
	}

	@Bean(name = "redisObjectTemplate")
	public RedisTemplate<String, Object> redisObjectTemplate(RedisConnectionFactory rcf) {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(rcf);
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new JsonRedisSerializer());
		return template;
	}

	static class JsonRedisSerializer implements RedisSerializer<Object> {

		private final ObjectMapper om;

		public JsonRedisSerializer() {
			this.om = new ObjectMapper().enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
			this.om.setSerializationInclusion(JsonInclude.Include.NON_NULL);
			JavaTimeModule module = new JavaTimeModule();
			module.addSerializer(OffsetDateTime.class, JSR310DateTimeSerializer.INSTANCE);
			module.addSerializer(ZonedDateTime.class, JSR310DateTimeSerializer.INSTANCE);
			module.addSerializer(LocalDateTime.class, JSR310DateTimeSerializer.INSTANCE);
			module.addSerializer(Instant.class, JSR310DateTimeSerializer.INSTANCE);
			module.addDeserializer(LocalDate.class, JSR310LocalDateDeserializer.INSTANCE);
			this.om.registerModule(module);
		}

		@Override
		public byte[] serialize(Object t) throws SerializationException {
			try {
				return om.writeValueAsBytes(t);
			} catch (JsonProcessingException e) {
				throw new SerializationException(e.getMessage(), e);
			}
		}

		@Override
		public Object deserialize(byte[] bytes) throws SerializationException {
			if (bytes == null) {
				return null;
			}
			try {
				return om.readValue(bytes, Object.class);
			} catch (Exception e) {
				throw new SerializationException(e.getMessage(), e);
			}
		}
	}
}
