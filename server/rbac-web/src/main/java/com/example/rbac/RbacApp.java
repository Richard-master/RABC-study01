package com.example.rbac;

import com.example.common.optimistic.EnableOptimisticLock;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 启动类
 *
 * @author Jack
 * @date 2022/05/10
 */
@SpringBootApplication
@EnableOptimisticLock
@MapperScan("com.example.rbac.dao")
public class RbacApp extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(RbacApp.class);
        app.run(args);
    }

    /**
     * 兼容tomcat部署模式
     *
     * @param application
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(RbacApp.class);
    }
}

