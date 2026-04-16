package com.skillverse.userservice.config;

import com.skillverse.userservice.entity.UserCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.util.backoff.FixedBackOff;
import org.apache.kafka.common.TopicPartition;
import java.util.HashMap;
import java.util.Map;

@Configuration
//@EnableKafka
@Slf4j
public class KafkaConfig {
//
//    @Bean
//    public Map<String, Object> consumerConfigs() {
//
//        Map<String, Object> props = new HashMap<>();
//
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        props.put(ConsumerConfig.GROUP_ID_CONFIG, "user-group");
//        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
//
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
//
//        //  Critical for JSON
//        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
//        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, UserCreatedEvent.class);
//
//        return props;
//    }
//
//    @Bean
//    public ConsumerFactory<String, UserCreatedEvent> consumerFactory() {
//
//        JsonDeserializer<UserCreatedEvent> deserializer =
//                new JsonDeserializer<>(UserCreatedEvent.class);
//
//        deserializer.addTrustedPackages("*");
//
//        return new DefaultKafkaConsumerFactory<>(
//                consumerConfigs(),
//                new StringDeserializer(),
//                deserializer
//        );
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, UserCreatedEvent>
//    kafkaListenerContainerFactory() {
//
//        ConcurrentKafkaListenerContainerFactory<String, UserCreatedEvent> factory =
//                new ConcurrentKafkaListenerContainerFactory<>();
//
//        factory.setConsumerFactory(consumerFactory());
//
//        //  Manual acknowledgment (better control)
//        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.RECORD);
//
//        return factory;
//    }
//    @Bean
//    public DefaultErrorHandler errorHandler(KafkaTemplate<Object, Object> kafkaTemplate) {
//
//        FixedBackOff backOff = new FixedBackOff(2000L, 3);
//
//        DeadLetterPublishingRecoverer recoverer =
//                new DeadLetterPublishingRecoverer(
//                        kafkaTemplate,
//                        (record, ex) -> new TopicPartition(
//                                record.topic() + ".DLT",
//                                record.partition()
//                        )
//                );
//
//        return new DefaultErrorHandler(recoverer, backOff);
//    }
//    @Bean
//    public NewTopic userCreatedTopic() {
//        return TopicBuilder.name("user-created")
//                .partitions(1)
//                .replicas(1)
//                .build();
//    }
//
//    @Bean
//    public NewTopic userCreatedDLT() {
//        return TopicBuilder.name("user-created.DLT")
//                .partitions(1)
//                .replicas(1)
//                .build();
//    }
}