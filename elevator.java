/**
 *
 * Author: Essenam Kakpo
 * Class: CSC 3410
 * Assignment 1
 * Instructor: Jaman Bhola
 */

// DOCUMENTATION
/*
 1.	Explain the purpose of the program as detailed as possible 
This program named elevator.java simulates the services provided by one 
 elevator in a 12-floor building. Through the use of objects of class Elev, 
 the “elevator” will go up and down, provided with an array of randomly 
 generated floor stops, on its way up as well as on its way down. 
 At all times, it will display its current position, its direction, wait 
 time at floor stops, and whether or not it has reached the last stop on 
 the trip.
Once the elevator is set in a direction it cannot be changed. However if for 
 a trip the first stop requires the elevator to go to the opposite direction 
 to take a passenger first, the elevator will do so first and then set a 
 definitive direction for the trip. In the beginning of the program the 
 elevator is on the 1t floor and can only go up. 8 random stops will be 
 generated for the elevator to stop to on its way up. On its way down, 
 5 random stops will be generated. After one round trip (up + down), 
 the user is left with the choice to rerun the program or terminate it at 
 once.
 * 
 * 
 2.	Develop a solution for the problem and mention algorithms to be used 
The solution to the problem is the program itself. It will use algorithms to 
 reverse the order of elements in an array (method inverseElements), delay 
 execution for a specific duration (method waitASecond), generate the arrays 
 (methods generateUp and generateDown), display the arrays (methods 
 displayUPRRAY, display DOWNARRAY), “travel” while notifying the user of all
 the info asked in the requirements (methods travelUp and travelDown) and 
 loop the program until the user terminates execution (method main).
 * 
 * 
 3.	List data structures to be used in solution. 
The data structure used here is the array.
 * 
 * 
 4.	Give a description of how to use the program and expected I/O 
When the program is run, it will generate floors to stop to automatically as 
 part of its routine. After one trip upward and one trip downward, the user 
 will be asked if he would want to run the program one more time (make 1 
 trip upward and 1 trip downward).
As far as the input, after the execution of the whole routine, to continue, 
 the user would have to press “y” or “Y” if he wanted to continue, or “n” or “N” 
 if he would like to stop the execution of the program at this point, and 
 then validate his choice by pressing enter. This choice, (which should be a
 unique token) is then analyzed as a string and results in either a new 
 execution of the program’s routine, or a termination of the program.
As far as output, the program will display, the UPARRAY, DOWNARRAY, and a 
 record of the current position of the elevator, the different stops it 
 makes, the wait time at floor stops, the direction of the elevator, and 
 will display a notification when the elevator reaches its highest floor 
 stop or lowest floor stop during the execution cycle, depending on whether
 the elevator was bound upward or downward. 
 * 
 * 
 5.	Explain the purpose of each class you develop in the program. 
The class Elev, will have derived from it objects that will simulate 
 elevators  in their behavior. Each instance of class Elev contains 
 attributes that  resemble those you might encounter in a actual elevator, 
 such as:  currentFloor (the current position of the elevator), 
 UPARRAY(the floors  the elevator has been requested to stop to on its 
 way up), DOWNARRAY(the floors the elevator has been requested to stop to 
 on its way down), lastStop(the last stop before the elevator can change 
 its direction). Class Elev also has methods that help operate its objects.
 These methods generate floors to stop to while going up or down, display
 the different stops and the wait time, display the current position of 
 the elevator and update it as the elevator moves.
Class elevator contains the main method with the execution of the program 
 routine, as well as a section that allows the user to choose the rerun 
 the program or terminate it.

 */

package elevator;

import java.util.Random;
import java.util.Arrays;
import java.util.Scanner;

class Elev
{
    Random random = new Random();
    private enum DIRECTION {UPWARD, DOWNWARD};
    private int currentFloor; // This variable will hold the value of
                              // the current floor
    private int[] UPARRAY = new int[8];
    private int[] DOWNARRAY = new int[5];
    
    public Elev()
    {
        currentFloor = 1; // THe default floor is set to 1 in the 
                          // the beginning of the program
    }
        
    //This method will display UPARRAY
    void displayUPARRAY()
    {
        System.out.println();
        System.out.println("UPARRAY: ");
        for(int floor : UPARRAY)
        {
            System.out.printf("|  %d  |" , floor);
        }
        System.out.println();
    }
    
    //This method will display DOWNARRAY
    void displayDOWNARRAY()
    {
        System.out.println();
        System.out.println("DOWNARRAY: ");
        for(int floor : DOWNARRAY)
        {
            System.out.printf("|  %d  |" , floor);
        }
        System.out.println();
    }    
    
    //This method generates different random numbers and stores them 
    //into UPARRAY
    void generateUp()
    {
        int[] floorsGoingUp = new int[8];
        Arrays.fill(floorsGoingUp,0);
        int temp;
        
        //This loop generates a random number between 1 and 12 and if it's
        //not already in one cell of the array stores it in it until we have
        //1 number in each of the 8 cells.
        for(int index = 0; index <= 7;)
        {
            temp = 1 + random.nextInt(12);
            boolean alreadyChosen = false;
            for (int floor : floorsGoingUp)
            {
                if(temp == floor)
                {
                    alreadyChosen = true;
                }
            }

            if(!alreadyChosen)
            {
                floorsGoingUp[index] = temp; index++;
            }
        }
        
        Arrays.sort(floorsGoingUp); //The array is sorted here    
        
        //The sorted array is copied to UPARRAY
        System.arraycopy(floorsGoingUp, 0, UPARRAY, 0, 8); 
        
    }    
    
    //This method generates different random numbers and stores them 
    //into DOWNARRAY
    void generateDown()
    {
        int[] floorsGoingDown = {0,0,0,0,0};
        int temp;
        
        //This loop generates a random number between 1 and 12 and if it's
        //not already in one cell of the array stores it in it until we have
        //1 number in each of the 5 cells
        for(int index = 0; index <= 4;)
        {
            temp = 1 + random.nextInt(12);
            boolean alreadyChosen = false;
            for (int floor : floorsGoingDown)
            {
                if(temp == floor)
                {
                    alreadyChosen = true;
                }
            }

            if(!alreadyChosen)
            {
                floorsGoingDown[index++] = temp;
            }
        }
        
        Arrays.sort(floorsGoingDown); //The array is sorted here       
        inverseElements(floorsGoingDown);  //The order of the elements
                                           //is reversed in the array
        
        //The sorted array is copied to UPARRAY
        System.arraycopy(inverseElements(floorsGoingDown), 0, DOWNARRAY, 0, 5);        
    }
    
    //This methode inverses the order of the elements in an array
    int[] inverseElements(int[] array) 
    {
        int[] tempArray = new int[array.length];
        
        for(int i = 0, j = array.length-1; i <= array.length - 1; i++, j--)
        {
            tempArray[j] = array[i];
        }
        
        return tempArray;
    }
    
    // This method halts the program for 1 second
    void waitASecond()
    {        
        try
        {
            Thread.sleep(1000);
        } catch(InterruptedException e)
          {
               Thread.currentThread().interrupt();
          }
    }
    
    // This method executes the whole routine on travelling up,
    // stopping at appropriate floors, displaying wait time and direction
    // of the elevator
    void travelUp()
    {        
        int bottomFloorStop = UPARRAY[0];
        int lastStop = UPARRAY[7];
        
        for(;currentFloor > bottomFloorStop;)
        {
            System.out.println();
            System.out.printf("Floor %d, GOING DOWN", currentFloor);
            waitASecond();
            waitASecond();
            currentFloor--;
        }
        
        for(int stopIndex = 0; currentFloor <= lastStop; currentFloor++)
        {            
            boolean update = false;
            if (currentFloor == UPARRAY[stopIndex])
            {    
                System.out.println("\n");
                System.out.printf("Stop at Floor %d, waiting 3 seconds. " + 
                        "CLOSING in 3 ", currentFloor);
                waitASecond();
                System.out.print("2 ");
                waitASecond();
                System.out.print("1 ");
                waitASecond();
                update = true;
                System.out.println();
            }
                
            // Display Specific message when we reach the last stop
            if(currentFloor == lastStop)
            {
                System.out.println();
                System.out.printf("Floor %d, awaiting for requests to go " + 
                        "down.\n", currentFloor);
                System.out.println();
            }
               
            else
            {
                System.out.println();
                System.out.printf("Floor %d, GOING UP", currentFloor);
                waitASecond();
                waitASecond();
                stopIndex += update ? 1 : 0;
            }                      
        }
        currentFloor--;
    }
        
    // This method executes the whole routine on travelling down,
    // stopping at appropriate floors, displaying wait time and direction
    // of the elevator
    void travelDown()
    {        
        int upperFloorStop = DOWNARRAY[0];
        int lastStop = DOWNARRAY[4];
        
        for(;currentFloor < upperFloorStop;)
        {
            System.out.println();
            System.out.printf("Floor %d, GOING UP", currentFloor);
            waitASecond();
            waitASecond();
            currentFloor++;
        }
        
        for(int stopIndex = 0; currentFloor >= lastStop; currentFloor--)
        {            
            boolean update = false;
            if (currentFloor == DOWNARRAY[stopIndex])
            {    
                System.out.println("\n");
                System.out.printf("Stop at Floor %d, waiting 3 seconds. " + 
                        "CLOSING in 3 ", currentFloor);
                waitASecond();
                System.out.print("2 ");
                waitASecond();
                System.out.print("1 ");
                waitASecond();
                update = true;
                System.out.println();
            }
                
            // Display Specific message when we reach the last stop
            if(currentFloor == lastStop)
            {
                System.out.println();
                System.out.printf("Floor %d, awaiting for requests to go " + 
                        "up.\n", currentFloor);
                System.out.println();
            }
               
            else
            {
                System.out.println();
                System.out.printf("Floor %d, GOING DOWN", currentFloor);
                waitASecond();
                waitASecond();
                stopIndex += update ? 1 : 0;
            }                      
        }
    }    
}

public class elevator 
{
    public static void main(String[] args) 
    {
        boolean userWantsIt = true; //
        Scanner scan = new Scanner(System.in);
        
        // The loop containing the routine
        do        
        {
            Elev elevator = new Elev();
            elevator.generateUp();
            elevator.displayUPARRAY();
            elevator.travelUp();
            elevator.generateDown();
            elevator.displayDOWNARRAY();
            elevator.travelDown();

            String answer = " ";
            System.out.println("Would you like a new ride?: Enter \"Y\" or " + 
                    "\"y\" to Continue or Enter \"N\" or \"n\" to stop.");
            answer = scan.next();

            //Here we check if the user wants to rerun the program
            while((!answer.equalsIgnoreCase("y")) && 
                  (!answer.equalsIgnoreCase("n")))
            {
                System.out.println("Please correct your input: Enter \"Y\" " + 
                        "or \"y\" to Continue or Enter \"N\" or \"n\" " + 
                        "to stop.");
                answer = scan.next();
            }

            // Make a note if the user wants to terminate
            if(answer.equalsIgnoreCase("n"))
                userWantsIt = false;

        } while (userWantsIt);       
    }
}