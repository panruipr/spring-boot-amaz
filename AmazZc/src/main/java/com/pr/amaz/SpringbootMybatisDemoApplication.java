package com.pr.amaz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.pr.amaz.mapper")
public class SpringbootMybatisDemoApplication {
    public static void main(String[] args){
        SpringApplication.run(SpringbootMybatisDemoApplication.class,args);
    }
}
