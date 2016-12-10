package gui;

import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import main.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import gui.GameScreen;
import gui.HomeScreen;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;



public class main extends Application{
	//private Stage primaryStage;
	public static main instance;
	private Pane pane;
	private Stage primaryStage;
	private Scene HOME;
	private Scene Play; 
	private GameScreen gameScreen;
	//private Pane screen;
	
	
	
	

	public static void main(String[] args) {
		launch(args);
		// TODO Auto-generated method stub

	}
	
	public void start(Stage primaryStage) throws Exception {
		try {
			this.primaryStage = primaryStage;
			instance = this;
			this.primaryStage.setTitle("Proj Prog");
			this.primaryStage.setResizable(false);
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
			Showscreen();
		} catch(Exception e) {
            e.printStackTrace();
        }
		
	}
	public void Showscreen() throws Exception {
		/*Parent root = FXMLLoader.load(getClass()
				.getResource("screen.fxml"));*/
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(main.class.getResource("screen.fxml"));
		pane = loader.load();
		HOME =new Scene(pane);
		primaryStage.setScene(HOME);
		primaryStage.show();
	}
	public void toggleScene(){
		if(this.primaryStage.getScene()==this.HOME){
			
			this.primaryStage.setScene(this.Play);
		}
			
		else if(this.primaryStage.getScene()==this.Play){
			this.primaryStage.setScene(this.HOME);
		}
	}

	
}
