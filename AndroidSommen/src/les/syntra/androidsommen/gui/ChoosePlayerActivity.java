package les.syntra.androidsommen.gui;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

import les.syntra.androidsommen.R;
import les.syntra.androidsommen.logic.Database;
import les.syntra.androidsommen.logic.Player;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class ChoosePlayerActivity extends Activity{
	EditText txtName, txtAge;
	Player plNew;
	Database database = null;
	PlayerChoiceAdapter  aaPlayerChoices;
	
	@Override
    public void onCreate(Bundle savedInstanceState) 
	{
		
        super.onCreate(savedInstanceState);
      
        setContentView(R.layout.chooseplayer);     
        String appName = getString(R.string.app_name);       
        String activeScreen = getString(R.string.txtBtnChoosePlayer);        
        setTitle(appName + " -> " + activeScreen);
        
        try {
			database = Database.instance(this);
		} catch (JSONException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        
        txtName = (EditText)findViewById(R.id.txtName);      
        txtAge = (EditText)findViewById(R.id.txtAge);      
        Button btnCreatePlayer=(Button)findViewById(R.id.btnCreatePlayer);
		btnCreatePlayer.setOnClickListener(new CreateNewPlayer());
		
		PopulatePlayerList();
		
		
	}
	
	public void PopulatePlayerList()
	{
		ListView lstPlayers = (ListView) findViewById(R.id.lstPlayers);
		aaPlayerChoices = new PlayerChoiceAdapter(ChoosePlayerActivity.this, R.id.lstPlayers, database.getPlayers());
		lstPlayers.setAdapter(aaPlayerChoices);
	}
	
	/**
	 * Adapter om gridview met mogelijke levels te vullen
	 * @author Brecht Jr.
	 */
	class PlayerChoiceAdapter extends ArrayAdapter<Player>
	{
		ArrayList<Player> players;
		Activity activity;
		
		public PlayerChoiceAdapter(Activity aActivity, int textViewResourceId,
				ArrayList<Player> objects) {
			super(aActivity, textViewResourceId, objects);
			players = objects;
			activity = aActivity;
		}
		
		// create a new ImageView for each item referenced by the Adapter
	    public View getView(int position, View convertView, ViewGroup parent) {
	        Button btnPlayer = new Button(activity);

	        Player currentPlayerChoice = players.get(position);

	        if(currentPlayerChoice != null)
	        {
	        	btnPlayer.setText(""+currentPlayerChoice.getPlayerName());
	        	btnPlayer.setOnClickListener(new SelectPlayer());
	        	if(currentPlayerChoice == database.getActivePlayer())
	        	{
	        		btnPlayer.setBackgroundColor(Color.RED);
	        	}
	        }

	        return btnPlayer;
	    }
	}
	
	public class SelectPlayer implements OnClickListener
	{
		@Override
		public void onClick(View v){
			Button b = (Button)v;
		    String buttonText = b.getText().toString();
		    Player selectedPlayer = new Player("Default",6);
		    for(Player p : database.getPlayers())
		    {
		    	if(p.getPlayerName().equalsIgnoreCase(buttonText))
		    	{
		    		selectedPlayer = p;
		    	}
		    }
			database.setActivePlayer(selectedPlayer);
			
			try {
				database.saveAll();
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			PopulatePlayerList();
		}
	}
	
	public class CreateNewPlayer implements OnClickListener {
		@Override
		public void onClick(View v){
			
			String strName = ""+ txtName.getText();
			Integer intAge = Integer.parseInt( ""+txtAge.getText());
			
			txtName.setText("");
			txtAge.setText("");
			
			plNew = new Player(strName,intAge);
			database.getPlayers().add(plNew);
			
			try {
				database.saveAll();
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			database.setActivePlayer(plNew);
			
			try {
				database.saveAll();
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			PopulatePlayerList();
		}
	}
}
