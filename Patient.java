

import java.sql.*;
import java.util.*;

public class Patient {
	
public static Scanner scan = new Scanner(System.in);

    /* ========================================================================
    *   newPatient()
    *   adds to patient to patient table
    *   table auto increments pid and assigns it to new patient
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
            System.out.println("Not connected to Database. Please Connect");
        }

    } // END NEW PATIENT


    /* ========================================================================
    *   getPatient()
    *   allows you to search the unique indexes of the patient table.
    *   search patient by pid or ssn
    *   prints all patient info.
    * =======================================================================*/
    public static void getPatient(Connection con) {
        System.out.println("Get Existing Patients");
        String tableName = "patient";


        try{
            Statement stmt =con.createStatement();

            List<String> columns = TableMethods.getUniqueColumns(tableName,con);
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

}