package dayo4;

import Utility.ConfigurationReader;
import Utility.DB_Utility;

public class SpartanDB_Practice {

    public static void main(String[] args) {
        String connectionStr = ConfigurationReader.getProperty("connStr");
        String username = ConfigurationReader.getProperty("spartanUsername");
        String password = ConfigurationReader.getProperty("spartanPassword");

        DB_Utility.createConnection(connectionStr, username, password);

        DB_Utility.runQuery("SELECT * FROM SPARTANS");
        // call few method from DB_UTILITY
        System.out.println("DB_Utility.getRowCount() = " + DB_Utility.getRowCount());
        // GET all the column name
        System.out.println("DB_Utility.getColumnNames() = \n\t"
                + DB_Utility.getColumnNames());

        DB_Utility.destroy();
    }
}
