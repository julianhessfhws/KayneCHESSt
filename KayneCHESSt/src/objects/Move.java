package objects;

public class Move {
	


	Square s1; 
	Square s2;


	public Move(Square s1, Square s2) {
		
		this.s1 = s1;
		this.s2 = s2;
	}
	
	@Override
	public String toString() {
		return "from: "+ s1.toString() + "\tto: " +s2.toString();
	}
}
