package gui;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import logic.APC;
import logic.Artillery;
import logic.GameManager;
import logic.Soldier;
import logic.Tank;
import logic.Unit;

public class GameZone extends Canvas {

	private static final int zonewidth = GameScreen.gscreenwidth - GameScreen.ctrwidth;
	private static final int zoneheight = GameScreen.gscreenheight;
	
	private Label desc = new Label() ;
	
	int p = 0;
	int q = 0;
	private int state = 0;

	private Unit unit;

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
					setInfo();
					gogo(gm);
					// paintComponents();
					start = now;
				}
			}
		}.start();

	}

	public void paintComponents() {
		// Fill in here
		GraphicsContext gc = this.getGraphicsContext2D();
		gc.setFill(Color.LIGHTGRAY);
		gc.fillRect(0, 0, zonewidth, zoneheight);

		gc.setLineWidth(2.0);
		gc.setFill(Color.RED);
		for (int i = 0; i <= zonewidth; i += 60) {
			for (int j = 0; j <= zoneheight; j += 60) {
				gc.strokeRoundRect(i, j, 60, 60, 0, 0);
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
		/*
		 * this.setOnMouseClicked(new EventHandler<MouseEvent>() { public void
		 * handle(MouseEvent event) { movegrid(gm,event); } });
		 */
		/*System.out.println("gm= " + gm.getturn());
		System.out.println("state= " + state);*/

		this.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				for (IRenderable ir : RenderableHolder.getInstance().getEntities()) {
					if (ir instanceof Unit) {
						Unit u = (Unit) ir;
						//System.out.println("move= " + u.getmovable());
						if (state == 0 && u.getmovable() == true)
							movegrid(gm, u, event);
						// movegrid(gm);
					}
				}
			}
		});

		if (state == 1)
			moveselect(gm, unit);
		else if (state == 2)
			atkgrid(gm, unit);
		else if (state == 3 && gm.atkcount != 0)
			atkselect(gm, unit);
		else
			state = 0;

		gm.update();

	}

	public void movegrid(GameManager gm, Unit u, MouseEvent event) {
		// public void movegrid(GameManager gm) {
		// Unit u ;
		p++;
		/*
		 * this.setOnMouseClicked(new EventHandler<MouseEvent>() { public void
		 * handle(MouseEvent event) { // System.out.println((int)
		 * (event.getSceneX()) / 60 + " " + // (int) (event.getSceneY()) / 60);
		 * for (IRenderable ir : RenderableHolder.getInstance().getEntities()) {
		 * if (ir instanceof Unit) { // System.out.println("gg"); Unit u =
		 * (Unit) ir;
		 */
		// unit = u;
		if (u.getmovable() == true) {
			//System.out.println(u.getX() / 60 + " " + u.getY() / 60);
			if (u.getX() / 60 == (int) (event.getSceneX()) / 60 && u.getY() / 60 == (int) (event.getSceneY()) / 60) {

				gm.resetOverlay();
				System.out.println("move "+u.getPlayer());
				if (u instanceof Tank) {
					unit = u;
					//System.out.println("go for tank");
					for (int i = ((Tank) u).getWalkrange(); i >= -((Tank) u).getWalkrange(); i--) {
						for (int j = ((Tank) u).getWalkrange() - Math.abs(i); j >= Math.abs(i)
								- ((Tank) u).getWalkrange(); j--) {
							// pos[x+i][y+i]
							// System.out.println((u.getX() / 60
							// +
							// i)+" "+(u.getY() / 60 + j));
							if (u.getX() / 60 + i >= 0 && u.getY() / 60 + j >= 0 && u.getX() / 60 + i < 18
									&& u.getY() / 60 + j < 12) {
								if ((i == 0 && j == 0) || gm.getUPos()[u.getX() / 60 + i][u.getY() / 60 + j] == 0) {

									Walk w = new Walk(u.getX() + i * 60, u.getY() + j * 60);
									RenderableHolder.instance.addOverlays(w);
									gm.getOPos()[u.getX() / 60 + i][u.getY() / 60 + j] = 1;
								}
							}

						}
					}
				}
				if (u instanceof Soldier) {
					unit = u;
					//System.out.println("we r all soldier");
					for (int i = ((Soldier) u).getWalkrange(); i >= -((Soldier) u).getWalkrange(); i--) {
						for (int j = ((Soldier) u).getWalkrange() - Math.abs(i); j >= Math.abs(i)
								- ((Soldier) u).getWalkrange(); j--) {
							// pos[x+i][y+i]
							if (u.getX() / 60 + i >= 0 && u.getY() / 60 + j >= 0 && u.getX() / 60 + i < 18
									&& u.getY() / 60 + j < 12) {
								if ((i == 0 && j == 0) || gm.getUPos()[u.getX() / 60 + i][u.getY() / 60 + j] == 0) {

									Walk w = new Walk(u.getX() + i * 60, u.getY() + j * 60);
									RenderableHolder.instance.addOverlays(w);
									gm.getOPos()[u.getX() / 60 + i][u.getY() / 60 + j] = 1;
								}
							}
						}
					}
				}
				if (u instanceof Artillery) {
					unit = u;
					for (int i = ((Artillery) u).getWalkrange(); i >= -((Artillery) u).getWalkrange(); i--) {
						for (int j = ((Artillery) u).getWalkrange() - Math.abs(i); j >= Math.abs(i)
								- ((Artillery) u).getWalkrange(); j--) {
							// pos[x+i][y+i]
							if (u.getX() / 60 + i >= 0 && u.getY() / 60 + j >= 0 && u.getX() / 60 + i < 18
									&& u.getY() / 60 + j < 12) {
								if ((i == 0 && j == 0) || gm.getUPos()[u.getX() / 60 + i][u.getY() / 60 + j] == 0) {

									Walk w = new Walk(u.getX() + i * 60, u.getY() + j * 60);
									RenderableHolder.instance.addOverlays(w);
									gm.getOPos()[u.getX() / 60 + i][u.getY() / 60 + j] = 1;
								}
							}
						}
					}
				}
				if (u instanceof APC) {
					unit = u;
					for (int i = ((APC) u).getWalkrange(); i >= -((APC) u).getWalkrange(); i--) {
						for (int j = ((APC) u).getWalkrange() - Math.abs(i); j >= Math.abs(i)
								- ((APC) u).getWalkrange(); j--) {
							// pos[x+i][y+i]
							if (u.getX() / 60 + i >= 0 && u.getY() / 60 + j >= 0 && u.getX() / 60 + i < 18
									&& u.getY() / 60 + j < 12) {
								if ((i == 0 && j == 0) || gm.getUPos()[u.getX() / 60 + i][u.getY() / 60 + j] == 0) {

									Walk w = new Walk(u.getX() + i * 60, u.getY() + j * 60);
									RenderableHolder.instance.addOverlays(w);
									gm.getOPos()[u.getX() / 60 + i][u.getY() / 60 + j] = 1;
								}
							}
						}
					}
				}
				paintComponents();
				state = 1;
				// System.out.println(u.getX()/60+"
				// "+u.getY()/60);
				// moveselect(gm, u);
			}
			// System.out.println("p="+p);

		}
		// }
		// }
		// }
		// });
	}

	public void moveselect(GameManager gm, Unit u) {

		this.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				// System.out.println("well");
				if (gm.getOPos()[(int) (event.getSceneX()) / 60][(int) (event.getSceneY()) / 60] == 1) {
					// System.out.println("ggpp");
					// System.out.println((int) (event.getSceneX()) / 60 + " " +
					// (int) (event.getSceneY()) / 60);

					gm.getUPos()[u.getX() / 60][u.getY() / 60] = 0;
					u.setX(((int) (event.getSceneX()) / 60) * 60);
					u.setY(((int) (event.getSceneY()) / 60) * 60);
					gm.getUPos()[u.getX() / 60][u.getY() / 60] = u.getPlayer();
					gm.resetOverlay();
					u.setmovable(false);
					state = 2;
					/*
					 * if(gm.getturn()==1)gm.setturn(2);
					 * if(gm.getturn()==2)gm.setturn(1);
					 */
				}
				// System.out.println("good");
				paintComponents();
			}
		});
		// System.out.println("555");
	}

	public void atkgrid(GameManager gm, Unit u) {
		gm.resetOverlay();
		gm.atkcount = 0;
		if (u instanceof Tank) {
			for (int i = ((Tank) u).getFirerange(); i >= -((Tank) u).getFirerange(); i--) {
				for (int j = ((Tank) u).getFirerange() - Math.abs(i); j >= Math.abs(i)
						- ((Tank) u).getFirerange(); j--) {
					// pos[x+i][y+i]
					// System.out.println((u.getX() / 60 +i)+" "+(u.getY() / 60
					// + j));
					if (u.getX() / 60 + i >= 0 && u.getY() / 60 + j >= 0 && u.getX() / 60 + i < 18
							&& u.getY() / 60 + j < 12) {
						if ((i != 0 || j != 0) && gm.getUPos()[u.getX() / 60 + i][u.getY() / 60 + j] != 0) {
							if ((i != 0 || j != 0)
									&& gm.getUPos()[u.getX() / 60 + i][u.getY() / 60 + j] != u.getPlayer()) {
								System.out.println(gm.getUPos()[u.getX() / 60 + i][u.getY() / 60 + j]+" "+u.getPlayer());
								Attack a = new Attack(u.getX() + i * 60, u.getY() + j * 60);
								RenderableHolder.instance.addOverlays(a);
								gm.atkcount++;
								gm.getOPos()[u.getX() / 60 + i][u.getY() / 60 + j] = 2;
								// System.out.println(gm.getOPos()[u.getX() / 60
								// +
								// i][u.getY() / 60 + i]);
							}
						}
					}

				}
			}
		}
		if (u instanceof Soldier) {
			for (int i = ((Soldier) u).getFirerange(); i >= -((Soldier) u).getFirerange(); i--) {
				for (int j = ((Soldier) u).getFirerange() - Math.abs(i); j >= Math.abs(i)
						- ((Soldier) u).getFirerange(); j--) {
					// pos[x+i][y+i]
					if (u.getX() / 60 + i >= 0 && u.getY() / 60 + j >= 0 && u.getX() / 60 + i < 18
							&& u.getY() / 60 + j < 12) {
						if ((i != 0 || j != 0) && gm.getUPos()[u.getX() / 60 + i][u.getY() / 60 + j] != 0) {
							if ((i != 0 || j != 0)
									&& gm.getUPos()[u.getX() / 60 + i][u.getY() / 60 + j] != u.getPlayer()) {
								System.out.println(gm.getUPos()[u.getX() / 60 + i][u.getY() / 60 + j]+" "+u.getPlayer());
								Attack a = new Attack(u.getX() + i * 60, u.getY() + j * 60);
								RenderableHolder.instance.addOverlays(a);
								gm.atkcount++;
								gm.getOPos()[u.getX() / 60 + i][u.getY() / 60 + j] = 2;
							}
						}
					}
				}
			}
		}
		if (u instanceof Artillery) {
			for (int i = ((Artillery) u).getFirerange(); i >= -((Artillery) u).getFirerange(); i--) {
				for (int j = ((Artillery) u).getFirerange() - Math.abs(i); j >= Math.abs(i)
						- ((Artillery) u).getFirerange(); j--) {
					// pos[x+i][y+i]
					if (u.getX() / 60 + i >= 0 && u.getY() / 60 + j >= 0 && u.getX() / 60 + i < 18
							&& u.getY() / 60 + j < 12) {
						if ((i != 0 || j != 0) && gm.getUPos()[u.getX() / 60 + i][u.getY() / 60 + j] != 0) {
							if ((i != 0 || j != 0)
									&& gm.getUPos()[u.getX() / 60 + i][u.getY() / 60 + j] != u.getPlayer()) {
								System.out.println(gm.getUPos()[u.getX() / 60 + i][u.getY() / 60 + j]+" "+u.getPlayer());
								Attack a = new Attack(u.getX() + i * 60, u.getY() + j * 60);
								RenderableHolder.instance.addOverlays(a);
								gm.atkcount++;
								gm.getOPos()[u.getX() / 60 + i][u.getY() / 60 + j] = 2;
							}
						}
					}
				}
			}
		}
		if (u instanceof APC) {
			for (int i = ((APC) u).getFirerange(); i >= -((APC) u).getFirerange(); i--) {
				for (int j = ((APC) u).getFirerange() - Math.abs(i); j >= Math.abs(i) - ((APC) u).getFirerange(); j--) {
					// pos[x+i][y+i]
					if (u.getX() / 60 + i >= 0 && u.getY() / 60 + j >= 0 && u.getX() / 60 + i < 18
							&& u.getY() / 60 + j < 12) {
						if ((i != 0 || j != 0) && gm.getUPos()[u.getX() / 60 + i][u.getY() / 60 + j] != 0) {
							if ((i != 0 || j != 0)
									&& gm.getUPos()[u.getX() / 60 + i][u.getY() / 60 + j] != u.getPlayer()) {
								System.out.println(gm.getUPos()[u.getX() / 60 + i][u.getY() / 60 + j]+" "+u.getPlayer());
								Attack a = new Attack(u.getX() + i * 60, u.getY() + j * 60);
								RenderableHolder.instance.addOverlays(a);
								gm.atkcount++;
								gm.getOPos()[u.getX() / 60 + i][u.getY() / 60 + j] = 2;
							}
						}
					}
				}
			}
		}
		state = 3;
		paintComponents();
	}

	public void atkselect(GameManager gm, Unit u) {
		this.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				//System.out.println("well");
				/*
				 * for (int i = 0; i < 18; i++) { for (int j = 0; j < 12; j++) {
				 * System.out.print(gm.getOPos()[i][j]); }
				 * System.out.println(""); }
				 */
				/*
				 * System.out.print(gm.getOPos()[3][2]);
				 * System.out.print(gm.getOPos()[4][2]);
				 * System.out.print(gm.getOPos()[5][2]);System.out.println("");
				 */
				if (gm.getOPos()[(int) (event.getSceneX()) / 60][(int) (event.getSceneY()) / 60] == 2) {

					// atk code here
					
					gm.resetOverlay();

				}
				if((int) (event.getSceneX()) / 60>u.getX()/60) u.setDirection(1);
				else if((int) (event.getSceneX()) / 60<u.getX()/60) u.setDirection(-1);
				state = 0;
				// System.out.println("good");
				paintComponents();
			}
		});
	}
	
	public void setInfo(){
		this.setOnMouseMoved(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event){
				//desc.setText(event.getSceneX()+" "+event.getSceneY());
				
				if(getGm().getUPos()[(int)(event.getSceneX())/60][(int)(event.getSceneY())/60] != 0){
					for (IRenderable ir : RenderableHolder.getInstance().getEntities()) {
						if (ir instanceof Unit) {
							Unit u = (Unit) ir;
							if(u.getX()/60==(int)(event.getSceneX())/60&&u.getY()/60==(int)(event.getSceneY())/60)
							if(u instanceof Tank)
								desc.setText("Player "+u.getPlayer()+"\n\nType Tank\n\nHealth : "+((Tank) u).getHealth()+"\n\nAtk : "+((Tank) u).getFirepower()+"\nAtk range : "+((Tank) u).getFirerange()+"\nMove range : "+((Tank) u).getWalkrange());
							else if(u instanceof Soldier)
								desc.setText("Player "+u.getPlayer()+"\n\nType Tank\n\nHealth : "+((Soldier) u).getHealth()+"\n\nAtk : "+((Soldier) u).getFirepower()+"\nAtk range : "+((Soldier) u).getFirerange()+"\nMove range : "+((Soldier) u).getWalkrange());
							else if(u instanceof Artillery)
								desc.setText("Player "+u.getPlayer()+"\n\nType Tank\n\nHealth : "+((Artillery) u).getHealth()+"\n\nAtk : "+((Artillery) u).getFirepower()+"\nAtk range : "+((Artillery) u).getFirerange()+"\nMove range : "+((Artillery) u).getWalkrange());
							else if(u instanceof APC)
								desc.setText("Player "+u.getPlayer()+"\n\nType Tank\n\nHealth : "+((APC) u).getHealth()+"\n\nAtk : "+((APC) u).getFirepower()+"\nAtk range : "+((APC) u).getFirerange()+"\nMove range : "+((APC) u).getWalkrange());
							
						}
					}
				}
				else{
					desc.setText("");
				}
			}
		});
		//desc.setText(MouseInfo.getPointerInfo().getLocation().x+" "+MouseInfo.getPointerInfo().getLocation().y);
	}

	public Label getDesc() {
		return desc;
	}

	public GameManager getGm() {
		return gm;
	}

	public void setState(int state) {
		this.state = state;
	}

}
