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
	int Time = 120;
	Exercise CurrentExercise;
	int CompletedExercises = 0;
	int FailedExercises = 0;
	Level CurrentLevel;
	Score Score;
	Player Player;
	
	public Game(Player aPlayer, int aLevelIndex)
	{
		Player = aPlayer;
		//Implement levelindex
		/* ---
		 * Is er dan geen lijst met levels nodig waarbij
		 * een level via index als object kan opgehaald worden?
		 * Of wordt dit hardcode in game gezet? Of mss gegenereerd door game
		 * en opgeslaan in xml?
		 	---*/
			
	}
	public Game(Player aPlayer)
	{
		Player = aPlayer;
	}
	
/*
  	private void CalculateScore()
 
	{
		//Bereken score
	}
	*/
	
}
