package les.syntra.androidsommen.gui;

//import java.util.ArrayList;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONException;

import les.syntra.androidsommen.R;
import les.syntra.androidsommen.logic.Database;
import les.syntra.androidsommen.logic.Score;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class HighScoresActivity extends ListActivity {

	// initialise the database
	Database database = null;;
	
	// declare the adapter
	private HighScoreAdapter highScoreAdapter;
	  	
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
  

    	// Get the highscores
        ArrayList<Score> ListHighScore = database.getHighScores();
        
        // create the adapter
        highScoreAdapter = new HighScoreAdapter(this, R.layout.tmpl_highscores_item, ListHighScore);
        ListView lstHighScores = (ListView)findViewById(R.id.lstHighScores);
        
        // set the adapter
        lstHighScores.setAdapter((ListAdapter) highScoreAdapter);
        
     		
	}
	
	

/*
 * Zie voor een voorbeeld: http://www.softwarepassion.com/android-series-custom-listview-items-and-adapters/
 * op dat voorbeeld heb ik mijn template gebaseerd
 * tmpl_highscores_item.xml is een template voor één listview item 
 * */
	
private class HighScoreAdapter  extends ArrayAdapter<Score> {

	    private ArrayList<Score> scores;
	    
	    public HighScoreAdapter(Activity aContext, int ResId,ArrayList<Score> listHighScore)
	    {
	        super(aContext, ResId, listHighScore);
	        scores = listHighScore;
	    }
	  
	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	            View v = convertView;
	            if (v == null) {
                    LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    v = vi.inflate(R.layout.tmpl_highscores_item, null);
                }
	            
	            
	            Score o = scores.get(position);
	            
	            if (o != null) {
	                    TextView player= (TextView) v.findViewById(R.id.PlayerName);
	                    
	                    TextView level = (TextView) v.findViewById(R.id.Level);

	                    TextView dateTime = (TextView) v.findViewById(R.id.DateTime);

	                    TextView score = (TextView) v.findViewById(R.id.Score);
	                    
	                    if (player != null) {
	                    	Log.d("Player: ", "" + o.getPlayer() );
	                          player.setText("Name: "+o.getPlayer());                           
	                    }
	                    if(level != null){
	                          level.setText("Level: "+ o.getLevelIndex());
	                    }
	                    if(dateTime != null){
	                    	
	                    	Calendar cal = o.getDateTime();
	                    	Date dt = cal.getTime();
	                    	int mins = dt.getMinutes();
	                    	String strMins;
	                    	
	                    	if(mins>9)
	                    	{
	                    		strMins = ""+mins;
	                    	}
	                    	else
	                    	{
	                    		strMins = "0"+mins;
	                    	}

	                    	String timeStamp = dt.getDay() + "/" + dt.getMonth() + "/" + (1900 + dt.getYear()) +
	                    			" - " + dt.getHours() + ":" + strMins;
	                    	
	                    	dateTime.setText( timeStamp);
	                    }
	                    if(score != null){
	                    	score.setText("Score: "+ o.getScore());
	                    }
	            }
	            return v;
	    }
	    
	}
	    

}

