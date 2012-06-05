package les.syntra.androidsommen.gui;

import java.io.IOException;

import org.json.JSONException;

import les.syntra.androidsommen.R;
import les.syntra.androidsommen.logic.Database;
import les.syntra.androidsommen.logic.Player;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ChoosePlayerActivity extends Activity{
	EditText txtName, txtAge;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chooseplayer);
        String appName = getString(R.string.app_name);
        String activeScreen = getString(R.string.txtBtnChoosePlayer);
        setTitle(appName + " -> " + activeScreen);
        
        txtName = (EditText)findViewById(R.id.txtName);
        txtAge = (EditText)findViewById(R.id.txtAge);
        Button btnCreatePlayer=(Button)findViewById(R.id.btnCreatePlayer);
        
        try {
        	try {
				btnCreatePlayer.setOnClickListener(new CreateNewPlayer());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } catch(JSONException e) {
        	e.printStackTrace();
        }
	}
	
	class CreateNewPlayer  implements OnClickListener {
		@Override
		public void onClick(View v){
			
			String strName = ""+ txtName.getText();
			Integer intAge = Integer.parseInt( ""+txtAge.getText());
			
			Player plNew = new Player(strName,intAge) ;
			/*			try {
				plNew.toJSON();
				try {
				saveAll();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		*/	
			
		}
		
		public CreateNewPlayer() throws JSONException, IOException{
			
		}
		
	}
}
