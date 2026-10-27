package com.stucoursesys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StuCourseSysApplication implements CommandLineRunner {

    @Value("${server.port:8080}")
    private String port;

    public static void main(String[] args) {
        SpringApplication.run(StuCourseSysApplication.class, args);
    }

    @Override
    public void run(String... args) {
        String baseUrl = "http://localhost:" + port;
        System.out.println();
        System.out.println("接口地址（控制台中可直接点击）：");
        System.out.println("  学生列表      " + baseUrl + "/api/students");
        System.out.println("  成绩查询      " + baseUrl + "/api/students/2023010001/scores");
        System.out.println("  空结果验证    " + baseUrl + "/api/students/9999999999/scores");
        System.out.println();
    }
}
