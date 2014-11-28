import java.util.Scanner;
import java.util.Random;

public class EducationalProgramTools
{
	Random randomNumber = new Random ();
	
	Scanner scan = new Scanner (System.in);
	
	enum ARITH_TYPE {ADDITION, SUBSTRACTION, MULTIPLICATION, DIVISION, RANDOM};
	ARITH_TYPE type;
	int level, result, answer, trueCount, falseCount, perf, typeInt;
	String question;
	
	boolean analysis;

	
	public static void runProgram ()
	{
		for (int a = 1; a > 0; a++)
		{
			EducationalProgramTools object = new EducationalProgramTools ();
		
			object.askLevel();
			object.setArithmetic();
			
			for (int n = 1; n <= 10; n++)
			{
				object.resetArithmetic();
				object.newQuestion();
				do
				{
					printQuestion(object.question);
					object.checkResult();
					object.printMessage();
				} while (object.analysis == false);
			}
			
			object.reportPerf();
		}		
	}
	
	public void askLevel ()
	{
		System.out.print("\nWhat is your level (1 or 2) ? : ");
		level = scan.nextInt();
		System.out.println();
	}
	
	public void setArithmetic ()
	{
		System.out.println("What type of operation will you work on?");
		System.out.printf("%-15s ==> 1\n" + 
						  "%-15s ==> 2\n" +
						  "%-15s ==> 3\n" +
						  "%-15s ==> 4\n" +
						  "%-15s ==> 5\n" +
						  "\n\nYour answer:  ", "Addition", "Substraction", "Multiplication", "Division", "Random");
		
		switch (typeInt = scan.nextInt())
		{
			case 1: type = ARITH_TYPE.ADDITION;
			break;
			case 2: type = ARITH_TYPE.SUBSTRACTION;
			break;
			case 3: type = ARITH_TYPE.MULTIPLICATION;
			break;
			case 4: type = ARITH_TYPE.DIVISION;
			break;
			case 5: 
				switch (1 + randomNumber.nextInt(4))
				{	
				 	case 1: type = ARITH_TYPE.ADDITION;
				 	break;
				 	case 2: type = ARITH_TYPE.SUBSTRACTION;
				 	break;
				 	case 3: type = ARITH_TYPE.MULTIPLICATION;
				 	break;
				 	case 4: type = ARITH_TYPE.DIVISION;
				}
			break;
		}
	}
	
	public void newQuestion ()
	{
		int member1, member2;
		member1 = member2 = result = 0;
		
		switch (level)
		{
			case 1: 
				member1 = 1 + randomNumber.nextInt(5);
				member2 = 1 + randomNumber.nextInt(5);
				if (type == ARITH_TYPE.DIVISION)
				{
					member1 = 1 + randomNumber.nextInt(3);
					member2 = 1 + randomNumber.nextInt(3);
					member1 = member1 * member2;
				}
			break;
			case 2:
				member1 = 1 + randomNumber.nextInt(99);
				member2 = 1 + randomNumber.nextInt(99);
				
				if (type == ARITH_TYPE.DIVISION)
				{
					member1 = 1 + randomNumber.nextInt(10);
					member2 = 1 + randomNumber.nextInt(10);
					member1 = member1 * member2;
				}
			break;
		}		
		
		switch (type)
		{
			case ADDITION: 
				result = member1 + member2;
				question = String.format("How much is  %d + %d ? ", member1, member2);
			break;
			case SUBSTRACTION:
				result = member1 - member2;
				question = String.format("How much is  %d - %d ? ", member1, member2);
			break;
			case MULTIPLICATION: 
				result = member1 * member2;
				question = String.format("How much is  %d * %d ? ", member1, member2);
			break;
			case DIVISION:
				result = member1 / member2;
				question = String.format("How much is  %d : %d ? ", member1 , member2);
			break;
		}		
	}
	
	public static void printQuestion (String s)
	{
		System.out.println(s);
	}
	
	public void checkResult ()
	{
		answer = scan.nextInt();
		analysis = (answer == result);
	}
	
	public void printMessage ()
	{
		int numbChosen= 1 + randomNumber.nextInt(3);
		String message = null;
		
		if (analysis == true)
		{
			switch (numbChosen)
			{
				case 1: message = "Very good!";
				break;
				case 2: message = "Excellent!";
				break;
				case 3: message = "Nice work!";
				break;
				case 4: message = "Keep up the good work!";
				break;
			}
			
			trueCount += 1;
		}
		
		else if (analysis == false)
		{
			switch (numbChosen)
			{
				case 1: message = "No. Please try again.";
				break;
				case 2: message = "Wrong. Try once more.";
				break;
				case 3: message = "Don't give up!";
				break;
				case 4: message = "No. Keep trying.";
				break;
			}
			
			falseCount += 1;
		}
		
		System.out.println(message);
	}
	
	public void resetArithmetic ()
	{
		switch (typeInt)
		{
			case 1: type = ARITH_TYPE.ADDITION;
			break;
			case 2: type = ARITH_TYPE.SUBSTRACTION;
			break;
			case 3: type = ARITH_TYPE.MULTIPLICATION;
			break;
			case 4: type = ARITH_TYPE.DIVISION;
			break;
			case 5: 
				switch (1 + randomNumber.nextInt(4))
				{	
				 	case 1: type = ARITH_TYPE.ADDITION;
				 	break;
				 	case 2: type = ARITH_TYPE.SUBSTRACTION;
				 	break;
				 	case 3: type = ARITH_TYPE.MULTIPLICATION;
				 	break;
				 	case 4: type = ARITH_TYPE.DIVISION;
				}
			break;
		}
	}
	
	public void reportPerf ()
	{
		perf = 100 * (trueCount) / (trueCount + falseCount);
		if (perf < 75)
		{
			System.out.printf("Please ask your instructor for extra help");
		}
		
		falseCount = trueCount = perf = 0;
	}	
}