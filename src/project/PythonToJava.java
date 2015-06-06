package project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

class pair {
    String name;
    String type;
}
public class PythonToJava {
        int tabs;
        ArrayList< pair> variables;
        int keep ;
        
	PythonToJava(){
             tabs= 0;
             variables = new ArrayList();
             keep =0;
	}
        
        public void print(String line,PrintStream  writer){
            int index = line.indexOf('"');
            String print = line.substring(index);
	    writer.println("System.out.println( "+ print + " );");
            
        }
       public void LOOP(String line,PrintStream  writer){
           String str = line.substring(4,line.indexOf(' ',5));
            String str2="";
           if(line.contains("len"))
                str2 = line.substring(line.indexOf('(')+1,line.indexOf(')'));
            
           String print = str2 +".size()";
	    writer.println("for(int "+ str + "=0 ;" + str +"<"+ print +"; ++i ){");            
        }
       
       public void VarOrCall(String line , PrintStream writer){             
           if (!line.contains("=")){
               writer.println(line);
               return;
           }
           //if variable;
               String name = line.substring(tabs,line.indexOf("="));
               String value = line.substring(line.indexOf("=")+1);
               
            for(int i=0 ; i < variables.size();++i) {
               if (variables.get(i).name.equals(name)){
                   writer.println(line);
                   return;}
            }
               pair temp = new pair();
               temp.name = name;
               //check type of variable
               if(value.contains("\"")){
                   writer.println("String " + name + "=" + value +";");
                   temp.type = "String"; 
               }
               else if (value.contains(".")){
                   writer.println("double " + name + "=" + value+";");
                   temp.type ="double";
               }
               else {
                   writer.println("int " + name + "=" +value+";");
                   temp.type = "int";
               }
            //   System.out.println(temp.name + " " + temp.type );
               variables.add(temp);

           }
           
       public boolean IsInteger (String s){
           try{
            Integer.parseInt(s);
           }
           catch(NumberFormatException exception){
               return false;
           }
         return true;
       }
       public boolean IsDouble (String s){
           try{
            Double.parseDouble(s);
           }
           catch(NumberFormatException exception){
               return false;
           }
         return true;
       }
       
       public String returnType(String newline){
           String afterReturn = newline.substring(newline.lastIndexOf("n")+2);
         //  System.out.println(afterReturn);
           if (afterReturn.contains("*")|| afterReturn.contains("/")  || afterReturn.contains("-"))
                return "int";
           else if (afterReturn.contains("+")){
               if (afterReturn.contains("\"")) return "String";
               else return "int";
           }
           
           for (int i=0 ; i<variables.size();++i){
               System.out.println(variables.get(i).name);
               if (variables.get(i).name.equals(afterReturn))
                   return variables.get(i).type;
           }
           
           if(IsInteger(newline)) return "int";
           if(IsDouble(newline)) return "double";
           
          return "void"; 
       } 
       
       public void Fun(String line , PrintStream writer) throws FileNotFoundException, IOException{ 
           FileReader  file = new FileReader ("Python.txt");
           BufferedReader readFun = new BufferedReader(file);
         //  System.out.println(keep);
           for (int i=0 ; i < keep;++i)
                readFun.readLine();
           String name = line.substring(3,line.length()-1);
           String newline ;
           //int countfunc =0;
           while(!(newline = readFun.readLine()).contains("return "));//{
             //  ++countfunc;
           //}
           
           String type = returnType(newline);
           
           writer.println(type + " " + name + "{ ");
           readFun.close();
       }
       
        public void If(String line,PrintStream  writer) throws FileNotFoundException, IOException{
           
            String name ,op ="", last;
            ArrayList<String> lis= new ArrayList();
            lis.add("==");//{"==","<=",">=",">" ,"<"};
            lis.add("<=");
            lis.add(">=");
            lis.add(">");
            lis.add("<");
            
            for(int i= 0 ; i < lis.size();++i){
                if(line.contains(lis.get(i))){
                    op = lis.get(i);
                    break;
                }
            }
            name = line.substring(3, line.indexOf(op));
            last = line.substring(line.indexOf(op)+1,line.length());
            writer.println("if(" + name + " " + op +  " " + last +"){");
            
        }
        public void elIf(String line,PrintStream  writer) throws FileNotFoundException, IOException{
            String name ,op ="", last;
            ArrayList<String> lis= new ArrayList();
            lis.add("==");//{"==","<=",">=",">" ,"<"};
            lis.add("<=");
            lis.add(">=");
            lis.add(">");
            lis.add("<");
            
            for(int i= 0 ; i < lis.size();++i){
                if(line.contains(lis.get(i))){
                    op = lis.get(i);
                    break;
                }
            }
            name = line.substring(5, line.indexOf(op));
            last = line.substring(line.indexOf(op)+2,line.length()-1);
            writer.println("else if(" + name + " " + op +  " " + last +"){");
            
            
        }
       

        public void WhatToDO(String line,PrintStream  writer) throws FileNotFoundException, IOException{
              if(line.contains("else "))// run if code
                    writer.println("else {" );
              else if (line.contains("elif "))
                  elIf(line,writer);
              else if (line.contains("if "))
                  If(line,writer);
              
              else if (line.contains("for ")) // run loop
                  LOOP(line,writer);
              else if (line.contains("print "))//run print
                  print(line,writer);
              else if (line.contains("return "))//run return code
                  writer.println(line+ ";");

              else if (line.contains("def "))
                      Fun(line,writer);
                      // run check on fun or var // call or declare 
              else   
                VarOrCall(line,writer);
        }

	public void converter (BufferedReader content) throws IOException
	{
		String line ;
		PrintStream  writer = new PrintStream  (new File("OutJavaPython.txt"));
		 while ((line = content.readLine()) != null){
                     
                     if(line.length()!= 0)
                     {                     
                        int count =0;
                        for (int i=0 ; line.indexOf("\t", i)!= -1;++i)
                                  ++count;
                       
                        if (count < tabs){
                            for (int j=count ; j<tabs;++j)
                             writer.println("}");
                        }
                        tabs = count;

                        WhatToDO(line,writer);

                     }
                   ++keep;
		 }
		 
		 writer.close();

	}

}
