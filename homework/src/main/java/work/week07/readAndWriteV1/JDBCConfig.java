package work.week07.readAndWriteV1;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import work.week07.JDBCTemplate.TemplateInsert;

import javax.sql.DataSource;

@Configuration
@ComponentScan
@PropertySource("jdbc.properties")
public class JDBCConfig {

    @Value("${jdbc.readUrl}")
    String readJdbcUrl;

    @Value("${jdbc.writeUrl}")
    String writeJdbcUrl;

    @Value("${jdbc.username}")
    String jdbcUsername;

    @Value("${jdbc.password}")
    String jdbcPassword;

    @Bean("readDataSource")
    DataSource createReadDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(readJdbcUrl);
        config.setUsername(jdbcUsername);
        config.setPassword(jdbcPassword);
        config.addDataSourceProperty("autoCommit", "true");
        config.addDataSourceProperty("connectionTimeout", "5");
        config.addDataSourceProperty("idleTimeout", "60");
        return new HikariDataSource(config);
    }

    @Bean("writeDataSource")
    DataSource createWriteDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(writeJdbcUrl);
        config.setUsername(jdbcUsername);
        config.setPassword(jdbcPassword);
        config.addDataSourceProperty("autoCommit", "true");
        config.addDataSourceProperty("connectionTimeout", "5");
        config.addDataSourceProperty("idleTimeout", "60");
        return new HikariDataSource(config);
    }

    @Bean("writeJdbcTemplate")
    JdbcTemplate createWriteJdbcTemplate(@Autowired @Qualifier("writeDataSource")DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean("readJdbcTemplate")
    JdbcTemplate createReadJdbcTemplate(@Autowired @Qualifier("readDataSource")DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(JDBCConfig.class);
        JdbcTemplate jdbcTemplate = (JdbcTemplate) context.getBean("writeJdbcTemplate");
    }
}