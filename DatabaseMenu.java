/**
 * Created by Kullen on 6/29/2015.
 */


//importing everything for now until we know what we don't need
import java.sql.*;
import java.util.*;

//Build path windows  ***************************************************
//Compile : javac -cp ".;mysql-connector-java-5.1.35-bin.jar;" DatabaseMenu.java
//Run:		java -cp ".;mysql-connector-java-s.1.35-bin.jar;" DbHelper
//***********************************************************************

public class DatabaseMenu{

    public static String username ;
    public static String password ;
    public static String databaseName ;
    public static Connection con;
    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws Exception{
        System.out.println("Database Menu");
        boolean loop = true;
        while(loop) {
            System.out.print("Menu:\nselect one of the following" +
                    "\n1: Connect to database.\n" +
                    "2: Add Patient\n" +
                    "3: Get Patient Info\n" +
                    "9: quit\n\n" +
                    "Enter: ");
            Scanner scan = new Scanner(System.in);

            int menu = scan.nextInt();
            switch (menu) {
                case 1: {
                    System.out.print("Enter Username: ");
                    username = scan.next();
                    System.out.print("Enter Password: ");
                    password = scan.next();
                    System.out.print("Enter DB Name:  ");
                    databaseName = scan.next();
                    getConnection();
                    break;
                }
                case 2: {
                    newPatient();
                    break;
                }
                case 3: {
                    getPatient();
                    break;
                }
                case 9: {
                    con.close();
                    loop = false;
                }
            }
        }


    }

    /** =======================================================================
    *   get connection
    *   connection to OIT MySQL database
    *   @returns Connection
    *   =======================================================================*/
    public static Connection getConnection() throws Exception{

        try{
            String driver 	="com.mysql.jdbc.Driver";
            String url		="jdbc:mysql://acadmysql.duc.auburn.edu/";
            //String username = "";
            //String password = database password;
            //String databaseName = ";
            Class.forName(driver).newInstance();

            //connect
            con = DriverManager.getConnection(url+databaseName,username,password);
            System.out.println("Connected");
            return con;
        }
        catch(Exception e){
            System.out.println("Failed");
            System.out.println(e);
        }

        return null;
    }


    /* ========================================================================
    *   newPatient()
    *   adds to patient to patient table
    *   table auto increments pid and assigns it to new patient
    * ========================================================================*/
    public static void newPatient(){
        System.out.println("New Patient Entry Portal");
        try{
            String tablename = "patient";
            Scanner scan = new Scanner(System.in);
            System.out.print("Enter Patient \nFirst Name: ");
            String fName = scan.next();
            System.out.print("Last Name: ");
            String lName = scan.next();
            System.out.print("SSN: ");
            String ssn = scan.next();
            System.out.print("Emergency Contact Number: ");
            String eContact = scan.next();
            System.out.print("Policy Number: ");
            String policyNum = scan.next();
            PreparedStatement insert  = con.prepareStatement("INSERT INTO " + tablename +
                    " (first,last,ssn,eContact,insurance) VALUES" +
                    " ( '" +fName +"'," +
                    "'" + lName +"'," +
                    "" + ssn +"," +
                    "'" + eContact +"'," +
                    "'" + policyNum +"');");
           insert.executeUpdate();
            insert.close();

        }
        catch(Exception e){
            System.out.println("Not connected to Database. Please Connect");
        }

    } // END NEW PATIENT


    /* ========================================================================
    *   getPatient()
    *   allows you to search the unique indexes of the patient table.
    *   search patient by pid or ssn
    *   prints all patient info.
    * =======================================================================*/
    public static void getPatient() {
        System.out.println("Get Existing Patients");
        String tableName = "patient";


        try{
            Statement stmt =con.createStatement();

            List<String> columns = getUniqueColumns(tableName);
            System.out.println("Please Select");
            int i  = 1;
            for(String x : columns){
                System.out.println(i + ": to search by " + x);
                i++;
            }
            System.out.print("ENTER: ");
            String columnNameS = scan.next();
            int columnNameI = Integer.parseInt(columnNameS)-1;
            System.out.print("Enter in Patient's " + columns.get(columnNameI).toUpperCase() + ": ");
            String search = scan.next();

            String query = "SELECT *" +
                    " FROM " + tableName +
                    " WHERE " + columns.get(columnNameI) + " = " + search + ";";
            ResultSet rs = stmt.executeQuery(query);

            //you should know what types of data that is being return.
            //make in the future we can un hard code this.
            while(rs.next()){
                System.out.println("\n\n***********************************");
                int pid = rs.getInt("pid");
                String first = rs.getString("first");
                String last = rs.getString("last");
                String ssn = rs.getString("ssn");
                String eContact = rs.getString("eContact");
                String insurance = rs.getString("insurance");
                //Display values
                System.out.println("PID: " + pid);
                System.out.println("First: " + first);
                System.out.println("Last: " + last);
                System.out.println("SSN: " + ssn);
                System.out.println("Emergency Contact Number: " + eContact);
                System.out.println("Policy Number: " + insurance);
                System.out.println("***********************************\n\n");
            }

        } catch(Exception e){
            System.out.println("Patient Not Found");
        }

    }


    /* ========================================================================
    *   get All returns all column indexes from any given table
    *   @param String
    *   @return ArrayList<String>
    *  ========================================================================*/
    private static ArrayList<String> getAllColumnNames(String tableName) throws SQLException {
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
    private static ArrayList<String> getUniqueColumns(String tableName)throws SQLException {
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