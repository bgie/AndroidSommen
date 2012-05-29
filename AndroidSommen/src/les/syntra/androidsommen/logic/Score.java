package les.syntra.androidsommen.logic;

/* Ontwerp velden:
	> Player: Player
	> LevelIndex: int
	> score: int
	- date: date
	- Score(Player, LevelIndex, Score, Date)
	> UpdateScore(quantity int)
 */
public class Score {
	Player Player;
	int LevelIndex = 0;
	int Score = 0;
	java.sql.Date Date;
	
	public Score(Player aPlayer, int aLevelIndex, int aScore, java.sql.Date aDate)
	{
		Player = aPlayer;
		LevelIndex = aLevelIndex;
		Score = aScore;
		Date = aDate;
	}
	public Score UpdateScore(int aDelta)
	{
		Score += aDelta;
		return this;
	}
}
