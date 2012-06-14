package les.syntra.androidsommen.logic;

public class ExerciseMultiply extends Exercise{
	
	public ExerciseMultiply(int aMinTotal, int aMaxTotal, int aTotalAnswers)
	{
		super(aMinTotal,aMaxTotal,aTotalAnswers);
		
		int result = (int) Math.round(Math.random() * (maxTotal-minTotal))+minTotal;
		//result als tijdelijke "seed"
		//TODO verbeter algorithme voor opgave generator
		int digit1 = (int) Math.round(Math.random() * Math.sqrt(result));
		int digit2 = (int) Math.round(Math.random() * Math.sqrt(result));
		result = digit1 * digit2;
		question = digit1+ " x " +digit2+ "=?";
		answer = result;
		
		//TODO zelf antwoord genereren
		super.GeneratePossibleAnswers(answer);
	}
}
