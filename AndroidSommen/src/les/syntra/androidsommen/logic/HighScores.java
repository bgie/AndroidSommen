package les.syntra.androidsommen.logic;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

/* Ontwerp:
	- Add(Score)
	- HighScores(source)
	- Reset()
	- GetTopScoresByLevel(levelindex, amount)
	- GetTopScores(amount)
	- GetTopScoresByPlayer(player, amount)
 */
public class HighScores extends ArrayList<Score> {
	
	// Constructor uit JSON object.
	public HighScores(JSONArray json) throws JSONException 
	{
		for (int i = 0; i < json.length(); i++)
			
			add(new Score(json.getJSONObject(i)));	
		
	}

	// Constructor nieuwe, lege lijst.
	public HighScores()
	{

	}

	// Converteer naar JSON object
	public JSONArray toJSON() throws JSONException
	{
		
		JSONArray json = new JSONArray();
		
		for(Score s : this)
			
			json.put(s.toJSON());
		
		return json;
		
	}
	
	private static final long serialVersionUID = -7258756160317032527L;
	
}
