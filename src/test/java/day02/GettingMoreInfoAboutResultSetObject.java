package day02;

import javax.xml.transform.Result;
import java.sql.*;

public class GettingMoreInfoAboutResultSetObject {

    public static void main(String[] args) throws SQLException {

        String connectionStr = "jdbc:oracle:thin:@54.211.214.241:1521:XE";
        String username = "hr";
        String password = "hr";

        Connection connection = DriverManager.getConnection(connectionStr, username, password);
        Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery("SELECT * FROM EMPLOYEES");

        // Metadata -- is data about the data
        // resultSetMetaData -- data about the ResultSet object that contain our resulting rows and columns
        // for example column names, column counts ..and more
        // this how we get the ResultSetMetaData

        ResultSetMetaData rsmd = rs.getMetaData();
        // we only need two methods from this to get column count and column name | label
        int colCount = rsmd.getColumnCount();
        System.out.println("colCount = " + colCount);
        // this how we get column label | name using index
        // get first column name
//        System.out.println("First column name is "+rsmd.getColumnLabel(1));
//        System.out.println("Second column name is "+rsmd.getColumnLabel(2));

        for(int i = 1; i <= colCount; i++){
            System.out.println("Column name is "+rsmd.getColumnLabel(i));
        }

        rs.close();
        stmt.close();
        connection.close();

    }
}
