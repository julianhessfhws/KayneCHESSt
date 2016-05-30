package objects;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Board {
	figures [] [] field; 
	int counterB, counterW;
	
	public Board() {
		this.field = new figures[5][6];
		this.counterB = this.counterW = 10;
	}

	public boolean isfree(int positionX, int positionY){
		return (field[positionX][positionY]==null);
	}
	/*
	public Board() {
		field[0][0]=new King(0, 0, false);
		field[4][5]=new King(4, 5, true);
	}
	*/
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
	public static void outgoingFiles() throws IOException{
		BufferedWriter writer = null;
		try
		{
		    writer = new BufferedWriter( new FileWriter( "C:\\here\\out.txt"));
		    writer.write( incomingFiles());

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
	public static void main(String[] args) throws IOException {
		System.out.println(incomingFiles());
		outgoingFiles();
	}
}
