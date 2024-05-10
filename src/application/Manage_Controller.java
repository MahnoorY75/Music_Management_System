package application;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Manage_Controller implements Initializable {
    @FXML
    private TableView<User> table;
    @FXML
    private TableColumn<User, String> emailcol;

    @FXML
    private TableColumn<User, Void>  managecol;

    @FXML
    private TableColumn<User, String>  namecol;

    @FXML
    private TableColumn<User, String>  passcol;

    @FXML
    private TableColumn<User, String>  typecol;

    @FXML
    private TableColumn<User, String>  unamecol;
    @FXML
    private TableColumn<User, String>  idcol;
    
    ObservableList<User> UserList = FXCollections.observableArrayList();

    
    public void getAddview() throws IOException
    {
    	Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("FXML/UserAdd.fxml"));

		primaryStage.setTitle("AuraMusica");
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("logo.jpg")));
		  primaryStage.initStyle(StageStyle.UNDECORATED);
		Scene scene = new Scene (root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
    }
    
    public void refreshTable ()
    {
    	try {
    		UserList.clear();

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject", "root", "Allah1069$");
		Statement stmt = con.createStatement();
		String sql = "select *  from registerinfo";
		ResultSet rs = stmt.executeQuery(sql);
			
		while(rs.next())
		{
			UserList.add(new User(rs.getString("id"),  rs.getString("full_name"), rs.getString("email"), rs.getString("username"), rs.getString("login_password"), rs.getString("user_type")));
		
		table.setItems(UserList);
		}
		} catch (Exception e) {
			// TODO: handle exception
		}
    }

    public void handleEditAction(User user) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/UserEdit.fxml"));
        Parent root = loader.load();

        // Get the controller for UserEdit.fxml
        Contoller editController = loader.getController();

        // Set the User object in the UserEditController
        editController.setEditData(user);

        // Continue with showing the stage
        Stage primaryStage = new Stage();
        primaryStage.setTitle("AuraMusica");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("logo.jpg")));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void handleDeleteAction(User user) {
        System.out.print(user.getId());
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject", "root", "Allah1069$");
            Statement stmt = con.createStatement();
            String sql = "DELETE FROM registerinfo WHERE id = " + user.getId();
            int rowsDeleted = stmt.executeUpdate(sql);

            if (rowsDeleted > 0) {
                System.out.print("User Deleted");
                refreshTable(); // Refresh the table after deletion
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }
    }



    public void loadData()
    {
    	refreshTable();
    	idcol.setCellValueFactory(new PropertyValueFactory<>("id"));;
    	namecol.setCellValueFactory(new PropertyValueFactory<>("full_name"));
    	emailcol.setCellValueFactory(new PropertyValueFactory<>("email"));
    	unamecol.setCellValueFactory(new PropertyValueFactory<>("username"));
    	passcol.setCellValueFactory(new PropertyValueFactory<>("password"));
    	typecol.setCellValueFactory(new PropertyValueFactory<>("usertype"));
    	managecol.setCellFactory(new Callback<TableColumn<User, Void>, TableCell<User, Void>>() {
            @Override
            public TableCell<User, Void> call(TableColumn<User, Void> param) {
                return new TableCell<User, Void>() {
                    private final HBox hbox = new HBox(); // Horizontal box for buttons
                    private final Button editButton = new Button("Edit");
                    private final Button deleteButton = new Button("Delete");

                    {
                        // Add button actions here
                        editButton.setOnAction(event -> {
                            User user = getTableView().getItems().get(getIndex());
                            try {
								handleEditAction(user);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
                        });
                        // Handle delete action when delete button is clicked
                        deleteButton.setOnAction(event -> {
                            User user = getTableView().getItems().get(getIndex());
                            handleDeleteAction(user);
                        });

                        editButton.getStyleClass().add("edit-button");
                        deleteButton.getStyleClass().add("delete-button");
                        // Add buttons to the HBox
                        hbox.getChildren().addAll(editButton, deleteButton);
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty) {
                            setGraphic(null); // No buttons in empty cells
                        } else {
                            HBox.setMargin(editButton, new Insets(0, 10, 0, 23)); // Right margin of 10
                            HBox.setMargin(deleteButton, new Insets(0, 0, 0, 10)); // Left margin of 10

                            setGraphic(hbox); // Set HBox with buttons in the cell
                        }
                    }
                };
            }
        });

    }
    


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		loadData();
		// TODO Auto-generated method stub
		
	}
	
	
}
