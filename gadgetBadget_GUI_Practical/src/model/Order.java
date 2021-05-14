package model;

import java.sql.*; 

public class Order {

private static Connection connect() {
		
		Connection con = null;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/it18213768", "root", "");
			
			System.out.println("succsess");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
		
	}
	
	//insert
public String insertOrder(String order_code, String customer_id, String customer_email, String customer_name,  String order_total_amount, String card_no, String cvv_no) 
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
			 	 String query = "INSERT INTO order_gui (orderCode, customerID, customerEmail, customerName, orderTotalAmount, cardNo, cvvNo) VALUES (?, ?, ?, ?, ?, ?, ?)"; 
			 	 
				 PreparedStatement preparedStmt = con.prepareStatement(query); 					 
				 
				 // binding values
//				 preparedStmt.setInt(1, 0);
				 preparedStmt.setString(1, order_code);
				 preparedStmt.setString(2, customer_id);
				 preparedStmt.setString(3, customer_email);
				 preparedStmt.setString(4, customer_name);
				 preparedStmt.setString(5, order_total_amount);
				 preparedStmt.setString(6, card_no);
				 preparedStmt.setString(7, cvv_no);
				 			
				 //System.out.println(query);
				 
				 preparedStmt.execute(); 
				 con.close(); 
				 String newOrder = readOrder(); 
				 
				 output = "{\"status\":\"success\", \"data\": \"" + newOrder + "\"}";
				 
		 } 
		 catch (Exception e) 
		 { 
			 //output = "Error while inserting the order.";
			 output = "{\"status\":\"error\", \"data\": \"Error while inserting the order.\"}";
			 System.err.println(e.getMessage());
			 System.out.println(e.getMessage());
				System.out.println(e);
				e.printStackTrace();
			
		 } 
	 	return output; 
	 } 
	
	//Read orders
	 public String readOrder() 
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
			 output = "<table class='container-fluid' border='1'>"+ 
			 "<thead><th>Order ID</th>" + 
			 "<th>Order Code</th>" + 
			 "<th>Customer ID</th>" + 
			 "<th>Customer Email</th>" + 
			 "<th>Customer Name</th>" +
			 "<th>Total Amount</th>" +
			 "<th>Card No</th>" +
			 "<th>CVV No</th>" +
			 "<th>Update</th><th>Remove</th></thead>"; 
		 
			 
			 String query = "SELECT * FROM order_gui"; 
			 Statement stmt = con.createStatement(); 
			 ResultSet rs = stmt.executeQuery(query); 
			 // iterate through the rows in the result set
			 while (rs.next()) 
			 { 
				 String orderID = Integer.toString(rs.getInt("orderID")); 
				 String orderCode = rs.getString("orderCode"); 
				 String customerID = rs.getString("customerID");  
				 String customerEmail = rs.getString("customerEmail"); 
				 String customerName = rs.getString("customerName");
				 String orderTotalAmount = Double.toString(rs.getDouble("orderTotalAmount")); 
				 String cardNo = rs.getString("cardNo");
				 String cvvNo = Integer.toString(rs.getInt("cvvNo"));
				 
				 // Add into the html table
				 output += "<td>" + orderID + "</td>";
				 output += "<td>" + orderCode + "</td>";
				 output += "<td>" + customerID + "</td>";   
				 output += "<td>" + customerEmail + "</td>"; 
				 output += "<td>" + customerName + "</td>";
				 output += "<td>" + orderTotalAmount + "</td>"; 
				 output += "<td>" + cardNo + "</td>"; 
				 output += "<td>" + cvvNo + "</td>"; 					 
				 
				 
				 // buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
						 + "<td><button class='btnRemove btn btn-danger' name='btnRemove' id ='btnRemove' value='"+ orderID +"' >Remove</button></td></tr>";
			 } 
			 	 con.close(); 
			 	 // Complete the html table
			 	 output += "</table>"; 
		 } 
		 catch (Exception e) 
		 { 
			 output = "Error while reading the order"; 
			 System.err.println(e.getMessage()); 
		 } 
	 	 return output; 
	 } 
		//Update orders
	 public String updateOrder(String orderID,String orderCode, String customerID , String customerEmail, String customerName , String orderTotalAmount, String cardNo, String cvvNo)
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
				 String query = "UPDATE order_gui SET orderCode=? ,customerID=? , customerEmail=? , customerName=?,  orderTotalAmount=? , cardNo=?, cvvNo=?  WHERE orderID=?"; 
				 PreparedStatement preparedStmt = con.prepareStatement(query); 
				 
				 // binding values
				 preparedStmt.setString(1, orderCode); 
				 preparedStmt.setString(2, customerID); 	  
				 preparedStmt.setString(3, customerEmail); 
				 preparedStmt.setString(4, customerName);
				 preparedStmt.setDouble(5, Double.parseDouble(orderTotalAmount)); 
				 preparedStmt.setString(6, cardNo); 
				 preparedStmt.setString(7, cvvNo); 
				 preparedStmt.setInt(8, Integer.parseInt(orderID)); 
				 
				 // execute the statement
				 preparedStmt.execute(); 
				 con.close(); 
				 //output = "Updated successfully"; 
				 String newOrder = readOrder(); 
				 output = "{\"status\":\"success\", \"data\": \"" + newOrder + "\"}"; 

			 } 
			 catch (Exception e) 
			 { 
				 //output = "Error while updating the item."; 
				 output = "{\"status\":\"error\", \"data\": \"Error while updating the order.\"}"; 
				 System.err.println(e.getMessage()); 
				 System.out.println(e);
			 } 
			 	return output; 
			 } 
		
		
			//Delete orders
			 public String deleteOrder(String orderID) 
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
				 String query = "DELETE FROM order_gui WHERE orderID=?"; 
				 PreparedStatement preparedStmt = con.prepareStatement(query); 
				 
				 // binding values
				 preparedStmt.setInt(1, Integer.parseInt(orderID)); 
				 
				 // execute the statement
				 preparedStmt.execute(); 
				 con.close(); 
				 //output = "Deleted successfully"; 
				 String newOrder = readOrder(); output = "{\"status\":\"success\", \"data\": \"" + newOrder + "\"}";

			 } 
			 catch (Exception e) 
			 { 
				 //output = "Error while deleting the order."; 
				 output = "{\"status\":\"error\", \"data\": \"Error while deleting the order.\"}"; 
				 System.err.println(e.getMessage()); 
				 System.out.println(e);
			 } 
			 return output;
			 }
}
