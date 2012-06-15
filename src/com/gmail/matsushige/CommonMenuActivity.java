package com.gmail.matsushige;

import android.os.Bundle;

import com.gmail.takashi316.lib.MenuActivity;

public class CommonMenuActivity extends MenuActivity {
	@SuppressWarnings("boxing")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.addActivityClass(R.id.itemLog, LogActivity.class);
		this.addActivityClass(R.id.itemMain, NFC1Activity.class);
		this.addActivityClass(R.id.itemUsers, UsersActivity.class);
		this.setExitItemId(R.id.itemExit);
		this.setMenuResourceId(R.menu.menu);
		super.onCreate(savedInstanceState);
	}
}
