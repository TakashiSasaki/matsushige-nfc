package com.gmail.takashi316.sandbox;

import android.app.Activity;
import android.os.Bundle;
import com.gmail.matsushige.R;

public class HelloAndroidActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hello_main);
	}// onCreate
}// HelloAndroidActivity

