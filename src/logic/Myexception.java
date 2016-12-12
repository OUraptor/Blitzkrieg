package logic;

public class Myexception extends Exception {
	
	public Myexception(int p1unit,int p2unit){
		if(p1unit==0){
			System.out.println("rip p1");
			/*Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("P1 Win!!!");
			alert.setHeaderText(null);
			alert.setContentText("Player 1 Win!!!");
			alert.showAndWait();*/
		}
		else if(p2unit==0){
			System.out.println("rip p2");
			/*Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("P2 Win!!!");
			alert.setHeaderText(null);
			alert.setContentText("Player 2 Win!!!");
			alert.showAndWait();*/
		}
	}
	
}
