package les.syntra.androidsommen.logic;

import org.json.JSONException;
import org.json.JSONObject;


import java.util.Calendar;

/* Ontwerp velden:
	> Player: String
	> LevelIndex: int
	> score: int
	- dateTime: Calendar
	- Score(Player, LevelIndex, Score)
	> UpdateScore(quantity int)
 */
public class Score {
	public Score(String aPlayer, int aLevelIndex, int aScore)
	{
		player = aPlayer;
		levelIndex = aLevelIndex;
		score = aScore;
		dateTime = Calendar.getInstance(); 
	}
	
	public Score(JSONObject json) throws JSONException 
	{
		player = json.getString(playerTag);
		levelIndex = json.getInt(levelIndexTag);
		score = json.getInt(scoreTag);
		dateTime = Calendar.getInstance();
		dateTime.setTimeInMillis(json.getLong(dateTimeTag));
	}
	
	public Score UpdateScore(int aDelta)
	{
		score += aDelta;
		return this;
	}
	
	public JSONObject toJSON() throws JSONException 
	{
		JSONObject json = new JSONObject();
		json.put(playerTag, player);
		json.put(levelIndexTag, levelIndex);
		json.put(scoreTag, score);		
		json.put(dateTimeTag,dateTime.getTimeInMillis());
		return json;
	}
	
	public String getPlayer() {
		return player;
	}
	
	/**
	 * dit is een samenvatting
	 * @return {Integer} The Level index of the player
	 * */
	public int getLevelIndex() {
		return levelIndex;
	}
	
	public int getScore() {
		return score;
	}
	
	public Calendar getDateTime() {
		return dateTime;
	}
	
	private String player;
	private int levelIndex = 0;
	private int score = 0;
	private Calendar dateTime;
	
	private final String playerTag = "player";
	private final String levelIndexTag = "levelIndex";
	private final String scoreTag = "score";
	private final String dateTimeTag = "dateTime";
}
