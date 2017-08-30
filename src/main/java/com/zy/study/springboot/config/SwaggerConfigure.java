package com.zy.study.springboot.config;

import static com.google.common.base.Predicates.or;

import com.google.common.base.Predicate;
import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Created by zy on 17-8-30.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfigure {

    /**
     * 可以定义多个组，比如本类中定义把test和demo区分开了
     * （访问页面就可以看到效果了）
     *
     */
//    @Bean
//    public Docket testApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("test")
//                .genericModelSubstitutes(DeferredResult.class)
////                .genericModelSubstitutes(ResponseEntity.class)
//                .useDefaultResponseMessages(false)
//                .forCodeGeneration(true)
//                .pathMapping("/")// base，最终调用接口后会和paths拼接在一起
//                .select()
//                .paths(or(regex("/api/.*")))//过滤的接口
//                .build();
////                .apiInfo(testApiInfo());
//    }
//
//    @Bean
//    public Docket demoApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("demo")
//                .genericModelSubstitutes(DeferredResult.class)
////              .genericModelSubstitutes(ResponseEntity.class)
//                .useDefaultResponseMessages(false)
//                .forCodeGeneration(false)
//                .pathMapping("/")
//                .select()
//                .paths(or(regex("/demo/.*")))//过滤的接口
//                .build();
////                .apiInfo(demoApiInfo());
//    }

    /**
     * 配置了对RestController类 和 添加了 ResponseBody 的方法的监听
     */
    @Bean
    public Docket createRestApi() {
        Predicate<RequestHandler> predicate = new Predicate<RequestHandler>() {
            @Override
            public boolean apply(RequestHandler input) {
                Class<?> declaringClass = input.declaringClass();
                if (declaringClass == BasicErrorController.class)// 排除
                    return false;
                // 被注解的类
                return declaringClass.isAnnotationPresent(RestController.class) || input.isAnnotatedWith(ResponseBody.class);
            }
        };
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("API 接口测试")//大标题
                .version("1.0")//版本
                .build();
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .useDefaultResponseMessages(false)
                .select()
                .apis(predicate)
                .build();
    }

//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()  // 选择那些路径和api会生成document
//                .apis(RequestHandlerSelectors.any()) // 对所有api进行监控
//                .paths(PathSelectors.any()) // 对所有路径进行监控
//                .build();
//    }
}
