package objects;

public class King extends figures {
	
	
	public King(int x, int y, boolean side) {
		super(x,y,side);
		name='K';
	}

	@Override
	boolean movingLegal(int positionX, int positionY) {
		if(positionX<0 || positionX>4 || positionY<0 || positionY>5)
			return false;
		
		int difX =x-positionX, difY=y-positionY;
		
		if (difX==0 && difY==0) 
			return false;
		if(difX>=-1 && difX<=1 && difY>=-1 && difY<=1)
			return true;
		else
			return false;
	}

}
