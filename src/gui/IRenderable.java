// Witsarut Boonmasuvaran 5831066721
// Possatorn Buakhom 5831043221
// Blitzkrieg master
package gui;

import javafx.scene.canvas.GraphicsContext;

public interface IRenderable {
	// Fill in here

	public int getZ();

	public void draw(GraphicsContext gc);

	public boolean isDestroy();
}