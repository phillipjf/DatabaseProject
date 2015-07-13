
import java.sql.*;
import java.util.*;

public class TableMethods {
	


    /* ========================================================================
    *   get All returns all column indexes from any given table
    *   @param String
    *   @return ArrayList<String>
    *  ========================================================================*/
    public static ArrayList<String> getAllColumnNames(String tableName,Connection con) throws SQLException {
        DatabaseMetaData meta = con.getMetaData();
        ArrayList<String> list = new ArrayList<String>();
        ResultSet rs = meta.getColumns(null, null, tableName,null);
        while(rs.next()) {
            String columns = rs.getString("COLUMN_NAME");
            list.add(columns);
        }
        return list;
    }

    /* ========================================================================
    *   get Unique returns all unique column indexes from any given table
    *   @param String
    *   @return ArrayList<String>
    *  ========================================================================*/
    public static ArrayList<String> getUniqueColumns(String tableName, Connection con)throws SQLException {
        DatabaseMetaData meta = con.getMetaData();
        ArrayList<String> list = new ArrayList<String>();
        ResultSet rs = meta.getIndexInfo(null, null, tableName, true, true);
        while(rs.next()) {
            String columns = rs.getString("COLUMN_NAME");
            list.add(columns);
        }
        return list;
    }
}