package logic;

import gui.RenderableHolder;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Artillery extends Unit{
	
	private int firepower ;
	private int firerange ;
	private int walkrange ;
	private int health ;
	//private int player ;
	//private static boolean movable ;
	
	public Artillery(int x, int y, int direction, int player){
		super(x, y,direction,player);
		this.firepower = 3 ;
		this.health = 10 ;
		this.firerange  = 6 ;
		this.walkrange = 2 ;
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
		if(direction==1)gc.drawImage(RenderableHolder.artillery[0],this.x,this.y);
		if(direction==-1)gc.drawImage(RenderableHolder.artillery[1], this.x, this.y);
		
	}
	
	public void hit(Unit u){
		if(u instanceof Soldier)this.health=this.health-((Soldier)u).getFirepower()*3 ;
		else if(u instanceof Tank)this.health=this.health-((Tank)u).getFirepower() ;
		else if(u instanceof APC)this.health=this.health-((APC)u).getFirepower() ;
		else if(u instanceof Artillery)this.health=this.health-((Artillery)u).getFirepower() ;
		if(this.health<=0)this.setDestroy() ;
		}

	@Override
	public boolean isDestroy() {
		// TODO Auto-generated method stub
		return isDestroy;
	}
	
	public void setDestroy() {
		this.isDestroy=true;
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
