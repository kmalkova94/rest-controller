package com.spring.kafka;

import com.spring.properties.ApplicationProperties;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
@AllArgsConstructor
public class KafkaTopic {
    private final ApplicationProperties applicationProperties;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, applicationProperties.getKafka().getBootstrapServers());
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic topic() {
        return TopicBuilder.name(applicationProperties.getKafka().getTopic().getName())
                .partitions(applicationProperties.getKafka().getTopic().getPartitions())
                .replicas(applicationProperties.getKafka().getTopic().getReplicas()).compact().build();
    }
}
