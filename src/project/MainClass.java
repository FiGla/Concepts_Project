package project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

class MainClass {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		JavaToHaskel_Convertor JtoH = new JavaToHaskel_Convertor();
		HaskelToJava_Convertor HtoJ = new HaskelToJava_Convertor();
		JavaToPython JtoP = new JavaToPython();
		PythonToJava PtoJ = new PythonToJava();
		
		FileReader  file = new FileReader ("Hask.txt");		
//		FileReader  file = new FileReader ("Java.txt");
//		FileReader  file = new FileReader ("Python.txt");

		
		int choose;
		System.out.println("1-From Java to Haskell \n2-From Haskell to Java\n"
				+ "3-From Java to Python\n4-From Python to Java");
		Scanner in = new Scanner(System.in);
		choose = in.nextInt();
//    	System.out.println(choose);

		BufferedReader reader = new BufferedReader(file);
		String line;

        while ((line = reader.readLine()) != null){
        	if (line.equals("Haskell") && choose == 2)
        		HtoJ.converter(reader);
        	if(line.equals("java") && choose == 1)
        		JtoH.converter(reader);
        	if(line.equals("java") && choose == 3)
        		JtoP.converter(reader);
        	if(line.equals("Python") && choose == 4)
        		PtoJ.converter(reader);
        }
        reader.close();
		

	}

	

}
