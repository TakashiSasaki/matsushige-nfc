package com.gmail.matsushige;

import com.gmail.takashi316.lib.android.activity.BasicFragment;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class UsersFragment extends BasicFragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		this.setFragmentLayoutId(R.layout.users_fragment);
		super.onCreate(savedInstanceState);
	}// onCreate

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = super.onCreateView(inflater, container, savedInstanceState);

		this.setButtonOnClickListener(R.id.buttonResetUsers,
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						UsersDatabaseHelper users_database_helper = new UsersDatabaseHelper(
								v.getContext());
						SQLiteDatabase sqlite_database = users_database_helper
								.getWritableDatabase();
						sqlite_database.close();
					}// onClick
				});// onClickListener
		return view;
	}// onCreateView

}// UserFragment
