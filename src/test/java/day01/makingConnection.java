package day01;

import java.sql.*;

public class makingConnection {

    //54.211.214.241

    public static void main(String[] args) throws SQLException {
        String connectionStr = "jdbc:oracle:thin:@54.211.214.241:1521:XE";
        String username = "hr";
        String password = "hr";

        Connection conn = DriverManager.getConnection(connectionStr, username, password);

        Statement stmnt = conn.createStatement();
        ResultSet rs = stmnt.executeQuery("SELECT * FROM REGIONS");
        rs.next(); // currently we are at the first row

        System.out.println("first column value using index: -->> "+rs.getString(1));
        System.out.println("Second column value using index: -->> "+rs.getString(2));

        //rs.getString(column names)
        System.out.println("Region_id at this row is "+rs.getString("REGION_ID"));
        System.out.println("Region_Name at this row is "+rs.getString("REGION_NAME"));

        rs.next(); // this will move us to the next row so we can move to the next row
        System.out.println("Region_id at this row is "+rs.getString("REGION_ID"));
        System.out.println("Region_Name at this row is "+rs.getString("REGION_NAME"));

        rs.next(); // this will move us to the next row so we can move to the next row
        System.out.println("Region_id at this row is "+rs.getString("REGION_ID"));
        System.out.println("Region_Name at this row is "+rs.getString("REGION_NAME"));

        rs.next(); // this will move us to the next row so we can move to the next row
        System.out.println("Region_id at this row is "+rs.getString("REGION_ID"));
        System.out.println("Region_Name at this row is "+rs.getString("REGION_NAME"));



    }
}
