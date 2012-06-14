package les.syntra.androidsommen.logic;

public class ExerciseSum extends Exercise{
	
	public ExerciseSum(int aMinTotal, int aMaxTotal, int aTotalAnswers)
	{
		super(aMinTotal,aMaxTotal,aTotalAnswers);
		
		int digit1 = (int) Math.round(Math.random() * (maxTotal-minTotal))+minTotal;
		int digit2 = (int) Math.round(Math.random() * digit1);
		int result = digit1 - digit2;
		question = digit1+ " - " +digit2+ "=?";
		answer = result;
		
		//TODO zelf antwoord genereren
		super.GeneratePossibleAnswers(answer);
	}
	
}
