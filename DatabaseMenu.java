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
                    Patient.newPatient(con);
                    break;
                }
                case 3: {
                    Patient.getPatient(con);
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


}