package day01;

import java.sql.*;

public class loopingResultSet {

    public static void main(String[] args) throws SQLException {
        String connectionStr = "jdbc:oracle:thin:@54.211.214.241:1521:XE";
        String username = "hr";
        String password = "hr";

        Connection connection = DriverManager.getConnection(connectionStr, username, password);

        Statement stmnt = connection.createStatement();
        ResultSet rs = stmnt.executeQuery("SELECT * FROM REGIONS");

        //rs.next();  method returns a boolean value
        // if there is next row --->> return true and move the pointer to the next row
        // if there is no next row --->> false
        // there is no index that is the reason we are not using for loop

        while(rs.next()){
            System.out.println("Region_ID is: "+ rs.getString(1));
            System.out.println("REGION_NAME is: "+ rs.getString("REGION_NAME"));
        }

        // Iterate over all the countries
        rs = stmnt.executeQuery("SELECT * FROM COUNTRIES");
        while(rs.next()) {
            System.out.print(rs.getString(1) +"\t");
            System.out.print(rs.getString(2) +"\t");
            System.out.println(rs.getString(3) );
        }

        //rs.previous(); this is the error that we got because we need type insensitive
    }
}
