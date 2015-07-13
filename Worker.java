

import java.sql.*;
import java.util.*;

public class Worker {
	
public static Scanner scan = new Scanner(System.in);
	
	public static void newWorker(Connection con) throws SQLException{
		System.out.println("New Worker Entry");
		String first,last,ssn;
		int globalID, jobID;

		System.out.print("First Name: ");
		first = scan.next();

		System.out.print("Last Name: ");
		last = scan.next();

		System.out.print("SSN: ");
		ssn = scan.next();

		System.out.print("Select Job Title: ");

		String[] id = {"title"};
		String[][] titles = TableMethods.selectXFrom("jobTitle",id, con);

		for(int i = 0; i < titles.length; i++){
			System.out.println(titles[i][0]);
		}

	}


}