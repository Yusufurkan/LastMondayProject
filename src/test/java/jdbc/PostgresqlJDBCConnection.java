package jdbc;

import org.junit.Test;

import java.sql.*;

public class PostgresqlJDBCConnection {
    String url = "jdbc:postgresql://room-reservation-qa.cxvqfpt4mc2y.us-east-1.rds.amazonaws.com:5432/hr";
    String username = "hr";
    String password = "hr";

    String url2 = "jdbc:postgresql//:54.148.96.210:5432/BriteErpDemo";
    String username2 = "podoo";
    String password2 = "podoo";


    @Test
        public void JDBCConnect() throws SQLException {

            Connection connection = DriverManager.getConnection(url,username,password);
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet resultSet = statement.executeQuery("Select * from employees");
            resultSet.next();
            System.out.println(resultSet.getString("first_name"));
        }
        @Test
        public void JDBCConnect2() throws SQLException {
            DBUtility.establishConnection(DBType.POSTGRESQL);
            DBUtility.runSQLQuery("Select * from lunch_alert");
            DBUtility.resultSet.next();
            System.out.println(DBUtility.resultSet.getString("id"));

        }

}