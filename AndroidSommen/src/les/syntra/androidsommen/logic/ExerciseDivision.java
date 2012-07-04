package les.syntra.androidsommen.logic;

import java.util.ArrayList;

public class ExerciseDivision extends Exercise{
	int baseFactor;
	
	/**
	 * Oefening met mogelijkheden binnen een basedigit range en tussen min en max.
	 * @param aMinTotal
	 * @param aMaxTotal
	 * @param aTotalAnswers
	 * @param aBaseFactor (ArrayList<Integer>) Lijst met cijfers die in de opgave kunnen zitten vb. tafel van 3 en 4
	 */
	public ExerciseDivision(int aMinTotal, int aMaxTotal, int aTotalAnswers, ArrayList<Integer> aBaseFactor)
	{
		super(aMinTotal,aMaxTotal,aTotalAnswers);
		baseFactor = aBaseFactor.get((int)Math.round(Math.random() * (aBaseFactor.size()-1)));
		int digit2 = baseFactor;
		int result = (int) Math.round(Math.random() * Math.sqrt(maxTotal-minTotal))+minTotal;
		int digit1 = result*digit2;
		question = digit1+ " : " +digit2+ "=?";
		answer = result;
		
		GeneratePossibleAnswers(answer);
	}
	
	
	public ExerciseDivision(int aMinTotal, int aMaxTotal, int aTotalAnswers)
	{
		super(aMinTotal,aMaxTotal,aTotalAnswers);
		
		//TODO verbeter algorithme voor opgave generator
		int digit2 = (int) Math.round(Math.random() * Math.sqrt(maxTotal-minTotal-1))+minTotal+1;
		int result = (int) Math.round(Math.random() * Math.sqrt(maxTotal-minTotal))+minTotal;
		int digit1 = result*digit2;
		question = digit1+ " : " +digit2+ "=?";
		answer = result;
		
		GeneratePossibleAnswers(answer);
	}
	
	protected void GeneratePossibleAnswers(int aAnswer)
	{//TODO overschrijf parent class method
		possibleAnswers.add(new AnswerChoice(aAnswer));
		for(int ii = 0;ii<totalAnswers;ii++)
		{
			possibleAnswers.add(new AnswerChoice((int) Math.round(Math.random() * (maxTotal-1))+1));
		}
		possibleAnswers.Shuffle();
	}
}
