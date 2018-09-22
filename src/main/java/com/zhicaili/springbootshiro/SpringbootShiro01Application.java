package com.zhicaili.springbootshiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = "com.zhicaili.springbootshiro.mapper")
public class SpringbootShiro01Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootShiro01Application.class, args);
    }
}
