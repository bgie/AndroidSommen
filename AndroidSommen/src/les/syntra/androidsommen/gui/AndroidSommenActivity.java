package les.syntra.androidsommen.gui;

import les.syntra.androidsommen.R;
import les.syntra.androidsommen.R.layout;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class AndroidSommenActivity extends Activity {
    /** Called when the activity is first created. */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
}