package les.syntra.androidsommen.gui;

//import java.util.ArrayList;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONException;

import les.syntra.androidsommen.R;
import les.syntra.androidsommen.logic.AdapterHighScores;
import les.syntra.androidsommen.logic.Database;
import les.syntra.androidsommen.logic.Score;
import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListView;



public class HighScoresActivity extends ListActivity {

	// initialise the database
	Database database = null;;
	
	// declare the adapter
	private AdapterHighScores highScoreAdapter;
	  	
	/*

TODO: styles aanpassen
	String getPlayer()
	int getLevelIndex() 
	int getScore() 
	Calendar getDateTime() 
			
	*/
	
	public void onCreate(Bundle savedInstanceState) 
	{
		
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.highscores);
        
        try {
			database = Database.instance(this);
		} catch (JSONException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        
        
        String appName = getString(R.string.app_name);
        String activeScreen = getString(R.string.txtBtnHighScores);
        setTitle(appName + " -> " + activeScreen);
  
        ListView lstHighScores = (ListView)findViewById(R.id.lstHighScores);
        
        // Get the highscores
        ArrayList<Score> ListHighScore = database.getHighScores();
        
        //Initialize our array adapter
        highScoreAdapter = new AdapterHighScores(HighScoresActivity.this, R.layout.tmpl_highscores_item,ListHighScore);
 
        //Set the above adapter as the adapter of choice for our list
        lstHighScores.setAdapter(highScoreAdapter);
	}
	
}