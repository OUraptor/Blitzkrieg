package gui;

import javafx.stage.Stage;
import main.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import gui.GameScreen;
import gui.HomeScreen;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;



public class main extends Application{
	//private Stage primaryStage;
	public static main instance;
	
	private Stage primaryStage;
	private Scene HOME;
	private Scene Play; 
	private HomeScreen home;
	//private Pane screen;
	
	
	
	

	public static void main(String[] args) {
		launch(args);
		// TODO Auto-generated method stub

	}
	
	public void start(Stage primaryStage) throws Exception {
		try {
			instance = this;
			
			Parent root = FXMLLoader.load(getClass()
					.getResource("screen.fxml"));
			
			this.home = new HomeScreen() ;
			this.HOME =new Scene(root);
			this.Play =new Scene(home);
			primaryStage.setScene(HOME); // Place the scene
			primaryStage.show();
		} catch(Exception e) {
            e.printStackTrace();
        }
		
	}
	public void toggleScene(){
		if(this.primaryStage.getScene()==this.HOME){
			System.out.println(5555);
			this.primaryStage.setScene(this.Play);
		}
			
		else if(this.primaryStage.getScene()==this.Play)this.primaryStage.setScene(this.HOME);
	}

	
}
