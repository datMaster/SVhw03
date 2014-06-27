package com.svtask.main;

import java.util.ArrayList;
import java.util.Random;

import android.os.CountDownTimer;
import android.os.IBinder;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.svtask.settings.Constants;
import com.svtask.utils.SharedPreferencesWorker;

public class MainViewHolder {
	private EditText etInput;
	private TextView tvScore;
	private TextView tvLives;
	private TextView tvEntered;
	private TextView tvRepeat;
	private TextView tvTimer;
	private TextView tvPleaseInputLabel;
	
	public void tvPleaseInputLabelHide() {
		tvPleaseInputLabel.setVisibility(View.GONE);
	}
	
	public void tvPleaseInputLabelShow() {
		tvPleaseInputLabel.setVisibility(View.VISIBLE);
	}
	
	public void etIputDisable() {
		etInput.setEnabled(false);
	}
	
	public void etIputEnable() {
		etInput.setEnabled(true);
	}
	
	public void tvRepeatUpdate(String data) {
		tvRepeat.setText(data);
	}
	
	public EditText getEtInput() {
		return etInput;
	}
	public void setEtInput(EditText etInput) {
		this.etInput = etInput;
	}
	public TextView getTvRepeat() {
		return tvRepeat;
	}
	public void setTvRepeat(TextView tvRepeat) {
		this.tvRepeat = tvRepeat;
	}
	public void setTvScore(TextView tvScore) {
		this.tvScore = tvScore;
	}
	public void setTvLives(TextView tvLives) {
		this.tvLives = tvLives;
	}
	public void setTvEntered(TextView tvEntered) {
		this.tvEntered = tvEntered;
	}
	public void setTvTimer(TextView tvTimer) {
		this.tvTimer = tvTimer;
	}
	public void setTvPleaseInputLabel(TextView tvPleaseInputLabel) {
		this.tvPleaseInputLabel = tvPleaseInputLabel;
	}
	
	public void setTimerTex(String data) {
		tvTimer.setText(data);
	}
	
	public void etInputSetTextChangeListener(TextWatcher watcher) {
		etInput.addTextChangedListener(watcher);
	}
	
	public void tvEnteredUpdate() {
		tvEntered.setText(etInput.getText());
	}
	
	public String getTvRepeatString() {
		return tvRepeat.getText().toString();
	}
	
	public void updateLives(String data) {
		tvLives.setText(data);
	}
	
	public void updateScores(String data) {
		tvScore.setText(data);
	}
	
	public void clearInputs() {
		etInput.setText(Constants.EMPTY);
		tvEntered.setText(Constants.EMPTY);
		tvTimer.setText(Constants.EMPTY);				
	}
		
	public IBinder getInputWindowToken() {
		return etInput.getWindowToken();
	}
	
}
