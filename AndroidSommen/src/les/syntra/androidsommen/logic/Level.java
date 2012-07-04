package les.syntra.androidsommen.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


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
	int minTotal = 0;
	int maxTotal = 100;
	int totalAnswers = 1;
	int totalCorrectAnswers = 1;
	boolean levelPassed = false;
	String operands = "+-*/";
	ArrayList<Integer> baseDigits;
	
	public Level(int aLevelIndex)
	{
		levelIndex = aLevelIndex;
		createLevel();
	}

	private void createLevel()
	{
		switch(levelIndex)
		{
		case 1:
			setLevelSettings("+", 1, 10, new ArrayList<Integer>(Arrays.asList(0,1)));
		break;
		case 2:
			setLevelSettings("+", 3, 10, new ArrayList<Integer>(Arrays.asList(0,1)));
		break;
		case 3:
			setLevelSettings("-", 1, 10, new ArrayList<Integer>(Arrays.asList(0,1)));
		break;
		case 4:
			setLevelSettings("-", 3, 10, new ArrayList<Integer>(Arrays.asList(0,1)));
		break;
		case 5:
			setLevelSettings("*", 1, 10, new ArrayList<Integer>(Arrays.asList(0,1)));
		break;
		case 6:
			setLevelSettings("*", 3, 10, new ArrayList<Integer>(Arrays.asList(0,1)));
		break;
		case 7:
			setLevelSettings("/", 5, 10, new ArrayList<Integer>(Arrays.asList(0,1)));
		break;
		case 8:
			setLevelSettings("+-", 5, 10);
		break;
		case 9:
			setLevelSettings("+-*", 5, 20);
		break;
		case 10:
			setLevelSettings("+-*/", 5, 20);
		break;
		case 11:
			setLevelSettings("+-*/", 5, 30);
		break;
		case 12:
			setLevelSettings("+-*/", 5, 50);
		break;
		case 13:
			setLevelSettings("+-*/", 10, 100, 10);
		break;
		case 14:
			setLevelSettings("+-*/", 10, 100, 30, 10);
		break;
		default:
			setLevelSettings("+-*/", 20, 999999, 0, 30);
		}
		//totalAnswers = levelIndex + 1;
	}
	
	//GETTERS
	public int getLevelIndex()
	{
		return levelIndex;
	}
	
	public int getMaxTime()
	{
		return maxTime;
	}
	
	public int getPenaltyTime()
	{
		return penaltyTime;
	}
	
	public int getCorrectAnswersNeeded()
	{
		return correctAnswersNeeded;
	}
	
	public int getTotalCorrectAnswers()
	{
		return totalCorrectAnswers;
	}
	
	//SETTERS
	/**
	 * Level instellen met operands, aantal juiste antwoorden, maxTotal, minTotal en penaltytime
	 * @param aOperands
	 * @param aCorrectAnswersNeeded
	 * @param aMaxTotal
	 * @param aMinTotal
	 * @param aPenaltyTime
	 */
	private void setLevelSettings(String aOperands, int aCorrectAnswersNeeded, int aMaxTotal, 
									int aMinTotal, int aPenaltyTime)
	{
		penaltyTime = aPenaltyTime;
		correctAnswersNeeded = aCorrectAnswersNeeded;
		minTotal = aMinTotal;
		maxTotal = aMaxTotal;
		operands = aOperands;
		totalAnswers = aCorrectAnswersNeeded;
	}
	
	/**
	 * Level instellen met operands, aantal juiste antwoorden, maxTotal en penaltytime
	 * Zonder minTotal
	 * @param aOperands
	 * @param aCorrectAnswersNeeded
	 * @param aMaxTotal
	 * @param aPenaltyTime
	 */
	private void setLevelSettings(String aOperands, int aCorrectAnswersNeeded,
									int aMaxTotal, int aPenaltyTime)
	{
		penaltyTime = aPenaltyTime;
		correctAnswersNeeded = aCorrectAnswersNeeded;
		minTotal = 0;
		maxTotal = aMaxTotal;
		operands = aOperands;
		totalAnswers = aCorrectAnswersNeeded;
	}
	
	/**
	 * Level instellen met operands, aantal juiste antwoorden, maxTotal
	 * Zonder minTotal en penaltytime
	 * @param aOperands
	 * @param aCorrectAnswersNeeded
	 * @param aMaxTotal
	 */
	private void setLevelSettings(String aOperands, int aCorrectAnswersNeeded,
			int aMaxTotal)
	{
		penaltyTime = 5;
		correctAnswersNeeded = aCorrectAnswersNeeded;
		minTotal = 0;
		maxTotal = aMaxTotal;
		operands = aOperands;
		totalAnswers = aCorrectAnswersNeeded;
	}
	
	/**
	 * Level instellen met operands, aantal juiste antwoorden, maxTotal en basedigits
	 * Zonder minTotal en penaltytime
	 * @param aOperands
	 * @param aCorrectAnswersNeeded
	 * @param aMaxTotal
	 * @param aBaseDigits
	 */
	private void setLevelSettings(String aOperands, int aCorrectAnswersNeeded,
			int aMaxTotal, ArrayList<Integer> aBaseDigits)
	{
		penaltyTime = 5;
		correctAnswersNeeded = aCorrectAnswersNeeded;
		minTotal = 0;
		maxTotal = aMaxTotal;
		operands = aOperands;
		baseDigits = aBaseDigits;
		totalAnswers = aCorrectAnswersNeeded;
	}
	//METHODS
	public Exercise CreateExercise()
	{
		Exercise exercise;
		
		//Bepaal operand
		int operandQty = operands.length();
		int randomOperandIndex = (int) Math.floor(Math.random() * operandQty );
		char randomOperand = operands.charAt(randomOperandIndex);
		
		if(baseDigits != null)
		{
			//Multi select operand
			if(randomOperand == '+' || randomOperand == '*')
			{// als + of maal dan uitkomst is gelijk aan maxtotal

				if(randomOperand == '+')
				{
					exercise =  new ExerciseSum(minTotal, maxTotal, totalAnswers, baseDigits);
				}
				else
				{// Operand = *
					exercise =  new ExerciseMultiply(minTotal, maxTotal, totalAnswers, baseDigits);
				}
			}
			else
			{// als - of / dan grootste getal is gelijk aan maxtotal
				
				if(randomOperand == '-')
				{
					exercise =  new ExerciseSubstraction(minTotal, maxTotal, totalAnswers, baseDigits);
				}
				else
				{// Operand = /
					exercise =  new ExerciseDivision(minTotal, maxTotal, totalAnswers, baseDigits);
				}
			}
		}
		else
		{
			//Multi select operand
			if(randomOperand == '+' || randomOperand == '*')
			{// als + of maal dan uitkomst is gelijk aan maxtotal

				if(randomOperand == '+')
				{
					exercise =  new ExerciseSum(minTotal, maxTotal, totalAnswers);
				}
				else
				{// Operand = *
					exercise =  new ExerciseMultiply(minTotal, maxTotal, totalAnswers);
				}
			}
			else
			{// als - of / dan grootste getal is gelijk aan maxtotal
				
				if(randomOperand == '-')
				{
					exercise =  new ExerciseSubstraction(minTotal, maxTotal, totalAnswers);
				}
				else
				{// Operand = /
					exercise =  new ExerciseDivision(minTotal, maxTotal, totalAnswers);
				}
			}
		}
		
		
		return exercise;
	}
	
	/**
	 * Antwoord was correct, aantal correcte antwoorden wordt verhoogd met 1
	 */
	public void AnswerWasCorrect()
	{
		totalCorrectAnswers += 1;
	}
}
