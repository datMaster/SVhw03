package com.svtask.adapters;

import com.svtask2.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class SettingsItemsAdapter extends BaseAdapter {
	
	private View view;
	private LayoutInflater inflater;
	private CharSequence[] words;
	
	public SettingsItemsAdapter(Context context) {
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		words = context.getResources().getTextArray(R.array.words);	
	}
	
	@Override
	public int getCount() {
		return words.length;
	}

	@Override
	public Object getItem(int position) {
		return words[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		view = convertView;
		if (view == null)
			view = inflater.inflate(R.layout.settings_list_item, null);
		
		TextView tvWord = (TextView)view.findViewById(R.id.textView_item);
		tvWord.setText(words[position]);
		CheckBox chBoxSelected = (CheckBox)view.findViewById(R.id.checkBox_active_word);	
		chBoxSelected.setTag(position);		
						
		return view;
	}
}
