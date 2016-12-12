package gui;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import logic.Unit;

public class GameScreen extends BorderPane {

	public static final int gscreenwidth = 1330;
	public static final int gscreenheight = 720;
	public static final int ctrwidth = 250;
	private GameZone gz = new GameZone();

	private Label turnButton = new Label("Start Game");

	public GameScreen() {
		this.setPrefSize(gscreenwidth, gscreenheight);

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
							}
						}
					} else {
						/*if (turnButton.getText().toString() == "P2 placing Unit") {
							turnButton.setText("End placing");
							gz.getGm().setturn(1);
							
						} else{*/
							/*if(turnButton.getText().toString() == "End placing"){
								turnButton.setText("P1 Turn End");
								gz.setState(0);
								for (IRenderable ir : RenderableHolder.getInstance().getEntities()) {
									if (ir instanceof Unit) {
										if (((Unit) ir).getPlayer() == 1)
											((Unit) ir).setmovable(true);
									}
								}
								gz.paintComponents();
							}
							else*/ buttonToggle();
							}
				}
			}
		});

		this.setCenter(gz);
		this.setRight(ctrcenter);
		ctrcenter.setCenter(info);
		ctrcenter.setBottom(act);

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
