package application;

import javafx.scene.control.Button;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CreditCard {
    @FXML
    private Button moibtn;

    @FXML
    private Label pricing;
    
    private int id;
    
  
    
	public void setdata(String pri, String id) {
	    pricing.setText(pri);
	    this.id = Integer.valueOf(id);
	}
	
	public void ConfirmPayment()
	{
	    int music_id = this.id;
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject", "root", "Allah1069$");
	        Statement stmt = con.createStatement();
	        String sql = "UPDATE music SET sold = 'true' WHERE id = '"+ music_id  +"'";
	        int rowsAffected = stmt.executeUpdate(sql);
	        
	        if (rowsAffected > 0) {
	            System.out.print("Music with id: " + music_id + " is sold");
	        } else {
	            System.out.print("No music found with id: " + music_id);
	        }
	        
	        // Close the resources (stmt, con) when done.
	        stmt.close();
	        con.close();
	    } catch (Exception e) {
	        System.out.print("Error: " + e.getMessage());
	    }
	}

}
