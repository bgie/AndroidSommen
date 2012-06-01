package les.syntra.androidsommen.logic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class Database {
	static private final String file = "data.json";
	static private final String playersTag = "players";
	static private final String highScoresTag = "highScores";
	
	private ArrayList<Player> players = new ArrayList<Player>();
	private HighScores highScores;	
	
	public Database() throws JSONException, IOException {
		String json = "";
		try {
			FileInputStream fis = new FileInputStream(file);
			StringBuffer content = new StringBuffer("");

			byte[] buffer = new byte[1024];
			while (fis.read(buffer) != -1) {
			    content.append(new String(buffer));
			}
			fis.close();
			json = content.toString();
		} catch(FileNotFoundException e) {			
		}
		
		JSONObject object = new JSONObject(json);
		
		highScores = new HighScores(object.getJSONArray(highScoresTag));
		
		JSONArray parray = object.getJSONArray(playersTag);
		for(int i = 0; i < parray.length(); i++)
			players.add(new Player(parray.getJSONObject(i)));
		
	}
	
	public void saveAll() throws JSONException, IOException {
		JSONObject object = new JSONObject();
		object.put(highScoresTag, highScores.toJSON());
				
		JSONArray parray = new JSONArray();
		for(Player p : players)
			parray.put(p.toJSON());
		object.put(playersTag, parray);
		
		String json = object.toString();
		
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(json.getBytes());
		fos.close();
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	public HighScores getHighScores() {
		return highScores;
	}
}
