package jdbc;


import utilities.ConfigurationReader;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtility {


    public static Connection connection;
    public static Statement statement;
    public static ResultSet resultSet;


    public static void establishConnection(DBType dbtype) throws SQLException {
        switch (dbtype) {
            case ORACLE:
                connection = DriverManager.getConnection(ConfigurationReader.getProperty("oracleDb.url"),
                        ConfigurationReader.getProperty("oracleDb .user"),
                        ConfigurationReader.getProperty("oracleDb.password"));
                break;
            case POSTGRESQL:
                connection = DriverManager.getConnection(ConfigurationReader.getProperty("postgreDb")
                        , ConfigurationReader.getProperty("postgre.user"),
                        ConfigurationReader.getProperty("postgre.password"));
                break;

            default:
                connection = null;
        }
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
    public static List<Map<String, String>> runSQLQueryStr(String sql) {
        List<Map<String, String>> finalDataList = null;
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(sql);
            finalDataList = new ArrayList<>();

            ResultSetMetaData rsMetaData = resultSet.getMetaData();
            while (resultSet.next()) {
                Map<String, String> rowMap = new HashMap<>();
                for (int i = 1; i < rsMetaData.getColumnCount(); i++) {
//                rowMap.put(rsMetaData.getColumnName(i), resultSet.getString(i));
                    rowMap.put(rsMetaData.getColumnName(i), resultSet.getString(i));
                }

                finalDataList.add(rowMap);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return finalDataList;

    }

    public static void closeConnection() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            System.out.println("Close connection error in dbutil class");
        }
    }

//    public static int getRowsCount


}
