
package application;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;
import java.util.ResourceBundle;

import java.net.URL;

public class Contoller implements Initializable {
	@FXML
	private ComboBox<String> combo;
	@FXML
	ObservableList<String> list = FXCollections.observableArrayList("Artist", "Buyer");
	@FXML
	Label testlabel;
	@FXML
	TextField textfield1;
	@FXML
	TextField textfield2;
	@FXML
	 TextField textfield3;
	@FXML
	TextField textfield4;
	@FXML
	 TextField textfield6;
	@FXML
	TextField textfield7;

	@FXML
	 AnchorPane errorside;
	@FXML
	 Label errormsg;
	private String user_id;
	  @FXML
	    private BorderPane pane2;
	  
	  @FXML
	    private TextField emailfield;

	    @FXML
	    private TextField namefield;

	    @FXML
	    private TextField passfield;
	    @FXML
	    private TextField unamefield;

	    @FXML
	    private TextField utypefield;
	    
	    @FXML
	    private TextField unamefield2;

	    @FXML
	    private TextField utypefield2;

	    @FXML
	    private TextField emailfield2;

	    @FXML
	    private TextField namefield2;

	    @FXML
	    private TextField passfield2;
	    @FXML
	    private TextField pathfield2;
	    @FXML
	    private TextField musicidfield;

public Contoller()
{
	textfield1 = new TextField();
	textfield2 = new TextField();
	textfield3 = new TextField();
	textfield4 = new TextField();
	textfield6 = new TextField();
	textfield7 = new TextField();
	combo = new ComboBox<>();
	
}

public void setEditData(User user)
{
	user_id = user.getId();
	namefield2.setText(user.getFull_name());
	emailfield2.setText(user.getEmail());
	unamefield2.setText(user.getUsername());
	utypefield2.setText(user.getUsertype());
	passfield2.setText(user.getPassword());
	
}

public void Update() throws ClassNotFoundException, SQLException
{
	String full_name = namefield2.getText().toString();
	String email =emailfield2.getText().toString();
	String username =unamefield2.getText().toString();
	String pass =passfield2.getText().toString();
	String usertype = utypefield2.getText().toString();
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject", "root", "Allah1069$");
Statement stmt2 = con2.createStatement();
System.out.print("Inserting Records\n");
String sql2 =  "UPDATE registerinfo SET full_name = '" + full_name + "', email = '" + email + "', " +
        "username = '" + username + "', login_password = '" + pass + "', user_type = '" + usertype + "' " +
        "WHERE id = " + user_id;
stmt2.executeUpdate(sql2);
	
}
	@FXML

    public void Exit(ActionEvent event) {
        System.exit(0);
    }
	@FXML
	public void OpenRegisterWindow (ActionEvent event) throws IOException
	{
		 Node source = (Node) event.getSource();
		 Stage oldStage = (Stage) source.getScene().getWindow();
		 oldStage.close();
		 
	Stage primaryStage = new Stage();
	Parent root = FXMLLoader.load(getClass().getResource("FXML/LoginForm.fxml"));

	primaryStage.setTitle("AuraMusica");
	primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("logo.jpg")));
	Scene scene = new Scene (root);
	scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	primaryStage.setScene(scene);
	primaryStage.show();
		
	}
	@FXML
    public void OpenLoginWindow2(ActionEvent event) throws IOException {
   
    
    
	 Node source = (Node) event.getSource();
	 Stage oldStage = (Stage) source.getScene().getWindow();
	 oldStage.close();
	 
Stage primaryStage = new Stage();
Parent root = FXMLLoader.load(getClass().getResource("FXML/LoginForm2.fxml"));

primaryStage.setTitle("AuraMusica");
primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("logo.jpg")));
Scene scene = new Scene (root);
scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
primaryStage.setScene(scene);
primaryStage.show();
    }
    
	
	
	

	@FXML
    public void OpenLoginWindow(ActionEvent event) throws IOException {
		String testuser = textfield3.getText().toString();
try 
{
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject", "root", "Allah1069$");
Statement stmt = con.createStatement();
System.out.print("Checking Database\n");
String sql = "select *  from registerinfo where username = '" + testuser + "' ";
ResultSet rs = stmt.executeQuery(sql);
boolean flag = rs.next();

if(flag)
{
	 
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("FXML/ErrorScreen2.fxml"));

		primaryStage.setTitle("AuraMusica");
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("logo.jpg")));
		Scene scene = new Scene (root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
}

else
{

	String full_name = textfield1.getText().toString();
	String email =textfield2.getText().toString();
	String username =textfield3.getText().toString();
	String pass =textfield4.getText().toString();
	String usertype = combo.getSelectionModel().getSelectedItem().toString();
	try
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject", "root", "Allah1069$");
	Statement stmt2 = con2.createStatement();
	System.out.print("Inserting Records\n");
	String sql2 = "INSERT INTO registerinfo (full_name, email, username, login_password, user_type) VALUES ( '" + full_name + "', '" + email + "' , '" + username + "' , '" + pass + "' , '" + usertype + "')";
	stmt2.executeUpdate(sql2);

	if(usertype.equals("Artist"))
	{
		 sql2 ="insert into artist (full_name , usertype) values ('"+full_name+"' , '"+usertype+"')";
		 stmt2.executeUpdate(sql2);

	
	}
	else if(usertype.equals("Producer"))
	{
		 sql2 ="insert into producer (full_name , usertype) values ('"+full_name+"' , '"+usertype+"')";
		 stmt2.executeUpdate(sql2);

	
	}
	else if(usertype.equals("Buyer"))
	{
		 sql2 ="insert into buyer (full_name , usertype) values ('"+full_name+"' , '"+usertype+"')";
		 stmt2.executeUpdate(sql2);

	
	}
	
	
	
	}
	catch(Exception e)
	{
		System.out.print(e);
	}
	 Node source = (Node) event.getSource();
	 Stage oldStage = (Stage) source.getScene().getWindow();
	 oldStage.close();
	 
Stage primaryStage = new Stage();
Parent root = FXMLLoader.load(getClass().getResource("FXML/LoginForm2.fxml"));

primaryStage.setTitle("AuraMusica");
primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("logo.jpg")));
Scene scene = new Scene (root);
scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
primaryStage.setScene(scene);
primaryStage.show();
}
}

catch(Exception e)
{
	System.out.print(e);
}
		
		
    }
    
	
	public void CheckDatabase(ActionEvent event) throws IOException
	{
		
		String testuser = textfield6.getText().toString();
		String testpass = textfield7.getText().toString();
		
	if(testuser.equals("Mahnoor") && testpass.equals("mani"))
	{
		 Node source = (Node) event.getSource();
		 Stage oldStage = (Stage) source.getScene().getWindow();
		 oldStage.close();
		 
	Stage primaryStage = new Stage();
	Parent root = FXMLLoader.load(getClass().getResource("FXML/Admin_Dashboard.fxml"));

	primaryStage.setTitle("AuraMusica");
	primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("logo.jpg")));
	Scene scene = new Scene (root);
	scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	primaryStage.setScene(scene);
	primaryStage.show();
	}
	else
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject", "root", "Allah1069$");
		Statement stmt = con.createStatement();
		System.out.print("Checking Database\n");
		String sql = "select *  from registerinfo where username = '" + testuser + "' ";
		ResultSet rs = stmt.executeQuery(sql);
		boolean flag = rs.next();
	
	
if(flag)
{
	String retrieved_password = rs.getString("login_password");
	String usertype = rs.getString("user_type");
	System.out.print(usertype);
	if(testpass.equals(retrieved_password))
	{
		if(usertype.equals("Buyer"))
		{
			 Node source = (Node) event.getSource();
			 Stage oldStage = (Stage) source.getScene().getWindow();
			 oldStage.close();
			 
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("FXML/Buyer_MainScreen.fxml"));

		primaryStage.setTitle("AuraMusica");
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("logo.jpg")));
		Scene scene = new Scene (root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		}
		else if(usertype.equals("Artist"))
		{
			Node source = (Node) event.getSource();
			 Stage oldStage = (Stage) source.getScene().getWindow();
			 oldStage.close();
			 

			 Stage primaryStage = new Stage();
			 FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/Artist_MainScreen.fxml"));

	testuser = "Mahnoor75";
			 Artist_Controller controller = new Artist_Controller(testuser);
			 loader.setController(controller);

			 Parent root = loader.load();;

			 // Continue with your existing code to set up the stage
			 primaryStage.setTitle("AuraMusica");
			 primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("logo.jpg")));
			 Scene scene = new Scene(root);
			 scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			 primaryStage.setScene(scene);
			 primaryStage.show();

			
		}
	}
	else
	{
		 
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("FXML/ErrorScreen.fxml"));

		primaryStage.setTitle("AuraMusica");
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("logo.jpg")));
		Scene scene = new Scene (root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
else 
{

		 
Stage primaryStage = new Stage();
Parent root = FXMLLoader.load(getClass().getResource("FXML/ErrorScreen.fxml"));

primaryStage.setTitle("AuraMusica");
primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("logo.jpg")));
Scene scene = new Scene (root);
scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
primaryStage.setScene(scene);
primaryStage.show();
}
		
		
		
		}
		catch(Exception e)
		{
			System.out.print(e);
		}
	}
		
	}
	
	
	public void btnBuy2(ActionEvent event) throws IOException
	{
		BorderPane view = FXMLLoader.load(getClass().getResource("FXML/BuyerBuy.fxml"));
		pane2.setCenter(view);
	}
    
    public void Close (ActionEvent event)
    {
    	 Node source = (Node) event.getSource();
		 Stage oldStage = (Stage) source.getScene().getWindow();
		 oldStage.close();
    }
    
    public void InsertToDatabase(ActionEvent event) throws ClassNotFoundException, SQLException
    {
    	String full_name = namefield.getText().toString();
    	String email =emailfield.getText().toString();
    	String username =unamefield.getText().toString();
    	String pass =passfield.getText().toString();
    	String usertype = utypefield.getText().toString();
    	Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject", "root", "Allah1069$");
	Statement stmt2 = con2.createStatement();
	System.out.print("Inserting Records\n");
	String sql2 = "INSERT INTO registerinfo (full_name, email, username, login_password, user_type) VALUES ( '" + full_name + "', '" + email + "' , '" + username + "' , '" + pass + "' , '" + usertype + "')";
	stmt2.executeUpdate(sql2);
    }
    
    public void UpdateMusic() throws ClassNotFoundException, SQLException
	{
    	String title = namefield2.getText().toString();
    	String genre =emailfield2.getText().toString();
    	String duration =unamefield2.getText().toString();
    	String price = utypefield2.getText().toString();
      	String path = pathfield2.getText().toString();
      	String strNumber = "\0";
      	
      	
      	Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject", "root", "Allah1069$");
	Statement stmt = con.createStatement();
	System.out.print("Checking Database\n");
	String sql = "select id from music order by id desc limit 1";
	ResultSet rs = stmt.executeQuery(sql);
	if(rs.next())
	{
		String id = rs.getString("id");
		int number = Integer.parseInt(id)+1;
	 strNumber = String.valueOf(number);
	}
	
	strNumber = "img"+strNumber;
	strNumber = "MusicImages/"+strNumber+".jpg";
	System.out.print(strNumber);
	
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject", "root", "Allah1069$");
Statement stmt2 = con2.createStatement();
System.out.print("Inserting Records\n");
String sql2 = "INSERT INTO music (title, duration, genre, musicsrc, sold, price) VALUES ( '" + title + "', '" + duration + "' , '" + genre + "' , '" + strNumber + "' ,'false', '" + price + "')";
stmt2.executeUpdate(sql2);

Class.forName("com.mysql.cj.jdbc.Driver");
Connection con3 = DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject", "root", "Allah1069$");
Statement stmt3 = con3.createStatement();
System.out.print("Inserting Records\n");
String sql3 = "INSERT INTO audio (music_path) VALUES ( '" + path + "')";
stmt3.executeUpdate(sql3);
      	
	}
    
    public void DeleteMusic() throws SQLException, ClassNotFoundException
    {
    	String music_id = musicidfield.getText().toString();
    	Class.forName("com.mysql.cj.jdbc.Driver");
    	Connection con3 = DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject", "root", "Allah1069$");
    	Statement stmt3 = con3.createStatement();
    	System.out.print("Inserting Records\n");
    	String sql3 = "delete from music where id = '" + music_id + "'";
    	stmt3.executeUpdate(sql3);
    	String sql4 = "delete from audio where id = '" + music_id + "'";
    	stmt3.executeUpdate(sql4);
    	
    }
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		combo.setItems(list);
		
	}



}
    
//}
