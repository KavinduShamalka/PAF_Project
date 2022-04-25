package model;


import java.sql.*;

public class PowerTransmission {
	
	private Connection connect() {
		//Create database connection
		 String dbURL = "jdbc:mysql://localhost:3306/power_transmission";
	     String dbUser = "root";
	     String dbPass = "12345";
	     
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
	
	public String insertPowerTransmission( String t_ID, String t_Acommercial, String t_Aresidential, String t_Aagriculture, String t_date) { 
		
		String output = ""; 
	 
		try { 
			Connection con = connect();
	  
			if (con == null) {
				return "Error while connecting to the database for inserting."; 
			} 
	 
			String query = " insert into p_transmission (`ptid`,`t_ID`, `t_Acommercial`,`t_Aresidential`,`t_Aagriculture`,`t_date`)" + " values (?,?, ?, ?, ?, ?)"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			preparedStmt.setInt(1, 0); 
			preparedStmt.setString(2, t_ID); 
			preparedStmt.setString(3, t_Acommercial); 
			preparedStmt.setString(4, t_Aresidential); 
			preparedStmt.setString(5, t_Aagriculture); 
			preparedStmt.setString(6, t_date); 
	 
	
			preparedStmt.execute(); 
			con.close(); 
	 
			output = "Inserted successfully"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while inserting the Payment."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	 } 
	
	
	
	public String readPowerTransmission() 
	 { 
		String output = ""; 
		
		try
		{ 
			Connection con = connect(); 
			if (con == null) {
				return "Error while connecting to the database for reading.";
				} 
	
			output = "<table border='1'> <tr><th>Power Transmission ID</th>"
					+ "<th>Commercial</th>"
					+ "<th>Residential Type</th>"
					+ "<th>Agriculture</th>"
					+ "<th>Total units</th>"
					+ "<th>Date of transmission</th>"
					+ "<th>Update</th>"
					+ "<th>Delete</th>"; 
	 
			String query = "select * from p_transmission"; 
			Statement stmt = con.createStatement(); 
			ResultSet rs = stmt.executeQuery(query); 
	 
	 
	
			while (rs.next()) 
			{ 
		 
				String t_ID = Integer.toString(rs.getInt("t_ID")); 
				String t_Acommercial = rs.getString("t_Acommercial"); 
				String t_Aresidential = rs.getString("t_Aresidential"); 
				String t_Aagriculture = rs.getString("t_Aagriculture"); 
				String t_date = rs.getString("t_date"); 
				
				
				float newAcommercial = Float.parseFloat(t_Acommercial);
				float newAresidential = Float.parseFloat(t_Aresidential);		
				float newAagriculture = Float.parseFloat(t_Aagriculture);		
				
				float totalUnit =  newAcommercial + newAresidential + newAagriculture;
	 
	
				output += "<tr><td>" + t_ID + "</td>";
				output += "<td>" + t_Acommercial + "</td>";
				output += "<td>" + t_Aresidential + "</td>"; 
				output += "<td>" + t_Aagriculture + "</td>"; 
				output += "<td>" + totalUnit + "</td>"; 
				output += "<td>" + t_date + "</td>"; 
				
	 
				 // buttons 		
				   output
						  += "<td><input name='btnUpdate' "
						  + " type='button' value='Update' class='btn btn-secondary' </td>"
				 		  + "<td><form method='post' action='Products.jsp'>"
				 		  + "<input name='btnRemove' " + " type='submit' value='Remove' class='btn btn-danger'>"
				 		  + "<input name='Payment' type='hidden' " + " value='" + t_ID + "'>" + "</form></td></tr>";
				 		 
			} 
			
	 con.close(); 
	 
	// Complete the html table
	 output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
		 output = "Error while reading the Power transmission details."; 
		 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	
	
	public String updatePowerTransmission(String ID, String t_ID, String t_Acommercial, String t_Aresidential, String t_Aagriculture, String t_date) { 
		
		 {
			
		 String output = "";
		 
		 try
		 {
			 Connection con = connect();
			 
			 if (con == null) {
				 
				 return "Error while connecting to the database for updating.";
				 }
		 
			 String query = "UPDATE p_transmission SET  t_ID=?, t_Acommercial=?, t_Aresidential=?, t_Aagriculture=?, t_date=? WHERE ptid=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);

			 preparedStmt.setString(1, t_ID); 
			 preparedStmt.setString(2, t_Acommercial); 
			 preparedStmt.setString(3, t_Aresidential); 
			 preparedStmt.setString(4, t_Aagriculture); 
			 preparedStmt.setString(5, t_date); 
			 preparedStmt.setInt(6, Integer.parseInt(ID)); 
			 preparedStmt.execute();
			 con.close();
			 
			 
			 output = "Updated successfully";
			
		 }
		 catch (Exception e){
			 
			 output = "Error while updating the Power transmission details.";
			 System.err.println(e.getMessage());
		 
		 }
		 return output;
		 } 
		
		
		
		}	
	
	public String deletePowerTransmissionRecord(String ptid)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for deleting."; }
		
			// create a prepared statement
			String query = "delete from p_transmission where ptid=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(ptid));
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			output = "Record deleted successfully";
		}
		catch (Exception e)
		{
			output = "Error while deleting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
