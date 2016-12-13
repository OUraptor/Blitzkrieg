package gui;

import javafx.event.EventHandler;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import logic.Unit;
import logic.GameManager;

public class GameScreen extends BorderPane {

	public static final int gscreenwidth = 1330;
	public static final int gscreenheight = 720;
	public static final int ctrwidth = 250;
	private GameZone gz = new GameZone();
	
	private Label turnButton = new Label("Start Game");
	private Button shopbutton = new Button("Shop") ;

	public GameScreen() {
		this.setPrefSize(gscreenwidth, gscreenheight);
		/*FlowPane Money = new FlowPane();
		Label getp1M = new Label();
		Label getp2M = new Label();
		getp1M.setFont(Font.font("Tahoma", FontWeight.BOLD, FontPosture.ITALIC, 20));
		getp2M.setFont(Font.font("Tahoma", FontWeight.BOLD, FontPosture.ITALIC, 20));
		getp1M.setTextFill(Color.LIGHTBLUE);
		getp2M.setTextFill(Color.LIGHTBLUE);
		getp1M.setText("Player1 money:"+gz.getGm().getP1Money());
		getp2M.setText("Player2 money:"+gz.getGm().getP2Money());
		Money.getChildren().add(getp1M);
		Money.getChildren().add(getp2M);*/
		
		FlowPane Ctr = new FlowPane();
		BorderPane ctrcenter = new BorderPane();
		FlowPane act = new FlowPane();
		GridPane gp = new GridPane();
		
		ctrcenter.setPrefWidth(250);
		ctrcenter.setStyle("-fx-background-color: black");

		gz.setInfo();

		gz.getDesc().setFont(Font.font("Tahoma", FontWeight.BOLD, FontPosture.ITALIC, 20));
		gz.getDesc().setTextFill(Color.LIGHTBLUE);
		
		//info.getChildren().add(shopbutton);
		//info.setAlignment(Pos.CENTER);
		//info.setPadding(new Insets(30, 10, 10, 15));
		//info.getChildren().add(gz.getDesc());
		
		gp.add(shopbutton, 0, 0);
		gp.add(gz.getDesc(), 0, 1);
		gp.setAlignment(Pos.CENTER);
		gp.setVgap(50);

		act.setPrefHeight(100);
		act.setStyle("-fx-background-color: darkgreen");

		// Label turnButton = new Label("Start Game");
		turnButton.setFont(Font.font("Tahoma", FontWeight.BOLD, FontPosture.ITALIC, 40));
		turnButton.setTextFill(Color.WHITE);
		
		shopbutton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(gz.getState()==0){
					if(gz.getGm().getturn()==1){
						gz.setS(gz.getGm().getP1Money()/50);
						gz.setA(gz.getGm().getP1Money()/50);
						gz.setT(gz.getGm().getP1Money()/50);
						gz.setAp(gz.getGm().getP1Money()/50);
					}
					if(gz.getGm().getturn()==2){
						gz.setS(gz.getGm().getP2Money()/50);
						gz.setA(gz.getGm().getP2Money()/50);
						gz.setT(gz.getGm().getP2Money()/50);
						gz.setAp(gz.getGm().getP2Money()/50);	
					}
					gz.setState(7);
				}
				else {
					if(gz.getState()==7||gz.getState()==6){
						gz.setState(0);
						gz.paintComponents();
					}
				}
			}
		});

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

					/*
					 * for (IRenderable ir :
					 * RenderableHolder.getInstance().getEntities()) { if (ir
					 * instanceof Unit) { if (((Unit) ir).getPlayer() == 1)
					 * ((Unit) ir).setmovable(true); } }
					 */
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
								
								turnButton.setText("P1 Go!");
								gz.setState(0);
								gz.paintComponents();
								gz.setState(-1);
							}
						}
					} else {
							if(turnButton.getText().equals("P1 Turn End")){
							
							try {
								gz.getGm().setP1Money(gz.getGm().getP1Money()+gz.getTimeint()*10);
								gz.stop();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								System.out.println("Stop Timer Thread");
							}
							
							turnButton.setText("P2 Go!");
							gz.setState(-1);
						}
						else{
							if(turnButton.getText().equals("P2 Turn End")){
								try {
									gz.getGm().setP2Money(gz.getGm().getP2Money()+gz.getTimeint()*10);
									gz.stop();
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
									System.out.println("Stop Timer Thread");
								}
								turnButton.setText("P1 Go!");
								gz.setState(-1);
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
		if(gz.getState()==10||gz.getState()==9||gz.getState()==8)gz.getTime().setText("");
		gz.getTime().setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 40));
		gz.getTime().setTextFill(Color.WHITE);
		Ctr.setAlignment(Pos.CENTER);
		Ctr.getChildren().add(gz.getTime());
		this.setCenter(gz);
		this.setRight(ctrcenter);
		
		ctrcenter.setCenter(gp);
		ctrcenter.setBottom(act);
		ctrcenter.setTop(Ctr);

	}

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
