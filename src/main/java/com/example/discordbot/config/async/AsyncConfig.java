package com.example.discordbot.config.async;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import java.util.concurrent.Executor;

@Configuration
@RequiredArgsConstructor
@EnableAsync
public class AsyncConfig {

    private final MessageEventHandlerThreadPoolConfig messageEventHandlerThreadPoolConfig;

    @Bean
    public Executor messageEventHandlerExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setQueueCapacity(messageEventHandlerThreadPoolConfig.getQueueCapacity());
        executor.setMaxPoolSize(messageEventHandlerThreadPoolConfig.getMaxPoolSize());
        executor.setCorePoolSize(messageEventHandlerThreadPoolConfig.getCorePoolSize());
        executor.setThreadNamePrefix(messageEventHandlerThreadPoolConfig.getThreadNamePrefix());
        executor.initialize();

        return executor;
    }
}
