package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class Methodss  {
    @FXML
    private BorderPane bordder;
    
    private int id;
    
    @FXML
    private Label showprice;

	public void OpenPaypal() throws IOException
	{
		 String price = showprice.getText();
		    String id = String.valueOf(this.id);
System.out.print(price);
System.out.print(id);

		    FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/PayPal.fxml"));
		    BorderPane view = loader.load();
		    
		    // Get the controller associated with the loaded FXML
		    PayPal card = loader.getController();
		    
		    // Set the data for the Methodss controller
		    card.setdata(price, id);
		    
		    bordder.setBottom(view);
	}
	
	public void OpenCredit() throws IOException
	{
		 String price = showprice.getText();
		    String id = String.valueOf(this.id);
System.out.print(price);
System.out.print(id);

		    FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/CreditCard.fxml"));
		    BorderPane view = loader.load();
		    
		    // Get the controller associated with the loaded FXML
		    CreditCard card = loader.getController();
		    
		    // Set the data for the Methodss controller
		    card.setdata(price, id);
		    
		    bordder.setBottom(view);
		
	}
	public void setdata(String pri, String id) {
	    this.showprice.setText(pri);
	    this.id = Integer.valueOf(id);
	}

	 public void initializeController() throws IOException {
	        String price = showprice.getText();
	        String id = String.valueOf(this.id);
	      

	        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/CreditCard.fxml"));
	        BorderPane view = loader.load();

	        // Get the controller associated with the loaded FXML
	        CreditCard card = loader.getController();

	        // Set the data for the Methodss controller
	        card.setdata(price, id);

	        bordder.setBottom(view);
	    }
	public void initialize() throws IOException {
	

		 String price = showprice.getText();
		    String id = String.valueOf(this.id);
System.out.print(price);
System.out.print(id);

		    FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/CreditCard.fxml"));
		    BorderPane view = loader.load();
		    
		    // Get the controller associated with the loaded FXML
		    CreditCard card = loader.getController();
		    
		    // Set the data for the Methodss controller
		    card.setdata(price, id);
		    
		    bordder.setBottom(view);
		
		
	}


}
