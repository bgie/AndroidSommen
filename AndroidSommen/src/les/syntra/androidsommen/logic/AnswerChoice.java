package les.syntra.androidsommen.logic;

public class AnswerChoice {// klasse voor mogelijke antwoorden
	int answer = 0;
	
	public AnswerChoice(int aAnswer)
	{
		answer = aAnswer;
	}
	
	// GETTERS
	/**
	 * Krijgt antwoord terug
	 * @return (int) Answer
	 * @author Brecht Jr.
	 *
	 */
	public int getAnswer()
	{
		return answer;
	}
}
