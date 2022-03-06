package work.week08.atomikosXA;

import org.apache.shardingsphere.transaction.annotation.ShardingSphereTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ShardingXA {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Transactional(rollbackFor = Exception.class)
    @ShardingSphereTransactionType(TransactionType.XA)
    public void shardingXA() {
        String sql = "insert into t_order(user_id) values (?)";
        for (int i = 0; i <= 10; i++) {
            jdbcTemplate.update(sql, i);
        }
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ShardingConfig.class);
        ShardingXA shardingXA = context.getBean(ShardingXA.class);
        shardingXA.shardingXA();
    }
}
