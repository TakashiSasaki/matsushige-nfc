package com.gmail.matsushige;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class UsersActivity extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setActivityLayoutId(R.layout.users_activity);
		setFragmentContainer(R.id.linearLayoutUsers, UsersFragment.class);
		super.onCreate(savedInstanceState);
	}// onCreate

	void resetUser() {
		SQLiteDatabase dbUsers = (new UsersDatabaseHelper(this))
				.getWritableDatabase();
		dbUsers.close();
	}// resetUser

}// UsersActivity
