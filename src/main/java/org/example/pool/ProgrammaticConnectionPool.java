package org.example.pool;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import lombok.Getter;

@Getter
public class ProgrammaticConnectionPool {
    private final String login;
    private final String password;
    private final MysqlDataSource ds;

    public ProgrammaticConnectionPool(String login, String password, String url) {
        this.login = login;
        this.password = password;
        this.ds=new MysqlDataSource();
        ds.setUrl(url);
    }
}