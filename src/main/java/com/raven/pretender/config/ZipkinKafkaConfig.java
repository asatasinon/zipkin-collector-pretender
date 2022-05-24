package com.raven.pretender.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author: raven
 * @date: 2022/5/24
 * @description:
 */
@Configuration
@Data
public class ZipkinKafkaConfig {

    @Value("${kafka-topic.zipkin}")
    private String zipkinTopic;

}
