package work.week07.JDBCTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class TemplateInsert {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public void batchInsert() {
        String sql = "insert into mall.oms_order(member_id, order_sn) values (1,1)";
        System.out.println("插入开始");
        long startTime = System.currentTimeMillis();
        for (int i = 0; i <= 1000000; i++) {
            jdbcTemplate.batchUpdate(sql);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("使用时间：" + (endTime - startTime));
    }
}
