package com.svtask.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.svtask.settings.Constants;
import com.svtask.settings.SettingsActivity;
import com.svtask.utils.SharedPreferencesWorker;
import com.svtask2.R;

public class MainActivity extends ActionBarActivity {			
	
	private SharedPreferencesWorker sharedPreferences;
	private MainPlaceHolderFragment placeHolder;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		sharedPreferences = new SharedPreferencesWorker(getSharedPreferences(Constants.SHAREDPREFERENCES_APP_NAME, 
				Context.MODE_PRIVATE));
		placeHolder = new MainPlaceHolderFragment(sharedPreferences);				
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
			.add(R.id.container, placeHolder).commit();
		}		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {			
			Intent settings = new Intent(this, SettingsActivity.class);
			startActivity(settings);
			placeHolder.stop();											
			return true;
		}
		return super.onOptionsItemSelected(item);
	}	
}
