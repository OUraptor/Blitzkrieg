package main;

import gui.GameScreen;
import gui.HomeScreen;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application{
	
	public static Main instance;
	
	private Stage primaryStage;
	private Scene homeScene;
	private Scene gameScene;
	private GameScreen gameScreen;
	private HomeScreen homeScreen;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage primaryStage) throws Exception {
		instance = this;
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Proj Prog");
		this.primaryStage.setResizable(false);
		this.primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			
			@Override
			public void handle(WindowEvent event) {
				System.exit(0);
			}
		});
		
		this.homeScreen = new HomeScreen() ;
		this.homeScene = new Scene(homeScreen) ;
		this.gameScreen = new GameScreen() ;
		this.gameScene = new Scene(gameScreen) ;
		
		this.primaryStage.setScene(this.homeScene);
		this.primaryStage.show();
	}
	
	
	public void toggleScene(){
		if(this.primaryStage.getScene()==this.homeScene)this.primaryStage.setScene(this.gameScene);
		else if(this.primaryStage.getScene()==this.gameScene)this.primaryStage.setScene(this.homeScene);
	}
	
	
}
