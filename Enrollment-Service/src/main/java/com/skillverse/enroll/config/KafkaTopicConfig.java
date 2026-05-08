package com.skillverse.enroll.config;

import lombok.AllArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class KafkaTopicConfig {

    @Bean
    public NewTopic userNotify() {
        return new NewTopic("enrollment-notify-topic", 1, (short) 1);
    }

}