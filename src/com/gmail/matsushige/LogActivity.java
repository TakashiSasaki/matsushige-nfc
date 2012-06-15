package com.gmail.matsushige;

import com.gmail.takashi316.lib.MenuActivity;

import android.os.Bundle;

public class LogActivity extends CommonMenuActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		getLayoutInflater().inflate(R.layout.log_activity, null);
		super.onCreate(savedInstanceState);
	}

}
