package com.gmail.matsushige;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MenuActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}// onCreate

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater menu_inflater = getMenuInflater();
		menu_inflater.inflate(R.menu.menu, menu);
		return true;
	}// onCreateOptionsMenu

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.itemExit) {
			Intent intent = new Intent();
			intent.putExtra("text", "終了");
			setResult(RESULT_OK);
			finish();
			return true;
		}
		if (item.getItemId() == R.id.itemUsers) {
			Intent intent = new Intent(this, UsersActivity.class);
			startActivityForResult(intent, 0);
			return true;
		}
		if (item.getItemId() == R.id.itemMain) {
			Intent intent = new Intent(this, NFC1Activity.class);
			startActivityForResult(intent, 0);
			return true;
		}
		if (item.getItemId() == R.id.itemLog) {
			Intent intent = new Intent(this, LogActivity.class);
			startActivityForResult(intent, 0);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}// MenuActivity
