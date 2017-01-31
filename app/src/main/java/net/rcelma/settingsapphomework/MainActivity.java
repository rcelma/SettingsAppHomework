package net.rcelma.settingsapphomework;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
	private static final String SHARED_PREF_THIS = "preferences";
	private EditText et;
	private TextView tv;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
	}

	@Override
	protected void onStart(){
		super.onStart();
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
		String textSize = prefs.getString("text_size", "14");
		Log.d("TAG2!!", textSize);
		et = (EditText) findViewById(R.id.et);
		tv = (TextView) findViewById(R.id.tv);
		Log.d("TAG", "ET = " + et + " TV = " + tv);
		et.setTextSize(Float.parseFloat(textSize));
		tv.setTextSize(Float.parseFloat(textSize));
		et.setText(prefs.getString("et", ""));
		tv.setText(prefs.getString("tv", ""));
	}

	@Override
	protected void onStop(){
		super.onStop();
		Log.d("STOP", "STOP");
		savePref("et", et.getText().toString());
		savePref("tv", et.getText().toString());
	}

	public void savePref(String key, String value) {
		SharedPreferences shared = PreferenceManager.getDefaultSharedPreferences(this);
		SharedPreferences.Editor editor = shared.edit();
		editor.putString(key, value);
		editor.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	public void triggerSettings(MenuItem item) {
		Intent intent = new Intent(this, SettingsActivity.class);
		startActivity(intent);
	}
}