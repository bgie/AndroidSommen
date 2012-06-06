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
import android.widget.ViewFlipper;

public class GameActivity extends Activity {
	TextView lblLevel,lblTime,lblScore,lblQuestion;
    EditText txtInput;
    Button btnAnswer,btnSelectLevel,btnNextLevel;
    boolean gameOver = false;
    boolean wasCorrectAnswer = false;
    Game activeGame;
    ViewFlipper vfGameViewer;
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameviewer);
        String appName = getString(R.string.app_name);
        String activeScreen = getString(R.string.txtBtnStart);
        setTitle(appName + " -> " + activeScreen);
        
        vfGameViewer = (ViewFlipper)findViewById(R.id.gameViewer);
        
        //Choose level scherm
        btnSelectLevel = (Button)findViewById(R.id.btnSelectLevel);
        btnSelectLevel.setOnClickListener(new SelectLevel());
        btnNextLevel = (Button)findViewById(R.id.btnNextLevel);
        btnNextLevel.setOnClickListener(new NextLevel());
        
        //Game scherm
        lblLevel = (TextView)findViewById(R.id.lblLevel);
        lblTime = (TextView)findViewById(R.id.lblTime);
        lblScore = (TextView)findViewById(R.id.lblScore);
        lblQuestion = (TextView)findViewById(R.id.lblQuestion);
        txtInput = (EditText)findViewById(R.id.txtInput);
        btnAnswer = (Button)findViewById(R.id.btnAnswer);
        btnAnswer.setOnClickListener(new Answer());
        
	}
	
	class SelectLevel implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			//Voorbeeld van test game met speler(naam:test,leeftijd:34) en op level 1
			activeGame = new Game(new Player("test",34),1);
			vfGameViewer.showNext();
			StartTimer();
		}
	}
	
	class NextLevel implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			//Voorbeeld van test game met speler(naam:test,leeftijd:34) en op level 11
			activeGame = new Game(new Player("test",34),11);
			vfGameViewer.showNext();
			StartTimer();
		}
	}
	
	class Answer implements OnClickListener
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
		ResumeTimer();
        
        //Game GUI
        lblLevel.setText("Level " + activeGame.getLevelIndex());
        lblTime.setText(""+activeGame.getTime());
        lblScore.setText(""+activeGame.getScore());
        lblQuestion.setText(""+activeGame.getExercise().getQuestion());
	}
	
	private void ResumeTimer()
	{// Spel wordt hervat
		mHandler.removeCallbacks(mUpdateTimer);
        mHandler.postDelayed(mUpdateTimer,1000);
	}
	
	private void StopTimer()
	{// Stopt tijd voor bijvoorbeeld pauze
		mHandler.removeCallbacks(mUpdateTimer);
	}

	private Runnable mUpdateTimer = new Runnable() {
		   public void run() {
			   
			   int intCurrentGameTime = activeGame.UpdateTime(1);
			   lblTime.setText(""+intCurrentGameTime);
			   lblScore.setText(""+activeGame.getScore());
			   if(intCurrentGameTime <= 0)
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
