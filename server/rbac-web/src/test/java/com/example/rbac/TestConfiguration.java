package com.example.rbac;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 测试配置类
 *
 * @author Jack
 * @date 2022/05/10
 */
@Configuration
public class TestConfiguration {

    @Bean
    public H2Flusher h2Flusher(JdbcTemplate jdbcTemplate) {
        return new H2Flusher(jdbcTemplate, "DB/rbac.sql");
    }

}

