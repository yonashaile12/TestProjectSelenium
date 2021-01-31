package day03;

import Utility.DB_Utility;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

public class UtilityPractice {


    public static void main(String[] args) throws SQLException {
        DB_Utility.createConnection();
        ResultSet jobRs = DB_Utility.runQuery("SELECT * FROM JOBS");
        /**
         * Get the row count the resultSet
         * move the pointer to the last row and get the row number
         */

//        jobRs.last();
//        int rowCount = jobRs.getRow(); // get row is getting the current row count
//        System.out.println("rowCount = " + rowCount);

        int rowCount = DB_Utility.getRowCount();
        System.out.println("rowCount = " + rowCount);

        int columnCount = DB_Utility.getColumnCount();
        System.out.println("columnCount = " + columnCount);

        System.out.println("All column names "+ DB_Utility.getColumnNames());

        System.out.println("Row Data At row 3 "+ DB_Utility.getRowDataAsList(3));
        System.out.println("Get cell value at row 2 col 4 "+DB_Utility.getColumnDataAtRow(2, 4));
        System.out.println("Get cell value ar row 2 col nam "+ DB_Utility.getColumnDataAtRow(2,"JOB_TITLE"));
        System.out.println("Third column value is "+DB_Utility.getColumnDataAsList(3));
        System.out.println("JOB_TITLE column value "+DB_Utility.getColumnDataAsList("JOB_TITLE"));
        System.out.println("--------------\n");
       // DB_Utility.displayAllData();


        Map<String, String> row1Map = new LinkedHashMap<>();
        // Map example
        row1Map.put("JOB_ID", "AC_ACCOUNT");
        row1Map.put("JOB_TITLE", "Public Accountant");
        row1Map.put("JOB_SALARY", "4200");
        row1Map.put("MAX_SALARY", "9000");

        // now do above programmatically
        // create row 1 map like above programmatically


//        System.out.println("row1Map = " + row1Map);

//        Map<String, String> rowMap = new LinkedHashMap<>();
//        jobRs.absolute(1);
//        ResultSetMetaData rsmd = jobRs.getMetaData();
//        for (int colNum = 1; colNum <= rsmd.getColumnCount(); colNum++) {
//
//            String columnName = rsmd.getColumnLabel(colNum);
//            String cellValue = jobRs.getString(colNum);
//            rowMap.put(columnName, cellValue);
//        }
        //System.out.println("first row rowMap = " + rowMap);
        System.out.println("First row rowMap "+ DB_Utility.getRowMap(1));

        // if one row can represented as one map object
        // what data structure is good to store 19 rows of data
        // List Map

        // get 2nd row and 4th row and save it into list of map as practice
        Map<String, String> row2map = DB_Utility.getRowMap(2);
        Map<String, String> row4map = DB_Utility.getRowMap(4);
        List<Map<String, String>> rowMapList = new ArrayList<>();

        rowMapList.add(row2map);
        rowMapList.add(row4map);

        System.out.println("rowMapList = " + rowMapList);


        DB_Utility.destroy();

    }
}
