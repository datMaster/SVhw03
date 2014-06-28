package com.svtask.settings;

import com.svtask.adapters.SettingsItemsAdapter;
import com.svtask.utils.SharedPreferencesWorker;
import com.svtask2.R;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class SettingsActivity extends ActionBarActivity {

	private static ListView wordsList;	
	private static SettingsItemsAdapter settingsAdapter;
	private SharedPreferencesWorker sharePreferences;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		
		sharePreferences = new SharedPreferencesWorker(getSharedPreferences(Constants.SHAREDPREFERENCES_APP_NAME, 
				Context.MODE_PRIVATE));
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment(sharePreferences)).commit();
		}						
		
		ActionBar abar = getSupportActionBar();
		abar.setDisplayHomeAsUpEnabled(true);
		abar.setDisplayShowHomeEnabled(false);
		abar.setDisplayShowTitleEnabled(true);		
		abar.setDisplayUseLogoEnabled(false);			
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
		settingsAdapter = (SettingsItemsAdapter) wordsList.getAdapter();
		settingsAdapter.setMenu(menu);
		settingsAdapter.checkSelected();
		
		return true;
	}
		
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.			
		
		switch (item.getItemId()) {
		case android.R.id.home: {
			settingsAdapter.saveSharedPreferences();
			finish();
			break;
		}
		case R.id.select_all: {
			settingsAdapter.selectAll();			
			break;
		}
			
		default:
			break;
		}
				
		return super.onOptionsItemSelected(item);
	}	
	
	@Override
	public void onBackPressed() {	   
		settingsAdapter.saveSharedPreferences();
		finish();
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		private static SharedPreferencesWorker sharedPreferences;
		public PlaceholderFragment(SharedPreferencesWorker sharedPreferences) {
			PlaceholderFragment.sharedPreferences = sharedPreferences;
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_settings, container, false);			
			
			wordsList = (ListView)rootView.findViewById(R.id.listView_settings);			
			SettingsItemsAdapter settingsAdapter = new SettingsItemsAdapter(getActivity(), sharedPreferences);
			wordsList.setAdapter(settingsAdapter);
									
			return rootView;
		}
	}		
}
