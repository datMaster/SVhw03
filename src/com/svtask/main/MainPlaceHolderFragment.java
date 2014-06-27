package com.svtask.main;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.svtask.logic.GameLogic;
import com.svtask.utils.SharedPreferencesWorker;
import com.svtask2.R;

public class MainPlaceHolderFragment extends Fragment {
		
	private MainViewHolder viewHolder;
	private SharedPreferencesWorker sharedPrefences;
	private GameLogic gameLogic;
	
	public MainPlaceHolderFragment(SharedPreferencesWorker sharedPref) {
		sharedPrefences = sharedPref;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main, container, false);
		viewHolder = new MainViewHolder();
		viewHolder.setTvPleaseInputLabel((TextView)rootView.findViewById(R.id.textView_please_eneter_label));
		viewHolder.setEtInput((EditText)rootView.findViewById(R.id.editText_inputed_words));
		viewHolder.setTvScore((TextView)rootView.findViewById(R.id.textView_score));
		viewHolder.setTvLives((TextView)rootView.findViewById(R.id.textView_lives));
		viewHolder.setTvEntered((TextView)rootView.findViewById(R.id.textView_entered));
		viewHolder.setTvRepeat((TextView)rootView.findViewById(R.id.textView_need_word));
		viewHolder.setTvTimer((TextView)rootView.findViewById(R.id.textView_timer));
		gameLogic = new GameLogic(getActivity(), sharedPrefences, viewHolder);
		
		return rootView;
	}
	
	@Override
	public void onResume() {
		super.onResume();			
		gameLogic.resume();
	}	
	
	public void stop() {			
		gameLogic.stop();
	}	
}
