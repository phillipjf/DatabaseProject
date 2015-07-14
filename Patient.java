

import java.sql.*;
import java.util.*;

public class Patient {
	
public static Scanner scan = new Scanner(System.in);

    /* ========================================================================
    *   newPatient()
    *   adds to patient to patient table
    *   table auto increments pid and assigns it to new patient
    *	@param Connection
    * ========================================================================*/
    public static void newPatient(Connection con){
        System.out.println("New Patient Entry");


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
            System.out.println("Error");
        }

    } // END NEW PATIENT


 /* ========================================================================
    *   getPatient()
    *   allows you to search the unique indexes of the patient table.
    *   search patient by pid or ssn
    *   prints all patient info.
    *   @param Connection
    * =======================================================================*/
    public static void getPatient(Connection con) {
        System.out.println("*********************************");
        System.out.println("Search For Existing Patients");
        System.out.println("*********************************\n\n");
        String tableName = "patient";

        try{
            //list all unique idtenifiers in the patient table.
            List<String> columns = TableMethods.getUniqueColumns(tableName,con);
            System.out.println("How would you like to search?\n");
            int i  = 1;
            for(String x : columns){
                System.out.println("Select " + i + ": to search by " + x);
                i++;
            }
            System.out.println("Enter all to display all");
            System.out.print("\n\nENTER: ");
            String columnNameS = scan.next();

            if(columnNameS.equalsIgnoreCase("all")){  //will list every body
                getAll(con);
            }
            else{

                int columnNameI = Integer.parseInt(columnNameS)-1;
                System.out.print("Enter in Patient's " + columns.get(columnNameI).toUpperCase() + ": ");
                String search = scan.next();
                Statement stmt =con.createStatement();
                String query = "SELECT *" +
                        " FROM " + tableName +
                        " WHERE " + columns.get(columnNameI) + " = " + search + ";";
                ResultSet rs = stmt.executeQuery(query);

                //you should know what types of data that is being return.
                //make in the future we can un hard code this.
                i = 0;
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
                    ++i;
                }

                if(i == 0){
                    System.out.println("\n\nUnable to find patient\n\n");
                }

            } //end else

        } catch(Exception e){
            System.out.println("Patient Not Found");
        }

    } //end get patient

    public static void getAll(Connection con) throws SQLException{
        String[][] arr = TableMethods.selectAll("patient",con);
        ArrayList<String> cNames = TableMethods.getAllColumnNames("patient",con);

        int rows = arr.length;
        int columns = arr[0].length;
        System.out.println("*******************************");
        System.out.println("All Patient Records");
        for(int i = 0; i < rows ; i++){
            System.out.print("\n");
            for(int c = 0 ; c < columns ; c++) {
                System.out.print(arr[i][c]);
                System.out.print(", ");
            }
        }

    } // end getALL

    public static void columns(Connection con) throws SQLException{

             String[] a = TableMethods.getColumnTypeNames("patient",con);
            for(int i = 0; i < a.length ; i++){
                System.out.println("" + a[i]);
            }
    }

}