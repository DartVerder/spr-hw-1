package org.example.pool;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;



@Component
@Getter
public class AnnotationConnectionPool {
    private final String login;
    private final String password;
    private final MysqlDataSource ds;

    public AnnotationConnectionPool(@Value("${login}") String login, @Value("${password}") String password,@Value("${url}") String url) {
        this.login = login;
        this.password = password;
        this.ds= new MysqlDataSource();
        this.ds.setUrl(url);
    }

}