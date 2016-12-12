package main;

import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import gui.main;
import gui.controller;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;

import gui.GameScreen;
import gui.HomeScreen;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;



public class Main extends Application{
	//private Stage primaryStage;
	public static Main instance;
	private Pane panes;
	private Stage primaryStage;
	private Scene HOME;
	private Scene Play; 
	private GameScreen gameScreen;
	//private Pane screen;
	AudioClip sound = new AudioClip("file:res/main-theme.mp3");
	
	
	
	
	public static void main(String[] args) {
		launch(args);
		// TODO Auto-generated method stub

	}
	
	public void start(Stage primaryStage) throws Exception {
		try {
			this.primaryStage = primaryStage;
			instance = this;
			this.primaryStage.setTitle("Proj Prog");
			//this.primaryStage.setResizable(false);
			this.primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				
				@Override
				public void handle(WindowEvent event) {
					System.exit(0);
				}
			});
			/*Parent root = FXMLLoader.load(getClass()
					.getResource("screen.fxml"));
			
			this.pane = new Pane();
			this.HOME =new Scene(pane);
			this.Play =new Scene(gameScreen);
			this.primaryStage.setScene(HOME); // Place the scene*/
			
			this.gameScreen = new GameScreen() ;
			this.Play =new Scene(gameScreen);
			ShowScreen();
			sound.play();
			
			
		} catch(Exception e) {
            e.printStackTrace();
        }
		
	}
	public void ShowScreen() throws Exception {
		/*Parent root = FXMLLoader.load(getClass()
				.getResource("screen.fxml"));*/
		FXMLLoader loaders = new FXMLLoader();
		loaders.setLocation(Main.class.getResource("screen.fxml"));
		panes = loaders.load();
		HOME =new Scene(panes);
		primaryStage.setScene(HOME);
		primaryStage.show();
		
	}
	public void toggleScene(){
		if(this.primaryStage.getScene()==this.HOME){
			sound.stop();
			
			this.primaryStage.setScene(this.Play);
		}
			
		else if(this.primaryStage.getScene()==this.Play){
			sound.play();
			this.primaryStage.setScene(this.HOME);
		}
	}

	
}