package gui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Walk extends Overlay{
	
	private boolean isDestroy ;
	
	public Walk(int x, int y){
		super(x,y);
		
		this.isDestroy = false ;
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.setGlobalAlpha(0.6);
		gc.setFill(Color.LIGHTGREEN);
		gc.fillRoundRect(x+3, y+3, 54, 54, 20, 20);
		gc.setGlobalAlpha(1);
	}

	@Override
	public boolean isDestroy() {
		// TODO Auto-generated method stub
		return isDestroy;
	}
	
	
	
	
}
