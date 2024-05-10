package application;

import javafx.scene.control.Button;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class Payment {

    @FXML
    private BorderPane border;
    @FXML
    private Label msg;
    @FXML
    private Label paygenre;

    @FXML
    private Label payid;

    @FXML
    private Label paytitle;
    @FXML
    private Label payprice;
    @FXML
    private Button paybtn;
    @FXML
    private Button focusbtn;

    
    
    public void setPaymentData(String title, String id, String genre) {
        paytitle.setText(title);
        paygenre.setText(genre);
        payid.setText(id);
        
        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject", "root", "Allah1069$");
	            Statement stmt = con.createStatement();
	            String sql = "select * from music where id = '" + id + "'";
	            ResultSet rs = stmt.executeQuery(sql);
	            if (rs.next()) {  // Move the cursor to the first row in the result set
	                String sold = rs.getString("sold");
	                String price = rs.getString("price");
	                payprice.setText(price);
	                if(sold.equals("true"))
	                {
	                	msg.setText("This Music is no longer available in the stock :(");
	                paybtn.setVisible(false);
	                }
	                else
	                {
	                	msg.setText("Select your Payment Method");
	                }
	            } else {
	                System.out.print("No data found for ID: " + id);
	            }
	              

	          
	        } 
        catch (Exception e) {
	            System.out.print(e);
	        }
        
     
    }

    public void setPaymentMethods() throws IOException {
        String price = payprice.getText();
        String id = payid.getText();

        // Create an instance of the Methodss controller
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/Methods.fxml"));
        BorderPane view = loader.load();
        Methodss methodssController = loader.getController();

        // Set the data for the Methodss controller
        methodssController.setdata(price, id);

        // Initialize the Methodss controller (explicitly)
        methodssController.initializeController();

        border.setCenter(view);
    }

	
}
