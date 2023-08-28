package com.example.discordbot.config.async;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "event-handler-thread-pool.message-event")
@Data
public class MessageEventHandlerThreadPoolConfig {

    private int queueCapacity;
    private int maxPoolSize;
    private int corePoolSize;
    private String threadNamePrefix;
}
