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
import android.util.Log;

public class Database {
	static private final String file = "data.json";
	static private final String playersTag = "players";
	static private final String activePlayerTag = "activePlayer";
	static private final String highScoresTag = "highScores";
	
	private ArrayList<Player> players = new ArrayList<Player>();
	private Player activePlayer = null;
	private HighScores highScores;	
	private Context context;
	
	private static Database _instance = null;
	
	/**
	 * Singleton factory method om de enige echte database op te vragen.
	 * Alle gegevens worden ineens vanaf schijf ingelezen.
	 * @param ctx	Een context voor security. Geef een Activity mee als context.
	 * @return Het databank object.
	 * @throws JSONException
	 * @throws IOException
	 */
	public static Database instance(Context ctx) throws JSONException, IOException {
		if(_instance == null)
			_instance = new Database(ctx);
		return _instance;
	}
	
	/**
	 * Constructor leest alle data.
	 * @param ctx	Een context voor security. Geef een Activity mee als context.
	 * @throws JSONException
	 * @throws IOException
	 */
	protected Database(Context ctx) throws JSONException, IOException {
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
			
			String name = object.optString(activePlayerTag).toLowerCase();
			if(name != null)
			{
				for(Player p : players) 
				{
					if( p.getPlayerName().toLowerCase().compareTo(name) == 0 )
					{
						activePlayer = p;
						break;
					}
				}
			}
		} 
		else
		{
			highScores = new HighScores();
		}
	}
	
	/**
	 * Slaat alles ineens op in een private file op schijf.
	 * @throws JSONException
	 * @throws IOException
	 */
	public void saveAll() throws JSONException, IOException {
		JSONObject object = new JSONObject();
		object.put(highScoresTag, highScores.toJSON());
				
		JSONArray parray = new JSONArray();
		for(Player p : players)
			parray.put(p.toJSON());
		object.put(playersTag, parray);
		
		if( activePlayer != null)
			object.put(activePlayerTag,activePlayer.getPlayerName());
		
		String json = object.toString();
		
		FileOutputStream fos = context.openFileOutput(file,Context.MODE_PRIVATE);
		fos.write(json.getBytes());
		fos.close();
	}
	
	/**
	 * @return Geeft de lijst met players (read-write access).
	 */
	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	/**
	 * @return Geeft de huidige (of laatst) actieve speler, of null als er geen is.
	 */
	public Player getActivePlayer() {
		return activePlayer;
	}
	
	/**
	 * Stelt een player in als de actieve speler.
	 * @param p		De player die nu de actieve player wordt. Moet bestaan in de lijst met players!
	 */
	public void setActivePlayer(Player p) {
		if( players.contains(p))
			activePlayer = p;
		else
			activePlayer = null;
	}
	
	/**
	 * @return Geeft de lijst met highscores (read-write access).
	 */
	public HighScores getHighScores() {
		return highScores;
	}
}
