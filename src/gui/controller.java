package gui;


import java.net.URL;
import java.util.ResourceBundle;

import gui.GameScreen;
import gui.HomeScreen;
import javafx.animation.AnimationTimer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import main.Main;


public class controller implements Initializable{
	
	@FXML
	private Pane screen;
	@FXML
	private Text Blitzkrieg;
	@FXML
	private ImageView Wall;
	@FXML
	private Button Campaign;
	@FXML
	private Button Skirmish;
	@FXML
	private Button exit;
	
	
	//right screen
	@FXML
	private Text time;
	
	
	
	public void initialize(URL location, ResourceBundle resources) {
		
	}
		
	
	public void GotoCampaign(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText("In maintenance");

		alert.showAndWait();
		System.out.println("goto Campaingn ");
		
			
	}
	public void GotoSkirmish(ActionEvent event) {
		System.out.println("goto Skirmish ");
		//main.instance.toggleScene(); // if use main in gui
		Main.instance.toggleScene();
		
	}
	public void GotoExit(ActionEvent event) {
		System.exit(0);
		System.out.println("goto Exit ");
		
	}
	public void Ckapip (MouseEvent event){
	
		Blitzkrieg.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event){
				Blitzkrieg.setFill(Color.WHITE);
				Blitzkrieg.setStyle("-fx-background-color: white");
			
		}		
		});
	}
	public void Ckapip2 (MouseEvent event){

	
		Blitzkrieg.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event){
				Blitzkrieg.setFill(Color.BLACK);
				Blitzkrieg.setStyle("-fx-background-color: black");
		}		
		});
	}
		
	// right pane part 		
	//Pane pane = new Pane();
	/*FXMLLoader loader = new FXMLLoader();
	loader.setLocation(main.class.getResource("right screen.fxml"));
	pane = loader.load();*/
	//ctrcenter.setTop(pane);
	
	//this.animationTimer.start();
	


	
	/*public void paintComponents() {
		// Fill in here
		String image_path = "file:res/field.jpg";
		Image javafx_logo = new Image(image_path);
		//gc.drawImage(javafx_logo, 60, 60);
		gc.setFill(Color.LIGHTGRAY);
		gc.fillRect(0, 0, zonewidth, zoneheight);
		gc.drawImage(javafx_logo,0,0,1080,720);
	*/
}
