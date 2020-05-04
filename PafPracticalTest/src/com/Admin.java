package com;

import java.sql.*; 

public class Admin {
	
	private Connection connect()
	 {
			Connection con = null;
			
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				con =
						DriverManager.getConnection(
						"jdbc:mysql://127.0.0.1:3306/test", "root", "");
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			return con;
	 }
	
	public String readAdmin()
	 {
			String output = "";
	 try
	 {
		 	Connection con = connect();
		 	
		 	if (con == null)
		 	{
		 		return "Error while connecting to the database for reading.";
		 	}
		 	
		 	// Prepare the html table to be displayed
		 	output = "<table border='1'><tr><th>Admin Username</th><th>Admin Password</th><th>Admin Reports</th><th>Update</th><th>Remove</th></tr>"; 
		 	String query = "select * from admin";
		 	Statement stmt = con.createStatement();
		 	ResultSet rs = stmt.executeQuery(query);
		 	
		 	// iterate through the rows in the result set
		 	while (rs.next())
		 	{
		 		String adminID = Integer.toString(rs.getInt("adminID"));
		 		String adminUsername = rs.getString("adminUsername");
		 		String adminPassword = rs.getString("adminPassword");
		 		String adminReports = rs.getString("adminReports");
		 		
		 	// Add into the html table
		 		output += "<tr><td><input id='hidAdminIDUpdate' name='hidAdminIDUpdate' type='hidden' value='" + adminID
		 				+ "'>" + adminUsername + "</td>";
		 		output += "<td>" + adminPassword + "</td>";
		 		output += "<td>" + adminReports + "</td>";
	
		 	// buttons
		 		output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"+ "<td><input name='btnRemove' type='button' value='Remove'class='btnRemove btn btn-danger'data-adminid='" 
		 					+ adminID + "'>" + "</td></tr>";
	}
		 	
		 	con.close();
	 
		 	// Complete the html table
		 	output += "</table>";
	 }
	 
	 catch (Exception e)
	 {
		 output = "Error while reading the items.";
		 System.err.println(e.getMessage());
	 }
	 
	 return output;
 } 
	
	public String insertAdmin(String username, String  password, String reports)
	 {
			String output = "";
			
			try
			{
				Connection con = connect();
				
				if (con == null)
				{
					return "Error while connecting to the database for inserting.";
				}
				
				// create a prepared statement
				String query = " insert into admin (`adminID`,`adminUsername`,`adminPassword`,`adminReports`)"
								+ " values (?, ?, ?, ?)";
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				//binding values
				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, username);
				preparedStmt.setString(3, password);
				preparedStmt.setString(4, reports);
				
				// execute the statement
				preparedStmt.execute();
				con.close();
				
				String newItems = readAdmin();
				output = "{\"status\":\"success\", \"data\": \"" +
							newItems + "\"}";
			}
			catch (Exception e)
			{
				output = "{\"status\":\"error\", \"data\": \"Error while inserting the admin.\"}";
				System.err.println(e.getMessage());
			}
			
			return output;
 } 
	
	public String updateAdmin(String ID, String username, String password,
	 String reports )
	 {
		String output = "";
			
	 try
	 {
		 	Connection con = connect();
		 	
		 	if (con == null)
		 	{
		 		return "Error while connecting to the database for updating.";
		 	}
		 	
		 	// create a prepared statement
		 	String query = "UPDATE admin SET adminUsername=?,adminPassword=?,adminReports=? WHERE adminID=?";
		 	PreparedStatement preparedStmt = con.prepareStatement(query);
		 	
		 	// binding values
		 	preparedStmt.setString(1, username);
		 	preparedStmt.setString(2, password);
		 	preparedStmt.setString(3, reports);
		 	preparedStmt.setInt(4, Integer.parseInt(ID));
		 	
		 	// execute the statement
		 	preparedStmt.execute();
		 	con.close();
		 	
		 	String newAdmin= readAdmin();
		 	output = "{\"status\":\"success\", \"data\": \"" +
		 					newAdmin + "\"}";
	 }
	 catch (Exception e)
	 {
		 
		 	output = "{\"status\":\"error\", \"data\": \"Error while updating admin.\"}";
		 	System.err.println(e.getMessage());
	 }
	 return output;
 }
	
	public String deleteAdmin(String adminID)
	{
		String output = "";
		
		try
		{
				Connection con = connect();
				
				if (con == null)
				{
						return "Error while connecting to the database for deleting.";
				} 
	
				// create a prepared statement
				String query = "delete from admin where adminID=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);

				// binding values
				preparedStmt.setInt(1, Integer.parseInt(adminID));
	 
				// execute the statement
				preparedStmt.execute();
				con.close();
				
				String newAdmin = readAdmin();
				output = "{\"status\":\"success\", \"data\": \"" +
							newAdmin + "\"}";
		}
		catch (Exception e)
		{
				output = "{\"status\":\"error\", \"data\": \"Error while deleting admin.\"}";
				System.err.println(e.getMessage());
		}
		
		return output;
	 }
}


