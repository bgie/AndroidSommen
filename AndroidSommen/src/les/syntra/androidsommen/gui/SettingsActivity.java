package les.syntra.androidsommen.gui;

import java.io.IOException;

import org.json.JSONException;

import les.syntra.androidsommen.R;
import les.syntra.androidsommen.logic.Database;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingsActivity extends Activity {
	Database database = null;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        String appName = getString(R.string.app_name);
        String activeScreen = getString(R.string.txtBtnSettings);
        setTitle(appName + " -> " + activeScreen);
        
        try {
			database = Database.instance(this);
		} catch (JSONException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        
        Button btnResetScores = (Button)findViewById(R.id.btnResetScores);
        btnResetScores.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				database.getHighScores().clear();
				try {
					database.saveAll();
				} catch (JSONException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

        Button btnDeleteData = (Button)findViewById(R.id.btnDeleteData);
        btnDeleteData.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				database.getPlayers().clear();
				database.getHighScores().clear();
				try {
					database.saveAll();
				} catch (JSONException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
