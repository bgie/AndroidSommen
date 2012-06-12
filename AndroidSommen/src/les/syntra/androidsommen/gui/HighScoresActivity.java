package les.syntra.androidsommen.gui;

//import java.util.ArrayList;
import java.io.IOException;
import java.util.ArrayList;

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
import android.widget.TextView;


public class HighScoresActivity extends ListActivity {

	// initilise the database
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
     			// TODO Auto-generated catch block
     			e1.printStackTrace();
     		} catch (IOException e1) {
     			// TODO Auto-generated catch block
     			e1.printStackTrace();
     		}
        
        
        String appName = getString(R.string.app_name);
    
        String activeScreen = getString(R.string.txtBtnHighScores);
    
        setTitle(appName + " -> " + activeScreen);
  

    	// Get the highscores
        ArrayList<Score> ListHighScore = database.getHighScores();
        
        // create the adapter
        this.highScoreAdapter = new HighScoreAdapter(this, R.layout.tmpl_highscores_item, ListHighScore);
        
        // set the adapter
        setListAdapter(this.highScoreAdapter);
        
     		
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
	                    TextView tt = (TextView) v.findViewById(R.id.PlayerName);
	                    
	                    TextView bt = (TextView) v.findViewById(R.id.Level);
	                    
	                    if (tt != null) {
	                    	Log.d("Player: ", "" + o.getPlayer() );
	                          tt.setText("Name: "+o.getPlayer());                           
	                    }
	                    if(bt != null){
	                          bt.setText("Level: "+ o.getLevelIndex());
	                    }
	            }
	            return v;
	    }
	    
	}
	    

}

