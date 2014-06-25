package com.svtask.adapters;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.svtask.utils.SettingsViewHolder;
import com.svtask2.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class SettingsItemsAdapter extends BaseAdapter implements OnClickListener {	

	private LayoutInflater inflater;
	private CharSequence[] words = null;
	private ArrayList<SettingsViewHolder> holderList;
	private ArrayList<Boolean> chBoxStatusList;
	private int selectedCount = 0;
	private int selectAllCount = 0;		
	private MenuItem selectAllMenuItem;	
	private SharedPreferences sharePreferences;
	
	public SettingsItemsAdapter(Context context, SharedPreferences sharePreferences) {

		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);		
		this.sharePreferences = sharePreferences;
		words = context.getResources().getTextArray(R.array.words);
		holderList = new ArrayList<SettingsViewHolder>();
		chBoxStatusList = new ArrayList<Boolean>();	
		selectAllCount = words.length + (((words.length - 1) * words.length) / 2);
		
		for (int i = 0; i < words.length; i++) {			
			holderList.add(new SettingsViewHolder());
			chBoxStatusList.add(false);
		}				
		loadSharedPreferences();
	}

	public int getSelectCount() {
		return selectedCount;
	}
	
	@Override
	public int getCount() {
		return holderList.size();
	}

	@Override
	public Object getItem(int position) {
		return holderList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
				
		if (convertView == null)
			convertView = inflater.inflate(R.layout.settings_list_item, null);
					
		SettingsViewHolder holder = holderList.get(position);		
				
		holder.tvWord = (TextView)convertView.findViewById(R.id.textView_item);		
		holder.tvWord.setText(words[position]);
		
		holder.chBox = (CheckBox)convertView.findViewById(R.id.checkBox_active_word);
		holder.chBox.setChecked(chBoxStatusList.get(position));
		holder.chBox.setTag(position);		
		holder.chBox.setOnClickListener(this);	
		
		return convertView;
	}

	@Override
	public void onClick(View v) {
		int id = Integer.parseInt(v.getTag().toString());		
		SettingsViewHolder holder = holderList.get(id);		
		chBoxStatusList.set(id, holder.chBox.isChecked());
		if(holder.chBox.isChecked()) {			
			selectedCount += (id + 1);
		}
		else {
			selectedCount -= (id + 1);
		}
		checkSelected();				
	}
	
	public void checkSelected() {
		if (selectedCount == selectAllCount) {
			selectAllMenuItem.setEnabled(false);
		} 
		else {
			selectAllMenuItem.setEnabled(true);
		}
			
	}
	
	public void selecAll() {
		for(int i = 0; i < getCount(); i ++) {		
			SettingsViewHolder holder = holderList.get(i);
			if (holder.chBox != null) {
				holder.chBox.setChecked(true);
			}
			chBoxStatusList.set(i, true);			
		}
		selectedCount = selectAllCount;
		checkSelected();
	}
	
	public void setMenu(Menu menu) {
		selectAllMenuItem = menu.findItem(R.id.select_all);
	}
	
	public void saveSharedPreferences() {
		Set<String> set = new HashSet<String>();
		for(int i = 0; i < chBoxStatusList.size(); i ++) {
			if(chBoxStatusList.get(i)) {
				set.add(Integer.toString(i));
			}
		}		
		sharePreferences.edit().putStringSet("selectedWords", set).commit();
	}
	
	public void loadSharedPreferences() {
		Set<String>set = sharePreferences.getStringSet("selectedWords", new HashSet<String>());		
		for(Iterator<String>iterator = set.iterator(); iterator.hasNext(); ) {
			int index = Integer.parseInt(iterator.next());
			chBoxStatusList.set(index, true);
			selectedCount += index + 1;
		}	
	}
}
