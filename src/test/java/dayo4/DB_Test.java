package dayo4;

import Utility.ConfigurationReader;
import Utility.DB_Utility;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import sun.jvm.hotspot.utilities.Assert;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DB_Test {


    @BeforeAll
    public static void setUp(){
        DB_Utility.createConnection();
    }

    @Test
    public void testEmployeeCount(){
        // run a query SELECT * FROM EMPLOYEES
        //assert that the employees count is 107
        DB_Utility.runQuery("SELECT * FROM EMPLOYEES");
        assertEquals(107, DB_Utility.getRowCount());


    }
    // Open another test
    // run query : SELECT * FROM REGIONS
    // assert --3rd row second column is ASIA
    @Test
    public void testRegions(){
        DB_Utility.runQuery("SELECT * FROM REGIONS");
        assertEquals("Asia",DB_Utility.getColumnDataAtRow(3, 2));
    }

    @AfterAll
    public static void tearDown(){
        DB_Utility.destroy();
    }
}
