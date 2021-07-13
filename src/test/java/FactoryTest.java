import org.example.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FactoryTest {
  @Test
  void shouldCreate() {
    final DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
    final XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
    reader.loadBeanDefinitions("beans.xml");

    final UserService bean = factory.getBean(UserService.class);
    assertNotNull(bean);
  }
}
