package application;

import javafx.event.ActionEvent;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Admin {
	
	

    @FXML
    private BorderPane adminborder;
	
	public void ManageUsers() throws IOException
	{
		BorderPane view = FXMLLoader.load(getClass().getResource("FXML/Manage_Users.fxml"));
		adminborder.setCenter(view);
	}
	
	public void AddMusic() throws IOException
	{
		BorderPane view = FXMLLoader.load(getClass().getResource("FXML/AddMusic.fxml"));
		adminborder.setCenter(view);
	}
	public void PlusMusic() throws IOException
	{
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("FXML/Music_Plus.fxml"));

		primaryStage.setTitle("AuraMusica");
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("logo.jpg")));
		  primaryStage.initStyle(StageStyle.UNDECORATED);
		Scene scene = new Scene (root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void DeleteMusic() throws IOException
	{
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("FXML/MusicDelete.fxml"));

		primaryStage.setTitle("AuraMusica");
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("logo.jpg")));
		  primaryStage.initStyle(StageStyle.UNDECORATED);
		Scene scene = new Scene (root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void OpenLoginWindow(ActionEvent event) throws IOException 
	{
		
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
