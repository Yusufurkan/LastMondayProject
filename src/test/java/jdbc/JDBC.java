package jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBC {

    public static Connection connection;
    public static Statement statement;
    public static ResultSetMetaData metaData;
    public static DatabaseMetaData databaseMetaData;
    public static ResultSet resultSet;

    public JDBC(String url, String username, String password) {

        try {
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    public void query(String query) throws SQLException {
        resultSet = statement.executeQuery(query);

        resultSet.beforeFirst();
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1) + "-" + resultSet.getString("country_name") + "-" + resultSet.getString("region_id"));

        }
        resultSet.beforeFirst();
        close();
    }

    public void close() throws SQLException {
        resultSet.close();
        connection.close();
        statement.close();
    }


    public static List<Map<String, Object>> runSQLQuery(String sql) {
        List<Map<String, Object>> finalDataList = null;
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(sql);
            finalDataList = new ArrayList<>();

            ResultSetMetaData rsMetaData = resultSet.getMetaData();
            while (resultSet.next()) {
                Map<String, Object> rowMap = new HashMap<>();
                for (int i = 1; i < rsMetaData.getColumnCount(); i++) {
//                rowMap.put(rsMetaData.getColumnName(i), resultSet.getString(i));
                    rowMap.put(rsMetaData.getColumnName(i), resultSet.getObject(i));
                }

                finalDataList.add(rowMap);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return finalDataList;

    }

}
