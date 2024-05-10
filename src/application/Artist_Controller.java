package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Artist_Controller implements Initializable {

    @FXML
    private ImageView img1;

    @FXML
    private ImageView img2;

    @FXML
    private ImageView img3;

    @FXML
    private ImageView img4;

    @FXML
    private Label title1;

    @FXML
    private Label title2;

    @FXML
    private Label title3;

    @FXML
    private Label title4;
    private List<String> my_list = new ArrayList<>();
    private List<String> my_list2 = new ArrayList<>();
  
    @FXML
    private Label labelname;
    public Artist_Controller() {
    }
	
    public Artist_Controller(String name) {
        labelname.setText(name);
        System.out.print(name);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		String username = "Mahnoor75";
		String user_id = "0";
		labelname.setText(username);

		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
	    	Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject", "root", "Allah1069$");
	    Statement stmt1 = con1.createStatement();
	    System.out.print("Checking Database\n");
	    String sql1 = "select id from registerinfo where username = '"+ username  +"'";
	    ResultSet rs1 = stmt1.executeQuery(sql1);
	    if(rs1.next())
	     user_id = (rs1.getString("id"));
	    
	    
		
		Class.forName("com.mysql.cj.jdbc.Driver");
    	Connection con2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject", "root", "Allah1069$");
    Statement stmt2 = con2.createStatement();
    System.out.print("Checking Database\n");
    String sql2 = "select * from project where artist_id = '"+ user_id  +"' limit 4";
    ResultSet rs2 = stmt2.executeQuery(sql2);
    int i = 1;
   
    while(rs2.next())
    {
    	
    	   String str= rs2.getString("title");
    	   my_list.add(str);
    	   String str2 = rs2.getString("src");
    	   my_list2.add(str2);
    	
    }
    Image image1 = new Image(getClass().getResourceAsStream(my_list2.get(0)));
	img1.setImage(image1);
	   Image image2 = new Image(getClass().getResourceAsStream(my_list2.get(1)));
		img2.setImage(image2);
		   Image image3 = new Image(getClass().getResourceAsStream(my_list2.get(2)));
			img3.setImage(image3);
			   Image image4 = new Image(getClass().getResourceAsStream(my_list2.get(3)));
				img4.setImage(image4);
	
    title1.setText(my_list.get(0));
    title2.setText(my_list.get(1));
    title3.setText(my_list.get(2));
    title4.setText(my_list.get(3));

 
			
			
		
			
		} catch (Exception e) {
			System.out.print(e);
			// TODO: handle exception
		}
		
	}
    
    
	
}
