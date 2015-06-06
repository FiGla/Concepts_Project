package project;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class JavaToPython {
	
	JavaToPython(){
	}
        
        public void checkWhat (String line , PrintStream writer){
            if(line.contains("System")) {
                String name = line.substring(line.indexOf("(")+1,line.indexOf(")"));
                writer.println("print " + name);
            }
            else if (line.contains("for")){
                
                writer.println("for ");
                
            }
            else if (line.contains("if")){
                String inside = line.substring(line.indexOf("(")+1,line.length()-2);
                writer.println("if" + inside + ":");
            }
            else if (line.contains("void")){
                String name = line.substring(line.indexOf("d ") +1, line.indexOf("{"));
                writer.println("def " + name + ":");
            }
          //  else if () 
        }
	
	public void converter (BufferedReader content) throws IOException{
		String line= null ;
		PrintStream  writer = new PrintStream (new File("OutPython.txt"));
		boolean mainFound = false;
		 
		 while ((line = content.readLine()) != null){
                     checkWhat(line,writer);
		 }
		 writer.close();
	}
	
}
