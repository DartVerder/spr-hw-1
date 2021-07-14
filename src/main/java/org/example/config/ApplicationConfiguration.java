package org.example.config;

import org.example.pool.ApplicationConnectionPool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class ApplicationConfiguration {
  @Bean
  public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
    final PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
    configurer.setLocations(new ClassPathResource("db.properties"));
    return configurer;
  }

  @Bean
  public ApplicationConnectionPool connectionPool(@Value("${login}") String login, @Value("${password}") String password, @Value("${url}") String url) {
    return new ApplicationConnectionPool(login, password, url);
  }

 }

