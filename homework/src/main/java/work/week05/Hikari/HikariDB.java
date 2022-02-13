package work.week05.Hikari;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class HikariDB {
    private DataSource dataSource;

    private void setDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/classroom");
        config.setUsername("root");
        config.setPassword("root");
        config.addDataSourceProperty("connectionTimeout", "1000"); // 连接超时：1秒
        config.addDataSourceProperty("idleTimeout", "60000"); // 空闲超时：60秒
        config.addDataSourceProperty("maximumPoolSize", "10"); // 最大连接数：10
        dataSource = new HikariDataSource(config);
    }

    public static void main(String[] args) {
        HikariDB hikariDB = new HikariDB();
        hikariDB.setDataSource();
        try {
            Connection connection = hikariDB.dataSource.getConnection();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
