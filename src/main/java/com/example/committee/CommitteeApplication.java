package com.example.committee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// 启动类分析
public class CommitteeApplication {
    public static void main(String[] args) {
        try{
            SpringApplication.run(CommitteeApplication.class, args);
        }catch (Throwable e){
            e.printStackTrace();
        }

    }

}
