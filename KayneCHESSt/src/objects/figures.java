package objects;

public class figures {
	
		int x; 			//A-E
		int y;			//1-6
		Square sq;
		boolean side;	//black(1) or white(0)
		char name;		//K,Q,B,N,R,P, ;
		
		public figures(int x, int y, boolean side, char name) {
			this.x = x;
			this.y = y;
			this.sq.setXY(x, y);
			this.side = side;
			this.name =name;
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

		public boolean isSide() {
			return side;
		}

		public void setSide(boolean side) {
			this.side = side;
		}

		public char getName() {
			return name;
		}

		public void setName(char name) {
			this.name = name;
		}
		
		
}
