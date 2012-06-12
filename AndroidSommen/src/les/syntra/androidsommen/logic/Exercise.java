package les.syntra.androidsommen.logic;


/* Ontwerp velden:
	> Question: string
	> Answer: double
	> Exercise(question, answer)
 */
public class Exercise {
	String question = "";
	double answer = 0;
	PossibleAnswers possibleAnswers;
	
	/**
	 * Constructor voor oefening aan te maken
	 * @param (String) Vraag, (double) Antwoord
	 * @author Brecht Jr.
	 *
	 */
	public Exercise(String aQuestion, double aAnswer)
	{
		question = aQuestion;
		answer = aAnswer;
	}
	
	/**
	 * Constructor voor oefening aan te maken
	 * @param (String) Vraag, (double) Antwoord, (PossibleAnswers) Arraylist mogelijke antwoorden
	 * @author Brecht Jr.
	 *
	 */
	public Exercise(String aQuestion, double aAnswer, PossibleAnswers aPossibleAnswers)
	{
		question = aQuestion;
		answer = aAnswer;
		possibleAnswers = aPossibleAnswers;
	}
	
	
	// GETTERS
	/**
	 * Get vraag
	 * @return (String) question
	 * @author Brecht Jr.
	 *
	 */
	public String getQuestion()
	{
		return question;
	}
	
	/**
	 * Get antwoord
	 * @return (double) answer
	 * @author Brecht Jr.
	 *
	 */
	public double getAnswer()
	{
		return answer;
	}
	
	/**
	 * Get mogelijke antwoorden
	 * @return (PossibleAnswers) Arraylist met mogelijke antwoorden
	 * @author Brecht Jr.
	 *
	 */
	public PossibleAnswers getPossibleAnswers()
	{
		return possibleAnswers;
	}
}
