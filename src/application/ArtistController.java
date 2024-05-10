package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ArtistController {
    @FXML
    private ImageView artistpic;

    @FXML
    private Label idartist;

    @FXML
    private Label nameartist;
    
    public ArtistController()
    {
    	
    	artistpic = new ImageView();
    	idartist =new Label();;
    	nameartist = new Label();
    }
    
    
    public void setArtistData(Artist artist)
    {
    	Image image = new Image(getClass().getResourceAsStream(artist.getPicsrc()));
    	artistpic.setImage(image);
    	nameartist.setText(artist.getName());
    	idartist.setText(artist.getId());
    	
    }
    

}
