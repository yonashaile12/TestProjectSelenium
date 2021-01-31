package Utility;

import java.sql.*;

public class DB_Utility {
        static Connection connection;
        static ResultSet rs;
        static Statement stmnt;


    public static void createConnection(){
        String connectionStr = "jdbc:oracle:thin:@54.211.214.241:1521:XE";
        String username = "hr";
        String password = "hr";

        try {
             connection = DriverManager.getConnection(connectionStr, username, password);
            System.out.println("!!!CONNECTION SUCEESS!!!");
        } catch (SQLException e) {
            System.out.println("!!! CONNECTION HAS FAILED !!!!"+e.getMessage() );
        }
    }


    public static ResultSet runQuery(String query){
        try {
            stmnt  = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmnt.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("Error while getting resultset "+e.getMessage());
        }
        return rs;
    }

    public static void main(String[] args) throws SQLException {
        createConnection();
        ResultSet rs = runQuery("SELECT * FROM REGIONS");
        rs.next();
        System.out.println(rs.getString(2));
    }

}
