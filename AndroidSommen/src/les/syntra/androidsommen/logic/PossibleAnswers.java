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
	
	/**
	 *  Lijst dooreen schudden, items op willekeurige volgorde zetten.
	 */
	public void Shuffle()
	{
		for(int i = 0; i < this.size(); i++)
		{
			int j = (int) (Math.round(Math.random() * (this.size() - i - 1)) + i);
			AnswerChoice temp = this.get(i);
			this.set(i, this.get(j));
			this.set(j,temp);
		}
	}
}
