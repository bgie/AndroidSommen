package les.syntra.androidsommen.logic;

public class ExerciseDivision extends Exercise{
	
	public ExerciseDivision(int aMinTotal, int aMaxTotal, int aTotalAnswers)
	{
		super(aMinTotal,aMaxTotal,aTotalAnswers);
		
		//TODO verbeter algorithme voor opgave generator
		int digit2 = (int) Math.round(Math.random() * Math.sqrt(maxTotal-minTotal-1))+minTotal+1;
		int result = (int) Math.round(Math.random() * Math.sqrt(maxTotal-minTotal))+minTotal;
		int digit1 = result*digit2;
		question = digit1+ " : " +digit2+ "=?";
		answer = result;
		
		//TODO zelf antwoord genereren
		super.GeneratePossibleAnswers(answer);
	}
}
