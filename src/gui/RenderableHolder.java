package gui;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

public class RenderableHolder {
	
	public static Image[] tank ;
	public static Image[] soldier ;
	public static Image[] APC ;
	public static Image[] Artillery ;
	
	private List<IRenderable> entities ;
	private List<IRenderable> overlays ;
	public static final RenderableHolder instance = new RenderableHolder();
	
	
	
	public RenderableHolder(){
		
		tank = new Image[2] ;
		soldier = new Image[2] ;
		APC = new Image[2] ;
		Artillery = new Image[2] ;
		entities = new ArrayList<IRenderable>() ;
		overlays = new ArrayList<IRenderable>() ;
	}
	
	public synchronized void addEntities(IRenderable entity){
		entities.add(entity) ;
	}
	
	public synchronized void addOverlays(IRenderable entity){
		overlays.add(entity) ;
	}
	
	static{
		loadResource();
	}
	
	private static void loadResource() {
		ClassLoader loader = ClassLoader.getSystemClassLoader() ;
		tank[0] = new Image(loader.getResourceAsStream("tankr.png")) ;
		tank[1] = new Image(loader.getResourceAsStream("tankl.png")) ;
		soldier[0] = new Image(loader.getResourceAsStream("soldierr.png")) ;
		soldier[1] = new Image(loader.getResourceAsStream("soldierl.png")) ;
		APC[0] = new Image(loader.getResourceAsStream("apcr.png")) ;
		APC[1] = new Image(loader.getResourceAsStream("apcl.png")) ;
		Artillery[0] = new Image(loader.getResourceAsStream("artilleryr.png")) ;
		Artillery[1] = new Image(loader.getResourceAsStream("artilleryl.png")) ;
	}
	
	public List<IRenderable> getEntities() {
		return entities;
	}
	
	public List<IRenderable> getOverlays() {
		return overlays;
	}



	public static RenderableHolder getInstance() {
		return instance;
	}
	
	
	
	
	
}
