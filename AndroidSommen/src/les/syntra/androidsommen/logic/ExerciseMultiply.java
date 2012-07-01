package les.syntra.androidsommen.logic;

import java.util.ArrayList;

public class ExerciseMultiply extends Exercise{
	int baseFactor;
	public ExerciseMultiply(int aMinTotal, int aMaxTotal, int aTotalAnswers, ArrayList<Integer> aBaseFactor)
	{
		super(aMinTotal,aMaxTotal,aTotalAnswers);
		baseFactor = aBaseFactor.get((int)Math.round(Math.random() * (aBaseFactor.size()-1)));
		int result = (int) Math.round(Math.random() * (maxTotal-minTotal))+minTotal;
		//result als tijdelijke "seed"
		//TODO verbeter algorithme voor opgave generator
		int digit2 = baseFactor;
		int digit1 = 0;
		if(digit2 != 0)
		{
			digit1 = (int) Math.round(result/digit2);
		}
		else
		{
			digit1 = (int) Math.round(Math.random() * (maxTotal-minTotal))+minTotal;
		}
		
		//int digit1 = (int) Math.round(Math.random() * Math.sqrt(result));
		result = digit1 * digit2;
		question = digit1+ " x " +digit2+ "=?";
		answer = result;
		
		//zelf antwoord genereren
		GeneratePossibleAnswers(answer);
	}
	
	protected void GeneratePossibleAnswers(int aAnswer)
	{
		possibleAnswers.add(new AnswerChoice(aAnswer));
		for(int ii = 0;ii<totalAnswers;ii++)
		{
			possibleAnswers.add(new AnswerChoice((int) Math.round(Math.random() * (maxTotal-1))+1));
		}
		possibleAnswers.Shuffle();
	}
}
