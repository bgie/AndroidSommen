package les.syntra.androidsommen.logic;

/* Ontwerp velden:
	> LevelIndex: int
	> MaxTime: int
	> PenaltyTime: int
	> CorrectAnswersNeeded: int
	> MinTotal: int
	> MaxTotal: int
	> Operands: string
	- CreateExercise()
	> Level(levelindex)
 */
public class Level {
	// Moet nog methods voor mogelijke antwoorden krijgen
	int LevelIndex = 0;
	int MaxTime = 120;
	int PenaltyTime = 5;
	int CorrectAnswersNeeded = 1;
	int MinTotal = 0;
	int MaxTotal = 99999999;
	String Operands = "+-*/";
	
	public Level(int aLevelIndex)
	{
		LevelIndex = aLevelIndex;
	}
	public Exercise CreateExercise()
	{
		//Schrijf hier logica
		//Dummy
		String Question = "40+2=...";
		double Answer = 42;
		//Dummy end
		Exercise exercise =  new Exercise(Question, Answer);
		return exercise;
	}
}
