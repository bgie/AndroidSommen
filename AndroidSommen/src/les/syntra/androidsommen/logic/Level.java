package les.syntra.androidsommen.logic;

import android.util.Log;

/* Ontwerp velden:
	> LevelIndex: int
	> MaxTime: int
	> PenaltyTime: int
	> CorrectAnswersNeeded: int
	> MinTotal: int
	> MaxTotal: int
	> Operands: string
	> CreateExercise()
	> Level(levelindex)
 */
public class Level {
	// Moet nog methods voor mogelijke antwoorden krijgen
	int levelIndex = 0;
	int maxTime = 120;
	int penaltyTime = 5;
	int correctAnswersNeeded = 1;
	int minTotal = 0; //tijdelijk nog geen nut
	int maxTotal = 100;
	String operands = "+-*/";
	
	public Level(int aLevelIndex)
	{
		levelIndex = aLevelIndex;
	}

	private void createLevel()
	{
		switch(levelIndex)
		{
		case 1:
			setLevelSettings("+", 5, 10);
		break;
		case 2:
			setLevelSettings("-", 5, 10);
		break;
		case 3:
			setLevelSettings("*", 5, 10);
		break;
		case 4:
			setLevelSettings("/", 5, 10);
		break;
		case 5:
			setLevelSettings("+-", 5, 10);
		break;
		case 6:
			setLevelSettings("+-*", 5, 10);
		break;
		case 7:
			setLevelSettings("+-*/", 5, 10);
		break;
		case 8:
			setLevelSettings("+-*/", 5, 30);
		break;
		case 9:
			setLevelSettings("+-*/", 5, 50);
		break;
		case 10:
			setLevelSettings("+-*/", 10, 100);
		break;
		default:
			setLevelSettings("+-*/", 20, 999999, 0, 30);
		}
	}
	private void setLevelSettings(String aOperands, int aCorrectAnswersNeeded, int aMaxTotal, 
									int aMinTotal, int aPenaltyTime)
	{
		penaltyTime = aPenaltyTime;
		correctAnswersNeeded = aCorrectAnswersNeeded;
		minTotal = aMinTotal;
		maxTotal = aMaxTotal;
		operands = aOperands;
	}
	private void setLevelSettings(String aOperands, int aCorrectAnswersNeeded,
									int aMaxTotal, int aPenaltyTime)
	{
		penaltyTime = aPenaltyTime;
		correctAnswersNeeded = aCorrectAnswersNeeded;
		minTotal = 0;
		maxTotal = aMaxTotal;
		operands = aOperands;
	}
	private void setLevelSettings(String aOperands, int aCorrectAnswersNeeded,
			int aMaxTotal)
	{
		penaltyTime = 5;
		correctAnswersNeeded = aCorrectAnswersNeeded;
		minTotal = 0;
		maxTotal = aMaxTotal;
		operands = aOperands;
	}
	public Exercise CreateExercise()
	{
		int result = 0;
		int digit1 = 0;
		int digit2 = 0;
		String question = "?+?=?";
		double answer = 0;

		
		
		//Bepaal operand
		int operandQty = operands.length();
		int randomOperandIndex = (int) Math.floor(Math.random() * operandQty );
		char randomOperand = operands.charAt(randomOperandIndex);
		Log.d("RandomOperand: ",randomOperand+"");
		Log.d("levelindex: ",levelIndex+"");
		//multi select operand
		
		if(randomOperand == '+' || randomOperand == '*')
		{// als + of maal dan uitkomst is gelijk aan maxtotal
			result = (int) Math.floor(Math.random() * maxTotal);
			if(randomOperand == '+')
			{
				digit1 = (int) Math.floor(Math.random() * result);
				digit2 = result - digit1;
				question = digit1+ "+" +digit2+ "=?";
				answer = result;
			}
			else
			{// Operand = *
				//result als tijdelijke "seed"
				digit1 = (int) Math.floor(Math.random() * Math.sqrt(result));
				digit2 = (int) Math.floor(Math.random() * Math.sqrt(result));
				result = digit1 * digit2;
				question = digit1+ "*" +digit2+ "=?";
				answer = result;
			}
		}
		else
		{// als - of / dan grootste getal is gelijk aan maxtotal
			
			if(randomOperand == '-')
			{
				digit1 = (int) Math.floor(Math.random() * maxTotal);
				digit2 = (int) Math.floor(Math.random() * digit1);
				result = digit1 - digit2;
				question = digit1+ "-" +digit2+ "=?";
				answer = result;
			}
			else
			{// Operand = /
				digit2 = (int) Math.floor(Math.random() * Math.sqrt(maxTotal-1))+1;
				result = (int) Math.floor(Math.random() * Math.sqrt(maxTotal));
				digit1 = result*digit2;
				question = digit1+ "/" +digit2+ "=?";
				answer = result;
			}
		}
		
		Exercise exercise =  new Exercise(question, answer);
		return exercise;
	}
}
