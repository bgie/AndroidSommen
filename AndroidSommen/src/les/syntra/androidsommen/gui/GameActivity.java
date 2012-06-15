package les.syntra.androidsommen.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONException;

import les.syntra.androidsommen.R;
import les.syntra.androidsommen.logic.AnswerChoice;
import les.syntra.androidsommen.logic.Database;
import les.syntra.androidsommen.logic.Exercise;
import les.syntra.androidsommen.logic.Game;
import les.syntra.androidsommen.logic.Level;
import les.syntra.androidsommen.logic.PossibleAnswers;
import les.syntra.androidsommen.logic.Score;
import les.syntra.androidsommen.logic.Sounds;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class GameActivity extends Activity {
	Database database = null;
	Sounds sounds = null;
	
    boolean gameOver = false;
    boolean wasCorrectAnswer = false;
    Game activeGame;
    ViewFlipper vfGameViewer;
    
    ScreenLevelSelection screenLevelSelection;
    ScreenGame screenGame;
    ScreenGameOver screenGameOver;
    
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
        
        sounds = new Sounds(this);
        
        vfGameViewer = (ViewFlipper)findViewById(R.id.gameViewer);
        
        //Choose level scherm
        screenLevelSelection = new ScreenLevelSelection();
        
        //Game scherm
        //wordt aangemaakt door de levelselectie
        
        //GameOver scherm
        //verplaatst naar screenGame -> ShowGameOver()
        
	}

	/**
	 * Klasse om level selectie scherm te instantieren
	 * @author Brecht Jr.
	 */
	class ScreenLevelSelection
	{
		Button btnNextLevel;
		GridView gridLevels;
		LevelChoiceAdapter aaLevelChoices;
		
		public ScreenLevelSelection()
		{
			btnNextLevel = (Button)findViewById(R.id.btnNextLevel);
			btnNextLevel.setOnClickListener(new NextLevel());
			gridLevels = (GridView)findViewById(R.id.gridLevels);
			
			 ArrayList<Level> levelArrayList = new ArrayList<Level>();
		        
		        // level selectie generatie
		        if(database.getActivePlayer() != null)
		        {
		        	for(int ii = database.getActivePlayer().getUnlockedLevelIndex();ii>=1;ii--)
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
		}
		
		
		/**
		 * Adapter om gridview met mogelijke levels te vullen
		 * @author Brecht Jr.
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
		 * Selecteer level om te spelen
		 * @author Brecht Jr.
		 */
		class SelectLevel implements OnClickListener
		{
			@Override
			public void onClick(View v) {
				Button b = (Button)v;
			    String buttonText = b.getText().toString();
			    //Start spel met huidige player en gekozen level
			    activeGame = new Game(database,Integer.parseInt(buttonText));
				//activeGame = new Game(database.getActivePlayer(),Integer.parseInt(buttonText));
			    vfGameViewer.setDisplayedChild(vfGameViewer.indexOfChild(findViewById(R.id.layoutGame)));
			    screenGame = new ScreenGame();
			}
		}
		
		/**
		 * Start hoogst vrijgespeeld level
		 * @author Brecht Jr.
		 */
		class NextLevel implements OnClickListener
		{
			@Override
			public void onClick(View v) {
				//Start spel met huidige player en huidig unlockedlevelindex
				activeGame = new Game(database,database.getActivePlayer().getUnlockedLevelIndex());
				//activeGame = new Game(database.getActivePlayer(),database.getActivePlayer().getUnlockedLevelIndex());
				vfGameViewer.setDisplayedChild(vfGameViewer.indexOfChild(findViewById(R.id.layoutGame)));
				screenGame = new ScreenGame();
			}
		}
	}
	
	class ScreenGame
	{
		TextView lblLevel,lblTime,lblScore,lblQuestion;
	    EditText txtInput;
	    Button btnAnswer;
	    GridView gridAnswers;
	    AnswerChoiceAdapter aaAnswerChoices;
		public ScreenGame()
		{
			lblLevel = (TextView)findViewById(R.id.lblLevel);
	        lblTime = (TextView)findViewById(R.id.lblTime);
	        lblScore = (TextView)findViewById(R.id.lblScore);
	        lblQuestion = (TextView)findViewById(R.id.lblQuestion);
	        txtInput = (EditText)findViewById(R.id.txtInput);
	        btnAnswer = (Button)findViewById(R.id.btnAnswer);
	        btnAnswer.setOnClickListener(new AnswerTxt());
	        gridAnswers = (GridView)findViewById(R.id.gridAnswers);
	        StartTimer();
		}
		
		/**
		 * Adapter om gridview met mogelijke antwoorden te vullen
		 * @author Brecht Jr.
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
		 * Controleer antwoord via text input
		 * @author Brecht Jr.
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
		 */
		public void checkAnswer(String strInput)
		{
			wasCorrectAnswer = activeGame.CalculateScore(Double.parseDouble(strInput));
			gameOver = activeGame.getIsGameOver();
			if(wasCorrectAnswer)
			{//Indien correct toon extra punten
				lblScore.setText(""+activeGame.getScore() + "+" + activeGame.getLevelIndex());
				sounds.PlayDing();
			}
			else
			{
				if(!gameOver)
				{//Indien niet einde spel en toch fout toon straftijd
					lblTime.setText(""+(activeGame.getTime() + "-" + activeGame.getLevelPenaltyTime()));
				}
				sounds.PlayBuzz();
			}
			
			if(!gameOver){
				Exercise newExercise = activeGame.getExercise();
				lblQuestion.setText(""+newExercise.getQuestion());
				aaAnswerChoices = new AnswerChoiceAdapter(GameActivity.this, R.id.gridAnswers, newExercise.getPossibleAnswers());
				gridAnswers.setAdapter(aaAnswerChoices);
			}
			else
			{
				gridAnswers.setAdapter(null);
				
				ShowGameOver();
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
				   
				   if(activeGame.getIsGameOver())
				   {// Einde spel		   
					   //Bereken score
					   activeGame.TimesUp();
					   gameOver = activeGame.getIsGameOver();
					   //Verwijder inputveld
					   //txtInput.setVisibility(View.GONE);
					   //Maak gridAnswers leeg
					   gridAnswers.setAdapter(null);
					   
					   ShowGameOver();
				   }
				   else
				   {// Herhaal loop
					   mHandler.postDelayed(this, 1000);
				   }		   
			   }
			};
			
		/**
		 * Toont het game over scherm
		 */
		private void ShowGameOver()
		{
			vfGameViewer.setDisplayedChild(vfGameViewer.indexOfChild(findViewById(R.id.layoutGameover)));
			screenGameOver = new ScreenGameOver(activeGame);
		}
	}
	
	/**
	 * Klasse om gameOver scherm te instantieren
	 * @author Brecht Jr.
	 */
	class ScreenGameOver
	{
		TextView lblEndTimePlayed,lblEndScore,lblEndCorrect,lblEndNeeded,lblLevelPassed;
		Button btnGoLevelSelection;
		Game activeGame;
		
		private HighScoreAdapter highScoreAdapter;
		
		public ScreenGameOver(Game aCurrentGame)
		{
			activeGame = aCurrentGame;
			lblEndTimePlayed = (TextView)findViewById(R.id.lblEndTimePlayed);
			lblEndScore = (TextView)findViewById(R.id.lblEndScore);
			lblEndCorrect = (TextView)findViewById(R.id.lblEndCorrect);
			lblEndNeeded = (TextView)findViewById(R.id.lblEndNeeded);
			lblLevelPassed = (TextView)findViewById(R.id.lblLevelPassed);
			btnGoLevelSelection = (Button)findViewById(R.id.btnGoLevelSelection);
			btnGoLevelSelection.setOnClickListener(new GoToLevelSelection());
			
			lblEndTimePlayed.setText(""+Math.round(activeGame.getPlayTime()/1000)+" sec.");
			lblEndScore.setText(""+activeGame.getScore());
			lblEndCorrect.setText(""+activeGame.getCurrentLevel().getTotalCorrectAnswers());
			lblEndNeeded.setText(""+activeGame.getCurrentLevel().getCorrectAnswersNeeded());
			if(activeGame.IsLevelCompleted())
			{
				String strLevelPassedMessage = getString(R.string.txtLblLevelPassedMessage);
				lblLevelPassed.setText(""+strLevelPassedMessage);
				sounds.PlayEndScreenPass();
			}
			else
			{
				lblLevelPassed.setText("");
				sounds.PlayEndScreenFail();
			}
			
			
			// Get the highscores
	        ArrayList<Score> ListHighScore = database.getHighScores();
	        
	        // create the adapter
	        highScoreAdapter = new HighScoreAdapter(GameActivity.this, R.layout.tmpl_highscores_item, ListHighScore);
	        ListView lstHighScores = (ListView)findViewById(R.id.lstHighScores);
	        
	        // set the adapter
	        lstHighScores.setAdapter((ListAdapter) highScoreAdapter);
	        
	        
			
		}
		
		/**
		 * Ga naar level selection scherm
		 * @author Brecht Jr.
		 */
		class GoToLevelSelection implements OnClickListener
		{
			@Override
			public void onClick(View v) {
				vfGameViewer.setDisplayedChild(vfGameViewer.indexOfChild(findViewById(R.id.layoutLevelSelection)));
				screenLevelSelection = new ScreenLevelSelection();
			}
		}
	}
	
	private class HighScoreAdapter extends ArrayAdapter<Score> {

	    private ArrayList<Score> scores;
	    
	    public HighScoreAdapter(Activity aContext, int ResId,ArrayList<Score> listHighScore)
	    {
	        super(aContext, ResId, listHighScore);
	        scores = listHighScore;
	    }
	  
	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	            View v = convertView;
	            if (v == null) {
                    LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    v = vi.inflate(R.layout.tmpl_highscores_item, null);
                }
	            
	            
	            Score o = scores.get(position);
	            
	            if (o != null) {
	                    TextView player= (TextView) v.findViewById(R.id.PlayerName);
	                    
	                    TextView level = (TextView) v.findViewById(R.id.Level);

	                    TextView dateTime = (TextView) v.findViewById(R.id.DateTime);

	                    TextView score = (TextView) v.findViewById(R.id.Score);
	                    
	                    if (player != null) {
	                    	Log.d("Player: ", "" + o.getPlayer() );
	                          player.setText("Name: "+o.getPlayer());                           
	                    }
	                    if(level != null){
	                          level.setText("Level: "+ o.getLevelIndex());
	                    }
	                    if(dateTime != null){
	                    	
	                    	Calendar cal = o.getDateTime();
	                    	Date dt = cal.getTime();
	                    	int mins = dt.getMinutes();
	                    	String strMins;
	                    	
	                    	if(mins>9)
	                    	{
	                    		strMins = ""+mins;
	                    	}
	                    	else
	                    	{
	                    		strMins = "0"+mins;
	                    	}

	                    	String timeStamp = dt.getDay() + "/" + dt.getMonth() + "/" + (1900 + dt.getYear()) +
	                    			" - " + dt.getHours() + ":" + strMins;
	                    	
	                    	dateTime.setText( timeStamp);
	                    }
	                    if(score != null){
	                    	score.setText("Score: "+ o.getScore());
	                    }
	            }
	            return v;
	    }
	    
	}
	
}