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
	{// Enkel als aangemaakt wordt zonder lvl --> v.b. geen save game
		/*player = aPlayer;
		currentLevel = new Level(0);
		time = currentLevel.getMaxTime();
		score = new Score(player.getPlayerName(),currentLevel.getLevelIndex(),0);*/
		this(aPlayer,0);
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
	
	public int getLevelPenaltyTime()
	{
		return currentLevel.getPenaltyTime();
	}
	
	public Exercise getExercise()
	{
		return currentExercise;
	}
	
	//SETTERS

	
	//METHODS
	public int UpdateTime(int aDeltaTime)
	{
		time -= aDeltaTime;
		if(time < 0){
			time = 0;
		}
		return time;
	}
  	public boolean CalculateScore(double aAnswer)
 
	{//Bereken score
		boolean wasCorrectAnswer = false;
  		if(aAnswer == currentExercise.getAnswer())
  		{
  			score = score.UpdateScore(1 * currentLevel.getLevelIndex());
  			wasCorrectAnswer = true;
  		}
  		else
  		{
  			time -= currentLevel.getPenaltyTime();
  		}
  		if(time <= 0)
  		{// Tijd is om, toon einde
  			TimesUp();
  		}
  		else
  		{// Er is nog tijd, gereneer volgende vraag
  			currentExercise = currentLevel.CreateExercise();
  		}
  		return wasCorrectAnswer;
	}
  	
  	public void TimesUp()
  	{
  		time = 0;
  		//-1 is gebruikt voor lege input
  		//indien dit vals antwoord ook -1 zou hebben kan je eenmalig bonuspunten verdienen door op
  		//submit te klikken op einde van spel
		currentExercise = new Exercise("x_X GAME OVER X_x\nScore: " + score.getScore(), -2);
  	}
	
}
