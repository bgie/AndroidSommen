package les.syntra.androidsommen.logic;

/* Ontwerp velden:
	> Time: int
	> CurrentExercise: Exercise
	> CompletedExercises: int
	> FailedExercises: int
	> CurrentLevel: Level
	> Score: Score
	0 Game(Player, LevelIndex)
	> Game(Player)
	- CalculateScore()
 */

public class Game {
	/* Moet nog methods krijgen voor:
	 * - opgave tonen
	 * - antwoord controleren
	 * - level laden 
	 */ 
	int time = 0;
	Exercise currentExercise;
	int completedExercises = 0;
	int failedExercises = 0;
	Level currentLevel;
	Score score;
	Player player;
	
	public Game(Player aPlayer, int aLevelIndex)
	{
		player = aPlayer;
		//Implement levelindex
		/* ---
		 * Is er dan geen lijst met levels nodig waarbij
		 * een level via index als object kan opgehaald worden?
		 * Of wordt dit hardcode in game gezet? Of mss gegenereerd door game
		 * en opgeslaan in xml?
		 	---*/
		currentLevel = new Level(aLevelIndex);
		time = currentLevel.getMaxTime();
		score = new Score(player.getPlayerName(),currentLevel.getLevelIndex(),0);
		currentExercise = currentLevel.CreateExercise();
	}
	
	public Game(Player aPlayer)
	{// Enkel als aangemaakt wordt zonder lvl --> wel nodig?
		player = aPlayer;
		currentLevel = new Level(0);
		time = currentLevel.getMaxTime();
		score = new Score(player.getPlayerName(),currentLevel.getLevelIndex(),0);
	}
	
	// GETTERS
	public int getTime()
	{
		return time;
	}
	
	public int getScore()
	{
		return score.getScore();
	}
	
	public int getLevelIndex()
	{
		return currentLevel.getLevelIndex();
	}
	
	public Exercise getExercise()
	{
		return currentExercise;
	}
	
	//METHODS
	
  	public void CalculateScore(double aAnswer)
 
	{
		//Bereken score
  		if(aAnswer == currentExercise.getAnswer())
  		{
  			score = score.UpdateScore(1);
  		}
  		else
  		{
  			time -= currentLevel.getPenaltyTime();
  		}
  		currentExercise = currentLevel.CreateExercise();
	}

	
}
