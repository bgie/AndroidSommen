package les.syntra.androidsommen.logic;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import les.syntra.androidsommen.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * Zie voor een voorbeeld: http://www.josecgomez.com/2010/05/03/android-putting-custom-objects-in-listview/
 * op dat voorbeeld heb ik mijn template gebaseerd
 * tmpl_highscores_item.xml is een template voor één listview item 
 */
public class AdapterHighScores extends ArrayAdapter<Score> {
		 
    int resource;
    String response;
    Context context;
    
    //Initialize adapter
    public AdapterHighScores(Context context, int resource, List<Score> items) {
        super(context, resource, items);
        this.resource=resource;
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LinearLayout highScoresScreen;
        //Get the current alert object
        Score scoreItem = getItem(position);
 
        //Inflate the view
        if(convertView==null)
        {
        	highScoresScreen = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater vi;
            vi = (LayoutInflater)getContext().getSystemService(inflater);
            vi.inflate(resource, highScoresScreen, true);
        }
        else
        {
        	highScoresScreen = (LinearLayout) convertView;
        }
        //Get the text boxes from the listitem.xml file
        TextView player = (TextView) highScoresScreen.findViewById(R.id.PlayerName);    
        TextView level = (TextView) highScoresScreen.findViewById(R.id.Level);
        TextView dateTime = (TextView) highScoresScreen.findViewById(R.id.DateTime);
        TextView score = (TextView) highScoresScreen.findViewById(R.id.Score);
 
        //Assign the appropriate data from our alert object above
        if (player != null) {
        	player.setText("Name: "+scoreItem.getPlayer());                           
        }
        if(level != null){
            level.setText("Level: "+ scoreItem.getLevelIndex());
        }
        if(dateTime != null){
        	
        	Calendar cal = scoreItem.getDateTime();
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
        	score.setText("Score: "+ scoreItem.getScore());
        }
 
        return highScoresScreen;
    }

}
