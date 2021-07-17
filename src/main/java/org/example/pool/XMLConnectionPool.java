package org.example.pool;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import lombok.Getter;


@Getter
public class XMLConnectionPool {
    private final String login;
    private final String password;
    private final MysqlDataSource ds;

    public XMLConnectionPool(String login, String password, String url) {
        this.login=login;
        this.password=password;
        ds = new MysqlDataSource();
        ds.setURL(url);
    }

}
