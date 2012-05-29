package les.syntra.androidsommen;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class AndroidSommenActivity extends Activity {
    /** Called when the activity is first created. */

    
    private TextView txtTest;
    //Brecht Jr. Reporting in: dit is van desktop!
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
}