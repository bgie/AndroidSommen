package les.syntra.androidsommen.gui;

import java.io.IOException;

import org.json.JSONException;

import les.syntra.androidsommen.R;
import les.syntra.androidsommen.logic.Database;
import les.syntra.androidsommen.logic.Player;
import les.syntra.androidsommen.logic.Score;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class StartActivity extends Activity {

	Database database = null;;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
        
        try {
			database = Database.instance(this);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        ImageView appLogo= (ImageView)findViewById(R.id.appLogo);
        Animation myFadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fadein);
        appLogo.startAnimation(myFadeInAnimation);
        Button btnPlayer = (Button)findViewById(R.id.btnChoosePlayer);
        btnPlayer.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(StartActivity.this,ChoosePlayerActivity.class);
				startActivity(i);
			}
		});
        
        Button btnSettings = (Button)findViewById(R.id.btnSettings);
        btnSettings.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(StartActivity.this,SettingsActivity.class);
				startActivity(i);
			}
		});
        
        Button btnHighScores = (Button)findViewById(R.id.btnHighScores);
        btnHighScores.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(StartActivity.this,HighScoresActivity.class);
				startActivity(i);
			}
		});
        
        Button btnStart = (Button)findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(StartActivity.this,GameActivity.class);
				startActivity(i);
			}
		});
        
        Button btnDebug = (Button)findViewById(R.id.btnDebug);
        btnDebug.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {		
					Player p;
					database.getPlayers().add(new Player("Fons",34));
					database.getPlayers().add(new Player("Mimi",58));
					database.getPlayers().add(p = new Player("Brecht",32,3,0));
					database.getHighScores().add(new Score("Mimi",0,1000000));
					database.getHighScores().add(new Score("Fons",0,2000000));
					database.getHighScores().add(new Score("Brecht",0,3000000));
					database.setActivePlayer(p);
					database.saveAll();	
					Log.d("Database", "Database heeft " + database.getPlayers().size() + " spelers.");
					Log.d("Database", "Database heeft " + database.getHighScores().size() + " scores.");
					if(database.getActivePlayer() == null)
						Log.d("Database", "Actieve speler is er niet." );
					else
						Log.d("Database", "Actieve speler is " +  database.getActivePlayer().getPlayerName() );
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}
