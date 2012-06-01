package les.syntra.androidsommen.gui;

import les.syntra.androidsommen.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class GameActivity extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        TextView lblLevel = (TextView)findViewById(R.id.lblTime);
        TextView lblTime = (TextView)findViewById(R.id.lblLevel);
        TextView lblScore = (TextView)findViewById(R.id.lblScore);
        TextView lblQuestion = (TextView)findViewById(R.id.lblQuestion);
        
        lblLevel.setText("Level 1");
        lblTime.setText("123");
        lblScore.setText("0");
        lblQuestion.setText("40+2=?");
	}
}
