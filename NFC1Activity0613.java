package com.gmail.matsushige;

import java.util.Calendar;
import java.util.Date;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.nfc.*;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.nfc.tech.*;

public class NFC1Activity extends Activity {
	/** Called when the activity is first created. */
	private TextView textView1;
	private TextView textView2;
	private TextView textView3;
	private TextView textView4;
	private TextView textView5;
	private Button buttonActionNdef;
	private Button buttonActionTech;
	private Button buttonActionTag;
	private Button buttonCheck; 
	private String judge = null;
	private int stand = 0;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d("NFC1Activity", "onCreate");

		SQLiteDatabase db = (new DatabaseHelper(this)).getWritableDatabase();
		db.close();
		//SQLiteDatabase dbUsers = (new UsersDatabaseHelper(this)).getWritableDatabase();
		//dbUsers.close();

		setContentView(R.layout.main);
		textView1 = (TextView) findViewById(R.id.textView1);
		textView2 = (TextView) findViewById(R.id.textView2);
		textView3 = (TextView) findViewById(R.id.textView3);
		textView4 = (TextView) findViewById(R.id.textView4);
		textView5 = (TextView) findViewById(R.id.textView5);

		textView1.setText("start read");

		buttonActionNdef = (Button) findViewById(R.id.buttonActionNdef);
		buttonActionTech = (Button) findViewById(R.id.buttonActionTech);
		buttonActionTag = (Button) findViewById(R.id.buttonActionTag);
		Button buttonClear = (Button) findViewById(R.id.buttonClear);
		buttonCheck = (Button) findViewById(R.id.buttonCheck);
		
		buttonClear.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				buttonActionNdef.setBackgroundColor(Color.GRAY);
				buttonActionTag.setBackgroundColor(Color.GRAY);
				buttonActionTech.setBackgroundColor(Color.GRAY);
				textView1.setText("waiting");
				textView2.setText("waiting");
				textView3.setText("waiting");
				textView4.setText("waiting");
				textView5.setText("waiting");
			}// onClick
		});// setOnClickListener
		
		buttonCheck.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				openUsers();
			}//onClick
		});//setOnClickListener

	}// onCreate

	@Override
	public void onResume() {
		super.onResume();
		Log.d("NFC1Activity", "onResume");
		String action = getIntent().getAction();
		if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {
			actionNdefDiscovered();
		} else if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)) {
			Log.d("NFC1Activity", "TECH_DISCOVERED");
			actionTechDiscovered();
		} else if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)) {
			actionTagDiscovered();
		}// if　
		else{
			Log.d("NFC1Activity", "finish");
		}//else
	} // onResume
	
	public void onPause(){
		super.onPause();
		Log.d("NFC1Avtivity", "onPause");
	}//onPause

	public void onDestroy(){
		super.onDestroy();
		Log.d("NFC1Activity", "onDestroy");
	}//onDestroy
	
	private void actionTechDiscovered() {
		Log.d("NFC1Activity", "actionTechDiscovered");
		buttonActionTech.setBackgroundColor(Color.RED);
		readNfc();
	}

	private void actionNdefDiscovered() {
		buttonActionNdef.setBackgroundColor(Color.RED);
	}

	private void actionTagDiscovered() {
		buttonActionTag.setBackgroundColor(Color.RED);
		readNfc();
	}// actionTagDiscovered

	public void readNfc() {
		Intent intent = getIntent();
		byte[] id = intent.getByteArrayExtra(NfcAdapter.EXTRA_ID);
		Tag tag = (Tag) intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

		String idD = "ID : " + hex(id);
		textView1.setText(idD);
		
		String[] techList = tag.getTechList();
		String tech = "TechList : ";
		for (String w : techList) {
			tech += w;
		}// for
		textView2.setText(tech);

		if (tech.contains("NfcF")) {
			// TODO: 要検討
			recordId("NfcF", hex(id).toUpperCase());

			NfcF techF = NfcF.get(tag);
			byte[] mc = techF.getManufacturer();
			String mcD = "ManufactureCode : " + hex(mc);
			textView3.setText(mcD);

			byte[] sc = techF.getSystemCode();
			String scD = "SystemCode : " + hex(sc);
			textView4.setText(scD);			
		}// if <NfcF>
		else if (tech.contains("NfcB")) {
			// TODO: 要検討
			recordId("NfcB", hex(id).toUpperCase());

			NfcB nfcB = NfcB.get(tag);
			byte[] ad = nfcB.getApplicationData();
			String adD = "ApplicationData : " + hex(ad);
			textView3.setText(adD);
			
			byte[] pi = nfcB.getProtocolInfo();
			String piD = "ProtocolInfo : " + hex(pi);
			textView4.setText(piD);

			if (tech.contains("IsoDep")) {
				IsoDep isoDep = IsoDep.get(tag);
				byte[] hlr = isoDep.getHiLayerResponse();
				String hlrD = "HiLayerResponse : " + hex(hlr);
				textView5.setText(hlrD); // 免許では読めなかった
			}// if <IsoDep>
		}// else if <NfcB>
		else if (tech.contains("NfcA")) {
			// TODO: 要検討
			recordId("NfcA", hex(id).toUpperCase());

			NfcA nfcA = NfcA.get(tag);

			byte[] at = nfcA.getAtqa();
			String atD = "ATQA/SENS_RES : " + hex(at);
			textView3.setText(atD);

			short sak = nfcA.getSak();
			String sakD = String.format("SAK : " + "%02x", sak);
			textView4.setText(sakD);

			if (tech.contains("Classic")) {
				MifareClassic mifareClassic = MifareClassic.get(tag);

				int size = mifareClassic.getSize();
				String sizeD = Integer.toString(size);

				int sector = mifareClassic.getSectorCount();
				String sectorD = Integer.toString(sector);

				int block = mifareClassic.getBlockCount();
				String blockD = Integer.toString(block);

				textView5.setText("MemorySize : " + sizeD + "\nSecterCount : "
						+ sectorD + "\nBlockCount : " + blockD);
				
			}// if <MifareClassic>
		}// else if <NfcA>
		else {
			textView3.setText("NOT READ");
		}// else
	}// readNfc

	private void recordId(String type, String ID) {
		long timestamp = Calendar.getInstance().getTimeInMillis();
		ContentValues cv = new ContentValues();
		cv.put("timestamp", timestamp);
		cv.put("type", type);
		cv.put("ID", ID);
		SQLiteDatabase db = (new DatabaseHelper(this)).getWritableDatabase();
		db.insert("touch", null, cv);
		db.close();

		db = (new DatabaseHelper(this)).getReadableDatabase();
		Cursor c = db.query("touch", null, null, null, null, null, "timestamp desc");
		//Log.d("NFC1Activity", "" + c.getCount());
		String s = "";
		c.moveToFirst();
		for (int i = 0; i < c.getCount(); ++i) {
			long _id = c.getLong(c.getColumnIndex("_id"));
			String type_ = c.getString(c.getColumnIndex("type"));
			String ID_ = c.getString(c.getColumnIndex("ID"));
			long timestamp_ = c.getLong(c.getColumnIndex("timestamp"));
			s += _id + " " + type_ + " " + ID_ + " " + timestamp_ + "\n";
			c.moveToNext();
		}// for
		((TextView) (findViewById(R.id.textViewTouch))).setText(s);
		db.close();
		
		SQLiteDatabase users = (new UsersDatabaseHelper(this)).getReadableDatabase();
		String where = "type = ?";
		String[] where_arg = {type};
		Cursor cursor = users.query("users", null, where, where_arg, null, null, null);
		/** 一致するtypeを確認 */		
		judge = null;
		textView5.setText(Integer.toString(cursor.getCount()));
		while(cursor.moveToNext()){
			String idre = cursor.getString(cursor.getColumnIndex("id"));
			if (ID.equals(idre)){
				/** 一致するidを確認 */
				judge = cursor.getString(cursor.getColumnIndex("user_name"));
				/** judgeにuser_nameを代入 */
			}//if
		}//while
		//String mess ="";
		if (judge != null){
			/** judgeに値が入っているか確認 */
			//mess = "こんにちは、" + judge + " さん";
			showDialog(1);
		}else{
			//mess = "登録されていないカードです。";
			showDialog(0);
		}//else
		//new AlertDialog.Builder(this)
		//.setTitle("結果")
		//.setMessage(mess)
		//.setNeutralButton("OK", null).show();
		//Log.d("Nfc1Activity", "dialog_out");
		
		users.close();
	}//recordId

	public String hex(byte[] data) {
		String hexText = "";
		for (byte v : data) {
			hexText += String.format("%02x", new Object[] { v });
		}// for
		return hexText;
	}// hex
	
	public void openUsers(){
		SQLiteDatabase usersData = (new UsersDatabaseHelper(this)).getReadableDatabase();
		Cursor c = usersData.query("users", null, null, null, null, null, null);
		String s = "";
		c.moveToFirst();
		for (int i = 0; i < c.getCount(); ++i) {
			long _id = c.getLong(c.getColumnIndex("_id"));
			String type_ = c.getString(c.getColumnIndex("type"));
			String ID_ = c.getString(c.getColumnIndex("id"));
			String name_ = c.getString(c.getColumnIndex("user_name"));
			s += _id + " " + type_ + " " + ID_ + " " + name_ + "\n";
			c.moveToNext();
		}// for
		((TextView) (findViewById(R.id.textViewTouch))).setText(s);
		usersData.close();
	}//openUsers
	
	protected Dialog onCreateDialog(int id){
		return createDialog(id);
	}//onCreateDialog
	private Dialog createDialog(int id){
		switch(id){
		case 0:
			AlertDialog.Builder builder0 = new AlertDialog.Builder(this);
			builder0.setTitle("結果");
			builder0.setMessage("登録されていないカードです。");
			builder0.setPositiveButton("OK", new DialogClickListener());
			return builder0.create();		
		case 1:
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setTitle("結果");
            builder1.setMessage("こんにちは" + judge + "さん");
            builder1.setPositiveButton("OK", new DialogClickListener());
            return builder1.create();
		case 2:
			AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
			builder2.setTitle("終了");
			builder2.setMessage("終了しました。");
			builder2.setPositiveButton("OK", null);
			return builder2.create();
		default:
			Log.e("NFC1Activity", "createDialog_default");
        }//switch
        return null;
	}//create+

	class DialogClickListener implements DialogInterface.OnClickListener{
		public void onClick(DialogInterface dialog, int which){
			showDialog(2);
		}//onClick
	}//Dialogclick
	
}// Nfc1Activity