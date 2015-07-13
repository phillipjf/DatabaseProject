
import java.sql.*;
import java.util.*;

public class TableMethods {
	
    /* ========================================================================
    *   Select all  returns 2-d array with all fields
    *   SELECT * FROM tableName;
    *    @param String, Connection
    *    @return String[][] 
    * =======================================================================*/
	public static String[][] selectAll(String tableName, Connection con) throws SQLException{
		
		Statement stmt =con.createStatement();
		String query = "SELECT * FROM " + tableName + ";";
		ResultSet rs = stmt.executeQuery(query);
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnCount = rsmd.getColumnCount();
		int rowCount = getRowCount(tableName,con);


		String[][] arr = new String[rowCount][columnCount];

		int r = 0;
		
		while(rs.next()){
			for(int c = 0 ; c < columnCount ; c++){
				arr[r][c] = rs.getString(c+1);
			}
			++r;
		}

		return arr;
	}


    /* ========================================================================
    *    Only returns column information that you ask for
    *    @param String, String[], Connection
    *    @return String[][] 
    * =======================================================================*/
    public static String[][] selectXFrom(String tableName, String[] ids, Connection con) throws SQLException{
        
        Statement stmt =con.createStatement();

        String search = "";

        for(int i = 0; i < ids.length ; i++){
            search += ids[i];
            if (i < (ids.length-1)){
                search += ", ";
            }
        }


        String query = "SELECT " + search + " FROM " + tableName + ";";
        ResultSet rs = stmt.executeQuery(query);
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = ids.length;
        int rowCount = getRowCount(tableName,con);


        String[][] arr = new String[rowCount][columnCount];

        int r = 0;
        
        while(rs.next()){
            for(int c = 0 ; c < columnCount ; c++){
                arr[r][c] = rs.getString(c+1);
            }
            ++r;
        }

        return arr;
    }

    /* ========================================================================
    *    Select Where
    *    @param String, String[], Connection
    *    @return String[][] 
    * =======================================================================*/
    public static String[][] selectWhere(String tableName, String id, int low, int high, Connection con) throws SQLException{
        
        Statement stmt =con.createStatement();

        String query = "SELECT * FROM " + tableName + 
        " WHERE " + id + ">" + low + " and " + id + " < " + high + ";";
        ResultSet rs = stmt.executeQuery(query);
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
        int rowCount = getRowCount(tableName,con);


        String[][] arr = new String[rowCount][columnCount];

        int r = 0;
        
        while(rs.next()){
            for(int c = 0 ; c < columnCount ; c++){
                arr[r][c] = rs.getString(c+1);
            }
            ++r;
        }

        return arr;
    }



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
    public static ArrayList<String> getUniqueColumns(String tableName, Connection con) throws SQLException {
        DatabaseMetaData meta = con.getMetaData();
        ArrayList<String> list = new ArrayList<String>();
        ResultSet rs = meta.getIndexInfo(null, null, tableName, true, true);
        while(rs.next()) {
            String columns = rs.getString("COLUMN_NAME");
            list.add(columns);
        }
        return list;
    }


    public static int[] getColumnTypes(String tableName, Connection con) throws SQLException{
        Statement st =con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM " + tableName);
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
        int[] a = new int[columnCount];

        for(int r = 0 ; r < columnCount ; r++){
            a[r] = rsmd.getColumnType(r + 1);
        }


        return a;
    }


    public static String[] getColumnTypeNames(String tableName, Connection con) throws SQLException{
        Statement st =con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM " + tableName);
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
        String[] a = new String[columnCount];

        for(int r = 0 ; r < columnCount ; r++){
            a[r] = rsmd.getColumnTypeName(r + 1);
        }

        
        return a;
    }




    /*	======================================================================
    *   count rows - returns the number of row in a table
    *   @params String, Connection
    *   @return int
    *   =====================================================================*/
	public static int getRowCount(String table, Connection con) throws SQLException{
		int count = 0;
		Statement stmt =con.createStatement();
		ResultSet res = stmt.executeQuery("SELECT COUNT(*) FROM "+table);
 		 while (res.next()){
  			count = res.getInt(1);
  		}
  		return count;
	}

}