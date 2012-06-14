package com.gmail.matsushige;

import android.os.Bundle;

public class LogActivity extends MenuActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		getLayoutInflater().inflate(R.layout.log_activity, null);
		super.onCreate(savedInstanceState);
	}

}
