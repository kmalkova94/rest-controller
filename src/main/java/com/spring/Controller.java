package com.spring;

import com.spring.properties.ApplicationProperties;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@AllArgsConstructor
public class Controller {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private final ApplicationProperties applicationProperties;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("/greeting")
    public ResponseEntity<Greeting> greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        String message = String.format(template, name);
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(applicationProperties.getKafka().getTopic().getName(),
                applicationProperties.getKafka().getTopic().getCustomerKey(),
                message);

        future.addCallback(new Callback(message));
        return new ResponseEntity<>( new Greeting(counter.incrementAndGet(), String.format(template, name)), HttpStatus.OK);
    }

}
