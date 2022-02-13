package work.week05.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MySQLJDBC {
    private Connection connection = null;
    private static PreparedStatement preparedStatement = null;

    private void setConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/classroom?useSSL=false", "root", "root");
            if (connection != null) {
                System.out.println("Connection successful!");
            }
        } catch (Exception e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
    }

    public boolean execute(String sql, List<Object> values) {
        try {
            preparedStatement = connection.prepareStatement(sql);
            for (int i=1; i<values.size() + 1; i++) {
                preparedStatement.setObject(i, values.get(i-1));
            }
            System.out.println(preparedStatement.toString());
            preparedStatement.execute();
            System.out.println("Insert successfully");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Map<String, Object>> select(String sql, List<Object> values) {
        try {
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            for (int i=1; i<values.size() + 1; i++) {
                preparedStatement.setObject(i, values.get(i-1));
            }
            while (resultSet.next()) {
                System.out.println(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void close() throws SQLException {
        preparedStatement.close();
        connection.close();
        System.out.println("Connection close");
    }


    public static void main(String[] args) {
        MySQLJDBC mySQLJDBC = new MySQLJDBC();
        mySQLJDBC.setConnection();
        String sql = "select * from t1";
        mySQLJDBC.select(sql, new ArrayList<>());
        try {
            mySQLJDBC.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
