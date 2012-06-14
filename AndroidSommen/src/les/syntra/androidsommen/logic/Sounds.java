package les.syntra.androidsommen.logic;

import les.syntra.androidsommen.R;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.provider.Settings.System;
import android.util.Log;

public class Sounds {
	
	private Context context;
	private static boolean soundEnabled = false;
	private static Sounds _instance = null;
	
	private static SoundPool sounds;
	
	private static int buzz;
	private static int ding;
	private static MediaPlayer startScreenLoop;
	private static int endScreenPass;
	private static int endScreenFail;
	
	private int playIdBuzz;
	private int playIdDing;
	private int playIdStartScreenLoop;
	private int playIdEndScreenPass;
	private int playIdEndScreenFail;
	
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
	
	/**
	 * Maakt het enige Sounds object aan + initialiseerd de settings
	 * @param aContext De activity die dit oproept
	 */
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
	
	//GETTERS
	
	/**
	 * Kijkt of het geluid aanstaat
	 * @return (boolean) true indien geluid aanstaat
	 */
	public boolean getIsSoundEnabled()
	{
		int val = System.getInt(context.getContentResolver(), System.VOLUME_MUSIC, 0);
		soundEnabled = val != 0;
		Log.d("SOUNDS","Volume Music: "+val);
		return soundEnabled;
	}
	
	/**
	 * Vraagt het systeem volume op (voor games)
	 * @return (float) waarde tussen 0 en 1
	 */
	public float getSystemVolume()
	{
		AudioManager mgr = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
        float streamVolumeCurrent = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);
        float streamVolumeMax = mgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC);    
        float volume = streamVolumeCurrent / streamVolumeMax;
        return volume;
	}
	
	//METHODS
	public void PlayBuzz() {
	    if (!soundEnabled) return; // if sound is turned off no need to continue
	    StopAllSounds();
	    float volume = getSystemVolume();
	    playIdBuzz = sounds.play(buzz, volume, volume, 1, 0, 1);
	}
	
	public void PlayDing() {
	    if (!soundEnabled) return; // if sound is turned off no need to continue
	    StopAllSounds();
	    float volume = getSystemVolume();
	    playIdDing = sounds.play(ding, volume, volume, 1, 0, 1);
	}
	
	public void PlayEndScreenPass() {
	    if (!soundEnabled) return; // if sound is turned off no need to continue
	    StopAllSounds();
	    float volume = getSystemVolume();
	    playIdEndScreenPass = sounds.play(endScreenPass, volume, volume, 1, 0, 1);
	}
	
	public void PlayEndScreenFail() {
	    if (!soundEnabled) return; // if sound is turned off no need to continue
	    StopAllSounds();
	    float volume = getSystemVolume();
	    playIdEndScreenFail = sounds.play(endScreenFail, volume, volume, 1, 0, 1);
	}
	
	public final void PlayStartScreenLoop() {
		getIsSoundEnabled();
	    if (!soundEnabled) return;
	    StopAllSounds();
	    if (!startScreenLoop.isPlaying()) 
	    {
	    	startScreenLoop.seekTo(0);
	    	startScreenLoop.setLooping(true);
	    	float volume = getSystemVolume();
	    	startScreenLoop.setVolume(volume, volume);
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
	
	/**
	 * Stopt alle effect geluiden (SoundPool)
	 */
	public final void StopAllSounds()
	{
		if (!soundEnabled) return;
		sounds.stop(playIdBuzz);
		sounds.stop(playIdDing);
		sounds.stop(playIdEndScreenPass);
		sounds.stop(playIdEndScreenFail);
	}
}
