package logic;

import gui.Attack;
import gui.RenderableHolder;

public class GameManager {
	
	private int[][] Upos = new int[18][12] ;
	private int[][] Opos = new int[18][12] ;
	private int[][] Tpos = new int[18][12] ;
	
	private int turnplayer=1 ;
	public int atkcount = 0 ;

	public GameManager(){
		
		
		createTank(2, 5, 1);
		createTank(1, 5, 1);
		createSoldier(2, 6, 1);
		createArtillery(2, 4, 1);
		createAPC(2, 7, 1);
		
		createTank(15, 5, 2);
		createSoldier(15, 6, 2);
		createArtillery(15, 4, 2);
		createAPC(15, 7, 2);
		
		
		Attack atk = new Attack(0, 0);
		RenderableHolder.instance.addOverlays(atk);
		
		
		
	}
	
	public void createTank(int x, int y, int player){
		if(player==1){
			Tank t = new Tank(x*60, y*60, 1, 1) ;
			Upos[x][y]=1 ;
			RenderableHolder.instance.addEntities(t) ;
		}
		else if (player==2) {
			Tank t = new Tank(x*60, y*60, -1, 2) ;
			Upos[x][y]=2 ;
			RenderableHolder.instance.addEntities(t) ;
		}
	}
	
	public void createSoldier(int x, int y, int player){
		if(player==1){
			Soldier s = new Soldier(x*60, y*60, 1, 1) ;
			Upos[x][y]=1 ;
			RenderableHolder.instance.addEntities(s) ;
		}
		else if (player==2) {
			Soldier s = new Soldier(x*60, y*60, -1, 2) ;
			Upos[x][y]=2 ;
			RenderableHolder.instance.addEntities(s) ;
		}
	}
	
	public void createArtillery(int x, int y, int player){
		if(player==1){
			Artillery l = new Artillery(x*60, y*60, 1, 1) ;
			Upos[x][y]=1 ;
			RenderableHolder.instance.addEntities(l) ;
		}
		else if (player==2) {
			Artillery l = new Artillery(x*60, y*60, -1, 2) ;
			Upos[x][y]=2 ;
			RenderableHolder.instance.addEntities(l) ;
		}
	}
	
	public void createAPC(int x, int y, int player){
		if(player==1){
			APC apc = new APC(x*60, y*60, 1, 1) ;
			Upos[x][y]=1 ;
			RenderableHolder.instance.addEntities(apc) ;
		}
		else if (player==2) {
			APC apc = new APC(x*60, y*60, -1, 2) ;
			Upos[x][y]=2 ;
			RenderableHolder.instance.addEntities(apc) ;
		}
	}
	
	public void update() {
	
	}
	
	public void resetOverlay(){
		for(int i=0;i<18;i++){
			for(int j=0;j<12;j++){
				Opos[i][j]=0 ;
			}
		}
		for(int i=RenderableHolder.instance.getOverlays().size()-1;i>=0;i--){
			RenderableHolder.getInstance().getOverlays().remove(i) ;
		}
	}
	
	
	public int getturn(){
		return turnplayer;
	}
	
	public void setturn(int turnplayer){
		this.turnplayer = turnplayer;
	}
	
	public int[][] getTPos() {
		return Tpos;
	}
	
	public int[][] getUPos() {
		return Upos;
	}
	
	public int[][] getOPos() {
		return Opos;
	}

	
	
}
