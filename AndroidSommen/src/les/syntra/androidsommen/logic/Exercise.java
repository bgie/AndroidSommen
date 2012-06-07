package les.syntra.androidsommen.logic;

import java.util.ArrayList;

/* Ontwerp velden:
	> Question: string
	> Answer: double
	> Exercise(question, answer)
 */
public class Exercise {
	String question = "";
	double answer = 0;
	PossibleAnswers possibleAnswers;
	
	
	public Exercise(String aQuestion, double aAnswer)
	{
		question = aQuestion;
		answer = aAnswer;
	}
	
	public Exercise(String aQuestion, double aAnswer, PossibleAnswers aPossibleAnswers)
	{
		question = aQuestion;
		answer = aAnswer;
		possibleAnswers = aPossibleAnswers;
	}
	
	
	// GETTERS
	public String getQuestion()
	{
		return question;
	}
	
	public double getAnswer()
	{
		return answer;
	}
	
	public PossibleAnswers getPossibleAnswers()
	{
		return possibleAnswers;
	}
}
