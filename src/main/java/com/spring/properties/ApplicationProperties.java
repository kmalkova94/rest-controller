package com.spring.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
@Data
public class ApplicationProperties {
    private Kafka kafka;

    @Data
    public static class Kafka {
        private String bootstrapServers;
        private String schemaRegistryUrl;
        private boolean autoRegisterSchemas;
        private Topic topic;

        @Data
        public static class Topic {
            private String name;
            private String customerKey;
            private int partitions;
            private int replicas;
        }
    }
}
