package les.syntra.androidsommen.gui;

import java.io.IOException;

import org.json.JSONException;

import les.syntra.androidsommen.R;
import les.syntra.androidsommen.logic.Database;
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

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
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
					Database database = Database.instance();
					Log.d("Database", "Test van debug knop.");
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
