package les.syntra.androidsommen.logic;

/* Ontwerp velden:
	> Question: string
	> Answer: double
	> Exercise(question, answer)
 */
public class Exercise {
	String question = "";
	double answer = 0;
	
	public Exercise(String aQuestion, double aAnswer)
	{
		question = aQuestion;
		answer = aAnswer;
	}
	
	public String getQuestion()
	{
		return question;
	}
	
	public double getAnswer()
	{
		return answer;
	}
}
