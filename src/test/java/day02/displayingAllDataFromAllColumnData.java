package day02;

import java.sql.*;

public class displayingAllDataFromAllColumnData {

    public static void main(String[] args) throws SQLException {

        String connectionStr = "jdbc:oracle:thin:@54.211.214.241:1521:XE";
        String username = "hr";
        String password = "hr";

        Connection connection = DriverManager.getConnection(connectionStr, username, password);
        Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery("SELECT * FROM EMPLOYEES");


        // print out entire first row of Employee table from above query
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();

        // print out column name in the beginning row, then print first row
        for(int colNum = 1; colNum<= columnCount; colNum++){
            System.out.print(rsmd.getColumnLabel(colNum)+"\t");
        }


        System.out.println("\n++++++++++++++++++++++++++++++++++++++++");
        System.out.println();
        rs.beforeFirst();

        // now how do you get all the row if you know how to gwt one row??
        // I want to go from the first row till the last row and print all columns
        while(rs.next()){
        for(int col = 1; col <= columnCount; col++){
            System.out.print(rs.getString(col)+ "\t");
        }

        }


    }
}
