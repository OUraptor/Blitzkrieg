package gui;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import gui.GameScreen;
import gui.HomeScreen;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


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
		main.instance.toggleScene();
	}
	public void GotoExit(ActionEvent event) {
		
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
		
			
	
	

}