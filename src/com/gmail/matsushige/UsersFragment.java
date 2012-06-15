package com.gmail.matsushige;

import android.app.Activity;
import android.app.Fragment;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class UsersFragment extends Fragment {

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.users_fragment, container, false);
		assert view != null;

		Button buttonResetUsers = (Button) view
				.findViewById(R.id.buttonResetUsers);
		buttonResetUsers.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				UsersDatabaseHelper users_database_helper = new UsersDatabaseHelper(
						v.getContext());
				SQLiteDatabase sqlite_database = users_database_helper
						.getWritableDatabase();
				sqlite_database.close();
			}// onClick
		});

		// return super.onCreateView(inflater, container, savedInstanceState);
		return view;
	}
}// UserFragment
