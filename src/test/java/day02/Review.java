package day02;

import java.sql.*;

public class Review {

    public static void main(String[] args) throws SQLException {


        String connectionStr = "jdbc:oracle:thin:@54.211.214.241:1521:XE";
        String username = "hr";
        String password = "hr";

        Connection connection = DriverManager.getConnection(connectionStr, username, password);
        Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery("SELECT * FROM JOBS");


        rs.next();
        System.out.println("First column value in Jobs "+rs.getString(1));
        System.out.println("Second column value in Jobs "+rs.getString(2));

        // move to row number 7 and get above 2 values
        rs.absolute(7);
        System.out.println("First column value in Jobs in row 7 "+rs.getString(1));
        System.out.println("Second column value in Jobs in row 7 "+rs.getString(2));

        rs.last();
        System.out.println("First column value in Jobs in the last row "+rs.getString(1));
        System.out.println("Second column value in Jobs in the last row "+rs.getString(2));

        rs.previous();
        System.out.println("second from the last row column value in Jobs "+rs.getString(1));
        System.out.println("Second from the last row column value in Jobs "+rs.getString(2));

        System.out.println("--------Loop from first row till last row print JOB_ID----------");
        rs.beforeFirst();

        while(rs.next()){
            System.out.println("Loop first column "+rs.getString("JOB_ID"));
        }

        System.out.println("--------Loop from last row till first row get MIN_SALARY COLUMN AS NUMBER----------");
        // WE ARE CURRENTLY AT AFTER LAST LOCATION
        // if you really want to make sure and explicitly say so
        // we can do below

        rs.afterLast();
        while(rs.previous()){
            System.out.println("MIN SALARY COLUMN AS NUMBER  $"+rs.getDouble("MIN_SALARY"));
        }

        // clean up the connection, statement and resultset object after usage
        rs.close();
        stmt.close();
        connection.close();




    }


}
