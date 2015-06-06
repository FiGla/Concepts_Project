package project;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class HaskelToJava_Convertor {

	HaskelToJava_Convertor() {
		// TODO Auto-generated method stub
	}

	private boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException exception) {
			return false;
		}
		return true;
	}

	private boolean isDouble(String s) {
		try {
			Double.parseDouble(s);
		} catch (NumberFormatException exception) {
			return false;
		}
		return true;
	}

	public void converter(BufferedReader content) throws IOException {
		// System.out.print("sdas");

		String line;
		
		PrintStream writer = new PrintStream(new File("OutJavaHaskel.txt"));
		
		writer.println("class MainClass {");
		
		writer.println("public static void main(String[] args){");

		while ((line = content.readLine()) != null) {
			line = line.trim();

			// print statment
			if (line.startsWith("main =") && line.contains("putStrLn")) {
		
				int index = line.indexOf('"');
				
				String print = line.substring(index);
				
				writer.println("System.out.println( " + print + " );");
			}

			
			
			// intialization
			else if (line.startsWith("let ")) {

				String[] splitted = line.replaceFirst("let ", "").split("=");
				
				int index = line.indexOf("=");
				
				
				// check type
				
				String varName = splitted[0].trim();
				
				String value = splitted[1].trim();

				if (value.charAt(0) == '\"') {
					// it is a string
					writer.println("String "+varName+"="+value+";");
					
				} else if (isInteger(value)) {
					// it is an integer
					writer.println("int "+varName+"="+value+";");
					
				} else if (isDouble(value)) {
					// it is a double
					writer.println("Double "+varName+"="+value+";");
				}
			}
			
			
			//if condition
			else if(line.startsWith("if"))
			{
				line = line.replaceAll("if", "");
				
				String[] ifLine = line.split("then");
				
				String condition = ifLine[0];
				
				String stmt = ifLine[1];
				
				String finalStmt = "if"+"("+condition+")"+"{"+stmt+";"+"}";
			}
			
			
			//else condition
			else if(line.startsWith("else"))
			{
				line = line.replaceAll("else", "");
				
				
				String finalStmt = "else"+"{"+"return "+line+";"+"}";
				writer.println(finalStmt);
			}

			
			
			// fun 
			else if(!line.startsWith("module"))
			{
				String finalStmt="";
				
				System.out.println(line);
				
				String[] split = line.split("=");
				String[] part1 = split[0].split(" ");
				String funName = part1[0];
				
				System.out.println("a"+split[0]+ " ");
			    
				String par = part1[1];
				String body = split[1];
			
			    System.out.println(body);
			    if(body.startsWith("if"))
					{
					body=body.replaceFirst("if", " ");
				
					String[] ifLine = body.split("then");
					
					String condition = ifLine[0];
					
					String stmt = ifLine[1];
					
					finalStmt = "if"+"("+condition+")"+"{"+"return" +stmt;
					
					body = finalStmt;
					}
				
				writer.println("void "+funName+"("+ "Object "+par+")"+"{"+body+";"+"}");
			}

		}

		writer.println("}\n}");
		writer.close();

	}
}
/*
package Cons;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class HaskelToJava_Convertor {

	HaskelToJava_Convertor() {
		// TODO Auto-generated method stub
	}

	private boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException exception) {
			return false;
		}
		return true;
	}

	private boolean isDouble(String s) {
		try {
			Double.parseDouble(s);
		} catch (NumberFormatException exception) {
			return false;
		}
		return true;
	}

	public void converter(BufferedReader content) throws IOException {
		// System.out.print("sdas");

		String line;
		
		PrintStream writer = new PrintStream(new File("OutJavaHaskel.txt"));
		
		writer.println("class MainClass {");
		
		writer.println("public static void main(String[] args){");

		while ((line = content.readLine()) != null) {
			line = line.trim();

			// print statment
			if (line.startsWith("main =") && line.contains("putStrLn")) {
		
				int index = line.indexOf('"');
				
				String print = line.substring(index);
				
				writer.println("System.out.println( " + print + " );");
			}

			
			
			// intialization
			else if (line.startsWith("let ")) {

				String[] splitted = line.replaceFirst("let ", "").split("=");
				
				int index = line.indexOf("=");
				
				
				// check type
				
				String varName = splitted[0].trim();
				
				String value = splitted[1].trim();

				if (value.charAt(0) == '\"') {
					// it is a string
					writer.println("String "+varName+"="+value+";");
					
				} else if (isInteger(value)) {
					// it is an integer
					writer.println("int "+varName+"="+value+";");
					
				} else if (isDouble(value)) {
					// it is a double
					writer.println("Double "+varName+"="+value+";");
				}
			}
			
			//if condition
			else if(line.startsWith("if"))
			{
				line = line.replaceAll("if", "");
				
				String[] ifLine = line.split("then");
				
				String condition = ifLine[0];
				
				String stmt = ifLine[1];
				
				String finalStmt = "if"+"("+condition+")"+"{"+stmt+";"+"}";
			}
			
			
			//else condition
			else if(line.startsWith("else"))
			{
				line = line.replaceAll("if", "");
				
				String[] ifLine = line.split("then");
				
				String condition = ifLine[0];
				
				String stmt = ifLine[1];
				
				String finalStmt = "if"+"("+condition+")"+"{"+stmt+";"+"}";
			}

			
			
			// fun 
			else if(!line.startsWith("module"))
			{
				String finalStmt="";
				
				System.out.println(line);
				
				String[] split = line.split("=");
				
				String[] part1 = split[0].split(" ");
				
				String funName = part1[0];
				
				System.out.println("a"+split[0]+ " ");
			    
				String par = part1[1];
				
			    String body = split[1];
			
			    
			    if(body.startsWith("if"))
					{
					body=body.replaceFirst("if", " ");
				
					String[] ifLine = body.split("then");
					
					String condition = ifLine[0];
					
					String stmt = ifLine[1];
					
					finalStmt = "if"+"("+condition+")"+"{"+"return" +stmt;
					
					}
				
				writer.println("void "+funName+"("+ "Object "+par+")"+"{"+finalStmt+";"+"}");
			}

		}

		writer.println("}\n}");
		writer.close();
package Cons;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class HaskelToJava_Convertor {

	HaskelToJava_Convertor() {
		// TODO Auto-generated method stub
	}

	private boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException exception) {
			return false;
		}
		return true;
	}

	private boolean isDouble(String s) {
		try {
			Double.parseDouble(s);
		} catch (NumberFormatException exception) {
			return false;
		}
		return true;
	}

	public void converter(BufferedReader content) throws IOException {
		// System.out.print("sdas");

		String line;
		
		PrintStream writer = new PrintStream(new File("OutJavaHaskel.txt"));
		
		writer.println("class MainClass {");
		
		writer.println("public static void main(String[] args){");

		while ((line = content.readLine()) != null) {
			line = line.trim();

			// print statment
			if (line.startsWith("main =") && line.contains("putStrLn")) {
		
				int index = line.indexOf('"');
				
				String print = line.substring(index);
				
				writer.println("System.out.println( " + print + " );");
			}

			
			
			// intialization
			else if (line.startsWith("let ")) {

				String[] splitted = line.replaceFirst("let ", "").split("=");
				
				int index = line.indexOf("=");
				
				
				// check type
				
				String varName = splitted[0].trim();
				
				String value = splitted[1].trim();

				if (value.charAt(0) == '\"') {
					// it is a string
					writer.println("String "+varName+"="+value+";");
					
				} else if (isInteger(value)) {
					// it is an integer
					writer.println("int "+varName+"="+value+";");
					
				} else if (isDouble(value)) {
					// it is a double
					writer.println("Double "+varName+"="+value+";");
				}
			}
			
			//if condition
			else if(line.startsWith("if"))
			{
				line = line.replaceAll("if", "");
				
				String[] ifLine = line.split("then");
				
				String condition = ifLine[0];
				
				String stmt = ifLine[1];
				
				String finalStmt = "if"+"("+condition+")"+"{"+stmt+";"+"}";
			}
			
			
			//else condition
			else if(line.startsWith("else"))
			{
				line = line.replaceAll("if", "");
				
				String[] ifLine = line.split("then");
				
				String condition = ifLine[0];
				
				String stmt = ifLine[1];
				
				String finalStmt = "if"+"("+condition+")"+"{"+stmt+";"+"}";
			}

			
			
			// fun 
			else if(!line.startsWith("module"))
			{
				String finalStmt="";
				
				System.out.println(line);
				
				String[] split = line.split("=");
				
				String[] part1 = split[0].split(" ");
				
				String funName = part1[0];
				
				System.out.println("a"+split[0]+ " ");
			    
				String par = part1[1];
				
			    String body = split[1];
			
			    
			    if(body.startsWith("if"))
					{
					body=body.replaceFirst("if", " ");
				
					String[] ifLine = body.split("then");
					
					String condition = ifLine[0];
					
					String stmt = ifLine[1];
					
					finalStmt = "if"+"("+condition+")"+"{"+"return" +stmt;
					
					}
				
				writer.println("void "+funName+"("+ "Object "+par+")"+"{"+finalStmt+";"+"}");
			}

		}

		writer.println("}\n}");
		writer.close();

	}
}

	}
}
*/