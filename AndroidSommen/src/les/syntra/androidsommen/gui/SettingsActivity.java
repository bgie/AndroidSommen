package les.syntra.androidsommen.gui;

import les.syntra.androidsommen.R;
import android.app.Activity;
import android.os.Bundle;

public class SettingsActivity extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        String appName = getString(R.string.app_name);
        String activeScreen = getString(R.string.txtBtnSettings);
        setTitle(appName + " -> " + activeScreen);
	}
}
