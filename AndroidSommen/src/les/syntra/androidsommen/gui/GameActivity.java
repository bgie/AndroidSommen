package les.syntra.androidsommen.gui;

import les.syntra.androidsommen.R;
import les.syntra.androidsommen.logic.Game;
import les.syntra.androidsommen.logic.Player;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GameActivity extends Activity {
	TextView lblLevel;
    TextView lblTime;
    TextView lblScore;
    TextView lblQuestion;
    EditText txtInput;
    Button btnSubmit;
    boolean gameOver = false;
    boolean wasCorrectAnswer = false;
    
	//Voorbeeld van test game met speler(naam:test,leeftijd:34) en op level 11
    Game activeGame = new Game(new Player("test",34),11);
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        String appName = getString(R.string.app_name);
        String activeScreen = getString(R.string.txtBtnStart);
        setTitle(appName + " -> " + activeScreen);
        
        lblLevel = (TextView)findViewById(R.id.lblLevel);
        lblTime = (TextView)findViewById(R.id.lblTime);
        lblScore = (TextView)findViewById(R.id.lblScore);
        lblQuestion = (TextView)findViewById(R.id.lblQuestion);
        txtInput = (EditText)findViewById(R.id.txtInput);
        btnSubmit = (Button)findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new Submit());
        
        
        StartTimer();
        
        
	}
	class Submit implements OnClickListener
	{
		@Override
		public void onClick(View v) {
				String strInput = ""+txtInput.getText();
				//Reset inputveld
				txtInput.setText("");
				if(strInput == "")
				{//Indien leeg
					strInput = "-1";
				}
				wasCorrectAnswer = activeGame.CalculateScore(Double.parseDouble(strInput));
				if(wasCorrectAnswer)
				{//Indien correct toon extra punten
					lblScore.setText(""+activeGame.getScore() + "+" + activeGame.getLevelIndex());
				}
				else
				{
					if(!gameOver)
					{//Indien niet einde spel en toch fout toon straftijd
						lblTime.setText(""+(activeGame.getTime() + "-" + activeGame.getLevelPenaltyTime()));
					}
				}
				
				lblQuestion.setText(""+activeGame.getExercise().getQuestion());
		}
	}
	
	// GAME LOOP
	private Handler mHandler = new Handler();

	private void StartTimer()
	{// Tijd begint te lopen
		mHandler.removeCallbacks(mUpdateTimer);
        mHandler.postDelayed(mUpdateTimer,1000);
        
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
	
	private void StopTimer()
	{// Stopt tijd voor bijvoorbeeld pauze
		mHandler.removeCallbacks(mUpdateTimer);
	}

	private Runnable mUpdateTimer = new Runnable() {
		   public void run() {
			   
			   int currentGameTime = activeGame.UpdateTime(1);
			   lblTime.setText(""+currentGameTime);
			   lblScore.setText(""+activeGame.getScore());
			   if(currentGameTime <= 0)
			   {// Einde spel
				   gameOver = true;
				   //Bereken score
				   activeGame.TimesUp();
				   //Verwijder inputveld
				   txtInput.setVisibility(View.GONE);
				   lblQuestion.setText(""+activeGame.getExercise().getQuestion());
			   }
			   else
			   {// Herhaal loop
				   mHandler.postDelayed(this, 1000);
			   }		   
		   }
		};
}
