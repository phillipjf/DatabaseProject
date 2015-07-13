

import java.sql.*;
import java.util.*;

public class Worker {
	
public static Scanner scan = new Scanner(System.in);
	
	public static void newWorker(Connection con) throws SQLException{
		System.out.println("New Worker Entry");
		String first,last,ssn,select;
		int globalID, jobID,x;
		String tableName = "worker";
		System.out.print("First Name: ");
		first = scan.next();

		System.out.print("Last Name: ");
		last = scan.next();

		System.out.print("SSN: ");
		ssn = scan.next();

		System.out.println("Select Job Title: ");

		String[] id = {"title","jobID"};
		String[][] titles = TableMethods.selectXFrom("jobTitle",id, con);

		//List available Job Titles
		for(int i = 0; i < titles.length; i++){
			System.out.println(" " + (i+1) + ": " + titles[i][0]);
		}
		System.out.print("Enter: ");
		
		//convert string id to int id
		select = scan.next();
		x = Integer.parseInt(select);
		jobID = Integer.parseInt(titles[x-1][1]);
		
		int max = getNextID(jobID,con);

		
	} // end new worker

	/*	=======================================================================
	*	Since global ID is not incremented manually and is set based on
	*	jobTitle this will find the next available id for a specific job title.
	*	=====================================================================*/
	public static int getNextID(int category, Connection con) throws SQLException{
		int globalID = 0;
		String tableName = "worker";
		String id = "globalID";
		int low = category * 100000000;
		int high = (category + 1) * 100000000;
		
		Statement stmt =con.createStatement();
		String query = "SELECT max(" + id + ") FROM " + tableName + 
        " WHERE " + id + ">" + low + " and " + id + " < " + high + ";";

        ResultSet rs = stmt.executeQuery(query);

        int max = 0;
        while (rs.next()){
        	max = rs.getInt(1);
        }

        
        if(max == 0){
        	max = low;
        }
        else {
        	++max;
        }

        return max;

	}


}