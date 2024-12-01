package com.example.counterapp.config;

@Configuration
public class ThreadPoolConfig {

    @Bean
    public TaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(50);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("CustomThread-");
        executor.initialize();
        return executor;
    }

}
