package les.syntra.androidsommen.logic;

import les.syntra.androidsommen.R;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.provider.Settings.System;

public class Sounds {
	/*private static SoundPool sounds;
	private static int select;
	private static int found;
	private static int next;
	private static MediaPlayer music;
	private static boolean sound = false;*/
	
	//System mSystemSettings = new System();
	//mContentResolver = this.getContentResolver();
	
	private Context context;
	private static boolean soundEnabled = false;
	private static Sounds _instance = null;
	
	private static SoundPool sounds;
	/*private static int select;
	private static int found;
	private static int next;
	private static MediaPlayer music;*/
	
	private static int buzz;
	private static int ding;
	private static MediaPlayer startScreenLoop;
	private static int endScreenPass;
	private static int endScreenFail;
	
	/**
	 * Singleton factory method om de enige echte sounds op te vragen.
	 * Alle gegevens worden ineens vanaf schijf ingelezen.
	 * @param ctx	Een context voor security. Geef een Activity mee als context.
	 * @return Het sounds object.
	 */
	public static Sounds instance(Context ctx)
	{
		if(_instance == null)
			_instance = new Sounds(ctx);
		return _instance;
	}
	
	public Sounds(Context aContext)
	{
		context = aContext;
		getIsSoundEnabled();
		
		sounds = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
	    // ref. naar sounds
	    buzz = sounds.load(context, R.raw.buzz, 1);
	    ding = sounds.load(context, R.raw.ding, 1);
	    endScreenPass = sounds.load(context, R.raw.end_pass, 1);
	    endScreenFail = sounds.load(context, R.raw.end_fail, 1);
	    // Begin muziek
	    startScreenLoop = MediaPlayer.create(context, R.raw.start_loop);
	}
	
	public boolean getIsSoundEnabled()
	{
		int val = System.getInt(context.getContentResolver(), System.VOLUME_MUSIC, 0);
		soundEnabled = val != 0;
		return soundEnabled;
	}
	
	
	//public static void loadSound(Context context) {
	    //sound = SilhouPreferences.sound(context); // should there be sound?
	    //sounds = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
	    // three ref. to the sounds I need in the application
	    //select = sounds.load(context, R.raw.zap1, 1);
	    //found = sounds.load(context, R.raw.accent1, 1);
	    //next = sounds.load(context, R.raw.accent2, 1);
	    // the music that is played at the beginning and when there is only 10 seconds left in a game
	    //music = MediaPlayer.create(context, R.raw.silhouette2);
	//}
	
	public void PlayBuzz() {
	    if (!soundEnabled) return; // if sound is turned off no need to continue
	    sounds.stop(buzz);
	    sounds.play(buzz, 1, 1, 1, 0, 1);
	}
	
	public void PlayDing() {
	    if (!soundEnabled) return; // if sound is turned off no need to continue
	    sounds.stop(ding);
	    sounds.play(ding, 1, 1, 1, 0, 1);
	}
	
	public void PlayEndScreenPass() {
	    if (!soundEnabled) return; // if sound is turned off no need to continue
	    sounds.stop(endScreenPass);
	    sounds.play(endScreenPass, 1, 1, 1, 0, 1);
	}
	
	public void PlayEndScreenFail() {
	    if (!soundEnabled) return; // if sound is turned off no need to continue
	    sounds.stop(endScreenFail);
	    sounds.play(endScreenFail, 1, 1, 1, 0, 1);
	}
	
	public final void PlayStartScreenLoop() {
	    if (!soundEnabled) return;
	    if (!startScreenLoop.isPlaying()) 
	    {
	    	startScreenLoop.seekTo(0);
	    	startScreenLoop.start();
	    }
	}
	
	public final void PauseStartScreenLoop() {
	    if (!soundEnabled) return;
	    if (startScreenLoop.isPlaying())
	    {
	    	startScreenLoop.pause();
	    }
	}
}
