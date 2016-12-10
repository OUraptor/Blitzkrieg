package gui;

public abstract class Entity implements IRenderable{
	protected int x,y,z;
	
	public Entity(int x,int y){
		this.x=x;
		this.y=y;
		z=1 ;
	}
	public int getZ() {
		// TODO Auto-generated method stub
		return z;
	}
	public void setZ(int z) {
		// TODO Auto-generated method stub
		this.z=z;
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
}
