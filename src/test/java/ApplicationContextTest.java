import org.example.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ApplicationContextTest {
  @Test
  void shouldCreate() {
    final ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

    final UserService bean = context.getBean(UserService.class);
    assertNotNull(bean);
  }

}
