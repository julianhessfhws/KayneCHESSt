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
	
	
	
	
	
	
	//CASS------------------------------------------
	
	
	public boolean checkLegalMove(Square pCur, Square pNew) {
		char name = boardArray[pCur.x][pCur.y];
		boolean legit = false;

		if (pNew.getX() >= 0 && pNew.getX() <= 5 && pNew.getY() >= 0 && pNew.getY() <= 4
				&& boardArray[pCur.getX()][pCur.getY()] != '.') {
			if (name == 'k' || name == 'K') {
				// System.out.println(Math.abs(pNew.getX() -
				// pCur.getX()) + " " + Math.abs(pNew.getY() -
				// pCur.getY()));
				if (checkCollision(name, pCur, pNew) && Math.abs(pNew.getX() - pCur.getX()) <= 1
						&& Math.abs(pNew.getY() - pCur.getY()) <= 1)
					legit = true;
			}
			if (name == 'q' || name == 'Q') {
				if (checkCollision(name, pCur, pNew)
						&& (checkDiagonalMove(pCur, pNew) || checkVertHoriMove(pCur, pNew)))
					legit = true;
			}
			if (name == 'b' || name == 'B') {
				if (checkCollision(name, pCur, pNew) && checkDiagonalMove(pCur, pNew))
					legit = true;
			}
			if (name == 'n' || name == 'N') {
				if (checkCollision(name, pCur, pNew)
						&& (Math.abs(pNew.getX() - pCur.getX()) == 1 && Math.abs(pNew.getY() - pCur.getY()) == 2)
						|| (Math.abs(pNew.getX() - pCur.getX()) == 2 && Math.abs(pNew.getY() - pCur.getY()) == 1))
					legit = true;
			}
			if (name == 'r' || name == 'R') {
				if (checkCollision(name, pCur, pNew) && checkVertHoriMove(pCur, pNew))
					legit = true;
			}
			if (name == 'p' || name == 'P') {
				int pnx = pNew.getX();
				int pny = pNew.getY();
				int pcx = pCur.getX();
				int pcy = pCur.getY();

				if (name == 'p') {
					if (checkCollision(name, pCur, pNew) && pcx < pnx && (pnx - pcx) == 1 && pny == pcy)
						legit = true;
					else if (checkCollision(name, pCur, pNew) == false && pcx < pnx && (pnx - pcx) == 1
							&& (pny - pcy) == 1)
						legit = true;

					// } else if (name == 'P') {
					// if (checkCollision(name, pNew) && pcx > pnx && (pcx -
					// pnx) == 1 && pcy == pny)
					// legit = true;
					// else if (checkCollision(name, pNew) == false && pcx > pnx
					// && (pcx - pnx) == 1 && (pcy - pny) == 1)
					// legit = true;
					// }
					//
					// if (checkCollision(name, pNew) && pcx < pnx && (pnx -
					// pcx) == 1 && (pny - pcy) == 1)
					// legit = true;
					// else if (checkCollision(name, pNew) == false && pcx < pnx
					// && (pnx - pcx) == 1 && pny == pcy)
					// legit = true;

				} else if (name == 'P') {
					if (checkCollision(name, pCur, pNew) && pcx > pnx && (pcx - pnx) == 1 && (pcy - pny) == 1)
						legit = true;
					else if (checkCollision(name, pCur, pNew) == false && pcx > pnx && (pcx - pnx) == 1 && pcy == pny)
						legit = true;
				}

			}

		}

		return legit;

	}

	public boolean checkCollision(char name, Square pCur, Square pNew) {

		int pnx = pNew.getX();
		int pny = pNew.getY();
		int pcx = pCur.getX();
		int pcy = pCur.getY();
		boolean pass = true;
		boolean enemyTarget = checkEnemy(name, pNew);

		if (enemyTarget || boardArray[pnx][pny] == '.') {
			// springer
			if (name == 'n' || name == 'N')
				return true;

			// geradeaus laufen
			else if (pcx == pnx && pcy < pny) {

				for (int i = 1; i < Math.abs(pcy - pny); i++) {
					char p = boardArray[pcx][pcy + i];
					if (p != '.')
						pass = false;
				}
			} else if (pcx == pnx && pcy > pny) {

				for (int i = 1; i < Math.abs(pcy - pny); i++) {
					char p = boardArray[pcx][pcy - i];
					if (p != '.')
						pass = false;
				}
			} else if (pcy == pny && pcx < pnx) {
				for (int i = 1; i < Math.abs(pcx - pnx); i++) {
					int p = boardArray[pcx + i][pcy];
					if (p != '.')
						pass = false;
				}
			} else if (pcy == pny && pcx > pnx) {
				for (int i = 1; i < Math.abs(pcx - pnx); i++) {
					int p = boardArray[pcx - i][pcy];
					if (p != '.')
						pass = false;
				}

				// schräg laufen
			} else if (Math.abs(pcx - pnx) == (Math.abs(pcy - pny)) && pNew.equals(pCur) == false) {

				if (pcy < pny && pcx < pnx) {
					for (int i = 1; i < Math.abs(pcx - pnx); i++) {
						int p = boardArray[pcx + i][pcy + i];
						if (p != '.')
							pass = false;
					}
				} else if (pcy > pny && pcx > pnx) {
					for (int i = 1; i < Math.abs(pcx - pnx); i++) {
						int p = boardArray[pcx - i][pcy - i];
						if (p != '.')
							pass = false;
					}
				} else if (pcy > pny && pcx < pnx) {
					for (int i = 1; i < Math.abs(pcx - pnx); i++) {
						int p = boardArray[pcx + i][pcy - i];
						if (p != '.')
							pass = false;
					}
				} else if (pcy < pny && pcx > pnx) {
					for (int i = 1; i < Math.abs(pcx - pnx); i++) {
						int p = boardArray[pcx - i][pcy + i];
						if (p != '.')
							pass = false;
					}
				}
			} else
				pass = false;
		} else
			pass = false;
		return pass;
	}
	public boolean checkEnemy(char name, Square pNew) {
		char enemy = boardArray[pNew.getX()][pNew.getY()];
		if ((name >= 97 && name <= 122) && (enemy >= 65 && enemy <= 90))
			return true;
		else if ((enemy >= 97 && enemy <= 122) && (name >= 65 && name <= 90))
			return true;
		else
			return false;
	}

	public boolean checkDiagonalMove(Square p1, Square p2) {
		if (Math.abs(p1.getX() - p2.getX()) == Math.abs(p1.getY() - p2.getY()))
			return true;
		else
			return false;
	}

	public boolean checkVertHoriMove(Square p1, Square p2) {
		if ((p1.getX() == p2.getX() && p1.getY() != p2.getY()) || (p1.getX() != p2.getX() && p1.getY() == p2.getY()))
			return true;
		else
			return false;
	}
	
	
	//CASS------------------------------------------
	
	
	
	
	
	public ArrayList<Move> allTheSmallMoves(){
		ArrayList<Move> thingy = new ArrayList<>();
		for (int i = 0; i < boardArray.length; i++) {
			for (int j = 0; j < boardArray[i].length; j++) {
				if(boardArray[i][j]!='.'){
					for (int k = 0; k < boardArray.length; k++) {
						for (int l = 0; l < boardArray[k].length; l++) {
							if(checkLegalMove(new Square(i, j),new Square(k, l))) {
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
