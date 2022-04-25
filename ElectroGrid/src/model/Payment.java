package model;

import java.sql.*; 

public class Payment {

	
	

	public  Connection connect() { 
		
		Connection con = null;
		
		try {
		 
		 	Class.forName("com.mysql.cj.jdbc.Driver");
		 	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/payment", "root", "root");
		 	//System.out.print("connected");
	 		
		}
	 
	 	catch(Exception e) {
	 		
	 		e.printStackTrace();
	 		//System.out.print("not connected");
	 		}

		return con;

	 
	}
	


	public String insertPayment( String HolderName, String Type, String CardNo, String CVV, String Month, String Year, String Total, String Date) { 
		
		String output = ""; 
	 
		try { 
			Connection con = connect();
	  
			if (con == null) {
				return "Error while connecting to the database for inserting."; 
			} 
	 
			String query = " insert into Payment (`pay_id`, `holder_name`,`ctype`,`card_no`,`cvv`,`exp_month`,`exp_year`,`total`, `pay_date`)" + " values (?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			
			preparedStmt.setInt(1, 0); 
			preparedStmt.setString(2, HolderName); 
			preparedStmt.setString(3, Type); 
			preparedStmt.setString(4, CardNo); 
			preparedStmt.setString(5, CVV); 
			preparedStmt.setString(6, Month); 
			preparedStmt.setString(7, Year); 
			preparedStmt.setString(8, Total);
			preparedStmt.setString(9, Date);
	 
	
			preparedStmt.execute(); 
			con.close(); 
	 
			output = "Inserted successfully"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while inserting the payments."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	 } 

	
	public String readPayment() 
	 { 
		String output = ""; 
		
		try
		{ 
			Connection con = connect(); 
			if (con == null) {
				return "Error while connecting to the database for reading.";
				} 
	
			output = "<table border='1'> <tr><th>Pay ID</th>"
					+ "<th>Holder Name</th>"
					+ "<th>Card Type</th>"
					+ "<th>Card No</th>"
					+ "<th>CVV</th>"
					+ "<th>Exp_Month</th>"
					+ "<th>Exp_Year</th>"
					+ "<th>Total</th>"
					+ "<th>Paid Date</th></tr>"; 
	 
			String query = "select * from Payment"; 
			Statement stmt = con.createStatement(); 
			ResultSet rs = stmt.executeQuery(query); 
	 
	 
	
			while (rs.next()) 
			{ 
		 
				String pay_id = Integer.toString(rs.getInt("pay_id")); 
				String holder_name = rs.getString("holder_name"); 
				String ctype = rs.getString("ctype"); 
				String card_no = rs.getString("card_no"); 
				String cvv = rs.getString("cvv"); 
				String exp_month = rs.getString("exp_month"); 
				String exp_year = rs.getString("exp_year"); 
				String total = rs.getString("total"); 
				String pay_date = rs.getString("pay_date"); 
	 
	
				output += "<tr><td>" + pay_id + "</td>";
				output += "<td>" + holder_name + "</td>";
				output += "<td>" + ctype + "</td>"; 
				output += "<td>" + card_no + "</td>"; 
				output += "<td>" + cvv + "</td>"; 
				output += "<td>" + exp_month + "</td>"; 
				output += "<td>" + exp_year + "</td>"; 
				output += "<td>" + total + "</td>"; 
				output += "<td>" + pay_date + "</td>"; 
	 
				 // buttons 		
				   output
						  += "<td><input name='btnUpdate' "
						  + " type='button' value='Update' class='btn btn-secondary' </td>"
				 		  + "<td><form method='post' action='Products.jsp'>"
				 		  + "<input name='btnRemove' " + " type='submit' value='Remove' class='btn btn-danger'>"
				 		  + "<input name='Payment' type='hidden' " + " value='" + pay_id + "'>" + "</form></td></tr>";
				 		 
			} 
			
	 con.close(); 
	 
	// Complete the html table
	 output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
		 output = "Error while reading the Payment."; 
		 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	
	
	public String updatePayment(String PaymentID, String HolderName, String Type, String CardNo, String CVV, String Month, String Year, String Total, String Date) { 
		
	 {
		
	 String output = "";
	 
	 try
	 {
		 Connection con = connect();
		 
		 if (con == null) {
			 
			 return "Error while connecting to the database for updating.";
			 }
	 
		 String query = "UPDATE Payment SET holder_name=?, ctype=?, card_no=?, cvv=?, exp_month=?, exp_year=?, total=?, pay_date=? WHERE pay_id=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);

		 preparedStmt.setString(1, HolderName); 
		 preparedStmt.setString(2, Type); 
		 preparedStmt.setString(3, CardNo); 
		 preparedStmt.setString(4, CVV); 
		 preparedStmt.setString(5, Month); 
		 preparedStmt.setString(6, Year); 
		 preparedStmt.setString(7, Total);
		 preparedStmt.setString(8, Date);
		 preparedStmt.setInt(9, Integer.parseInt(PaymentID));
	
		 preparedStmt.execute();
		 con.close();
		 
		 
		 output = "Updated successfully";
		
	 }
	 catch (Exception e){
		 
		 output = "Error while updating the Payment.";
		 System.err.println(e.getMessage());
	 
	 }
	 return output;
	 } 
	
	
	
	}	
	
	
	
	public String deletePayment(String pay_id)
	 {
	 
		String output = "";
		
		try{
			
			Connection con = connect();
			if (con == null){
				return "Error while connecting to the database for deleting.";
				}
	 
			String query = "delete from Payment where pay_id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);


			preparedStmt.setInt(1, Integer.parseInt(pay_id));
			
			preparedStmt.execute();
			con.close();
			
			output = "Deleted successfully";
		}
	 catch (Exception e) {
		 
		 output = "Error while deleting the Payment.";
		 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	 
}
