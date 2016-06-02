package objects;

public class Square {
	int x;
	int y;

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

	public Square(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	
	@Override
	public String toString() {
		return "Square [x=" + x + ", y=" + y + "]";
	}


	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public boolean equals(Square p) {
		boolean eq = false;

		if (x == p.getX() && y == p.getY()) {
			eq = true;
		}
		return eq;
	}

	public void setSquare(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
