package model;

import java.sql.*;
import java.sql.Connection;


public class Billing {
	
	//A common method to connect to the DB
	private Connection connect() 
	 { 
	 Connection con = null; 
	 try
	 { 
	 Class.forName("com.mysql.jdbc.Driver"); 
	 
	 //Provide the correct details: DBServer/DBName, username, password 
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electro-grid", "root", "");
	 
	//For testing
	 System.out.println("Successfully Connected");
	 } 
	 catch (Exception e) 
	 {
		 e.printStackTrace();
	 } 
	 return con; 
	 } 
	
	public String insertBilling(String customerName, float amount, int units) 
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
			String query = " insert into billing(`customerName`,`amount`,`units`)" + " values (?, ?, ?)"; 
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setString(1, customerName);
			preparedStmt.setFloat(2, amount);
			preparedStmt.setInt(3, units);
			
			// execute the statement
			preparedStmt.execute(); 
			con.close(); 
			output = "Billing Inserted successfully";
			} 
		catch (Exception e) 
		{ 
			output = "Error while inserting the billing details."; 
			System.err.println(e.getMessage()); 
			} 
		return output; 
		} 
	
	public String readBillings() 
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
			output = "<table border='1'><tr><th>Bill ID</th>" + "<th>Customer</th>" + "<th>Amount</th>" + 
			"<th>Units</th>"+"<th>Update</th><th>Remove</th></tr>";
			
			String query = "select * from billing"; 
			Statement stmt = con.createStatement(); 
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			while (rs.next()) 
			{ 
				String billingId = Integer.toString(rs.getInt("id"));
				String customerName = rs.getString("customerName");
				int units = rs.getInt("units"); 
				float amount = rs.getFloat("amount");
				
				// Add into the html table
				output += "<tr><td>" + billingId + "</td>";
				output += "<td>" + customerName + "</td>";
				output += "<td>" + amount + "</td>"; 
				output += "<td>" + units + "</td>"; 
				
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>" 
				+ "<td><form method='post' action='Billings.jsp'>" 
						+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>" 
				+ "<input name='itemID' type='hidden' value='" + billingId  
				+ "'>" + "</form></td></tr>";
				
			} 
	 con.close(); 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while reading the billing details."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	
	public String updateBilling(int id, String customerName, float amount, int units)
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
			String query = "UPDATE billing SET customerName=?,amount=?,units=? WHERE id=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setString(1, customerName);
			preparedStmt.setFloat(2, amount);
			preparedStmt.setInt(3, units);
			preparedStmt.setInt(4, id);
			
			// execute the statement
			preparedStmt.execute(); 
			con.close(); 
			output = "Billing Updated successfully";
			
		 } 
		 catch (Exception e) 
		 { 
			 output = "Error while updating the billing details."; 
			 System.err.println(e.getMessage()); 
		 } 
		
		return output;
    } 
	
		public String deleteBilling(int id) 
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
				String query = "delete from billing where id=?"; 
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				// binding values
				preparedStmt.setInt(1, id);
				
				// execute the statement
				preparedStmt.execute(); 
				con.close(); 
				output = "Billing Deleted successfully"; 
					
			} 
			catch (Exception e) 
			{ 
				output = "Error while deleting the billing details."; 
				System.err.println(e.getMessage()); 
			} 
			
			return output; 
		 } 
}