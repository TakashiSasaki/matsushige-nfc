package com.gmail.matsushige;

import android.os.Bundle;

public class UsersActivity extends MenuActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		getLayoutInflater().inflate(R.layout.users_activity, null);
		super.onCreate(savedInstanceState);
	}

}// UsersActivity

