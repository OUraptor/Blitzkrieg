package logic;

import gui.RenderableHolder;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Soldier extends Unit {
	
	private int firepower ;
	private int firerange ;
	private int walkrange ;
	private int health ;
	//private int player ;
	//private static boolean movable ;
	
	public Soldier(int x, int y, int direction, int player){
		super(x, y,direction,player);
		this.firepower = 2 ;
		this.health = 14 ;
		this.firerange  = 1 ;
		this.walkrange = 6 ;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		if(this.movable){
			if(this.player==1){
				gc.setStroke(Color.DARKBLUE);
				gc.setLineWidth(3.0);
				gc.strokeOval(this.x+5,this.y+5, 50, 50);
			}
			else if(this.player==2){
				gc.setStroke(Color.DEEPPINK);
				gc.setLineWidth(3.0);
				gc.strokeOval(this.x+5,this.y+5, 50, 50);
				}
			gc.setStroke(Color.BLACK);
		}
		if(direction==1)gc.drawImage(RenderableHolder.soldier[0],this.x,this.y);
		if(direction==-1)gc.drawImage(RenderableHolder.soldier[1], this.x, this.y);
		
	}

	@Override
	public boolean isDestroy() {
		// TODO Auto-generated method stub
		return isDestroy;
	}
	
	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getFirepower() {
		return firepower;
	}

	public int getFirerange() {
		return firerange;
	}

	public int getWalkrange() {
		return walkrange;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getPlayer() {
		return player;
	}
	
}
