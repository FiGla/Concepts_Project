package project;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class JavaToHaskel_Convertor {

	JavaToHaskel_Convertor() {
		// TODO Auto-generated method stub
	}
	
	public void converter (BufferedReader content) throws IOException
	{
		String line= null ;
		PrintStream  writer = new PrintStream (new File("OutHaskell.txt"));
		 writer.println("module Main where");
		boolean mainFound = false;
		 
		 while ((line = content.readLine()) != null){
			 if ( line.contains("main"))
				 mainFound = true;

			if(line.contains("System.out") && mainFound ){
				int firstIndex = line.indexOf('"');
				int lastIndex = line.indexOf(")");
				 String print = line.substring(firstIndex,lastIndex);
				 writer.println("main = putStrLn" + print );
			 }
		 }
		 writer.close();

	}
	
}
