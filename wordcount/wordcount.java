/**
 *
 * Author: Essenam Kakpo
 * Class: CSC 3410
 * Assignment 2
 * Instructor: Jaman Bhola
 */

// DOCUMENTATION
/*
 1.	Explain the purpose of the program as detail as possible.
This program, wordcount.java, reads from a text file specified by the user 
 and outputs the number of lines, sentences, words, vowels, alphanumeric 
 characters and punctuations present in the file, both on screen and in the
 creation of a new file “output.txt” containing the results.
 * 
 * 
2.	Develop a solution for the problem and mention algorithms to be used 
The solution to the problem is the program itself. It will use algorithms to
 determine if the file specified already has the “.txt” extension and append 
 it if not, to read from and write to files, and close them.
 * 
 * 
3.	List data structures to be used in solution.
The Array data structure was occasionally used, although it was an auxiliary 
 tool, not the turning point of our program.
 * 
 * 
4.	Give a description of how to use the program and expected input/output
When run, the program will ask the user for the name of the file to open. 
The input should be a string representing the name of the file, with or 
 without the “.txt” extension.  
The output will be a console print of a set of 6 lines each indicating the 
 number of words, lines, alphanumeric characters, sentences, vowels or 
 punctuations present in the file, respectively, as well as a text file 
 “output.txt” containing the same text as the one output to screen.
In case the file specified by the user is empty, the program will print a 
 message saying that “the input file is empty” and then terminate the program.
 * 
 * 
5.	Explain the purpose of each class you develop in the program.
Class wordcount.java has the main method that contains the logic behind 
 the program and the center of execution of the program.
 */

import java.util.Scanner;
import java.io.*;

public class wordcount
{
    public static void main(String args[])
    {        
        //The following variables will store the different values we need
        //to keep track of
        int numOfLines, numOfWords, numOfAlphaNumerics, numOfSentences, 
            numOfVowels, numOfPunctuations;
        
        numOfLines = numOfWords = numOfAlphaNumerics = numOfSentences = 
        numOfVowels = numOfPunctuations = 0;
        
        //Prompting the user for the file name
        System.out.print("Enter your file name: ");
        Scanner scan = new Scanner(System.in);
        String fileName = scan.nextLine();       
        Scanner inputFile = new Scanner(fileName);
                 
          String input;
          Scanner fileinput = null;
          
          //We are going to determine if the name input by the user has the
          //extension. If not we append the extension before using it the
          //file name
          boolean present = false;
          for(String str : fileName.split(""))
          {
              if (str.equalsIgnoreCase(".")) present = true;
          }
          
          if (!present)
              fileName+= ".txt";
          
          File inFile = new File(fileName);
          
          try
          {
              fileinput = new Scanner(inFile);
              
              //This loop will execute as long as there is input to be
              //processed in the file
              while(fileinput.hasNext())
              {
                  input = fileinput.nextLine(); int i=0;
                  try
                  {
                  if (String.valueOf(input.charAt(0)).matches
                          ("\\p{Alpha}|\\p{Digit}"))
                      i += 1; //We try to account for words beginning a line  
                              //and also beginning a sentence
                  
                  }catch (StringIndexOutOfBoundsException e)
                  {
                      i -= 1;
                  }
                  
                  //Here we split the current line around some delimiters
                  //specified by regex expressions and count the pieces we
                  //get, to estimate the number of delimiters found.
                  String[] v = input.split("[aeiouAEIOU]", -2); //vowels
                  String[] l = input.split("$",-2); //lines
                  String[] s = input.split("\\. [A-Z|#] ",-2); //sentences
                  String[] p = input.split("\\p{Punct}",-2); //punctuations
                  String[] w = input.split("\\p{Blank}",-2); //words
                  String[] a = input.split("\\p{Alpha}|\\p{Digit}",-2); 
                  //alphanumeric characters

                  //Here we update the variables we need to keep track of
                  numOfLines++; 
                  numOfWords+= w.length-1+i; 
                  numOfAlphaNumerics += a.length-1; 
                  numOfSentences+= s.length; 
                  numOfVowels+= v.length-1; 
                  numOfPunctuations+= p.length-1;
              }              
                           
          }   catch(FileNotFoundException e)
              {
                  System.out.println("The file was not found. " + 
                  "Check its spelling, please."); //File not found handled
                  System.exit(1);
              }
          
          finally
          {
              fileinput.close();
              //We display a specific message when the input file is empty
              if(numOfWords == 0)
              {
                  System.out.println("The input file is empty");
                  System.exit(0);
              }
              
          }
          
        //We format the string to be output to the file and on screen  
        String answer1 = String.format("The amount of words in the file is: "
                + "%d\n", numOfWords);
        String answer2 = String.format("The amount of lines in the file is: "
                + "%d\n", numOfLines);
        String answer3 = String.format("The amount of alphanumeric "
                + "characters in the file is: %d\n", numOfAlphaNumerics);
        String answer4 = String.format("The number of sentences in the file"
                + " is: %d\n", numOfSentences);
        String answer5 = String.format("The number of vowels in the file "
                + "is: %d\n", numOfVowels);
        String answer6 = String.format("The amount of punctuation in the"
                + " file is: %d\n", numOfPunctuations);       
              
              
        //We start out put our results
        PrintWriter output = null;
         try
         {
            //output.txt file is created
            output = new PrintWriter(new FileWriter("output.txt"));
            //Each of these lines prints both to the screen and to file.
            System.out.println("1. "+answer1); output.println(answer1);
            System.out.println("2. "+answer2); output.println(answer2);
            System.out.println("3. "+answer3); output.println(answer3);
            System.out.println("4. "+answer4); output.println(answer4);
            System.out.println("5. "+answer5); output.println(answer5);
            System.out.println("6. "+answer6); output.println(answer6);
            
         }catch(IOException e)
         {
             System.out.println(e);
             System.exit(1);
         }
         finally 
         {
            output.close(); 
         }   
    }
}