package les.syntra.androidsommen.gui;

//import java.util.ArrayList;
import les.syntra.androidsommen.R;
import les.syntra.androidsommen.logic.HighScores;
import android.app.Activity;
import android.os.Bundle;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
import android.widget.TextView;


public class HighScoresActivity extends Activity {
	
	HighScores highScore ;
	TextView txtVw;
	
	//private String[] lv_arr = {};

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
        

    	// Prepare an Array list of todo items
        //ArrayList<String> listTODO = PrepareList();

		// Get a handle to the list view
		//ListView lv = (ListView) findViewById(R.id.ListView01);

		// Bind the data with the list
		//lv_arr = (String[]) listTODO.toArray(new String[0]);
		//ArrayAdapter<String> arrAdpt ;
		
		
		//arrAdpt = new ArrayAdapter<String>(this,R.id.ListView01,lv_arr);
		
		//lv.setAdapter(arrAdpt);
        
        
       String appName = getString(R.string.app_name);
        
        String activeScreen = getString(R.string.txtBtnHighScores);
        
       
      setTitle(appName + " -> " + activeScreen);

        
	}
	
	
	 
	// The main ArrayList .
	/*
		private ArrayList<String> PrepareList() {
			ArrayList<String> todoItems = new ArrayList<String>();
			todoItems.add("Fill up Gasoline");
			todoItems.add("Wash car");
			todoItems.add("Dinner with friends");
			todoItems.add("Watch Movie");
			return todoItems;
		}
	*/
		  
	    /*

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
	    */

}

