package les.syntra.androidsommen.logic;

import org.json.JSONException;
import org.json.JSONObject;

/* Ontwerp velden:
	> Name: string
	> Age: int
	> UnlockedLevelIndex: int
	> TotalTimePlayed: int
	> Player(Name, Age, ULI, TTP)
	> Player (Name, Age)
 */
public class Player {
	private String name = "";
	private int age = 0;
	private int unlockedLevelIndex = 0;
	private long totalTimePlayed = 0;
	
	public Player(String aName, int aAge, int aULI, int aTTP)
	{
		name = aName;
		age = aAge;
		unlockedLevelIndex = aULI;
		totalTimePlayed = aTTP;
	}
	public Player(String aName, int aAge)
	{
		name = aName;
		age = aAge;
	}
	
	public Player(JSONObject json) throws JSONException {
		name = json.getString(nameTag);
		age = json.getInt(ageTag);
		unlockedLevelIndex = json.getInt(unlockedLevelIndexTag);
		totalTimePlayed = json.getLong(totalTimePlayedTag);
	}
	
	public JSONObject toJSON() throws JSONException {
		JSONObject json = new JSONObject();
		json.put(nameTag, name);
		json.put(ageTag, age);
		json.put(unlockedLevelIndexTag, unlockedLevelIndex);		
		json.put(totalTimePlayedTag,totalTimePlayed);
		return json;
	}
	
	private final String nameTag = "name";
	private final String ageTag = "age";
	private final String unlockedLevelIndexTag = "unlockedLevelIndex";
	private final String totalTimePlayedTag = "totalTimePlayedTag";
}
