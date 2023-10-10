package com.movie.base.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Slf4j
@Data
@Configuration
@ConfigurationProperties(prefix = "app")
public class AppConf {

    private String serviceName;
    private String kafkaHost;
    private String nodeId = "1";
    private String instanceId = "1";
    private Integer kafkaTimeout;
    private String env;

    public String getKafkaBootstraps() {
        return this.kafkaHost.replace(";", ",");
    }

    @PostConstruct
    public void init() {
        log.info("appConf: {}", this);
    }
}
