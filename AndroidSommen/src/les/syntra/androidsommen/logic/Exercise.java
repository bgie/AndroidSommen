package les.syntra.androidsommen.logic;


/* Ontwerp velden:
	> Question: string
	> Answer: double
	> Exercise(question, answer)
 */
public abstract class Exercise {
	String question = "";
	int answer = 0;
	PossibleAnswers possibleAnswers = new PossibleAnswers();
	
	int minTotal, maxTotal, totalAnswers;
	
	/**
	 * Constructor voor oefening aan te maken
	 * @param aQuestion (String) De vraag die getoond wordt
	 * @param aAnswer 	(double) Het juiste antwoord
	 * @author Brecht Jr.
	 */
	public Exercise(String aQuestion, int aAnswer)
	{
		question = aQuestion;
		answer = aAnswer;
	}
	
	/**
	 * Constructor voor oefening aan te maken met mogelijke antwoorden
	 * @param aQuestion (String) De vraag die getoond wordt 
	 * @param aAnswer (double) Het juiste antwoord
	 * @param aPossibleAnswers (PossibleAnswers) Arraylist mogelijke antwoorden
	 * @author Brecht Jr.
	 */
	public Exercise(String aQuestion, int aAnswer, PossibleAnswers aPossibleAnswers)
	{
		question = aQuestion;
		answer = aAnswer;
		possibleAnswers = aPossibleAnswers;
	}
	
	/**
	 * Constructor gebruikt door child classes
	 * @param aMinTotal (int) Laagste cijfer
	 * @param aMaxTotal (int) Hoogste cijfer
	 * @param aTotalAnswers (int) Totaal aantal antwoord mogelijkheden
	 */
	public Exercise(int aMinTotal, int aMaxTotal, int aTotalAnswers)
	{
		minTotal = aMinTotal;
		maxTotal = aMaxTotal;
		totalAnswers = aTotalAnswers;
	}
	
	// GETTERS
	/**
	 * Get vraag
	 * @return (String) question
	 * @author Brecht Jr.
	 */
	public String getQuestion()
	{
		return question;
	}
	
	/**
	 * Get antwoord
	 * @return (int) answer
	 * @author Brecht Jr.
	 */
	public int getAnswer()
	{
		return answer;
	}
	
	/**
	 * Get mogelijke antwoorden
	 * @return (PossibleAnswers) Arraylist met mogelijke antwoorden
	 * @author Brecht Jr.
	 */
	public PossibleAnswers getPossibleAnswers()
	{
		return possibleAnswers;
	}
	
	//METHODS
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
