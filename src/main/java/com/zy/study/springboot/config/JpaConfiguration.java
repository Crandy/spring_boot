package com.zy.study.springboot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * @ClassName: JpaConfiguration
 * @Description: Jpa的配置类。
 * @EnableTransactionManagement 启用了JPA 的事务管理
 * @EnableJpaRepositories 启用了JPA资源库并指定了上面定义的接口资源库的位置
 * @EntityScan 指定了定义实体的位置
 * Created by zy on 17-8-29.
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@EnableJpaRepositories(basePackages = "com.zy.study.springboot.repository")
@EntityScan(basePackages = "com.zy.study.springboot.domain")
public class JpaConfiguration {

    private final Logger log = LoggerFactory.getLogger(JpaConfiguration.class);

    @Bean
    PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
