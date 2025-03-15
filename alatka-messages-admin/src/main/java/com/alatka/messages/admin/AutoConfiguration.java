package com.alatka.messages.admin;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ThreadPoolExecutor;

@EnableJpaRepositories
@EntityScan
@ComponentScan
@Configuration
public class AutoConfiguration {

    @Value("${alatka.messages.admin.rest.connectionTimeout:5000}")
    private int connectionTimeout;

    @Value("${alatka.messages.admin.rest.readTimeout:5000}")
    private int readTimeout;

    public static final String REST_TEMPLATE_NAME = "messagesRestTemplate";

    @Bean(REST_TEMPLATE_NAME)
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(connectionTimeout);
        factory.setReadTimeout(readTimeout);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(factory);
        return restTemplate;
    }

    @Value("${alatka.messages.admin.threadPool.corePoolSize:5}")
    private int corePoolSize;

    @Value("${alatka.messages.admin.threadPool.maxPoolSize:10}")
    private int maxPoolSize;

    @Value("${alatka.messages.admin.threadPool.queueCapacity:1}")
    private int queueCapacity;

    @Value("${alatka.messages.admin.threadPool.keepAliveSeconds:600}")
    private int keepAliveSeconds;

    @Value("${alatka.messages.admin.threadPool.threadNamePrefix:alatka-rule-admin-thread-}")
    private String threadNamePrefix;

    public static final String TASK_EXECUTOR_NAME = "messagesTaskExecutor";

    @Bean(TASK_EXECUTOR_NAME)
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(corePoolSize);
        taskExecutor.setMaxPoolSize(maxPoolSize);
        taskExecutor.setQueueCapacity(queueCapacity);
        taskExecutor.setKeepAliveSeconds(keepAliveSeconds);
        taskExecutor.setThreadNamePrefix(threadNamePrefix);
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return taskExecutor;
    }
}
