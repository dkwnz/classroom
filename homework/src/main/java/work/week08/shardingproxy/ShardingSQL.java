package work.week08.shardingproxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import work.week07.JDBCTemplate.JDBCConfig;
import work.week07.JDBCTemplate.TemplateInsert;

import java.util.List;
import java.util.Map;

@Component
public class ShardingSQL {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public void insert() {
        String sql = "insert into t_order(user_id) values (?)";
        System.out.println("插入开始");
        long startTime = System.currentTimeMillis();
        for (int i = 0; i <= 10000; i++) {
            jdbcTemplate.update(sql, i);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("使用时间：" + (endTime - startTime));
    }


    public void query() {
        String sql = "select * from t_order where user_id=1 limit 100";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ShardingConfig.class);
        ShardingSQL shardingSQL = context.getBean(ShardingSQL.class);
        shardingSQL.query();
    }
}
