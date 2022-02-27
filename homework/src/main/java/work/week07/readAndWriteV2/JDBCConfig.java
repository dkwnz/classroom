package work.week07.readAndWriteV2;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

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

    @Bean("writeSessionFactory")
    SqlSessionFactoryBean createSqlSessionFactoryBean(@Autowired @Qualifier("writeDataSource")DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean;
    }

    @Bean("readSessionFactory")
    SqlSessionFactoryBean createReadSqlSessionFactoryBean(@Autowired @Qualifier("readDataSource")DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean;
    }

    @Bean
    public SwitchDataSource dataSource(@Qualifier("writeDataSource") DataSource masterDataSource, @Qualifier("readDataSource") DataSource slaveDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("master",masterDataSource);
        targetDataSources.put("slave", slaveDataSource);
        return new SwitchDataSource(masterDataSource, targetDataSources);
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(JDBCConfig.class);
        JdbcTemplate jdbcTemplate = (JdbcTemplate) context.getBean("writeJdbcTemplate");
    }
}