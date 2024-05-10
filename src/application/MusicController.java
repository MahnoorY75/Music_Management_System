package application;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MusicController implements Initializable {
	
	private MediaPlayer player;
    @FXML
    private Button play;

	 @FXML
	    private Label musicgenre;

	    @FXML
	    private Label musicid;

	    @FXML
	    private ImageView musicimg;

	    @FXML
	    private Label musictitle;
	    @FXML
	    private ImageView note;
	    
	    @FXML
	    private ImageView artistpic;

	    @FXML
	    private Label idartist;

	    @FXML
	    private Label nameartist;
	    
	    public MusicController()
	    {
	    	musicgenre = new Label();
	    	musicid =  new Label();
	    	musictitle =  new Label();
	    	musicimg = new ImageView();
	    	note =  new ImageView();
	    	artistpic = new ImageView();
	    	idartist =new Label();;
	    	nameartist = new Label();
	    }
	    
		
	    public void setMusicData (Music music)
	    {
	    	Image image = new Image(getClass().getResourceAsStream(music.getMusicsrc()));
	    	musicimg.setImage(image);
	    	musictitle.setText(music.getTitle());
	    	int id = music.getId();
	    	String str = Integer.toString(id);
	    	musicid.setText(str);
	    	musicgenre.setText(music.getGenre());
	    }
	    
	
	 public void MusicBuy() throws IOException
	 {
		  FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/Payment.fxml"));
	        Parent root = loader.load();

	        Payment paymentController = loader.getController();
	        String title = musictitle.getText();
	        String id = musicid.getText().toString();
	        String genre = musicgenre.getText().toString();
	        paymentController.setPaymentData(title , id, genre);

	        Stage primaryStage = new Stage();
	        primaryStage.setTitle("AuraMusica");
	        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("logo.jpg")));
	        Scene scene = new Scene(root);
	        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	        primaryStage.setScene(scene);
	        primaryStage.show();
	 }

	    public void PlayMusic() {
	        if (play.getText().equals("Play")) {
	            try {
	                Class.forName("com.mysql.cj.jdbc.Driver");
	                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject", "root", "Allah1069$");
	                String sql = "SELECT * FROM audio WHERE id = ?";
	                PreparedStatement pstmt = con.prepareStatement(sql);
	                pstmt.setInt(1, Integer.parseInt(musicid.getText())); // Bind the musicid as a parameter

	                ResultSet rs = pstmt.executeQuery();

	                if (rs.next()) {
	                    String path = rs.getString("music_path");
	                    Media media = new Media(new File(path).toURI().toString());
	                    player = new MediaPlayer(media); // Initialize the instance variable
	                    player.play();
	                    note.setVisible(true);
	                    play.setText("Stop");
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        } else {
	            if (player != null) {
	                player.stop(); // Stop the player if it's not null
	            }
	            note.setVisible(false);
	            play.setText("Play");
	        }
	    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		TranslateTransition translate = new TranslateTransition();
		translate.setNode(note);
		translate.setDuration(Duration.millis(1000));
		translate.setCycleCount(TranslateTransition.INDEFINITE);
		translate.setByX(103);
		translate.setAutoReverse(true);
		translate.play();
	}
	

}
