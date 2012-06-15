package com.gmail.matsushige;

import com.gmail.takashi316.lib.MenuActivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

public class UsersActivity extends CommonMenuActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		View view = getLayoutInflater().inflate(R.layout.users_activity, null);
		FragmentManager fragment_manager = getFragmentManager();
		FragmentTransaction fragment_transaction = fragment_manager
				.beginTransaction();
		Fragment fragment = new UsersFragment();
		fragment_transaction.add(R.id.linearLayoutUsers, fragment);
		fragment_transaction.commit();
		setContentView(view);
		super.onCreate(savedInstanceState);
	}

	void resetUser() {
		SQLiteDatabase dbUsers = (new UsersDatabaseHelper(this))
				.getWritableDatabase();
		dbUsers.close();
	}

}// UsersActivity
