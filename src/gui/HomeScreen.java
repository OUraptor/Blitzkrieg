// Witsarut Boonmasuvaran 5831066721
// Possatorn Buakhom 5831043221
// Blitzkrieg master
package gui;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import main.*;

public class HomeScreen extends BorderPane {

	public static final int hscreenwidth = 600;
	public static final int hscreenheight = 460;

	private Button startGame = new Button("Start Game");

	public HomeScreen() {
		this.setPrefSize(hscreenwidth, hscreenheight);
		this.setStyle("-fx-background-color: lightblue");
		this.setCenter(startGame);
		// getChildren().add(startGame) ;
		// this.getChildren().add(startGame) ;

		startGame.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				Main.instance.toggleScene();
			}
		});
	}

}
