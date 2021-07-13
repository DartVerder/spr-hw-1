import lombok.var;
import org.example.config.ApplicationConfiguration;
import org.example.pool.AnnotationConnectionPool;
import org.example.pool.ApplicationConnectionPool;
import org.example.pool.ProgrammaticConnectionPool;
import org.example.pool.XMLConnectionPool;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class ConfigTest {
    @Test
    public void xmlConnector() throws SQLException {
        final var context = new GenericApplicationContext();
        new XmlBeanDefinitionReader(context).loadBeanDefinitions("beans.xml");
        context.refresh();
        final var bean = context.getBean("xml-connector", XMLConnectionPool.class);
        assertNotNull(bean);
        assertEquals("root", bean.getLogin());
        assertEquals("root", bean.getPassword());
        assertEquals("jdbc:mysql://localhost/test",bean.getDs().getUrl());
        //assertEquals(true,bean.getDs().getConnection(bean.getLogin(), bean.getPassword()).isValid(1));
    }

    @Test
    public void annotationConnector() throws SQLException
    {
        final var context = new AnnotationConfigApplicationContext("org.example");
        final var bean = context.getBean(AnnotationConnectionPool.class);
        assertEquals("root", bean.getLogin());
        assertEquals("root", bean.getPassword());
        assertEquals("jdbc:mysql://localhost/test",bean.getDs().getUrl());
        //assertEquals(true,bean.getDs().getConnection(bean.getLogin(), bean.getPassword()).isValid(1));
    }

    @Test
    public void javaConnector()
    {
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        final ApplicationConnectionPool bean = context.getBean(ApplicationConnectionPool.class);
        assertEquals("root", bean.getLogin());
        assertEquals("root", bean.getPassword());
        assertEquals("jdbc:mysql://localhost/test",bean.getDs().getUrl());
    }

    @Test
    public void programmaticConnector()
    {
        final GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean(PropertySourcesPlaceholderConfigurer.class, () -> {
            final PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
            configurer.setLocations(new ClassPathResource("db.properties"));
            return configurer;
        });

        context.registerBean(UserRepository.class);
        context.registerBean(UserService.class);
        context.registerBean(ProgrammaticConnectionPool.class, "${login}", "${password}","${url}");
        context.refresh();

        final ProgrammaticConnectionPool bean = context.getBean(ProgrammaticConnectionPool.class);
        assertEquals("root", bean.getLogin());
        assertEquals("root", bean.getPassword());
        assertEquals("jdbc:mysql://localhost/test",bean.getDs().getUrl());
    }

}
