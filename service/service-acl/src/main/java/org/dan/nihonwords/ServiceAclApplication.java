package org.dan.nihonwords;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * 启动类 用springboot注解：SpringBootApplication
 * @author mcd
 * @create 2023-07-14 11:22
 */

//@ComponentScan(basePackages={"org.dan.nihonwords"})
@SpringBootApplication
@EnableSwagger2WebMvc
@EnableDiscoveryClient
public class ServiceAclApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceAclApplication.class, args);
    }

}
