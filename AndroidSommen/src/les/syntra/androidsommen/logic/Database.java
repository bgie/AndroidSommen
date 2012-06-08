package les.syntra.androidsommen.logic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

public class Database {
	static private final String file = "data.json";
	static private final String playersTag = "players";
	static private final String highScoresTag = "highScores";
	
	private ArrayList<Player> players = new ArrayList<Player>();
	private HighScores highScores;	
	private Context context;
	
	// Singleton instance - de enige echte database
	private static Database _instance = null;
	
	// Singleton factory method om de enige echte database op te vragen.
	public static Database instance(Context ctx) throws JSONException, IOException {
		if(_instance == null)
			_instance = new Database(ctx);
		return _instance;
	}
	
	// Constructor leest alle data.
	public Database(Context ctx) throws JSONException, IOException {
		context = ctx;
		String json = "";
		try {
			FileInputStream fis = ctx.openFileInput(file);
			StringBuffer content = new StringBuffer("");

			byte[] buffer = new byte[1024];
			while (fis.read(buffer) != -1) {
			    content.append(new String(buffer));
			}
			fis.close();
			json = content.toString();
		} catch(FileNotFoundException e) {			
		}
				
		if( json.length() > 0 ) { 
			JSONObject object = new JSONObject(json);
		
			highScores = new HighScores(object.getJSONArray(highScoresTag));
		
			JSONArray parray = object.getJSONArray(playersTag);
			for(int i = 0; i < parray.length(); i++)
				players.add(new Player(parray.getJSONObject(i)));
		} else
		{
			highScores = new HighScores();
		}
	}
	
	// Slaat alles op
	public void saveAll() throws JSONException, IOException {
		JSONObject object = new JSONObject();
		object.put(highScoresTag, highScores.toJSON());
				
		JSONArray parray = new JSONArray();
		for(Player p : players)
			parray.put(p.toJSON());
		object.put(playersTag, parray);
		
		String json = object.toString();
		
		FileOutputStream fos = context.openFileOutput(file,Context.MODE_PRIVATE);
		fos.write(json.getBytes());
		fos.close();
	}
	
	// Geeft de lijst met players (read-write access)
	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	// Geeft de lijst met highscores (read-write access)
	public HighScores getHighScores() {
		return highScores;
	}
}
