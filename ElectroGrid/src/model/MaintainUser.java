package model;

import java.sql.*;

public class MaintainUser {
	
	private Connection connect() {
		//Create database connection
		String dbURL = "jdbc:mysql://localhost:3306/userdb1";
		String dbUser = "root";
		String dbPass = "root";

		Connection con = null;
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		//Provide the correct details: DBServer/DBName, username, password
		con = DriverManager.getConnection(dbURL, dbUser, dbPass);
		}
		catch (Exception e)
		{e.printStackTrace();}
		return con;
	}

	
	
	// Insert...
	
	public String insertUserDetails(String u_id, String f_Name, String l_Name, String address, String phone, String mail, String city) { 
		
		String output = ""; 
	 
		try { 
			Connection con = connect();
	  
			if (con == null) {
				return "Errors occurred while connecting to the database for inserting."; 
			} 
	 
			String query = " insert into maintainuser (`u_num`, `u_id`, `f_Name`,`l_Name`,`address`,`phone`,`mail`,`city`)" + " values (?, ?, ?, ?, ?, ?, ?, ?)"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, u_id); 
			preparedStmt.setString(3, f_Name); 
			preparedStmt.setString(4, l_Name); 
			preparedStmt.setString(5, address); 
			preparedStmt.setString(6, phone); 
			preparedStmt.setString(7, mail); 
			preparedStmt.setString(8, city); 
	 
	
			preparedStmt.execute(); 
			con.close(); 
	 
			output = "User Details successfully added.."; 
		} 
		catch (Exception e) 
		{ 
			output = "Errors occurred while inserting the User Details."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	 } 
	
	//Read...
	
	public String readUserDetails() 
	 { 
		String output = ""; 
		
		try
		{ 
			Connection con = connect(); 
			if (con == null) {
				return "Error occurred while connecting to the database for reading.";
				} 
	
			output = "<table border='1'> <tr><th>User ID</th>"
					+ "<th>First Name</th>"
					+ "<th>Last Name</th>"
					+ "<th>Address</th>"
					+ "<th>Contact No</th>"
					+ "<th>Email</th>"
					+ "<th>City</th></tr>"; 
	 
			String query = "select * from maintainuser"; 
			Statement stmt = con.createStatement(); 
			ResultSet rs = stmt.executeQuery(query); 
	 
	 
	
			while (rs.next()) 
			{ 
		 
				String u_id = Integer.toString(rs.getInt("u_id")); 
				String f_name = rs.getString("f_name"); 
				String l_name = rs.getString("l_name"); 
				String address = rs.getString("address");
				String phone = rs.getString("phone"); 
				String mail = rs.getString("mail"); 
				String city = rs.getString("city"); 
	 
	
				output += "<tr><td>" + u_id + "</td>";
				output += "<td>" + f_name + "</td>";
				output += "<td>" + l_name + "</td>"; 
				output += "<td>" + address + "</td>";
				output += "<td>" + phone + "</td>"; 
				output += "<td>" + mail + "</td>"; 
				output += "<td>" + city + "</td>"; 
				 // buttons 		
				   output
						  += "<td><input name='btnUpdate' "
						  + " type='button' value='Update' class='btn btn-secondary' </td>"
				 		  + "<td><form method='post' action='Products.jsp'>"
				 		  + "<input name='btnRemove' " + " type='submit' value='Remove' class='btn btn-danger'>"
				 		  + "<input name='buyer' type='hidden' " + " value='" + u_id + "'>" + "</form></td></tr>";
				 		 
			} 
			
	 con.close(); 
	 
	// Complete the html table
	 output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
		 output = "Error occurred while reading the user Details."; 
		 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	
	
	
	//update...
	
	public String updateUserDetails(String u_num, String u_id, String f_name, String l_name, String address, String phone, String mail, String city)
	 {
		
	 String output = "";
	 
	 try
	 {
		 Connection con = connect();
		 
		 if (con == null) {
			 
			 return "Error occurred while connecting to the database for updating.";
			 }
		 
		 
		
			 
		 String query = "UPDATE maintainuser SET u_id=?, f_name=?, l_name=?, address=?, phone=?, mail=?, city=? WHERE u_num=?";

		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 preparedStmt.setString(1, u_id);
		 preparedStmt.setString(2, f_name); 
		 preparedStmt.setString(3, l_name); 
		 preparedStmt.setString(4, address); 
		 preparedStmt.setString(5, phone); 
		 preparedStmt.setString(6, mail); 
		 preparedStmt.setString(7, city);;
		 preparedStmt.setInt(8, Integer.parseInt(u_num));
		
				
			 
		 preparedStmt.execute();
		 con.close();
	
		 output = "User Details successfully updated ";
		 }
	
	 catch (Exception e){
		 
		 output = "Error occurred while updating the user Details.";
		 System.err.println(e.getMessage());
	 
	 }
	
	 return output;
	 }
	
	//delete
	
	public String deleteUserDetails(String u_num)
	 {
	 
		String output = "";
		
		try{
			
			Connection con = connect();
			if (con == null){
				return "Error occurred while connecting to the database for deleting.";
				}
	 
			
			String query = "delete from maintainuser where u_num=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			
			preparedStmt.setInt(1, Integer.parseInt(u_num));
			
			preparedStmt.execute();
			con.close();
			
			output = "user Details successfully deleted ";
		}
			
	 catch (Exception e) {
		 
		 output = "Error while deleting the user Details.";
		 System.err.println(e.getMessage());
	 }
		
	 return output;
	 
	 }

}
