package les.syntra.androidsommen.logic;

import java.io.IOException;
import java.util.Calendar;

import org.json.JSONException;



/* Ontwerp velden:
	> Time: int
	> CurrentExercise: Exercise
	> CompletedExercises: int
	> FailedExercises: int
	> CurrentLevel: Level
	> Score: Score
	0 Game(Player, LevelIndex)
	> Game(Player)
	> CalculateScore()
 */

public class Game {
	int time = 0;
	Exercise currentExercise;
	int completedExercises = 0;
	int failedExercises = 0;
	Level currentLevel;
	Score score;
	Player player;
	boolean gameOver;
	boolean levelCompleted;
	
	Database database = null;
	Calendar dateTime = Calendar.getInstance();
	long starttime = dateTime.getTimeInMillis();
	long timeAtStop, playTime;
	
	public Game(Database aDatabase, int aLevelIndex)
	{
		database = aDatabase;
		player = database.getActivePlayer();
		currentLevel = new Level(aLevelIndex);
		time = currentLevel.getMaxTime();
		score = new Score(player.getPlayerName(),currentLevel.getLevelIndex(),0);
		currentExercise = currentLevel.CreateExercise();
	}
	
	public Game(Player aPlayer, int aLevelIndex)
	{// DEPRECATED?
		player = aPlayer;
		currentLevel = new Level(aLevelIndex);
		time = currentLevel.getMaxTime();
		score = new Score(player.getPlayerName(),currentLevel.getLevelIndex(),0);
		currentExercise = currentLevel.CreateExercise();
	}
	
	public Game(Player aPlayer)
	{// Enkel als aangemaakt wordt zonder lvl --> v.b. geen save game
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
	
	public Level getCurrentLevel()
	{
		return currentLevel;
	}
	
	public int getLevelPenaltyTime()
	{
		return currentLevel.getPenaltyTime();
	}
	
	public Exercise getExercise()
	{
		return currentExercise;
	}
	
	public boolean getIsGameOver()
	{
		return gameOver;
	}
	
	public long getPlayTime()
	{
		return playTime;
	}
	
	public boolean getIsLevelCompleted()
	{
		return levelCompleted;
	}
	
	//SETTERS

	
	//METHODS
	public int UpdateTime(int aDeltaTime)
	{
		time -= aDeltaTime;
		if(time < 0){
			TimesUp();
		}
		return time;
	}
	
	/**
	 * Berekent of het antwoord juist was en of er nog tijd is om een volgende vraag te genereren
	 * @param aAnswer	(double) Het antwoord dat gecontroleerd moet worden
	 * @return boolean	Was antwoord juist?
	 */
  	public boolean CalculateScore(double aAnswer)
	{//Bereken score
		boolean wasCorrectAnswer = false;
  		if(aAnswer == currentExercise.getAnswer())
  		{
  			score = score.UpdateScore(1 * currentLevel.getLevelIndex());
  			currentLevel.AnswerWasCorrect();
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
  		{// Er is nog tijd, genereer volgende vraag
  			currentExercise = currentLevel.CreateExercise();
  		}
  		return wasCorrectAnswer;
	}
  	
  	public void TimesUp()
  	{
  		time = 0;
  		gameOver = true;
  		//nieuwe huidige tijd nodig
  		Calendar dateTime2 = Calendar.getInstance();
  		timeAtStop = dateTime2.getTimeInMillis();
  		playTime = timeAtStop-starttime;
  		

  		if(IsLevelCompleted())
  		{
  			database.getActivePlayer().LevelCompleted(currentLevel.levelIndex, playTime);
  		}
  		else
  		{
  			database.getActivePlayer().LevelPlayed(playTime);
  		}
  		
  		database.getHighScores().add(score);
  		
  		try {
			database.saveAll();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
  		
  	}
	
  	public boolean IsLevelCompleted()
  	{
  		levelCompleted = currentLevel.getTotalCorrectAnswers() >= currentLevel.getCorrectAnswersNeeded();
  		return levelCompleted;
  	}
}
