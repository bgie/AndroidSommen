package les.syntra.androidsommen.logic;

import java.util.ArrayList;

public class PossibleAnswers  extends ArrayList<AnswerChoice>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<AnswerChoice> possibleAnswers;
	
	public PossibleAnswers(ArrayList<AnswerChoice> aPossibleAnswers)
	{
		super(aPossibleAnswers);
		possibleAnswers = aPossibleAnswers;
	}
	
	public PossibleAnswers()
	{
		possibleAnswers = new ArrayList<AnswerChoice>();
	}
	
	public void Shuffle()
	{
		//shuffles de lijst door elkaar
		// TODO maak shuffle functie
	}
}
