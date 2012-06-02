package les.syntra.androidsommen.gui;

import les.syntra.androidsommen.R;
import les.syntra.androidsommen.logic.Game;
import les.syntra.androidsommen.logic.Player;
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
        
        //Voorbeeld van test game met speler(naam:test,leeftijd:34) en op level 9
        Game activeGame = new Game(new Player("test",34),9);
        
        //Voorbeeld van game gui
        /*lblLevel.setText("Level 1");
        lblTime.setText("123");
        lblScore.setText("0");
        lblQuestion.setText("40+2=?");*/
        
        lblLevel.setText("Level " + activeGame.getLevelIndex());
        lblTime.setText(""+activeGame.getTime());
        lblScore.setText(""+activeGame.getScore());
        lblQuestion.setText(""+activeGame.getExercise().getQuestion());
        
	}
}
