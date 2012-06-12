package les.syntra.androidsommen.gui;

import java.util.ArrayList;
import java.util.Calendar;

import les.syntra.androidsommen.R;
import les.syntra.androidsommen.logic.HighScores;
import les.syntra.androidsommen.logic.Score;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class HighScoresActivity extends Activity {
	
	HighScores highScore ;
	TextView txtVw;
	
	
	/*
	 * http://www.softwarepassion.com/android-series-custom-listview-items-and-adapters/
	 * 	http://www.softwarepassion.com/android-series-custom-listview-items-and-adapters/
	String getPlayer()
	int getLevelIndex() 
	int getScore() 
	Calendar getDateTime() 
			
	*/
	
	@Override
    public void onCreate(Bundle savedInstanceState) 
	{
		
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.highscores);
        
        // instantiate the HighScore Object
        highScore = new HighScores();

        
        txtVw = (TextView)findViewById(R.id.txtTest);
        
        String test = "";
        int i ;
        
        for (i = 0;i<highScore.size();i++)
        {
        	test += highScore.get(i) + "\n";
        }
        
        txtVw.setText(test);
        
        String appName = getString(R.string.app_name);
        
        String activeScreen = getString(R.string.txtBtnHighScores);
        
        setTitle(appName + " -> " + activeScreen);

        
	}
	
	private class HighScoreAdapter  extends ArrayAdapter<HighScores> {

	    private ArrayList<HighScores> items;
	    
	    public HighScoreAdapter(Context context, int textViewResourceId, ArrayList<HighScores> items) {
	            super(context, textViewResourceId, items);
	            this.items = items;
	    }

	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	            View v = convertView;
	            if (v == null) {
	                LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	                v = vi.inflate(R.layout.tmpl_highscores_item, null);
	            }
	            HighScores o = items.get(position);
	            if (o != null) {
	                    TextView tt = (TextView) v.findViewById(R.id.PlayerName);
	                    TextView bt = (TextView) v.findViewById(R.id.bottomtext);
	                    if (tt != null) {
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

