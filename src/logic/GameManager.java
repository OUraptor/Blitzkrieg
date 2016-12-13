package logic;

import gui.IRenderable;
import gui.RenderableHolder;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class GameManager {
	
	private int[][] Upos = new int[18][12] ;
	private int[][] Opos = new int[18][12] ;
	private int[][] Tpos = new int[18][12] ;
	
	private int turnplayer=1 ;
	public int atkcount = 0 ;
	
	private boolean p1drop = false ;
	private boolean p2drop = false ;
	
	private int p1unit=0;
	private int p2unit=0;
	
	private int p1Money=100 ;
	private int p2Money=100 ;
	

	public GameManager(){
		
		
		//createTank(2, 5, 1);
		//createTank(1, 5, 1);
		//createSoldier(2, 6, 1);
		//createArtillery(2, 4, 1);
		//createAPC(2, 7, 1);
		
		//createTank(15, 5, 2);
		//createSoldier(15, 6, 2);
		//createArtillery(15, 4, 2);
		//createAPC(15, 7, 2);
		
		
		/*Attack atk = new Attack(0, 0);
		RenderableHolder.instance.addOverlays(atk);*/
		
		
		
	}
	
	public void createTank(int x, int y, int player){
		if(player==1){
			Tank t = new Tank(x*60, y*60, 1, 1) ;
			Upos[x][y]=1 ;
			RenderableHolder.instance.addEntities(t) ;
			this.p1unit++ ;
		}
		else if (player==2) {
			Tank t = new Tank(x*60, y*60, -1, 2) ;
			Upos[x][y]=2 ;
			RenderableHolder.instance.addEntities(t) ;
			this.p2unit++ ;
		}
	}
	
	public void createSoldier(int x, int y, int player){
		if(player==1){
			Soldier s = new Soldier(x*60, y*60, 1, 1) ;
			Upos[x][y]=1 ;
			RenderableHolder.instance.addEntities(s) ;
			this.p1unit++ ;
		}
		else if (player==2) {
			Soldier s = new Soldier(x*60, y*60, -1, 2) ;
			Upos[x][y]=2 ;
			RenderableHolder.instance.addEntities(s) ;
			this.p2unit++ ;
		}
	}
	
	public void createArtillery(int x, int y, int player){
		if(player==1){
			Artillery l = new Artillery(x*60, y*60, 1, 1) ;
			Upos[x][y]=1 ;
			RenderableHolder.instance.addEntities(l) ;
			this.p1unit++ ;
		}
		else if (player==2) {
			Artillery l = new Artillery(x*60, y*60, -1, 2) ;
			Upos[x][y]=2 ;
			RenderableHolder.instance.addEntities(l) ;
			this.p2unit++ ;
		}
	}
	
	public void createAPC(int x, int y, int player){
		if(player==1){
			APC apc = new APC(x*60, y*60, 1, 1) ;
			Upos[x][y]=1 ;
			RenderableHolder.instance.addEntities(apc) ;
			this.p1unit++ ;
		}
		else if (player==2) {
			APC apc = new APC(x*60, y*60, -1, 2) ;
			Upos[x][y]=2 ;
			RenderableHolder.instance.addEntities(apc) ;
			this.p2unit++ ;
		}
	}
	
	public void update() {
	}
	
	public void checkwin(){
		if(p1unit==0){
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("P1 Win!!!");
			alert.setHeaderText(null);
			alert.setContentText("Player 1 Win!!!");
			alert.show();
		}
		else if(p2unit==0){
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("P2 Win!!!");
			alert.setHeaderText(null);
			alert.setContentText("Player 2 Win!!!");
			alert.show();
		}
	}
	
	public void removeDestroyEntity() {
		for(int i=RenderableHolder.getInstance().getEntities().size()-1;i>=0;i--){
		if(RenderableHolder.getInstance().getEntities().get(i).isDestroy()){
			IRenderable ir = RenderableHolder.getInstance().getEntities().get(i) ;
			if(ir instanceof Unit)this.Upos[((Unit) ir).getX()/60][((Unit) ir).getY()/60]=0;
			if(((Unit) ir).getPlayer()==1)p1unit--;
			else if(((Unit) ir).getPlayer()==2)p2unit--;
			RenderableHolder.getInstance().getEntities().remove(i) ;
		}
		}

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

	public boolean isP1drop() {
		return p1drop;
	}

	public boolean isP2drop() {
		return p2drop;
	}

	public void setP1drop(boolean p1drop) {
		this.p1drop = p1drop;
	}

	public void setP2drop(boolean p2drop) {
		this.p2drop = p2drop;
	}

	public int getP1unit() {
		return p1unit;
	}

	public int getP2unit() {
		return p2unit;
	}

	public int getP1Money() {
		return p1Money;
	}

	public void setP1Money(int p1Money) {
		this.p1Money = p1Money;
	}

	public int getP2Money() {
		return p2Money;
	}

	public void setP2Money(int p2Money) {
		this.p2Money = p2Money;
	}

	
	
}
