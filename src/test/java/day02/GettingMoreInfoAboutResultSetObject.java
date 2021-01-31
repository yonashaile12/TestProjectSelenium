package day02;

import Utility.DB_Utility;

import javax.xml.transform.Result;
import java.sql.*;

public class GettingMoreInfoAboutResultSetObject {


    public static void main(String[] args) throws SQLException {
        DB_Utility.createConnection();
        ResultSet rs = DB_Utility.runQuery("SELECT * FROM REGIONS");

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

       DB_Utility.destroy();


    }
}
