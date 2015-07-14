/**
 * Created by Kullen on 6/29/2015.
 */


//importing everything for now until we know what we don't need
import java.sql.*;
import java.util.*;
import java.io.Console;
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
    public static int loggedIn = 0;


    public static void main(String[] args) throws Exception{
        System.out.print("\n\nWelcome the COMP Health. \n");
        Console console = System.console();
        
        //LOG INTO OIT MYSQL
        while(loggedIn < 1){
            System.out.print("\nEnter Username: ");
            username = scan.next();
            System.out.print("Enter Password: ");
            char[] passString = console.readPassword();
            password = new String(passString );
            System.out.print("Enter DB Name:  ");
            databaseName = scan.next();
            getConnection();
        }



        boolean loop = true;
        

        while(loop) {
            System.out.println("\n\n*********************************");
            System.out.print("Menu:\nselect one of the following" +
                    "\n1: Open Patient Menu\n" +
                    "3: Add Worker\n" +
                    "9: quit\n" +
                    "*********************************\n\n" +
                    "Enter: ");
            Scanner scan = new Scanner(System.in);

            int menu = scan.nextInt();
            switch (menu) {
                case 1: {
                    Patient.patientMenu(con);
                    break;
                }
                case 2: {

                    break;
                }
                case 3: {
                   Worker.newWorker(con);
                    break;
                }
                case 4: {
                    
                    break;
                }
                case 5: {
                    
                    break;
                }
                case 9: {
                    con.close();
                    loop = false;
                }
            }


        } //end while loop


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
            loggedIn = 1;
            return con;
        }
        catch(Exception e){
            loggedIn = 0;
            System.out.println("Failed");
            System.out.println(e);
        }

        return null;
    }


}