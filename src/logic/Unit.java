package logic;

import gui.*;

public abstract class Unit extends Entity{
	protected int x,y,player,direction ;
	protected boolean movable=false ;
	public boolean isDestroy=false;


	public Unit(int x, int y, int direction, int player){
		super(x, y);
		this.x=x;
		this.y=y;
		this.player=player ;
		this.direction=direction;
	}
	
	
	public int getPlayer() {
		return player;
	}
	
	public boolean getmovable() {
		return movable;
	}
	
	public boolean setmovable(boolean movable) {
		return this.movable=movable;
	}
	
	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}
	
	public boolean isDestroy() {
		return isDestroy;
	}


	public void setDestroy(boolean isDestroy) {
		this.isDestroy = isDestroy;
	}


	public int getDirection() {
		return direction;
	}


	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	
}
