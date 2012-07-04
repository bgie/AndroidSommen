package les.syntra.androidsommen.logic;

import java.util.ArrayList;

public class ExerciseSubstraction extends Exercise{
	int baseFactor;
	
	/**
	 * Oefening met mogelijkheden binnen een basedigit range en tussen min en max.
	 * @param aMinTotal
	 * @param aMaxTotal
	 * @param aTotalAnswers
	 * @param aBaseFactor (ArrayList<Integer>) Lijst met cijfers die in de opgave kunnen zitten vb. tafel van 3 en 4
	 */
	public ExerciseSubstraction(int aMinTotal, int aMaxTotal, int aTotalAnswers, ArrayList<Integer> aBaseFactor)
	{
		super(aMinTotal,aMaxTotal,aTotalAnswers);
		baseFactor = aBaseFactor.get((int)Math.round(Math.random() * (aBaseFactor.size()-1)));
		int digit2 = baseFactor;
		int digit1 = (int) Math.round(Math.random() * (maxTotal+digit2-minTotal))+minTotal;
		
		int result = digit1 - digit2;
		question = digit1+ " - " +digit2+ "=?";
		answer = result;
		
		GeneratePossibleAnswers(answer);
	}
	
	
	public ExerciseSubstraction(int aMinTotal, int aMaxTotal, int aTotalAnswers)
	{
		super(aMinTotal,aMaxTotal,aTotalAnswers);
		
		int digit1 = (int) Math.round(Math.random() * (maxTotal-minTotal))+minTotal;
		int digit2 = (int) Math.round(Math.random() * digit1);
		int result = digit1 - digit2;
		question = digit1+ " - " +digit2+ "=?";
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
