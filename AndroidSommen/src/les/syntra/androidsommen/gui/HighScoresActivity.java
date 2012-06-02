package les.syntra.androidsommen.gui;

import les.syntra.androidsommen.R;
import android.app.Activity;
import android.os.Bundle;

public class HighScoresActivity extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.highscores);
        String appName = getString(R.string.app_name);
        String activeScreen = getString(R.string.txtBtnHighScores);
        setTitle(appName + " -> " + activeScreen);
	}
}
