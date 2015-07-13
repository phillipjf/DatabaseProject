
import java.sql.*;
import java.util.*;

public class TableMethods {
	

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


    /*	
    * returns number of rows for any given table
    */
	public static int getRowCount(String table, Connection con) throws SQLException{
		int count = 0;
		Statement stmt =con.createStatement();
		ResultSet res = stmt.executeQuery("SELECT COUNT(*) FROM "+table);
 		 while (res.next()){
  			count = res.getInt(1);
  		}
  		return count;
	}


    /**
     *  Add Rows to existing 2D array
     *  Adds a 1/5 of the current size
     * @param A
     * @return
     */
    public static String[][] increaseSize(String[][] A){
        int rows = A.length + (A.length/5);
        int cols = A[1].length;
        String[][] newA = new String[rows][cols];
        for(int i = 0; i < A.length ; i++){
            for (int j = 0; j < cols ; j++){
                newA[i][j] = A[i][j];
            }
        }
        return newA;
    }

}