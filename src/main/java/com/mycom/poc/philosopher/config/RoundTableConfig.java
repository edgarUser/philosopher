package com.mycom.poc.philosopher.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoundTableConfig {

  @Value("${application.availableThreads}")
  private int availableThreads;

  @Bean
  public ExecutorService getExecutorService() {
    return Executors.newFixedThreadPool(availableThreads);
  }
}
