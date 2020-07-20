package com.spring;

import com.spring.properties.ApplicationProperties;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

import static org.springframework.boot.SpringApplication.*;
//@EnableKafka
@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperties.class)
public class Application {
    /*@KafkaListener(topics="mytopic")
    public void msgListener(String msg){
        System.out.println(msg);
    }*/

    public static void main(String[] args) {
        run(Application.class, args);
    }
}
