package main;

import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import gui.GameScreen;
import gui.HomeScreen;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
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
	//private HomeScreen homeScreen;
	AudioClip sound = new AudioClip("file:res/main-theme2.mp3");
	
	
	
	
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
			
			/*this.gameScreen = new GameScreen() ;
			this.Play =new Scene(gameScreen);
			this.homeScreen = new HomeScreen() ;
			this.HOME = new Scene(homeScreen) ;
			/*this.gameScreen = new GameScreen() ;
			this.gameScene = new Scene(gameScreen) ;*/
			
			this.primaryStage.setScene(this.HOME);
			this.primaryStage.show();
			playsound(sound);
			ShowScreen();
			
			
		} catch(Exception e) {
            e.printStackTrace();
        }
		
	}
	public void ShowScreen() throws Exception {
		
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
			playsound(sound);
			this.gameScreen = new GameScreen() ;
			this.Play = new Scene(gameScreen) ;
			this.primaryStage.setScene(this.Play);
		}
			
		else if(this.primaryStage.getScene()==this.Play){
			sound.play();
			this.primaryStage.setScene(this.HOME);
		}
	}
	public void playsound(AudioClip sound){
		//this.sound = sound;
		//sound.setCycleCount(AudioClip.INDEFINITE);
		sound.play();  
	}
	
}