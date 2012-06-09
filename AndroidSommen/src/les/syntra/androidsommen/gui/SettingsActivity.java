package les.syntra.androidsommen.gui;

import java.io.IOException;

import org.json.JSONException;

import les.syntra.androidsommen.R;
import les.syntra.androidsommen.gui.GameActivity.ScreenLevelSelection.NextLevel;
import les.syntra.androidsommen.logic.Database;
import les.syntra.androidsommen.logic.Player;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
        
        //TODO TIJDELIJK om DB te testen
        Button btnDeleteData = (Button)findViewById(R.id.btnDeleteData);
        btnDeleteData.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.d("SETTINGSACTIVITY","active player lvl na update:" +database.getActivePlayer().getUnlockedLevelIndex());
				Player testplayer = new Player("test",22);
				Log.d("SETTINGSACTIVITY","player 1 lvl:" +database.getPlayers().get(0).getUnlockedLevelIndex());
				database.getPlayers().get(0).LevelCompleted(5, 23);
				Log.d("SETTINGSACTIVITY","player 1 lvl na update:" +database.getPlayers().get(0).getUnlockedLevelIndex());
				database.getPlayers().clear();
				Log.d("SETTINGSACTIVITY","lijst van players is leeggemaakt");
				Log.d("SETTINGSACTIVITY","players in db:" +database.getPlayers());
				
				database.getPlayers().add(testplayer);
				Log.d("SETTINGSACTIVITY","1 aan lijst van players toegevoegd");
				Log.d("SETTINGSACTIVITY","players in db:" +database.getPlayers().get(0));
				database.setActivePlayer(testplayer);
				Log.d("SETTINGSACTIVITY","activplayer gezet");
				Log.d("SETTINGSACTIVITY","active player:" +database.getActivePlayer());
				Log.d("SETTINGSACTIVITY","active player unlock lvl:" +database.getActivePlayer().getUnlockedLevelIndex());
				database.getActivePlayer().LevelCompleted(5, 23);
				Log.d("SETTINGSACTIVITY","activeplayer  lvl na update:" +database.getActivePlayer().getUnlockedLevelIndex());
				try {
					database.saveAll();
					Log.d("SETTINGSACTIVITY","SAVED");
					Log.d("SETTINGSACTIVITY","players in db na delete:" +database.getPlayers());
				} catch (JSONException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
