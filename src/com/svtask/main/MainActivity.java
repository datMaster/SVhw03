package com.svtask.main;

import java.util.ArrayList;
import java.util.Random;

import com.svtask.settings.SettingsActivity;
import com.svtask.utils.SharedPreferencesWorker;
import com.svtask2.R;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager.OnActivityResultListener;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {			
	
	private SharedPreferencesWorker sharedPreferences;
	private ArrayList<Integer> allowedWords = new ArrayList<Integer>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);		
		sharedPreferences = new SharedPreferencesWorker(getSharedPreferences(Constants.SHAREDPREFERENCES_APP_NAME, 
				Context.MODE_PRIVATE));
		allowedWords = sharedPreferences.getAllowedWords();
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment(allowedWords)).commit();
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
									
			return true;
		}
		return super.onOptionsItemSelected(item);
	}	

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		private EditText etInput;
		private TextView tvScore;
		private TextView tvLives;
		private TextView tvEntered;
		private TextView tvRepeat;
		private TextView tvTimer;
		private Boolean isTimerStarted = false;
		private CharSequence[] words;
		private int score;
		private int lives;
		private Random rand;				
		private CountDownTimer timer;
		private ArrayList<Integer> allowedWordsIndexes;
				
		public PlaceholderFragment(ArrayList<Integer> alloweWords) {
			allowedWordsIndexes = alloweWords;
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container, false);
			
			etInput = (EditText)rootView.findViewById(R.id.editText_inputed_words);
			tvScore = (TextView)rootView.findViewById(R.id.textView_score);
			tvLives = (TextView)rootView.findViewById(R.id.textView_lives);
			tvEntered = (TextView)rootView.findViewById(R.id.textView_entered);
			tvRepeat = (TextView)rootView.findViewById(R.id.textView_need_word);
			tvTimer = (TextView)rootView.findViewById(R.id.textView_timer);
			words = getResources().getTextArray(R.array.words);			
			rand = new Random();
			
			timer = new CountDownTimer(Constants.TIMER_IN_FUTURE, Constants.TIMER_INTERVAL) {
				
				@Override
				public void onTick(long millisUntilFinished) {					
					if(isTimerStarted) {
						tvTimer.setText(((Integer)((int)millisUntilFinished / Constants.TIMER_INTERVAL)).toString());
					}
				}
				
				@Override
				public void onFinish() {					
					if(isTimerStarted) {						
						dead();
					}
				}
			};
			
			etInput.addTextChangedListener(new TextWatcher() {
				
				@Override
				public void onTextChanged(CharSequence s, int start, int before, int count) {
					tvEntered.setText(etInput.getText());						
					String need = tvRepeat.getText().toString();					 										
					if(need.length() == count) {						
						if(need.equals(s.toString())) {
							nextWord(Constants.LIVING);
						}
					}
				}
				
				@Override
				public void beforeTextChanged(CharSequence s, int start, int count,	int after) {				
					if(!isTimerStarted) {
						timer.start();
						isTimerStarted = true;
					}
				}
				
				@Override
				public void afterTextChanged(Editable s) {
					// TODO Auto-generated method stub					
				}
			});						
			
			init();
			
			return rootView;
		}
		
		private void init() {
			score = Constants.SCORE_INIT;
			lives = Constants.LIVES;
			updateLives();
			updateScore();
			if(allowedWordsIndexes.size() == 0) {
				tvRepeat.setText(getString(R.string.help_string));
				etInput.setEnabled(false);
			}
			else {
				nextNeedString();
			}
		}
		
		private void nextWord(Boolean deadStatus) {
			stopTimer();
			if(deadStatus == Constants.LIVING) {
				score ++;
				updateScore();							
			}
			clearInputs();
			nextNeedString();				
			startTimer();
		}
		
		private void nextNeedString() {			
			int id = rand.nextInt(allowedWordsIndexes.size() - 1);			
			tvRepeat.setText(words[allowedWordsIndexes.get(id)]);
		}
		
		private void dead() {			
			if(lives < 1) {							
				clearInputs();
				stopTimer();
				showAlert();
			}
			else {
				lives --;
				updateLives();
				nextWord(Constants.DEADED);
			}
		}
		
		private void updateLives() {
			tvLives.setText(getString(R.string.lives) + lives);
		}
		
		private void updateScore() {
			tvScore.setText(getString(R.string.score) + score);
		}
		
		private void clearInputs() {
			etInput.setText(Constants.EMPTY);
			tvEntered.setText(Constants.EMPTY);
			tvTimer.setText(Constants.EMPTY);
		}
		
		private void showAlert() {
			hideSoftKeyboard(getActivity().getApplicationContext());
			new AlertDialog.Builder(getActivity())
		    .setTitle(getString(R.string.gameover))
		    .setMessage(getString(R.string.you_got) 
		    		+ " " + score + " " 
		    		+ getString(R.string.you_scores))
		    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int which) { 
		            init();
		        }
		     })
		    .setIcon(android.R.drawable.ic_dialog_info)
		    .show();
		}
		
		private void stopTimer() {
			timer.cancel();
			isTimerStarted = false;
		}
		
		private void startTimer() {
			timer.start();
			isTimerStarted = true;
		}
		
		public void hideSoftKeyboard(Context context) {
			InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(etInput.getWindowToken(), 0);
		}
	}

}
