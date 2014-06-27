package com.svtask.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import android.content.SharedPreferences;

import com.svtask.settings.Constants;

public class SharedPreferencesWorker {
	private SharedPreferences sharedPreferences;

	public SharedPreferencesWorker(SharedPreferences sharedPreferences) {
		this.sharedPreferences = sharedPreferences;
	}

	public void saveSharedPreferences(ArrayList<Boolean> chBoxStatusList) {
		Set<String> set = new HashSet<String>();
		for (int i = 0; i < chBoxStatusList.size(); i++) {
			if (chBoxStatusList.get(i)) {
				set.add(Integer.toString(i));
			}
		}
		sharedPreferences.edit().putStringSet(Constants.SHAREDPREFERENCES_KEY, set).commit();
	}
	
	public ArrayList<Integer> getAllowedWords() {
		ArrayList<Integer> allowedIndexes = new ArrayList<Integer>();
		Set<String>set = sharedPreferences.getStringSet(Constants.SHAREDPREFERENCES_KEY, new HashSet<String>());		
		for(Iterator<String>iterator = set.iterator(); iterator.hasNext(); ) {			
			allowedIndexes.add(Integer.parseInt(iterator.next()));						
		}	
		return allowedIndexes;
	}
		
}
