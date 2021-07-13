package org.example.property;

import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class ApplicationPropertySourcesPlaceholderConfigurer extends PropertySourcesPlaceholderConfigurer {
  public ApplicationPropertySourcesPlaceholderConfigurer() {
    setLocations(new ClassPathResource("db.properties"));
  }
}
