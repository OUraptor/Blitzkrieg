package gui;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import logic.Unit;
import main.Main;

public class GameScreen extends BorderPane {
	
	
	
	public static final int gscreenwidth = 1330;
	public static final int gscreenheight = 720;
	public static final int ctrwidth = 250;
	private GameZone gz = new GameZone();
	
	
	//private String timeStr = "";
	
	private Label time = new Label();

	private Label turnButton = new Label("Start Game");

	public GameScreen() throws IOException {
		this.setPrefSize(gscreenwidth, gscreenheight);
		FlowPane Ctr = new FlowPane();
		
		BorderPane ctrcenter = new BorderPane();
		FlowPane info = new FlowPane();
		FlowPane act = new FlowPane();
		
		
		ctrcenter.setPrefWidth(250);
		ctrcenter.setStyle("-fx-background-color: black");
		
		gz.setInfo();

		gz.getDesc().setFont(Font.font("Tahoma", FontWeight.BOLD, FontPosture.ITALIC, 20));
		gz.getDesc().setTextFill(Color.LIGHTBLUE);

		info.getChildren().add(gz.getDesc());

		act.setPrefHeight(100);
		act.setStyle("-fx-background-color: darkgreen");

		// Label turnButton = new Label("Start Game");
		turnButton.setFont(Font.font("Tahoma", FontWeight.BOLD, FontPosture.ITALIC, 40));
		turnButton.setTextFill(Color.WHITE);

		act.getChildren().add(turnButton);
		act.setAlignment(Pos.CENTER);

		act.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				act.setStyle("-fx-background-color: red");
			}
		});
		act.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				act.setStyle("-fx-background-color: darkgreen");
			}
		});

		act.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {

				if (turnButton.getText().toString() == "Start Game") {
					turnButton.setText("End placing");
					gz.setState(9);
					gz.paintComponents();
				} else {

					if (turnButton.getText().toString() == "End placing") {
						if(gz.getGm().getturn()==1){
							gz.getGm().setturn(2);
							gz.setS(3);
							gz.setA(1);
							gz.setT(2);
							gz.setAp(2);
							gz.setSp("");
						}
						else {
							if(gz.getGm().getturn()==2){
								turnButton.setText("P1 Turn End");
								
								
								gz.getGm().setturn(1);
								gz.setState(0);
								for (IRenderable ir : RenderableHolder.getInstance().getEntities()) {
									if (ir instanceof Unit) {
										if (((Unit) ir).getPlayer() == 1)
											((Unit) ir).setmovable(true);
									}
								}
								gz.paintComponents();
								gz.countdown();
							}
						}
					} else {
						
						if(turnButton.getText().equals("P1 Turn End")){
							
							try {
								gz.stop();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								System.out.println("Stop Timer Thread");
							}
							
							turnButton.setText("P2 Go!");
						}
						else{
							if(turnButton.getText().equals("P2 Turn End")){
								try {
									gz.stop();
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
									System.out.println("Stop Timer Thread");
								}
								turnButton.setText("P1 Go!");
							}
							else{
								buttonToggle();
								gz.setTimeint(15);
								gz.countdown();
							}
						}
						
						
						
							}
				}
			}
		});
		/*Thread timer = new Thread(() -> {
			try{
				while(true){
					Thread.sleep(1000);
					timerdown(timeint);
					System.out.println(timeint);
				}
			}catch(InterruptedException e){
				
			}
		});
		timer.start();*/
		/*this.timeint = 15;
		
		Thread timer = new Thread(() -> {
			while(timeint>=0){
				try {
					Thread.sleep(1000);
					timeint--;
					//drawCurrentTimeString(gc);
					System.out.println(timeint);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Stop Timer Thread");
					//break;
				}
			}
		});
		timer.start();*/
		
		
		System.out.println("");
		
		//if(gz.getState()==10 || gz.getState()==9 ||gz.getState()==8)gz.getTime().setText("");
		//else timeStr = ""+timeint;
		//gz.getTime().setText(gz.getTimestr());
		gz.getTime().setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 40));
		gz.getTime().setTextFill(Color.WHITE);
		Ctr.setAlignment(Pos.CENTER);
		Ctr.getChildren().add(gz.getTime());
		
		this.setCenter(gz);
		this.setRight(ctrcenter);
		ctrcenter.setTop(Ctr);
		ctrcenter.setCenter(info);
		ctrcenter.setBottom(act);
		
		
	}
	/*public void timerdown(int timeint) {
		// TODO Auto-generated method stub
		
		timeint--;
		
	}*/

	public void buttonToggle() {
		
		if (gz.getGm().getturn() == 1) {
			gz.getGm().setturn(2);
			for (IRenderable ir : RenderableHolder.getInstance().getEntities()) {
				if (ir instanceof Unit) {
					if (((Unit) ir).getPlayer() == 2)
						((Unit) ir).setmovable(true);
					else
						((Unit) ir).setmovable(false);
				}
			}
			gz.getGm().resetOverlay();
			gz.setState(0);
			turnButton.setText("P2 Turn End");
			
			gz.paintComponents();
		}

		else {

			if (gz.getGm().getturn() == 2) {
				gz.getGm().setturn(1);
				for (IRenderable ir : RenderableHolder.getInstance().getEntities()) {
					if (ir instanceof Unit) {
						if (((Unit) ir).getPlayer() == 1)
							((Unit) ir).setmovable(true);
						else
							((Unit) ir).setmovable(false);
					}
				}
				gz.getGm().resetOverlay();
				gz.setState(0);
				turnButton.setText("P1 Turn End");
				
				gz.paintComponents();

			}
		}
	}

}
