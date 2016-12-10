package gui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Attack extends Overlay {
	
	private boolean isDestroy ;
	
	public Attack(int x, int y){
		super(x, y) ;
		
		this.isDestroy = false ;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.setGlobalAlpha(0.4);
		gc.setFill(Color.RED);
		gc.fillRoundRect(x+3, y+3, 54, 54, 20, 20);
		gc.setGlobalAlpha(1);
	}

	@Override
	public boolean isDestroy() {
		// TODO Auto-generated method stub
		return isDestroy;
	}
	
	
	
}
