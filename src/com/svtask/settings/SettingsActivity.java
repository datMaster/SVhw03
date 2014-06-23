package com.svtask.settings;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.svtask.adapters.SettingsItemsAdapter;
import com.svtask2.R;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
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
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.			
		
		switch (item.getItemId()) {
		case android.R.id.home: {
			Toast.makeText(getApplicationContext(), "home", Toast.LENGTH_LONG).show();
			finish();
			break;
		}
		case R.id.select_all: {
			Toast.makeText(getApplicationContext(), "select all", Toast.LENGTH_LONG).show();			
			
			break;
		}
			
		default:
			break;
		}
				
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_settings,
					container, false);
			
			ListView wordsList = (ListView)rootView.findViewById(R.id.listView1);
			SettingsItemsAdapter settingsAdapter = new SettingsItemsAdapter(getActivity());
			wordsList.setAdapter(settingsAdapter);
			
			return rootView;
		}
	}	
}
