package jdbc;

import org.junit.Test;

import java.sql.SQLException;

public class EmployeeDbTest {

    @Test
    public void test() throws SQLException {
        DBUtility.establishConnection(DBType.ORACLE);
    }
}
