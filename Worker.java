

import java.sql.*;
import java.util.*;

public class Worker {
	
public static Scanner scan = new Scanner(System.in);
	
	public static void newWorker(Connection con) throws SQLException{
		System.out.println("New Worker Entry");
		String first,last,ssn,select;
		int globalID, jobID,x;

		System.out.print("First Name: ");
		first = scan.next();

		System.out.print("Last Name: ");
		last = scan.next();

		System.out.print("SSN: ");
		ssn = scan.next();

		System.out.println("Select Job Title: ");

		String[] id = {"title","jobID"};
		String[][] titles = TableMethods.selectXFrom("jobTitle",id, con);

		for(int i = 0; i < titles.length; i++){
			System.out.println(" " + (i+1) + ": " + titles[i][0]);
		}
		System.out.print("Enter: ");
		select = scan.next();
		x = Integer.parseInt(select);
		jobID = Integer.parseInt(titles[x-1][1]);
		System.out.println(jobID + "");
	}


}