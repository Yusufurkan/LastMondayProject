package jdbc;

import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCConnection {

    public JDBCConnection() throws SQLException {
    }

    String dbUrl = "jdbc:oracle:thin:@ec2-35-173-134-12.compute-1.amazonaws.com:1521:xe";
    String oracledbUsername = "hr";

    String oracledbPassword = "hr";

    Connection connection = DriverManager.getConnection(dbUrl, oracledbUsername, oracledbPassword);
    //        Statement statement = connection.createStatement();

    Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

    ResultSet resultSet = statement.executeQuery("Select * from countries");

    @Test
    public void oracleJDBC() throws SQLException {


        resultSet.beforeFirst();
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1) + "-" + resultSet.getString("country_name") + "-" + resultSet.getString("region_id"));

        }

        resultSet.close();
        connection.close();
        statement.close();
    }


    @Test
    public void itereateSamples() throws SQLException {

        resultSet.next();
        System.out.println(resultSet.getString(1) + "-" + resultSet.getString("country_name") + "-" + resultSet.getString("region_id"));
        resultSet.getRow();
        System.out.println(resultSet.getString(1) + "-" + resultSet.getString("country_name") + "-" + resultSet.getString("region_id"));
        resultSet.first();
        System.out.println(resultSet.getString(1) + "-" + resultSet.getString("country_name") + "-" + resultSet.getString("region_id"));
        resultSet.last();
        System.out.println(resultSet.getString(1) + "-" + resultSet.getString("country_name") + "-" + resultSet.getString("region_id"));
        resultSet.previous();
        System.out.println(resultSet.getString(1) + "-" + resultSet.getString("country_name") + "-" + resultSet.getString("region_id"));


        //jumps to row number
        System.out.println("jump to row: " + resultSet.absolute(3));


        // if you start with the first you lose the first one it's better to start with this onw
        resultSet.beforeFirst();
        // points to beginning

        resultSet.close();
        connection.close();
        statement.close();
    }


    @Test
    public void getTheTableSize() throws SQLException {

        resultSet.last();
        int rowCount = resultSet.getRow();
        System.out.println("number of rows: " + rowCount);

        resultSet.close();
        connection.close();
        statement.close();
    }

    @Test
    public void test() throws SQLException {

        resultSet.last();
        int rowCount = resultSet.getRow();
        System.out.println("number of rows: " + rowCount);

        resultSet.close();
        connection.close();
        statement.close();
    }

    @Test
    public void metaData() throws SQLException {

       // resultSet = statement.executeQuery("Select employee_id , last_name , job_id salary from employees");

        DatabaseMetaData dbMetaData = connection.getMetaData();
        System.out.println("user: " + dbMetaData.getUserName());
        System.out.println("DB type: " + dbMetaData.getDatabaseProductVersion());


        ResultSetMetaData rsMetaData = resultSet.getMetaData();
        System.out.println("Column count: " + rsMetaData.getColumnCount());
        System.out.println("Column name: " + rsMetaData.getColumnName(1));


        for (int i = 1; i <= rsMetaData.getColumnCount(); i++) {
            System.out.println(rsMetaData.getColumnName(i));
        }


    }


    @Test
    public void storeResults() throws Exception {

        resultSet = statement.executeQuery("Select employee_id , last_name ,salary from employees");

        DatabaseMetaData dbMetaData = connection.getMetaData();
//        System.out.println("user: " + dbMetaData.getUserName());
//        System.out.println("DB type: " + dbMetaData.getDatabaseProductVersion());


        ResultSetMetaData rsMetaData = resultSet.getMetaData();
//        System.out.println("Column count: " + rsMetaData.getColumnCount());
//        System.out.println("Column name: " + rsMetaData.getColumnName(1));


        List<Map<String, Object>> listData = new ArrayList<>();

        List<String> columnNames = getColumnNames(rsMetaData);
        System.out.println(columnNames);

        List<Map<String, Object>> table = finalTable(resultSet , rsMetaData);

        System.out.println(table);
    }



    public List<Map<String, Object>> finalTable(ResultSet resultSet, ResultSetMetaData rsMetaData) throws SQLException {

        List<Map<String, Object>> finalData = new ArrayList<>();
        while (resultSet.next()) {
            Map<String, Object> rowMap = new HashMap<>();
            for (int i = 1; i < rsMetaData.getColumnCount(); i++) {
//                rowMap.put(rsMetaData.getColumnName(i), resultSet.getString(i));
                rowMap.put(rsMetaData.getColumnName(i), resultSet.getObject(i));
            }

            finalData.add(rowMap);
        }


        return finalData;
    }


    public List<String> getColumnNames(ResultSetMetaData rsMetaData) throws SQLException {
        List<String> column = new ArrayList<>();
        for (int i = 1; i <= rsMetaData.getColumnCount(); i++) {
            column.add(rsMetaData.getColumnName(i));
        }
        return column;
    }


}
