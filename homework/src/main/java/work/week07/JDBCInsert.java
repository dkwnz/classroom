package work.week07;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class JDBCInsert {

    public static void main(String[] args) {
        JDBCInsert jdbcInsert = new JDBCInsert();
        jdbcInsert.batchInsert();
    }

    //104576ms
    public void batchInsert() {
        String sql = "insert into mall.oms_order(member_id, order_sn) values (?,?)";
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mall?useSSL=false", "root", "root");
            if (connection != null) {
                System.out.println("Connection successful!");
            }
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i <= 1000000; i++) {
                preparedStatement.setInt(1, i);
                preparedStatement.setString(2, "breakHeart"+i);
                preparedStatement.addBatch();
            }
            long startTime = System.currentTimeMillis();
            preparedStatement.executeBatch();
            connection.commit();
            long endTime = System.currentTimeMillis();
            connection.close();
            System.out.println("使用时间：" + (endTime - startTime));
        } catch (Exception e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
    }
}
