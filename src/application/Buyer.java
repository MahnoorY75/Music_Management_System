package application;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.sql.PreparedStatement;
import java.awt.Button;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import java.net.URL;
public class Buyer {
	
	@FXML
	Label name;
	@FXML
	BorderPane borderpane;
	
	@FXML
	 ComboBox<String> combo2;
    @FXML
    private TextField searchbar;

	@FXML
	ObservableList<String> list2 = FXCollections.observableArrayList("Pop" , "Funk", "Jazz", "Disco", "Folk", "All" );
	
	  @FXML
	    private GridPane artGrid;
	  private List<Artist> artist;
    @FXML
    private GridPane musicGrid;
	private List<Music> music;
  


public Buyer()
{
	borderpane = new BorderPane();
	combo2 = new ComboBox<>();
	artGrid = new GridPane();
}
private List<Artist> artistss() 
{
	
    try
    {
    	Class.forName("com.mysql.cj.jdbc.Driver");
    	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject", "root", "Allah1069$");
    Statement stmt = con.createStatement();
    String sql = "select *  from artist";
    ResultSet rs = stmt.executeQuery(sql);
    List<Artist> artist_list = new ArrayList<>();
    while (rs.next()) {
        Artist obj = new Artist();
       obj.setName(rs.getString("full_name"));
       obj.setPicsrc(rs.getString("picpath"));
       obj.setId(rs.getString("id"));
       artist_list.add(obj);
    }
	return artist_list;

    }
    
    catch(Exception e)
	{
    	System.out.print("Error Here");
		System.out.print(e);
		return null;
	}
	

	
}
	

	public void initialize() throws IOException {
		artist = new ArrayList(artistss());
		int col2 = 0;
		int row2 = 1;
		
		try
		{
			for(int i =0 ; i<artist.size(); i++)
			{
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("FXML/ArtistLayout.fxml"));
				VBox box = loader.load();
				ArtistController control = new ArtistController(); // Initialize the MusicController
				control = loader.getController();
				control.setArtistData(artist.get(i));
				
				if(col2 == 1)
				{
					col2=0;
					++row2;
				}
				
				artGrid.add(box, col2++, row2);
				GridPane.setMargin(box, new Insets(10));
			}
		
		}
	    catch(Exception e)
		{
			System.out.print(e);
			
		}
	

		
		
		
		//--------------------------------------------------------------------------------------------
		music = new ArrayList(music());
		int col = 0;
		int row = 1;
		
		try
		{
			for(int i =0 ; i<music.size(); i++)
			{
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("FXML/MusicLayout.fxml"));
				VBox box = loader.load();
				MusicController control = loader.getController();
				control.setMusicData(music.get(i));
				
				if(col == 3)
				{
					col=0;
					++row;
				}
				
				musicGrid.add(box, col++, row);
				GridPane.setMargin(box, new Insets(10));
			}
		
		}
	    catch(Exception e)
		{
			System.out.print("Unexpected Error Occured");
			
		}
	

		combo2.setItems(list2);
		BorderPane view = FXMLLoader.load(getClass().getResource("FXML/BuyerHome.fxml"));
		borderpane.setCenter(view);
	}
	

	
	private List<Music> music() 
	{
		
	    try
	    {
	    	Class.forName("com.mysql.cj.jdbc.Driver");
	    	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject", "root", "Allah1069$");
	    Statement stmt = con.createStatement();
	    System.out.print("Checking Database\n");
	    String sql = "select *  from music";
	    ResultSet rs = stmt.executeQuery(sql);
	    List<Music> music_list = new ArrayList<>();
	    while (rs.next()) {
	        Music obj = new Music();
	       obj.setId(rs.getInt("id"));
	       obj.setTitle(rs.getString("title"));
	       obj.setDuration(rs.getString("duration"));
	       obj.setGenre(rs.getString("genre"));
	       obj.setMusicsrc(rs.getString("musicsrc"));
	       music_list.add(obj);
	    }
		return music_list;

	    }
	    
	    catch(Exception e)
		{
	    	System.out.print("Error Here");
			System.out.print(e);
			return null;
		}
		
	
		
	}

	public void btnHome(ActionEvent event) throws IOException
	{

		BorderPane view = FXMLLoader.load(getClass().getResource("FXML/BuyerHome.fxml"));
		borderpane.setCenter(view);
		
	}
	
	public void btnBuy(ActionEvent event) throws IOException
	{
		BorderPane view = FXMLLoader.load(getClass().getResource("FXML/BuyerBuy.fxml"));
		borderpane.setCenter(view);
	}
	
	

	
	public void btnShowArtists(ActionEvent event) throws IOException
	{
		BorderPane view = FXMLLoader.load(getClass().getResource("FXML/ExploreArtists.fxml"));
		borderpane.setCenter(view);
	}
	
	public void SetSearchBar(ActionEvent event)
	{
		String genre = combo2.getSelectionModel().getSelectedItem().toString();
		searchbar.setText(genre);
	}
	private void updateGridPane(List<Music> musicList) {
	    musicGrid.getChildren().clear(); // Clear the existing content
	    int col = 0;
	    int row = 1;

	    try {
	        for (int i = 0; i < musicList.size(); i++) {
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(getClass().getResource("FXML/MusicLayout.fxml"));
	            VBox box = loader.load();
	            MusicController control = loader.getController();
	            control.setMusicData(musicList.get(i));

	            if (col == 3) {
	                col = 0;
	                ++row;
	            }

	            musicGrid.add(box, col++, row);
	            GridPane.setMargin(box, new Insets(10));
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	public void SearchMusic(ActionEvent event) {
	    String genre = combo2.getSelectionModel().getSelectedItem();
	    
	    if (genre != null && !genre.isEmpty()) {
	    	
	    	if(genre.equals("All"))
	    	{
	    	     List<Music> musicList = new ArrayList<>();

	 	        try {
	 	            Class.forName("com.mysql.cj.jdbc.Driver");
	 	            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject", "root", "Allah1069$");
	 	            Statement stmt = con.createStatement();
	 	            String sql = "SELECT * FROM music";
	 	            ResultSet rs = stmt.executeQuery(sql);

	 	            while (rs.next()) {
	 	                Music obj = new Music();
	 	                obj.setId(rs.getInt("id"));
	 	                obj.setTitle(rs.getString("title"));
	 	                obj.setDuration(rs.getString("duration"));
	 	                obj.setGenre(rs.getString("genre"));
	 	                obj.setMusicsrc(rs.getString("musicsrc"));
	 	                musicList.add(obj);
	 	            }
	 	            updateGridPane(musicList); // Update the GridPane with search results
	 	        } catch (Exception e) {
	 	            System.out.print("Error: " + e.getMessage());
	 	        }
	    	}
	    	
	    	else
	    	{
		        List<Music> musicList = new ArrayList<>();

		        try {
		            Class.forName("com.mysql.cj.jdbc.Driver");
		            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject", "root", "Allah1069$");
		            Statement stmt = con.createStatement();
		            String sql = "SELECT * FROM music WHERE genre = '" + genre + "'";
		            ResultSet rs = stmt.executeQuery(sql);

		            while (rs.next()) {
		                Music obj = new Music();
		                obj.setId(rs.getInt("id"));
		                obj.setTitle(rs.getString("title"));
		                obj.setDuration(rs.getString("duration"));
		                obj.setGenre(rs.getString("genre"));
		                obj.setMusicsrc(rs.getString("musicsrc"));
		                musicList.add(obj);
		            }
		            updateGridPane(musicList); // Update the GridPane with search results
		        } catch (Exception e) {
		            System.out.print("Error: " + e.getMessage());
		        }
	    	}

	    }
	}


	

}
