package objects;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.omg.CosNaming.IstringHelper;

public class Board {

// Variables
	int roundcount;
	char[][] boardArray;

// Constructors
	public Board(int roundcount, char[][] boardArray) {

		this.roundcount = roundcount;
		this.boardArray = boardArray;
	}

	public Board() {
		this.roundcount = 0;
		this.boardArray = new char[6][5];

		char[] c = { 'k', 'q', 'b', 'n', 'r' }, C = { 'R', 'N', 'B', 'Q', 'K' };
		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < boardArray[0].length; j++) {
				boardArray[i][j] = c[j];
			}
		}
		for (int i = 5; i < 6; i++) {
			for (int j = 0; j < boardArray[0].length; j++) {
				boardArray[i][j] = C[j];
			}
		}
		for (int i = 2; i < 4; i++) {
			for (int j = 0; j < boardArray[0].length; j++) {
				boardArray[i][j] = '.';
			}
		}
		for (int i = 1; i < 2; i++) {
			for (int j = 0; j < boardArray[0].length; j++) {
				boardArray[i][j] = 'p';
			}
		}
		for (int i = 4; i < 5; i++) {
			for (int j = 0; j < boardArray[0].length; j++) {
				boardArray[i][j] = 'P';
			}
		}
	}

	
// Helping Methods
	public char upperlowerpoint(char c) {
		int i = c;
		if (i >= 97 && i <= 122)
			return 'l';

		if (i >= 65 && i <= 90)
			return 'u';

		if (i == 46)
			return 'p';
		else
			return 'x';
	}

	public boolean playsBlack() {
		if (roundcount % 2 == 1)
			return true;
		else
			return false;
	}

	public boolean playsWhite() {
		if (roundcount % 2 == 1)
			return false;
		else
			return true;
	}

	
	public void showStats() {
		char c;

		if (playsWhite())
			c = 'W';
		else
			c = 'B';

		System.out.println("\n" + roundcount / 2 + "   " + c + "\n");
		String s = "";
		int i, j;
		for (i = 0; i < boardArray.length; i++) {
			for (j = 0; j < boardArray[0].length; j++) {
				s = (s + boardArray[i][j] + " ");
			}
			System.out.println(s);
			s = "";
		}
	}

// not used methods: mappingA1ToSquare, printBoardComplete, boardToString
	/*
	 * public Square mappingA1ToSquare(char alpha, char nr) { Square s = new
	 * Square(alpha - 97, nr - 49); return s; }
	 * 
	 * private void printBoardComplete() { String s = ""; int i, j; for (i = 0;
	 * i < boardArray.length; i++) { for (j = 0; j < boardArray[0].length; j++)
	 * { s = (s + boardArray[i][j] + " "); } System.out.println(s); s = ""; } }
	 * 
	 * private String boardToString() { String str = ""; for (int i = 0; i <
	 * boardArray.length; i++) { for (int j = 0; j < boardArray[i].length; j++){
	 * str += boardArray[i][j]; } } return str; }
	 *
	 *public boolean aNewMoveThatIsMaybeLegalMaybeNot(Square start, Square end){
		if((start.x<0||start.y<0||end.x<0||end.y<0||start.x>4||start.y>5||end.x>4||end.y>5)
			|| (start.x==end.x&&start.y==end.y)
			|| (upperlowerpoint(boardArray[start.y][start.x])==upperlowerpoint(boardArray[end.y][end.x]))
		) 	return false;
		
		if(boardArray[start.y][start.x]=='k'||boardArray[start.y][start.x]=='K'){
			int dx = start.x-end.x, dy = end.y-start.y;
			if(dx>1||dx<-1||dy<-1||dy>1) return false;
		}
		
		if(boardArray[start.y][start.x]=='N'||boardArray[start.y][start.x]=='n'){
			if(start.x==end.x||start.y==end.y) return false;
			
		}
		
		
		
		return true;
	}
	
	 *
	 */
	
	
// I/O Methods
	public static String incomingFiles() throws IOException {
		String end = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\here\\in.txt")));
		try {
			String line;
			while ((line = br.readLine()) != null) {
				end = end + "\n" + line;
			}
		} finally {
			br.close();
		}

		return end;
	}

	public static void outgoingFiles(String toWrite) throws IOException {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter("C:\\here\\out.txt"));
			writer.write(toWrite);

		} catch (IOException e) {
		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (IOException e) {
			}
		}
	}

	
// Moving Methods
	public boolean legalMove(int y, int x, int yz, int xz) {
		if (xz < 0 || xz > 5 || yz < 0 || yz > 4 || (x == xz && y == yz))
			return false;
		if (upperlowerpoint(boardArray[x][y]) != 'l' && playsBlack())
			return false;
		if (upperlowerpoint(boardArray[x][y]) != 'u' && playsWhite())
			return false;
		if (upperlowerpoint(boardArray[x][y]) == upperlowerpoint(boardArray[xz][yz]))
			return false;

		if (boardArray[x][y] == 'k' || boardArray[x][y] == 'K') {
			if (xz <= x + 1 && xz >= x - 1 && yz <= y + 1 && yz >= y - 1)
				return true;
 		}
		
		if (boardArray[x][y] == 'q' || boardArray[x][y] == 'Q') {
			if ((Math.abs(xz - x) == Math.abs(yz - y)) || (x == xz || y == yz))
				return true;
		}
		if (boardArray[x][y] == 'b' || boardArray[x][y] == 'B') {
			if (Math.abs(xz - x) == Math.abs(yz - y))
				return true;
			if ((x == xz || y == yz) && Math.abs(xz - x) <= 1 && Math.abs(yz - y) <= 1 && boardArray[xz][yz] == '.')
				return true;
		}
		if (boardArray[x][y] == 'n' || boardArray[x][y] == 'N') {
			if (xz == x || yz == y)
				return false;
			if (Math.abs(xz - x) + Math.abs(yz - y) == 3)
				return true;
		}
		if (boardArray[x][y] == 'r' || boardArray[x][y] == 'R') {
			if (x == xz || y == yz)
				return true;
		}
		if (boardArray[x][y] == 'p') {
			if (xz == x + 1 && yz == y && boardArray[xz][yz] == '.')
				return true;
			if (xz == x + 1 && (yz == y + 1 || yz == y - 1) && upperlowerpoint(boardArray[xz][yz]) == 'u')
				return true;
		}
		if (boardArray[x][y] == 'P') {
			if (xz == x - 1 && yz == y && boardArray[xz][yz] == '.')
				return true;
			if (xz == x - 1 && (yz == y + 1 || yz == y - 1) && upperlowerpoint(boardArray[xz][yz]) == 'l')
				return true;

		}
		return false;
	}
	
	public Board move(Move m) {
		Board b = new Board(roundcount, boardArray);
		try {
			if (	
					//(b.aNewMoveThatIsMaybeLegalMaybeNot(m.s1, m.s2)||
					b.legalMove(m.s1.x, m.s1.y, m.s2.x, m.s2.y)//)
					&& ((upperlowerpoint(boardArray[m.s1.y][m.s1.x]) == 'l' && playsBlack())
					|| (upperlowerpoint(boardArray[m.s1.y][m.s1.x]) == 'u' && playsWhite()))
					
			) {

				// legal move ?

				b.boardArray[m.s2.y][m.s2.x] = b.boardArray[m.s1.y][m.s1.x];
				b.boardArray[m.s1.y][m.s1.x] = '.';
				b.roundcount++;

			} else {
				throw new Error("tried illegal move");
			}

		} catch (Exception e) {
			System.out.println("Ungültiger Move");

		}
		return b;
	}

	public Move move(String s) {
		char[] c = s.toCharArray();
		return (new Move(new Square(c[0] - 97, c[1] - 49), new Square(c[3] - 97, c[4] - 49)));
	}

	public ArrayList<Move> allTheSmallMoves(){
		ArrayList<Move> thingy = new ArrayList<>();
		for (int i = 0; i < boardArray.length; i++) {
			for (int j = 0; j < boardArray[i].length; j++) {
				if(boardArray[i][j]!='.'){
					for (int k = 0; k < boardArray.length; k++) {
						for (int l = 0; l < boardArray.length; l++) {
							if(legalMove(j, i, l, k)) {
								thingy.add(new Move(new Square(i, j),new Square(k, l)));
							}
						}
					}
				}
					
			}
		}
		return thingy;
	}
	
// Main Method
	public static void main(String[] args) throws IOException {
//		Board z = new Board();
//		z.showStats();
//
//		String[] stro = { "a5-a4", "d2-d3", "d3-d4", "d3-e4" };
//		for (int i = 0; i < stro.length; i++) {
//			String a = stro[i];
//			Move m = z.move(a);
//			z = z.move(m);
//			z.showStats();
//		}
		Board portd = new Board();
		ArrayList<Move> arl = portd.allTheSmallMoves();
		Move[] mdfjh = new Move[arl.size()];
		for (int i = 0; i < arl.size(); i++) {
			mdfjh[i]=arl.get(i);
		}
		
		for (int i = 0; i < mdfjh.length; i++) {
			System.out.println(mdfjh[i].toString());
		}
		
	}
}
