package objects;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Board {
	
	int roundcount;
	char[][] boardArray;
	
	public Board(int roundcount, char[][] boardArray) {
		
		this.roundcount = roundcount;
		this.boardArray = boardArray;
	}
	public Board() {
		this.roundcount=0;
		this.boardArray = new char[6][5];
		/*int[] figs = {107, 113, 98, 110, 114};
		for (int i = 0; i < boardArray.length-1; i++) {
			for (int j = 0; j < boardArray[i].length-1; j++) {
				if (i == 0) boardArray[i][j] = (char)(figs[j]);
				if (i == 1) boardArray[i][j] = (char)112;
				if (i > 1 && i < 4) boardArray[i][j] = (char)46;
				if (i == 4) boardArray[i][j] = (char)(80);
				if (i == 5) boardArray[i][j] = (char)(figs[4-j]-32);
			}
		}*/
		char[] c= {'k','q','b','n','r'}, C = {'R','N','B','Q','K'};
		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < boardArray[0].length; j++) {
				boardArray[i][j]=c[j];
			}
		}
		for (int i = 5; i < 6; i++) {
			for (int j = 0; j < boardArray[0].length; j++) {
				boardArray[i][j]=C[j];
			}
		}
		for (int i = 2; i < 4; i++) {
			for (int j = 0; j < boardArray[0].length; j++) {
				boardArray[i][j]='.';
			}
		}
		for (int i = 1; i < 2; i++) {
			for (int j = 0; j < boardArray[0].length; j++) {
				boardArray[i][j]='p';
			}
		}
		for (int i = 4; i < 5; i++) {
			for (int j = 0; j < boardArray[0].length; j++) {
				boardArray[i][j]='P';
			}
		}
	}
	
	public boolean whoPlays(){
		if(roundcount%2==1)
			return true;
		else
			return false;
	}
	
	private void printBoardComplete()
	{
		String s = "";
		int i,j;
		
		for(i = 0; i < boardArray.length; i++)
		{
			for (j = 0; j < boardArray[0].length; j++)
			{
				s = (s + boardArray[i][j] + " ");
			}
			System.out.println(s);
			s = "";
		}
	}
	private String boardToString(){
		String str="";
		for (int i = 0; i < boardArray.length; i++) {
			for (int j = 0; j < boardArray[i].length; j++) {
				str+=boardArray[i][j];
			}
			
		}
		return str;
	}
	public static String incomingFiles() throws IOException{
		String end ="";
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
	public static void outgoingFiles(String toWrite) throws IOException{
		BufferedWriter writer = null;
		try
		{
		    writer = new BufferedWriter( new FileWriter( "C:\\here\\out.txt"));
		    writer.write(toWrite);

		}
		catch ( IOException e)
		{
		}
		finally
		{
		    try
		    {
		        if ( writer != null)
		        writer.close( );
		    }
		    catch ( IOException e)
		    {
		    }
		}
	}
	public void showStats(){
		char c;
		
		if(whoPlays())
			c='W';
		else
			c='B';
		
		System.out.println(""+roundcount+"   "+c+"\n");
		printBoardComplete();
	}
	
	public static void main(String[] args) throws IOException {
		Board b = new Board();
		b.showStats();
	}
}
