package les.syntra.androidsommen.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import les.syntra.androidsommen.R;
import les.syntra.androidsommen.logic.AnswerChoice;
import les.syntra.androidsommen.logic.Database;
import les.syntra.androidsommen.logic.Exercise;
import les.syntra.androidsommen.logic.Game;
import les.syntra.androidsommen.logic.Level;
import les.syntra.androidsommen.logic.Player;
import les.syntra.androidsommen.logic.PossibleAnswers;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class GameActivity extends Activity {
	Database database = null;
	
	TextView lblLevel,lblTime,lblScore,lblQuestion;
    EditText txtInput;
    Button btnAnswer,btnNextLevel;
    GridView gridLevels, gridAnswers;
    boolean gameOver = false;
    boolean wasCorrectAnswer = false;
    Game activeGame;
    ViewFlipper vfGameViewer;
    LevelChoiceAdapter aaLevelChoices;
    AnswerChoiceAdapter aaAnswerChoices;
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameviewer);
        String appName = getString(R.string.app_name);
        String activeScreen = getString(R.string.txtBtnStart);
        setTitle(appName + " -> " + activeScreen);
        
        try {
			database = Database.instance(this);
		} catch (JSONException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        
        vfGameViewer = (ViewFlipper)findViewById(R.id.gameViewer);
        
        //Choose level scherm
        btnNextLevel = (Button)findViewById(R.id.btnNextLevel);
        btnNextLevel.setOnClickListener(new NextLevel());
        gridLevels = (GridView)findViewById(R.id.gridLevels);
        ArrayList<Level> levelArrayList = new ArrayList<Level>();
        // level generatie
        if(database.getActivePlayer() != null)
        {
        	for(int ii = 1;ii<=database.getActivePlayer().getUnlockedLevelIndex();ii++)
        	{
        		levelArrayList.add(new Level(ii));
        	}
        }
        else
        {
        	levelArrayList.add(new Level(1));
        }
        
        
        aaLevelChoices = new LevelChoiceAdapter(GameActivity.this, R.id.gridLevels, levelArrayList);
		gridLevels.setAdapter(aaLevelChoices);
        
        //Game scherm
        lblLevel = (TextView)findViewById(R.id.lblLevel);
        lblTime = (TextView)findViewById(R.id.lblTime);
        lblScore = (TextView)findViewById(R.id.lblScore);
        lblQuestion = (TextView)findViewById(R.id.lblQuestion);
        txtInput = (EditText)findViewById(R.id.txtInput);
        btnAnswer = (Button)findViewById(R.id.btnAnswer);
        btnAnswer.setOnClickListener(new AnswerTxt());
        gridAnswers = (GridView)findViewById(R.id.gridAnswers);
        
	}
	
	/**
	 * Adapter om gridview met mogelijke levels te vullen
	 * @author Brecht Jr.
	 *
	 */
	class LevelChoiceAdapter extends ArrayAdapter<Level>
	{
		ArrayList<Level> levels;
		Activity activity;
		
		public LevelChoiceAdapter(Activity aActivity, int textViewResourceId,
				ArrayList<Level> objects) {
			super(aActivity, textViewResourceId, objects);
			levels = objects;
			activity = aActivity;
		}
		
		// create a new ImageView for each item referenced by the Adapter
	    public View getView(int position, View convertView, ViewGroup parent) {
	        Button btnLevel = new Button(activity);

	        Level currentLevelChoice = levels.get(position);

	        if(currentLevelChoice != null)
	        {
	        	btnLevel.setText(""+currentLevelChoice.getLevelIndex());
	        	btnLevel.setOnClickListener(new SelectLevel());
	        }

	        return btnLevel;
	    }
	}
	
	/**
	 * Adapter om gridview met mogelijke antwoorden te vullen
	 * @author Brecht Jr.
	 *
	 */
	class AnswerChoiceAdapter extends ArrayAdapter<AnswerChoice>
	{
		PossibleAnswers possibleAnswers;
		Activity activity;
		
		public AnswerChoiceAdapter(Activity aActivity, int textViewResourceId,
				PossibleAnswers objects) {
			super(aActivity, textViewResourceId, objects);
			possibleAnswers = objects;
			activity = aActivity;
		}
		
		// create a new ImageView for each item referenced by the Adapter
	    public View getView(int position, View convertView, ViewGroup parent) {
	        Button btnAnswer = new Button(activity);

	        AnswerChoice currentAnswerChoice = possibleAnswers.get(position);

	        if(currentAnswerChoice != null)
	        {
	        	btnAnswer.setText(""+currentAnswerChoice.getAnswer());
	        	btnAnswer.setOnClickListener(new AnswerButton());
	        }

	        return btnAnswer;
	    }
	}
	
	/**
	 * 
	 * @author Brecht Jr.
	 *
	 */
	class SelectLevel implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			Button b = (Button)v;
		    String buttonText = b.getText().toString();
			//Voorbeeld van test game met speler(naam:test,leeftijd:34) en gekozen level
			activeGame = new Game(new Player("test",34),Integer.parseInt(buttonText));
			vfGameViewer.showNext();
			StartTimer();
		}
	}
	
	/**
	 * Start hoogst vrijgespeeld level
	 * @author Brecht Jr.
	 *
	 */
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
	
	/**
	 * Controleer antwoord via text input
	 * @author Brecht Jr.
	 *
	 */
	class AnswerTxt implements OnClickListener
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
				checkAnswer(strInput);		
		}
	}
	
	/**
	 * Controleer antwoord via knop
	 * @author Brecht Jr.
	 *
	 */
	class AnswerButton implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			Button b = (Button)v;
		    String buttonText = b.getText().toString();

			checkAnswer(buttonText);		
		}
	}
	
	/**
	 * Antwoord check functie
	 * @author Brecht Jr.
	 *
	 */
	public void checkAnswer(String strInput)
	{
		wasCorrectAnswer = activeGame.CalculateScore(Double.parseDouble(strInput));
		gameOver = activeGame.getIsGameOver();
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
		Exercise newExercise = activeGame.getExercise();
		lblQuestion.setText(""+newExercise.getQuestion());
		if(!gameOver){
			Log.d("GAMEOVER1",""+gameOver);
			aaAnswerChoices = new AnswerChoiceAdapter(GameActivity.this, R.id.gridAnswers, newExercise.getPossibleAnswers());
			gridAnswers.setAdapter(aaAnswerChoices);
		}
		else
		{
			Log.d("GAMEOVER2",""+gameOver);
			gridAnswers.setAdapter(null);
		}
	}
	
	// GAME LOOP
	private Handler mHandler = new Handler();

	private void StartTimer()
	{// Tijd begint te lopen
		
		//Game GUI
        lblLevel.setText("Level " + activeGame.getLevelIndex());
        lblTime.setText(""+activeGame.getTime());
        lblScore.setText(""+activeGame.getScore());
        Exercise newExercise = activeGame.getExercise();
		lblQuestion.setText(""+newExercise.getQuestion());
        aaAnswerChoices = new AnswerChoiceAdapter(GameActivity.this, R.id.gridAnswers, newExercise.getPossibleAnswers());
		gridAnswers.setAdapter(aaAnswerChoices);
		
		ResumeTimer();
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
			   // TODO Dit aanpassen naar game class en hier getisgameover
			   if(intCurrentGameTime <= 0)
			   {// Einde spel		   
				   //Bereken score
				   activeGame.TimesUp();
				   gameOver = activeGame.getIsGameOver();
				   //Verwijder inputveld
				   txtInput.setVisibility(View.GONE);
				   //Maak gridAnswers leeg
				   gridAnswers.setAdapter(null);
				   lblQuestion.setText(""+activeGame.getExercise().getQuestion());
			   }
			   else
			   {// Herhaal loop
				   mHandler.postDelayed(this, 1000);
			   }		   
		   }
		};
}
