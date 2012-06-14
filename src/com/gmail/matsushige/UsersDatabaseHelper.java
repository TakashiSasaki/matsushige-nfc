package com.gmail.matsushige;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class UsersDatabaseHelper extends SQLiteOpenHelper {

	public UsersDatabaseHelper(Context context) {
		super(context, "NFC1.2.sqlite", null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE users(" +
				"_id INTEGER PRIMARY KEY AUTOINCREMENT," +
				"type TEXT, id TEXT, user_name TEXT, math INTEGER)");
		
		ContentValues cv = new ContentValues();
		
		cv.put("type", "NfcF");
		cv.put("id", "0115E5005B0BB104");
		cv.put("user_name", "�w����");
		db.insert("users", null, cv);
		
		cv.put("type","NfcB");
		cv.put("id", "91D3B724");
		cv.put("user_name", "�Ƌ���");
		db.insert("users", null, cv);
		
		cv.put("type", "NfcA");
		cv.put("id", "8532D18C");
		cv.put("user_name", "taspo");
		db.insert("users", null, cv);
		
		cv.put("type","NfcF");
		cv.put("id", "01130200F10C3B01");
		cv.put("user_name", "�s�z �L��");
		db.insert("users", null, cv);
		

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
