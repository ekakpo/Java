/**
 *
 * Author: Essenam Kakpo
 * Class: CSC 3410
 * Assignment 3
 * Instructor: Jaman Bhola
 */

// DOCUMENTATION
/*
 1.	Explain the purpose of the program as detailed as possible.
This program, test_Array_list  prompts the user for an input file name 
* to read from. The Input file will consist of records made up of first 
* name, last name and an account balance of individuals and store the 
* individuals in an ArrayList. Each line in the file would be made up of 
* one record starting with the last name, first name and account balance. 
* Each token is separated by ONE space. The individuals’ names will be 
* inserted in sorted order into the list. The entries will be sorted by 
* last name and then first name. Then the sorted list will be output to 
* the screen and in an output file “output.txt”.
* 
* 
2.	Develop a solution for the problem and mention algorithms to be used.
The solution to the problem is the program itself. It will use algorithms 
* to determine if the file specified already has the “.txt” extension and 
* append it if not, to read from and write to files, and close them, to 
* render a print version of each Customer object and of the ArrayList, and 
* to determine where to insert a new customer in the list with respect to 
* alphabetical order .
* 
* 
3.	List data structures to be used in solution.
* The ArrayList Data structure is used in this program.
* 
* 
4.	Give a description of how to use the program and expected input/output
* When run, the program will prompt the user for the name of the file to 
* be processed. This has to be to be a valid filename otherwise, the user 
* will be notified that file does not exist and to try again. When the 
* correct file name is entered, the program processes the data in the file, 
* retrieving individual records and putting them in alphabetical order. 
* The output will be a String as an alphabetically ordered list of the 
* records in file and their total number  displayed on the screen, as well 
* as in an output file named “output.txt”.
* 
* 
5.	Explain the purpose of each class you develop in the program.
* The class Customer contains definitions for customer objects that will 
* be used to record each entry. Each Customer object will be made up of 
* a last name, a first name and a balance. The class also contains methods 
* that permits to determine where to put a new customer in an alphabetically 
* ordered ArrayList, given the list and a Customer object as parameters 
* (method insertionIndex); to  make up a String representation of any 
* Customer object (method toString); to determine if the file specified 
* already has the “.txt” extension and append it if not (method AppendTxt); 
* to printout the contents of an ArrayList of Customer objects as a 
* String (method printList)
 */


package testarraylist;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Kheops
 */
public class test_Array_list {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
                
        ArrayList <Customer> myList = new ArrayList ();
        Scanner scan = new Scanner(System.in); 
        
        System.out.println("Enter the file name: ");
        String fileName = scan.nextLine();
        
        
        File inputFile = new File(Customer.AppendTxt(fileName));
        String input;
        Scanner fileInput = null;
        try
        {
            fileInput = new Scanner(inputFile);
            
            while(fileInput.hasNext())
            {
                //Taking next line as input to be processed
                input = fileInput.nextLine();
                if (input != null)
                {
                    String[] info = input.split("\\s");
                    
                    //Creating a new object from the info on this input line
                    Customer cus = new Customer(info[0],info[1],Double.parseDouble(info[2]));
                    int indice = Customer.insertionIndex(myList,cus);
                    
                    //What to do when the indice is in-between
                    if(indice < 0)
                    {    
                         myList.add(cus);
                    }
                    else myList.add(indice, cus);
                }
                else break;
            }            
            //Printing results on screen
            System.out.println(Customer.printList(myList));
            System.out.printf("There are %d customers in the database.\n", myList.size());
            
        } catch (FileNotFoundException e)
         {
              System.out.println("The file was not found. " + 
              "Check its spelling, please."); //File not found handled
              System.exit(1);
          }
        finally
        {
            fileInput.close();
        }
        
        PrintWriter output = null;
        try
        {
            output = new PrintWriter(new FileWriter("output.txt"));           
            for(int i =0; i< myList.size(); i++)
            {
                //Wrinting to the file
                output.println(String.format("%s\n",(String)(myList.get(i)).toString()));
            }
            
            output.printf("There are %d customers in the database.\n", myList.size());
            
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

class Customer
{
    String firstName, lastName;
    double balance;
    Customer(String last, String first, double bal)
    {        
        lastName = last;
        firstName = first;        
        balance = bal;
    }
    
    public String toString()
    {
        return String.format("%s %s %2f", lastName, firstName, balance);
    }
    
    static String AppendTxt(String s)
    {
          //We are going to determine if the name input by the user has the
          //extension. If not we append the extension before using it the
          //file name
        boolean present = false;
          for(String str : s.split(""))
          {
              if (str.equalsIgnoreCase(".")) present = true;
          }
          
          if (!present)
              s+= ".txt";
          
        return s;
    }
    
    //Used to determine where to put the new object in the list 
    static int insertionIndex(ArrayList <Customer> list, Customer cnew)
    {
        //Creating a String for comparison from passed object
        String newName = String.format("%s %s", cnew.lastName, cnew.firstName);
        int index = 0;
        
        //Apply for non-empty lists. If the list is empty 0 is returned
        if (!list.isEmpty())               
        {    
            for(; index < list.size(); index++)
            {
                //Retrieving object at index for comparison
                Customer c = list.get(index);
                String current = String.format("%s %s", c.lastName, c.firstName); 
                //The result of the comparison is stored here
                int comparisonResult = newName.compareToIgnoreCase(current);
                //When we find the first position suitable for the new
                //object we break. Otherwise we continue
                if(comparisonResult <= 0)
                    break;
            } 
        }
        
        return index;
    }     
    
    static String printList(ArrayList <Customer> list)
    {
        String outputString = "";
                
        //Increments the outputString with the new line of record at each
        //iteration
        for(int i =0; i< list.size(); i++)
        {
            outputString += String.format("%s\n",(String)(list.get(i)).toString());
        }
        
        return outputString;
    }   
}