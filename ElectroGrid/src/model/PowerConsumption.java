package model;

import java.sql.*; 

public class PowerConsumption {

		//A common method to connect to the DB
		private Connection connect()
		{
			//Create database connection
			//Provide the correct details: dbURL, dbUser, dbPass
			 String dbURL = "jdbc:mysql://localhost:3306/power-consumption";
		     String dbUser = "root";
		     String dbPass = "199808";
		     
			Connection con = null;
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection(dbURL, dbUser, dbPass);
			}
			catch (Exception e)
			{e.printStackTrace();}
			return con;
		}
		
		public String insertPowerConsumption( String c_ID, String c_commercial, String c_agriculture, String c_residential, String c_date ) { 
			
			String output = ""; 
		 
			try { 
				Connection con = connect();
		  
				if (con == null) {
					return "Error while connecting to the database for inserting."; 
				} 
		 
				String query = " insert into PowerConsumption (`pc_ID`,`c_ID`, `c_commercial`,`c_agriculture`,`c_residential`, `c_date`)" + " values (?, ?, ?, ?, ?, ?)"; 
				PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
				preparedStmt.setInt(1, 0); 
				preparedStmt.setString(2, c_ID); 
				preparedStmt.setString(3, c_commercial); 
				preparedStmt.setString(4, c_agriculture); 
				preparedStmt.setString(5, c_residential); 
				preparedStmt.setString(6, c_date);
		 
		
				preparedStmt.execute(); 
				con.close(); 
		 
				output = "Data Inserted To Database successfully"; 
			} 
			catch (Exception e) 
			{ 
				output = "Error while inserting the PowerConsumption."; 
				System.err.println(e.getMessage()); 
			} 
			return output; 
		 } 
		
		
	public String readPowerConsumption() 
		 { 
			String output = ""; 
			
			try
			{ 
				Connection con = connect(); 
				if (con == null) {
					return "Error while connecting to the database for reading.";
					} 
		
				output = "<table border='1'> <tr><th>Table ID</th>"
						+ "<th>PowerConsumption ID</th>"
						+ "<th>CommercialAreaPCUnit</th>"
						+ "<th>AgricultureAreaPCUnit</th>"
						+ "<th>ResidentialAreaPCUnit</th>"
						+ "<th>PowerConsumptionDate</th>"
						+ "<th>Total PC Unit</th></tr>";
; 
		 
				String query = "select * from PowerConsumption"; 
				Statement stmt = con.createStatement(); 
				ResultSet rs = stmt.executeQuery(query); 
		 
		 
		
				while (rs.next()) 
				{ 
					String pc_id = Integer.toString(rs.getInt("pc_id"));
					String c_ID = rs.getString ("c_ID"); 
					String c_commercial = rs.getString("c_commercial"); 
					String c_agriculture = rs.getString("c_agriculture"); 
					String c_residential = rs.getString("c_residential"); 
					String c_date = rs.getString("c_date"); 
					
					
					float newCcommercial = Float.parseFloat(c_commercial);
					float newCresidential = Float.parseFloat(c_agriculture);		
					float newCagriculture = Float.parseFloat(c_residential);		
					
					float totalUnit =  newCcommercial + newCresidential + newCagriculture;
		
					output += "<tr><td>" + pc_id + "</td>";
					output += "<td>" + c_ID + "</td>";
					output += "<td>" + c_commercial + "</td>";
					output += "<td>" + c_agriculture + "</td>"; 
					output += "<td>" + c_residential + "</td>"; 
					output += "<td>" + c_date + "</td>";
					output += "<td>" + totalUnit + "</td>"; 
				} 
				
		 con.close(); 
		 
		// Complete the HTML table
		 output += "</table>"; 
		 } 
		 catch (Exception e) 
		 { 
			 output = "Error while reading the PowerConsumption."; 
			 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
	
	public String updatePowerConsumption(String ID, String c_ID, String c_commercial, String c_agriculture, String c_residential, String c_date) { 
		
		 {
			
		 String output = "";
		 
		 try
		 {
			 Connection con = connect();
			 
			 if (con == null) {
				 
				 return "Error while connecting to the database for updating.";
				 }
		 
			 String query = "UPDATE PowerConsumption SET c_ID=?, c_commercial=?, c_agriculture=?, c_residential=?, c_date=? WHERE pc_ID=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);

			 preparedStmt.setString(1, c_ID); 
			 preparedStmt.setString(2, c_commercial); 
			 preparedStmt.setString(3, c_agriculture); 
			 preparedStmt.setString(4, c_residential); 
			 preparedStmt.setString(5, c_date); 
			 preparedStmt.setInt(6, Integer.parseInt(ID));
		
			 preparedStmt.execute();
			 con.close();
			 
			 
			 output = "Data Updated successfully";
			
		 }
		 catch (Exception e){
			 
			 output = "Error while updating the PowerConsumption Details.";
			 System.err.println(e.getMessage());
		 
		 }
		 return output;
		 } 
	
		}	

	public String deletePowerConsumption(String pc_ID)
	 {
	 
		String output = "";
		
		try{
			
			Connection con = connect();
			if (con == null){
				return "Error while connecting to the database for deleting.";
				}
	 
			String query = "delete from PowerConsumption where pc_ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);


			preparedStmt.setInt(1, Integer.parseInt(pc_ID));
			
			preparedStmt.execute();
			con.close();
			
			output = "Deleted successfully Data In Database";
		}
	 catch (Exception e) {
		 
		 output = "Error while deleting the PowerConsumption Details.";
		 System.err.println(e.getMessage());
	 }
	 return output;
	 }
}