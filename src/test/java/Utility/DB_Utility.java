package Utility;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DB_Utility {
    static Connection connection;
    static ResultSet rs;
    static Statement stmnt;


    public static void createConnection() {
        String connectionStr = "jdbc:oracle:thin:@54.211.214.241:1521:XE";
        String username = "hr";
        String password = "hr";

        try {
            connection = DriverManager.getConnection(connectionStr, username, password);
            System.out.println("!!!CONNECTION SUCEESS!!!");
        } catch (SQLException e) {
            System.out.println("!!! CONNECTION HAS FAILED !!!!" + e.getMessage());
        }
    }


    public static ResultSet runQuery(String query) {
        try {
            stmnt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmnt.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("Error while getting resultset " + e.getMessage());
        }
        return rs;
    }

    public static void destroy() {
        try {
            rs.close();
            stmnt.close();
            connection.close();
            System.out.println("CLOSING CONNECTION!!");
        } catch (SQLException e) {
            System.out.println("ERROR WHILE CLOSING CONNECTION " + e.getMessage());
        }
    }

    public static int getRowCount() {
        int rowCount = 0;
        try {
            rs.last();
            rowCount = rs.getRow();
            // move the cursor back to beforefirst location to avoid accodent
            rs.beforeFirst();
        } catch (SQLException e) {
            System.out.println("ERROR WHILE GETTING ROW COUNT " + e.getMessage());
        }
        return rowCount;
    }

    /**
     * Get the column count
     *
     * @return count of column the ResultSet have
     */
    public static int getColumnCount() {

        int coulmnCount = 0;
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            coulmnCount = rsmd.getColumnCount();
        } catch (SQLException e) {
            System.out.println("ERROR WHILE COUNTING THE COULMNS " + e.getMessage());
        }

        return coulmnCount;
    }


    public static List<String> getColumnNames() {
        List<String> columnList = new ArrayList<>();

        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            for (int colNum = 1; colNum <= getColumnCount(); colNum++) {

                String columName = rsmd.getColumnLabel(colNum);
                columnList.add(columName);
            }
        } catch (SQLException e) {
            System.out.println("ERROR WHILE GETTING COLUMN NAME " + e.getMessage());
        }

        return columnList;
    }

    /**
     * create a method that return all row data as a List<String>
     *
     * @param rowNum Row Number you want to get the data
     * @return the row data as List object
     */
    public static List<String> getRowDataAsList(int rowNum) {
        List<String> rowDataList = new ArrayList<>();

        // first we nedd to move the pointer to the location the rowNum specified
        try {
            rs.absolute(rowNum);

            for (int colNum = 1; colNum <= getColumnCount(); colNum++) {
                String cellValue = rs.getString(colNum);
                rowDataList.add(cellValue);
            }
            rs.beforeFirst();
        } catch (SQLException e) {
            System.out.println("ERROR WHILE GETTING ROW DATA AS LIST " + e.getMessage());
        }

        return rowDataList;
    }

    /**
     * Create a method to return the cell value at certain row certain column
     *
     * @param rowNum
     * @param colNum
     * @return Cell value as String
     */

    public static String getColumnDataAtRow(int rowNum, int colNumIndex) {

        String result = "";

        try {
            rs.absolute(rowNum);
            result = rs.getString(colNumIndex);
            rs.beforeFirst();
        } catch (SQLException e) {
            System.out.println("ERROR WHILE GETTING CELL VALUE AT ROWNUM COULMN " + e.getMessage());
        }

        return result;
    }

    /**
     * Create a method to return the cell value at certain row certain column
     *
     * @param rowNum
     * @param colName
     * @return Cell value as String
     */

    public static String getColumnDataAtRow(int rowNum, String colName) {

        String result = "";

        try {
            rs.absolute(rowNum);
            result = rs.getString(colName);
            rs.beforeFirst();
        } catch (SQLException e) {
            System.out.println("ERROR WHILE GETTING CELL VALUE AT ROWNUM COULMN " + e.getMessage());
        }

        return result;
    }

    /**
     * return value of all cells in that column
     *
     * @param colNum the column number you want to get the list out of
     * @return List of all the cell datat at that column as a List<String>
     */

    public static List<String> getColumnDataAsList(int colNum) {

        List<String> cellValuesList = new ArrayList<>();
        try {
            while (rs.next()) {
                String cellvalue = rs.getString(colNum);
                cellValuesList.add(cellvalue);
            }
            rs.beforeFirst();
        } catch (SQLException e) {
            System.out.println("ERROR WHILE GETTING ONE COLUMN DATA AS LIST " + e.getMessage());
        }
        return cellValuesList;
    }
    /**
     * return value of all cells in that column
     *
     * @param colName the column number you want to get the list out of
     * @return List of all the cell datat at that column as a List<String>
     */



    public static List<String> getColumnDataAsList(String colName) {

        List<String> cellValuesList = new ArrayList<>();
        try {
            while (rs.next()) {
                String cellvalue = rs.getString(colName);
                cellValuesList.add(cellvalue);
            }
            rs.beforeFirst();
        } catch (SQLException e) {
            System.out.println("ERROR WHILE GETTING ONE COLUMN DATA AS LIST " + e.getMessage());
        }
        return cellValuesList;
    }

    public static void displayAllData(){

        try {
        rs.beforeFirst();
        while(rs.next()) {
            for (int colNum = 1; colNum <= getColumnCount(); colNum++) {
//                System.out.print(rs.getString(colNum) + "\t");
                System.out.printf("%-35s",rs.getString(colNum));
            }
            System.out.println();
        }
            rs.beforeFirst();

        } catch (SQLException e) {
            System.out.println("ERROR WHILE GETTING PRINTING WHOLE TABLE "+e.getMessage());
                }
            }

    /**
     * A method that return the row data with column name as map object
     * @param rowNum row number you want to get the data
     * @return Map object -- column name as key and cell value
      */

    public static Map<String, String> getRowMap(int rowNum){
        Map<String, String> rowMap = new LinkedHashMap<>();
        try{
        rs.absolute(rowNum);
        ResultSetMetaData rsmd = rs.getMetaData();
                for (int colNum = 1; colNum <= rsmd.getColumnCount(); colNum++) {

                    String columnName = rsmd.getColumnLabel(colNum);
                    String cellValue = rs.getString(colNum);
                    rowMap.put(columnName, cellValue);
                }
                rs.beforeFirst();
        }catch (SQLException e){
                System.out.println("ERROR WHILE getting row map "+e.getMessage());
            }

        return rowMap;
        }

        public static List<Map<String, String>> getAllDataAsListofMap(){
            List<Map<String, String>> rowMapList = new ArrayList<>();
            for (int rowNum = 1; rowNum <= getRowCount(); rowNum++) {
                rowMapList.add(   getRowMap(rowNum));

            }


        return rowMapList;
        }

}



