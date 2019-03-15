package jdbc;

import org.junit.Test;

import java.sql.*;
import java.util.Collection;
import java.util.Set;

public class PracticeJDBC {

    String dbUrl = "jdbc:oracle:thin:@ec2-35-173-134-12.compute-1.amazonaws.com:1521:xe";
    String oracledbUsername = "hr";

    String oracledbPassword = "hr";


    @Test
    public void test() throws SQLException {
        JDBC jdbc = new JDBC(dbUrl, oracledbUsername, oracledbPassword);

        jdbc.resultSet = jdbc.statement.executeQuery("Select * from employees");
        jdbc.metaData = jdbc.resultSet.getMetaData();

        jdbc.resultSet.beforeFirst();

        int a = 0;
        for (int i = 1; i < jdbc.metaData.getColumnCount(); i++) {
            System.out.println(jdbc.metaData.getColumnName(i));

            a++;
            jdbc.resultSet.next();
            int rows = jdbc.resultSet.getRow();
            jdbc.resultSet.absolute(a);
            while (jdbc.resultSet.next()) {
                if (rows == 12) {
                    break;
                } else {
                    System.out.println(jdbc.resultSet.getString(a));
                }
            }

        }

    }


    @Test
    public void test2() throws SQLException {
        JDBC jdbc = new JDBC(dbUrl, oracledbUsername, oracledbPassword);

        jdbc.resultSet = jdbc.statement.executeQuery("Select * from countries");
        jdbc.metaData = jdbc.resultSet.getMetaData();

        Set<String> set = JDBC.runSQLQuery("Select * from employees ").get(0).keySet();
        Collection<Object> values = JDBC.runSQLQuery("Select * from countries ").get(0).values();


        System.out.println(JDBC.metaData.getColumnCount());

        for (int i = 1; i <= JDBC.metaData.getColumnCount(); i++) {
            System.out.println(JDBC.metaData.getColumnName(i));
        }
    }

    @Test
    public void test3() throws SQLException {

        Connection connection = DriverManager.getConnection(dbUrl, oracledbUsername, oracledbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("Select * from employees");
        System.out.println(resultSet.absolute(4));
        System.out.println(resultSet.getString(4));

    }


}



