package com.svtask.adapters;

import java.util.ArrayList;

import com.svtask.utils.SettingsViewHolder;
import com.svtask2.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class SettingsItemsAdapter extends BaseAdapter implements OnClickListener {	

	private LayoutInflater inflater;
	private static CharSequence[] words = null;
	private static ArrayList<SettingsViewHolder> holderList;
	private static ArrayList<Boolean> boolList;	
	
	public SettingsItemsAdapter(Context context) {

		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		words = context.getResources().getTextArray(R.array.words);
		holderList = new ArrayList<SettingsViewHolder>();
		boolList = new ArrayList<Boolean>();
		for (int i = 0; i < words.length; i++) {
			holderList.add(new SettingsViewHolder());
			boolList.add(false);
		}
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
		holder.chBox.setChecked(boolList.get(position));
		holder.chBox.setTag(position);		
		holder.chBox.setOnClickListener(this);								
		
		return convertView;
	}

	@Override
	public void onClick(View v) {
		int id = Integer.parseInt(v.getTag().toString());
		SettingsViewHolder holder = holderList.get(id);		
		boolList.set(id, holder.chBox.isChecked());
	}
}
