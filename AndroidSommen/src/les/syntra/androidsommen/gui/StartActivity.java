package les.syntra.androidsommen.gui;

import les.syntra.androidsommen.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends Activity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
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
	}
}
