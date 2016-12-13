package gui;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logic.APC;
import logic.Artillery;
import logic.GameManager;
import logic.Myexception;
import logic.Soldier;
import logic.Tank;
import logic.Unit;

import main.Main;

public class GameZone extends Canvas {

	private static final int zonewidth = GameScreen.gscreenwidth - GameScreen.ctrwidth;
	private static final int zoneheight = GameScreen.gscreenheight;

	private GraphicsContext gc = this.getGraphicsContext2D();

	private Label desc = new Label();

	private String timestr = "15";
	private Label time = new Label(timestr);
	private Thread timer;
	private int timeint = 15;

	private int xtemp = -1;
	private int ytemp = -1;

	private int state = 10;
	private String sp = "";

	private int s = 3;
	private int a = 1;
	private int t = 2;
	private int ap = 2;

	//private String image_path3 = "file:res/build.jpg";
	//protected Image build = new Image(image_path3); 
	protected Image build = new Image(ClassLoader.getSystemResource("build.jpg").toString());
	
	//private String image_path2 = "file:res/loading.jpg";
	protected Image loading = new Image(ClassLoader.getSystemResource("loading.jpg").toString());

	private Unit unit;

	//private String image_path = "file:res/field.jpg";
	protected Image javafx_logo = new Image(ClassLoader.getSystemResource("field.jpg").toString());

	private GameManager gm;

	public GameZone() {
		super(zonewidth, zoneheight);

		this.gm = new GameManager();
		paintComponents();
		new AnimationTimer() {
			Long start = 0l;

			@Override
			public void handle(long now) {
				// TODO Auto-generated method stub
				if (start == 0l)
					start = now;
				long diff = now - start;
				if (diff >= 100000000l) { // 100000000l = 100ms.
					// Fill in here
					try {
						if ((gm.getP1unit() != 0 && gm.getP2unit() != 0) || (state == 9 || state == 10 || state == 8)) {
							setInfo();
							gogo(gm);
							gm.removeDestroyEntity();
						} else
							throw new Myexception(gm.getP1unit(), gm.getP2unit());
						// System.out.println("p1 = " + gm.getP1unit() + " p2 =
						// " + gm.getP2unit());
					} catch (Myexception e) {
						stop();
						//state = 10 ;
						if(gm.getP1unit() == 0 && gm.getP2unit() == 0){
							Platform.runLater(new Runnable() {
								public void run() {
									Alert alert = new Alert(AlertType.INFORMATION);
									alert.setTitle("Draw!!!");
									alert.setHeaderText(null);
									alert.setContentText("Draw!!!");
									alert.showAndWait();
								}
							});
						}
						else {if (gm.getP1unit() == 0) {
							// System.out.println("rip p1");

							Platform.runLater(new Runnable() {
								public void run() {
									Alert alert = new Alert(AlertType.INFORMATION);
									alert.setTitle("P2 Win!!!");
									alert.setHeaderText(null);
									alert.setContentText("Player 2 Win!!!");
									alert.showAndWait();
								}
							});
						}else if(gm.getP2unit()==0)

	{
							// System.out.println("rip p1");

							Platform.runLater(new Runnable() {
								public void run() {
									Alert alert = new Alert(AlertType.INFORMATION);
									alert.setTitle("P1 Win!!!");
									alert.setHeaderText(null);
									alert.setContentText("Player 1 Win!!!");
									alert.showAndWait();
								}
							});
						}}for(
	int i = RenderableHolder.getInstance().getEntities().size() - 1;i>=0;i--)
	{
		IRenderable ir = RenderableHolder.getInstance().getEntities().get(i);
		if (ir instanceof Unit)
			((Unit) ir).setDestroy(true);
	}

	getGm().removeDestroyEntity();
						Main.instance.toggleScene();
					}
	// gm.checkwin();

	start=now;

	}}}.start();

	}

	public void paintComponents() {
		// Fill in here
		
		
		gc.drawImage(javafx_logo,0,0,1080,720);
		//gc.setFill(Color.LIGHTGRAY);
		//gc.fillRect(0, 0, zonewidth, zoneheight);

		if (state == 8 || state == 9) {
			gc.setLineWidth(2.0);
			for (int i = 0; i <= zonewidth; i += 60) {
				for (int j = 0; j <= zoneheight; j += 60) {
					gc.setStroke(Color.WHITE);
					gc.strokeRoundRect(i, j, 60, 60, 0, 0);
				}
			}
		}

		for (IRenderable ir : RenderableHolder.getInstance().getEntities()) {
			if (!ir.isDestroy()) {
				ir.draw(gc);
			}
		}
		for (IRenderable ir : RenderableHolder.getInstance().getOverlays()) {
			if (!ir.isDestroy()) {
				ir.draw(gc);
			}
		}
	}

	public void gogo(GameManager gm) {
		
		 //System.out.println("state : " + state);// System.out.println("sp : " +
		 //sp); System.out.println("turn : " + getGm().getturn());
		//System.out.println("s "+s+" s "+t+" a "+a+" ap "+ap);
		System.out.println("p1money : "+gm.getP1Money()/2);
		System.out.println("p2money : "+gm.getP2Money()/2);
		if (state == 10) {
			
			gc.drawImage(loading,0,0,1080,720);
		} else {
			if (state == 9) {
				paintComponents();
				prePlacingUnit();
				selectPlacing(s, t, a, ap);
			} else {
				if (state == 8) {
					paintComponents();
					prePlacingUnit();
					place();
					
				} else {
					if (state == 7) {
						
						paintComponents();
						prePlacingUnit();
						selectPlacing(s, t, a, ap);
					}
					else {
						if (state == 6) {
						paintComponents();
						prePlacingUnit();
						place();
						}
						else{
							this.setOnMouseClicked(new EventHandler<MouseEvent>() {
								public void handle(MouseEvent event) {
									for (IRenderable ir : RenderableHolder.getInstance().getEntities()) {
										if (ir instanceof Unit) {
											Unit u = (Unit) ir;
											if (state == 0 && u.getmovable() == true)
												movegrid(gm, u, event);
										}
									}
								}
							});

							if (state == 1) {
								moveselect(gm, unit);
							} else if (state == 2)
								atkgrid(gm, unit);
							else if (state == 3 && gm.atkcount != 0) {
								atkselect(gm, unit);
							} else
								if(state!=-1)state = 0;
						}
						
					}
				}
			}
		}
		
		//System.out.println("fewfewfwefewfwefewfwewfewfwefwefwefewfewfwe");
	}

	

	public void movegrid(GameManager gm, Unit u, MouseEvent event) {

		if (u.getmovable() == true) {
			if (u.getX() / 60 == (int) (event.getSceneX()) / 60 && u.getY() / 60 == (int) (event.getSceneY()) / 60) {

				gm.resetOverlay();

				if (u instanceof Tank) {
					unit = u;
					for (int i = ((Tank) u).getWalkrange(); i >= -((Tank) u).getWalkrange(); i--) {
						for (int j = ((Tank) u).getWalkrange() - Math.abs(i); j >= Math.abs(i)
								- ((Tank) u).getWalkrange(); j--) {

							createMove(u, i, j);

						}
					}
				}
				if (u instanceof Soldier) {
					unit = u;
					for (int i = ((Soldier) u).getWalkrange(); i >= -((Soldier) u).getWalkrange(); i--) {
						for (int j = ((Soldier) u).getWalkrange() - Math.abs(i); j >= Math.abs(i)
								- ((Soldier) u).getWalkrange(); j--) {

							createMove(u, i, j);
						}
					}
				}
				if (u instanceof Artillery) {
					unit = u;
					for (int i = ((Artillery) u).getWalkrange(); i >= -((Artillery) u).getWalkrange(); i--) {
						for (int j = ((Artillery) u).getWalkrange() - Math.abs(i); j >= Math.abs(i)
								- ((Artillery) u).getWalkrange(); j--) {

							createMove(u, i, j);
						}
					}
				}
				if (u instanceof APC) {
					unit = u;
					for (int i = ((APC) u).getWalkrange(); i >= -((APC) u).getWalkrange(); i--) {
						for (int j = ((APC) u).getWalkrange() - Math.abs(i); j >= Math.abs(i)
								- ((APC) u).getWalkrange(); j--) {

							createMove(u, i, j);
						}
					}
				}
				paintComponents();
				state = 1;
			}
		}
	}

	public void moveselect(GameManager gm, Unit u) {

		this.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {

				if (event.getButton().equals(MouseButton.PRIMARY)) {

					if (gm.getOPos()[(int) (event.getSceneX()) / 60][(int) (event.getSceneY()) / 60] == 1) {

						if (u.getX() / 60 == ((int) (event.getSceneX()) / 60)
								&& u.getY() / 60 == ((int) (event.getSceneY()) / 60)) {
							if (u instanceof Soldier) {
								if (((Soldier) u).getHealth() < 14)
									((Soldier) u).setHealth(((Soldier) u).getHealth() + 1);
							}
							if (u instanceof Tank) {
								if (((Tank) u).getHealth() < 16)
									((Tank) u).setHealth(((Tank) u).getHealth() + 1);
							}
							if (u instanceof Artillery) {
								if (((Artillery) u).getHealth() < 10)
									((Artillery) u).setHealth(((Artillery) u).getHealth() + 1);
							}
							if (u instanceof APC) {
								if (((APC) u).getHealth() < 13)
									((APC) u).setHealth(((APC) u).getHealth() + 1);
							}
						}

						gm.getUPos()[u.getX() / 60][u.getY() / 60] = 0;

						/*
						 * u.setX(((int) (event.getSceneX()) / 60) * 60);
						 * u.setY(((int) (event.getSceneY()) / 60) * 60);
						 */
						// gm.getUPos()[u.getX() / 60][u.getY() / 60] =
						// u.getPlayer();
						gm.getUPos()[(int) (event.getSceneX()) / 60][(int) (event.getSceneY()) / 60] = u.getPlayer();
						xtemp = ((int) (event.getSceneX()) / 60) * 60;
						ytemp = ((int) (event.getSceneY()) / 60) * 60;
						// gm.update(u, ((int) (event.getSceneX()) / 60) * 60,
						// ((int) (event.getSceneY()) / 60) * 60);
						gm.resetOverlay();
						u.setmovable(false);
						// gm.getUPos()[((int) (event.getSceneX()) / 60)][((int)
						// (event.getSceneY()) / 60) / 60] = u.getPlayer();
						Thread t = new Thread(() -> {
							while (u.getX() != ((int) (event.getSceneX()) / 60) * 60
									|| u.getY() != ((int) (event.getSceneY()) / 60) * 60) {
								try {
									Thread.sleep(25);
									if (u.getX() > ((int) (event.getSceneX()) / 60) * 60) {
										u.setX(u.getX() - 10);
									}
									if (u.getX() < ((int) (event.getSceneX()) / 60) * 60) {
										u.setX(u.getX() + 10);
									}
									if (u.getY() > ((int) (event.getSceneY()) / 60) * 60) {
										u.setY(u.getY() - 10);
									}
									if (u.getY() < ((int) (event.getSceneY()) / 60) * 60) {
										u.setY(u.getY() + 10);
									}
									paintComponents();
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
									System.out.println("Stop Timer Thread");
									break;
								}
							}
							//System.out.println("55");
						});
						t.start();
						// System.out.println(((int) (event.getSceneX()) / 60) *
						// 60+" "+((int) (event.getSceneY()) / 60) * 60);
						/*
						 * if(u.getX()==((int) (event.getSceneX()) / 60) *
						 * 60&&u.getY()==((int) (event.getSceneY()) / 60) * 60){
						 * //gm.resetOverlay(); u.setmovable(false); state = 2;
						 * }
						 */

					}
					paintComponents();
				} else
					cancelAction();

			}
		});
		System.out.println(u.getX() + " " + u.getY());
		System.out.println(xtemp + " " + ytemp);
		if (u.getX() == xtemp && u.getY() == ytemp) {
			// gm.resetOverlay();
			// u.setmovable(false);
			state = 2;
		}
	}

	public void atkgrid(GameManager gm, Unit u) {
		gm.resetOverlay();
		gm.atkcount = 0;
		if (u instanceof Tank) {
			for (int i = ((Tank) u).getFirerange(); i >= -((Tank) u).getFirerange(); i--) {
				for (int j = ((Tank) u).getFirerange() - Math.abs(i); j >= Math.abs(i)
						- ((Tank) u).getFirerange(); j--) {
					createAttack(u, i, j);

				}
			}
		}
		if (u instanceof Soldier) {
			for (int i = ((Soldier) u).getFirerange(); i >= -((Soldier) u).getFirerange(); i--) {
				for (int j = ((Soldier) u).getFirerange() - Math.abs(i); j >= Math.abs(i)
						- ((Soldier) u).getFirerange(); j--) {
					createAttack(u, i, j);
				}
			}
		}
		if (u instanceof Artillery) {
			for (int i = ((Artillery) u).getFirerange(); i >= -((Artillery) u).getFirerange(); i--) {
				for (int j = ((Artillery) u).getFirerange() - Math.abs(i); j >= Math.abs(i)
						- ((Artillery) u).getFirerange(); j--) {
					createAttack(u, i, j);
				}
			}
		}
		if (u instanceof APC) {
			for (int i = ((APC) u).getFirerange(); i >= -((APC) u).getFirerange(); i--) {
				for (int j = ((APC) u).getFirerange() - Math.abs(i); j >= Math.abs(i) - ((APC) u).getFirerange(); j--) {
					createAttack(u, i, j);
				}
			}
		}
		state = 3;
		paintComponents();
	}

	public void atkselect(GameManager gm, Unit u) {
		this.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {

				if (event.getButton().equals(MouseButton.PRIMARY)) {

					if (gm.getOPos()[(int) (event.getSceneX()) / 60][(int) (event.getSceneY()) / 60] == 2) {

						// atk code here
						for (IRenderable ir : RenderableHolder.getInstance().getEntities()) {
							if (ir instanceof Unit) {
								Unit au = (Unit) ir;
								if (au.getX() / 60 == (int) (event.getSceneX()) / 60
										&& au.getY() / 60 == (int) (event.getSceneY()) / 60) {
									au.hit(u);
									if ((int) (event.getSceneX()) / 60 > u.getX() / 60)
										u.setDirection(1);
									else if ((int) (event.getSceneX()) / 60 < u.getX() / 60)
										u.setDirection(-1);
									state = 0;
								}

							}
						}

						gm.resetOverlay();

					}

					paintComponents();
				} else
					cancelAction();
			}
		});
	}

	public void setInfo() {
		this.setOnMouseMoved(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				if (state != 9 && state != 10) {
					if (getGm().getUPos()[(int) (event.getSceneX()) / 60][(int) (event.getSceneY()) / 60] != 0) {
						for (IRenderable ir : RenderableHolder.getInstance().getEntities()) {
							if (ir instanceof Unit) {
								Unit u = (Unit) ir;
								if (u.getX() / 60 == (int) (event.getSceneX()) / 60
										&& u.getY() / 60 == (int) (event.getSceneY()) / 60)
									if (u instanceof Tank)
										desc.setText("Player " + u.getPlayer() + "\n\nType Tank\n\nHealth : "
												+ ((Tank) u).getHealth() + "\n\nAtk : " + ((Tank) u).getFirepower()
												+ "\nAtk range : " + ((Tank) u).getFirerange() + "\nMove range : "
												+ ((Tank) u).getWalkrange());
									else if (u instanceof Soldier)
										desc.setText("Player " + u.getPlayer() + "\n\nType Tank\n\nHealth : "
												+ ((Soldier) u).getHealth() + "\n\nAtk : "
												+ ((Soldier) u).getFirepower() + "\nAtk range : "
												+ ((Soldier) u).getFirerange() + "\nMove range : "
												+ ((Soldier) u).getWalkrange());
									else if (u instanceof Artillery)
										desc.setText("Player " + u.getPlayer() + "\n\nType Tank\n\nHealth : "
												+ ((Artillery) u).getHealth() + "\n\nAtk : "
												+ ((Artillery) u).getFirepower() + "\nAtk range : "
												+ ((Artillery) u).getFirerange() + "\nMove range : "
												+ ((Artillery) u).getWalkrange());
									else if (u instanceof APC)
										desc.setText("Player " + u.getPlayer() + "\n\nType Tank\n\nHealth : "
												+ ((APC) u).getHealth() + "\n\nAtk : " + ((APC) u).getFirepower()
												+ "\nAtk range : " + ((APC) u).getFirerange() + "\nMove range : "
												+ ((APC) u).getWalkrange());

							}
						}
					} else {
						desc.setText("Player1 money:"+gm.getP1Money()+"\n\nPlayer2 money:"+gm.getP2Money());
					}
				}
			}
		});

	}

	public void cancelAction() {
		getGm().resetOverlay();
		setState(0);
		paintComponents();
	}

	/*
	 * public void characterInput(int player){ if(player==1){
	 * gc.setFill(Color.DARKBLUE); gc.fillRect(300, 0, zonewidth-300,
	 * zoneheight); } else if(player==2){ gc.setFill(Color.DEEPPINK);
	 * gc.fillRect(300, 0, zonewidth-300, zoneheight); } }
	 */

	private void createAttack(Unit u, int i, int j) {
		if (u.getX() / 60 + i >= 0 && u.getY() / 60 + j >= 0 && u.getX() / 60 + i < 18 && u.getY() / 60 + j < 12) {
			if ((i != 0 || j != 0) && gm.getUPos()[u.getX() / 60 + i][u.getY() / 60 + j] != 0) {
				if ((i != 0 || j != 0) && gm.getUPos()[u.getX() / 60 + i][u.getY() / 60 + j] != u.getPlayer()) {
					/*
					 * System.out.println( gm.getUPos()[u.getX() / 60 +
					 * i][u.getY() / 60 + j] + " " + u.getPlayer());
					 */
					Attack a = new Attack(u.getX() + i * 60, u.getY() + j * 60);
					RenderableHolder.instance.addOverlays(a);
					gm.atkcount++;
					gm.getOPos()[u.getX() / 60 + i][u.getY() / 60 + j] = 2;
				}
			}
		}
	}

	private void createMove(Unit u, int i, int j) {
		if (u.getX() / 60 + i >= 0 && u.getY() / 60 + j >= 0 && u.getX() / 60 + i < 18 && u.getY() / 60 + j < 12) {
			if ((i == 0 && j == 0) || gm.getUPos()[u.getX() / 60 + i][u.getY() / 60 + j] == 0) {

				Walk w = new Walk(u.getX() + i * 60, u.getY() + j * 60);
				RenderableHolder.instance.addOverlays(w);
				gm.getOPos()[u.getX() / 60 + i][u.getY() / 60 + j] = 1;
			}
		}
	}

	private void prePlacingUnit() {
		paintComponents();
		if (gm.getturn() == 1) {

			// gc.setFill(Color.LIGHTGRAY);
			// gc.fillRect(300, 0, zonewidth-300, zoneheight);
			gc.drawImage(build, 300, 0, zonewidth - 300, zoneheight);

			iconPlacing();
		} else if (gm.getturn() == 2) {

			// gc.setFill(Color.LIGHTGRAY);
			// gc.fillRect(0, 0, zonewidth-300, zoneheight);
			gc.drawImage(build, 0, 0, zonewidth - 300, zoneheight);

		}
		iconPlacing();
	}

	public void iconPlacing() {

		Font theFont = Font.font("Arial", FontWeight.LIGHT, 20);
		gc.setFont(theFont);
		// if (s + a + t + ap != 0) {
		if (getGm().getturn() == 1) {
			// if (s != 0) {
			gc.setFill(Color.DARKGRAY);
			gc.fillRoundRect(540, 180, 60, 60, 20, 20);
			if (sp.equals("s")) {
				gc.setStroke(Color.DARKBLUE);
				gc.strokeRoundRect(540, 180, 60, 60, 20, 20);
			}
			gc.setFill(Color.LIGHTGOLDENRODYELLOW);
			gc.fillText("left : " + s, 620, 180);
			gc.drawImage(RenderableHolder.soldier[0], 540, 180);

			if (s == 0) {
				gc.setGlobalAlpha(0.7);
				gc.setFill(Color.DIMGRAY);
				gc.fillRoundRect(540, 180, 60, 60, 20, 20);
				gc.setGlobalAlpha(1);
			}
			// }
			// if (t != 0) {
			gc.setFill(Color.DARKGRAY);
			gc.fillRoundRect(540, 300, 60, 60, 20, 20);
			if (sp.equals("t")) {
				gc.setStroke(Color.DARKBLUE);
				gc.strokeRoundRect(540, 300, 60, 60, 20, 20);
			}
			gc.setFill(Color.LIGHTGOLDENRODYELLOW);
			gc.fillText("left : " + t, 620, 300);
			gc.drawImage(RenderableHolder.tank[0], 540, 300);

			if (t == 0) {
				gc.setGlobalAlpha(0.7);
				gc.setFill(Color.DIMGRAY);
				gc.fillRoundRect(540, 300, 60, 60, 20, 20);
				gc.setGlobalAlpha(1);
			}
			// }
			// if (a != 0) {
			gc.setFill(Color.DARKGRAY);
			gc.fillRoundRect(540, 420, 60, 60, 20, 20);
			if (sp.equals("a")) {
				gc.setStroke(Color.DARKBLUE);
				gc.strokeRoundRect(540, 420, 60, 60, 20, 20);
			}
			gc.setFill(Color.LIGHTGOLDENRODYELLOW);
			gc.fillText("left : " + a, 620, 420);
			gc.drawImage(RenderableHolder.artillery[0], 540, 420);

			if (a == 0) {
				gc.setGlobalAlpha(0.7);
				gc.setFill(Color.DIMGRAY);
				gc.fillRoundRect(540, 420, 60, 60, 20, 20);
				gc.setGlobalAlpha(1);
			}
			// }
			// if (ap != 0) {
			gc.setFill(Color.DARKGRAY);
			gc.fillRoundRect(540, 540, 60, 60, 20, 20);
			if (sp.equals("ap")) {
				gc.setStroke(Color.DARKBLUE);
				gc.strokeRoundRect(540, 540, 60, 60, 20, 20);
			}
			gc.setFill(Color.LIGHTGOLDENRODYELLOW);
			gc.fillText("left : " + ap, 620, 540);
			gc.drawImage(RenderableHolder.apc[0], 540, 540);

			if (ap == 0) {
				gc.setGlobalAlpha(0.7);
				gc.setFill(Color.DIMGRAY);
				gc.fillRoundRect(540, 540, 60, 60, 20, 20);
				gc.setGlobalAlpha(1);
			}
			// }
			// paintComponents();

		} else {
			if (getGm().getturn() == 2) {
				// if (s != 0) {
				gc.setFill(Color.DARKGRAY);
				gc.fillRoundRect(240, 180, 60, 60, 20, 20);
				if (sp.equals("s")) {
					gc.setStroke(Color.DEEPPINK);
					gc.strokeRoundRect(240, 180, 60, 60, 20, 20);
				}
				gc.setFill(Color.LIGHTGOLDENRODYELLOW);
				gc.fillText("left : " + s, 320, 180);
				gc.drawImage(RenderableHolder.soldier[0], 240, 180);

				if (s == 0) {
					gc.setGlobalAlpha(0.7);
					gc.setFill(Color.DIMGRAY);
					gc.fillRoundRect(240, 180, 60, 60, 20, 20);
					gc.setGlobalAlpha(1);
				}
				// }
				// if (t != 0) {
				gc.setFill(Color.DARKGRAY);
				gc.fillRoundRect(240, 300, 60, 60, 20, 20);
				if (sp.equals("t")) {
					gc.setStroke(Color.DEEPPINK);
					gc.strokeRoundRect(240, 300, 60, 60, 20, 20);
				}
				gc.setFill(Color.LIGHTGOLDENRODYELLOW);
				gc.fillText("left : " + t, 320, 300);
				gc.drawImage(RenderableHolder.tank[0], 240, 300);

				if (t == 0) {
					gc.setGlobalAlpha(0.7);
					gc.setFill(Color.DIMGRAY);
					gc.fillRoundRect(240, 300, 60, 60, 20, 20);
					gc.setGlobalAlpha(1);
				}
				// }
				// if (a != 0) {
				gc.setFill(Color.DARKGRAY);
				gc.fillRoundRect(240, 420, 60, 60, 20, 20);
				if (sp.equals("a")) {
					gc.setStroke(Color.DEEPPINK);
					gc.strokeRoundRect(240, 420, 60, 60, 20, 20);
				}
				gc.setFill(Color.LIGHTGOLDENRODYELLOW);
				gc.fillText("left : " + a, 320, 420);
				gc.drawImage(RenderableHolder.artillery[0], 240, 420);

				if (a == 0) {
					gc.setGlobalAlpha(0.7);
					gc.setFill(Color.DIMGRAY);
					gc.fillRoundRect(240, 420, 60, 60, 20, 20);
					gc.setGlobalAlpha(1);
				}
				// }
				// if (ap != 0) {
				gc.setFill(Color.DARKGRAY);
				gc.fillRoundRect(240, 540, 60, 60, 20, 20);
				if (sp.equals("ap")) {
					gc.setStroke(Color.DEEPPINK);
					gc.strokeRoundRect(240, 540, 60, 60, 20, 20);
				}
				gc.setFill(Color.LIGHTGOLDENRODYELLOW);
				gc.fillText("left : " + ap, 320, 540);
				gc.drawImage(RenderableHolder.apc[0], 240, 540);

				if (ap == 0) {
					gc.setGlobalAlpha(0.7);
					gc.setFill(Color.DIMGRAY);
					gc.fillRoundRect(240, 540, 60, 60, 20, 20);
					gc.setGlobalAlpha(1);
				}
				// }
				// paintComponents();
				// }
			}
		}

	}

	public void selectPlacing(int s, int t, int a, int ap) {
		// System.out.println("here pls");
		this.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				// System.out.println("go get it");
				if (getGm().getturn() == 1) {
					if (s != 0 && ((int) event.getSceneX()) / 60 == 9 && ((int) event.getSceneY()) / 60 == 3) {
						// System.out.println("go get s");
						sp = "s";
						// place();
						if(state == 7)state=6 ;
						else state = 8;
					}
					if (t != 0 && ((int) event.getSceneX()) / 60 == 9 && ((int) event.getSceneY()) / 60 == 5) {
						sp = "t";
						// place();
						if(state == 7)state=6 ;
						else state = 8;
					}
					if (a != 0 && ((int) event.getSceneX()) / 60 == 9 && ((int) event.getSceneY()) / 60 == 7) {
						sp = "a";
						// place();
						if(state == 7)state=6 ;
						else state = 8;
					}
					if (ap != 0 && ((int) event.getSceneX()) / 60 == 9 && ((int) event.getSceneY()) / 60 == 9) {
						sp = "ap";
						// place();
						if(state == 7)state=6 ;
						else state = 8;
					}
					// paintComponents();

				} else if (getGm().getturn() == 2) {
					System.out.println("here bro");
					if (s != 0 && ((int) event.getSceneX()) / 60 == 4 && ((int) event.getSceneY()) / 60 == 3) {
						sp = "s";
						// place();
						if(state == 7)state=6 ;
						else state = 8;
					}
					if (t != 0 && ((int) event.getSceneX()) / 60 == 4 && ((int) event.getSceneY()) / 60 == 5) {
						sp = "t";
						// place();
						if(state == 7)state=6 ;
						else state = 8;
					}
					if (a != 0 && ((int) event.getSceneX()) / 60 == 4 && ((int) event.getSceneY()) / 60 == 7) {
						sp = "a";
						// place();
						if(state == 7)state=6 ;
						else state = 8;
					}
					if (ap != 0 && ((int) event.getSceneX()) / 60 == 4 && ((int) event.getSceneY()) / 60 == 9) {
						sp = "ap";
						// place();
						if(state == 7)state=6 ;
						else state = 8;
					}
				}
			}
		});
		// prePlacing();
		prePlacingUnit();
	}

	// if(s.equals("s"))
	public void place() {

		this.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				// System.out.println("dioqehfiwjioefjoq");
				if (event.getButton().equals(MouseButton.PRIMARY)) {
					if (getGm().getUPos()[((int) event.getSceneX()) / 60][((int) event.getSceneY()) / 60] == 0) {
						if (getGm().getturn() == 1) {
							// System.out.println("dioqehfiwjioefjoq");
							if (event.getSceneX() >= 0 && event.getSceneX() < 300 && event.getSceneY() >= 0
									&& event.getSceneY() < zoneheight) {
								// System.out.println("this is 1");
								if (sp.equals("s")) {
									// System.out.println(" crt s");
									getGm().createSoldier((((int) event.getSceneX()) / 60),
											(((int) event.getSceneY()) / 60), 1);
									getGm().getUPos()[((int) event.getSceneX()) / 60][((int) event.getSceneY())
											/ 60] = 1;
									s--;
									sp = "";
									if(state == 6){
										getGm().setP1Money(getGm().getP1Money()-50);
										state = 7;
										s=getGm().getP1Money()/50 ;
										t=getGm().getP1Money()/50 ;
										a=getGm().getP1Money()/50 ;
										ap=getGm().getP1Money()/50 ;
									}
									else state = 9;
								}
								if (sp.equals("t")) {
									getGm().createTank((((int) event.getSceneX()) / 60),
											(((int) event.getSceneY()) / 60), 1);
									getGm().getUPos()[((int) event.getSceneX()) / 60][((int) event.getSceneY())
											/ 60] = 1;
									t--;
									sp = "";
									if(state == 6){
										getGm().setP1Money(getGm().getP1Money()-50);
										state = 7;
										s=getGm().getP1Money()/50 ;
										t=getGm().getP1Money()/50 ;
										a=getGm().getP1Money()/50 ;
										ap=getGm().getP1Money()/50 ;
									}
									else state = 9;
								}
								if (sp.equals("a")) {
									getGm().createArtillery((((int) event.getSceneX()) / 60),
											(((int) event.getSceneY()) / 60), 1);
									getGm().getUPos()[((int) event.getSceneX()) / 60][((int) event.getSceneY())
											/ 60] = 1;
									a--;
									sp = "";
									if(state == 6){
										getGm().setP1Money(getGm().getP1Money()-50);
										state = 7;
										s=getGm().getP1Money()/50 ;
										t=getGm().getP1Money()/50 ;
										a=getGm().getP1Money()/50 ;
										ap=getGm().getP1Money()/50 ;
									}
									else state = 9;
								}
								if (sp.equals("ap")) {
									getGm().createAPC((((int) event.getSceneX()) / 60),
											(((int) event.getSceneY()) / 60), 1);
									getGm().getUPos()[((int) event.getSceneX()) / 60][((int) event.getSceneY())
											/ 60] = 1;
									ap--;
									sp = "";
									if(state == 6){
										getGm().setP1Money(getGm().getP1Money()-50);
										state = 7;
										s=getGm().getP1Money()/50 ;
										t=getGm().getP1Money()/50 ;
										a=getGm().getP1Money()/50 ;
										ap=getGm().getP1Money()/50 ;
									}
									else state = 9;
								}
							}
						} else {
							if (getGm().getturn() == 2) {
								// System.out.println("this is 2");
								if (event.getSceneX() >= zonewidth - 300 && event.getSceneX() < zonewidth
										&& event.getSceneY() >= 0 && event.getSceneY() < zoneheight) {
									if (sp.equals("s")) {
										getGm().createSoldier((((int) event.getSceneX()) / 60),
												(((int) event.getSceneY()) / 60), 2);
										getGm().getUPos()[((int) event.getSceneX()) / 60][((int) event.getSceneY())
												/ 60] = 2;
										s--;
										sp = "";
										if(state == 6){
											getGm().setP2Money(getGm().getP2Money()-50);
											state = 7;
											s=getGm().getP2Money()/50 ;
											t=getGm().getP2Money()/50 ;
											a=getGm().getP2Money()/50 ;
											ap=getGm().getP2Money()/50 ;
										}
										else state = 9;
									}
									if (sp.equals("t")) {
										getGm().createTank((((int) event.getSceneX()) / 60),
												(((int) event.getSceneY()) / 60), 2);
										getGm().getUPos()[((int) event.getSceneX()) / 60][((int) event.getSceneY())
												/ 60] = 2;
										t--;
										sp = "";
										if(state == 6){
											getGm().setP2Money(getGm().getP2Money()-50);
											state = 7;
											s=getGm().getP2Money()/50 ;
											t=getGm().getP2Money()/50 ;
											a=getGm().getP2Money()/50 ;
											ap=getGm().getP2Money()/50 ;
										}
										else state = 9;
									}
									if (sp.equals("a")) {
										getGm().createArtillery((((int) event.getSceneX()) / 60),
												(((int) event.getSceneY()) / 60), 2);
										getGm().getUPos()[((int) event.getSceneX()) / 60][((int) event.getSceneY())
												/ 60] = 2;
										a--;
										sp = "";
										if(state == 6){
											getGm().setP2Money(getGm().getP2Money()-50);
											state = 7;
											s=getGm().getP2Money()/50 ;
											t=getGm().getP2Money()/50 ;
											a=getGm().getP2Money()/50 ;
											ap=getGm().getP2Money()/50 ;
										}
										else state = 9;
									}
									if (sp.equals("ap")) {
										getGm().createAPC((((int) event.getSceneX()) / 60),
												(((int) event.getSceneY()) / 60), 2);
										getGm().getUPos()[((int) event.getSceneX()) / 60][((int) event.getSceneY())
												/ 60] = 2;
										ap--;
										sp = "";
										if(state == 6){
											getGm().setP2Money(getGm().getP2Money()-50);
											state = 7;
											s=getGm().getP2Money()/50 ;
											t=getGm().getP2Money()/50 ;
											a=getGm().getP2Money()/50 ;
											ap=getGm().getP2Money()/50 ;
										}
										else state = 9;
									}
								}
							}
						}
					}
				} else {
					sp = "";
					state = 9;
					if(state == 6)state = 6;
				}
			}
		});
		// System.out.println("ooo");
		paintComponents();
		prePlacingUnit();
	}

	public void countdown() {
		this.timer = new Thread(() -> {
			while (true) {
				try {
					Thread.sleep(1000);
					timeint--;
					// drawCurrentTimeString(gc);

					Platform.runLater(new Runnable() {
						public void run() {
							setClock();
						}
					});
					// System.out.println(timeint);
					if (timeint <= 0)
						stop();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Stop Timer Thread");
					break;
				}
			}
		});
		timer.start();
		this.time.setText(timestr);
	}

	public void stop() throws InterruptedException {
		// TODO Auto-generated method stub
		this.timer.interrupt();

		for (IRenderable ir : RenderableHolder.getInstance().getEntities()) {
			if (ir instanceof Unit) {
				((Unit) ir).setmovable(false);
			}
		}
		gm.resetOverlay();
		setState(0);

		paintComponents();
	}

	public void setClock() {
		this.timestr = "" + timeint;
		time.setText(timestr);
	}

	/*public void recruit() {
		cancelAction();

		if (getGm().getturn() == 1) {
			s = getGm().getP1Money() / 50;
			t = getGm().getP1Money() / 50;
			a = getGm().getP1Money() / 50;
			ap = getGm().getP1Money() / 50;
		} else {
			if (getGm().getturn() == 2) {
				s = getGm().getP2Money() / 50;
				t = getGm().getP2Money() / 50;
				a = getGm().getP2Money() / 50;
				ap = getGm().getP2Money() / 50;
			}
		}
		state = 9;
	}*/

	public Label getDesc() {
		return desc;
	}

	public GameManager getGm() {
		return gm;
	}

	public void setState(int state) {
		this.state = state;
	}

	public void setS(int s) {
		this.s = s;
	}

	public void setA(int a) {
		this.a = a;
	}

	public void setT(int t) {
		this.t = t;
	}

	public void setAp(int ap) {
		this.ap = ap;
	}

	public void setSp(String sp) {
		this.sp = sp;
	}

	public int getState() {
		return state;
	}

	public Label getTime() {
		return time;
	}

	public void setTime(Label time) {
		this.time = time;
	}

	public void setTimeint(int timeint) {
		this.timeint = timeint;
	}

	public String getTimestr() {
		return timestr;
	}

	public Thread getTimer() {
		return timer;
	}

	public int getTimeint() {
		return timeint;
	}

}
