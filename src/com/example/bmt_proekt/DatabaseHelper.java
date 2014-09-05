package com.example.bmt_proekt;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.animation.BounceInterpolator;
import android.widget.EditText;

@SuppressLint("NewApi")
public class DatabaseHelper extends Activity{

	
	public static final String KEY_ROWID="_id";
	public static final String KEY_NAME="persons_name";
	public static final String KEY_SURNAME="persons_surname";
	public static final String KEY_EMBG="persons_idnumber";
	public static final String KEY_STREET="persons_street";
	public static final String KEY_CITY="persons_city";
	public static final String KEY_USERNAME="persons_username";
	public static final String KEY_PASSWORD="persons_simCard";
	public static final String KEY_PHONEID="persons_PhoneId";
	public static final String KEY_DATEBIRTH="persons_dateBirth";
	
	private static final String DATABASE_NAME="persondb";
	private static final String DATABASE_TABLE="peopletable";
	private static final int DATABASE_VERSION=1;
	

	private DbHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;
	
	
	
	private static class DbHelper extends SQLiteOpenHelper{

		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("CREATE TABLE "+ DATABASE_TABLE +" (" + 
					KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + 
					KEY_NAME + " TEXT, " +
					KEY_SURNAME + " TEXT, " +
					KEY_EMBG + " TEXT, " +
					KEY_DATEBIRTH + " TEXT, " +
					KEY_STREET + " TEXT, " +
					KEY_USERNAME + " TEXT not null, " +
					KEY_PASSWORD + " TEXT not null, " +
					KEY_PHONEID + " TEXT, " +
					KEY_CITY + " TEXT); "		
			);
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS");
			onCreate(db);
			
		}	
	}

	public DatabaseHelper(Context c){
		ourContext=c;
	}
	
	public DatabaseHelper open() throws SQLException{
		ourHelper = new DbHelper(ourContext);
		ourDatabase=ourHelper.getWritableDatabase();
		return this;
		
	}
	
	public void close(){
		ourHelper.close();
	}

	public long createEntry(String name, String surname, String embg,String dateBirth, String street, String city,String username, String Password,String PhoneID) {
		// TODO Auto-generated method stub
		ContentValues cv=new ContentValues();
		cv.put(KEY_NAME, name);
		cv.put(KEY_SURNAME, surname);
		cv.put(KEY_EMBG, embg);
		cv.put(KEY_DATEBIRTH, dateBirth);
		cv.put(KEY_STREET, street);
		cv.put(KEY_CITY, city);
		cv.put(KEY_USERNAME, username);
		cv.put(KEY_PASSWORD, Password);
		cv.put(KEY_PHONEID, PhoneID);
		return ourDatabase.insert(DATABASE_TABLE, null, cv);
	}

	public String getData() {
		
		
		String[] columns=new String[]{KEY_ROWID, KEY_NAME, KEY_SURNAME, KEY_EMBG,KEY_DATEBIRTH, KEY_STREET, KEY_CITY, KEY_USERNAME,KEY_PASSWORD , KEY_PHONEID};
		
		Cursor c= ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
		
		
				String result="";
		
		
		int iRow=c.getColumnIndex(KEY_ROWID);
		int iname=c.getColumnIndex(KEY_NAME);
		int isurname=c.getColumnIndex(KEY_SURNAME);
		int iembg=c.getColumnIndex(KEY_EMBG);
		int idate=c.getColumnIndex(KEY_DATEBIRTH);
		int iStreet=c.getColumnIndex(KEY_STREET);
		int iCity=c.getColumnIndex(KEY_CITY);
		int iUsename=c.getColumnIndex(KEY_USERNAME);
		int iPassword=c.getColumnIndex(KEY_PASSWORD);
		int iPhoneID=c.getColumnIndex(KEY_PHONEID);
		
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			result = result+c.getString(iRow) + " "+c.getString(iname)+" "+c.getString(isurname)+" "+c.getString(iembg)+" "+c.getString(idate)+" "+c.getString(iStreet)+" "+c.getString(iCity)+" "+c.getString(iUsename)+" "+c.getString(iPassword)+" "+c.getString(iPhoneID)+"\n";
			
		}
		
		
		
		return result;
	}
	
	
	public String getIme(){
		
		return KEY_NAME;
	}
	
	
	
	
	public String getDataCustom(String password,String username){
	
		
		

		Cursor c= ourDatabase.query(DATABASE_TABLE, 
				new String[]{KEY_ROWID, KEY_NAME, KEY_SURNAME, KEY_EMBG,KEY_DATEBIRTH, KEY_STREET, KEY_CITY, KEY_USERNAME ,KEY_PASSWORD, KEY_PHONEID}
				, KEY_PASSWORD +" like "+"'"+password+"'"+" and " +KEY_USERNAME+" like "+"'"+username+"'", null, null, null, null);
				String result="";
//		
//		
		int iRow=c.getColumnIndex(KEY_ROWID);
		int iname=c.getColumnIndex(KEY_NAME);
		int isurname=c.getColumnIndex(KEY_SURNAME);
		int iembg=c.getColumnIndex(KEY_EMBG);
		int iDate=c.getColumnIndex(KEY_DATEBIRTH);
		int iStreet=c.getColumnIndex(KEY_STREET);
		int iCity=c.getColumnIndex(KEY_CITY);
		int iUsername= c.getColumnIndex(KEY_USERNAME);
		int iPassword=c.getColumnIndex(KEY_PASSWORD);
		int iPhoneID=c.getColumnIndex(KEY_PHONEID);
		
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			result = result+c.getString(iname)+" "+c.getString(isurname)+" "+c.getString(iembg)+" "+c.getString(iDate)+" "+c.getString(iStreet)+" "+c.getString(iCity)+" "+c.getString(iUsername)+" "+c.getString(iPhoneID)+"\n";
			
	}
		
		
		
		return result;
		
	}
	

	public int Login(String password, String username){
		int i=0;
		try{
			
			
			Cursor c= ourDatabase.query(DATABASE_TABLE, 
					new String[]{KEY_ROWID, KEY_NAME, KEY_SURNAME, KEY_EMBG,KEY_DATEBIRTH, KEY_STREET, KEY_CITY, KEY_USERNAME,KEY_PASSWORD, KEY_PHONEID}
					, KEY_PASSWORD +" like "+"'"+password+"'"+" and " +KEY_USERNAME+" like "+"'"+username+"'", null, null, null, null);
			c.moveToFirst();
	        i = c.getCount(); 
	        c.close(); 
	        //return i;
		}catch (Exception e){
			e.printStackTrace();
		}
		return i;

	}
	
	
	public int checjUsername(String username){
		int i=0;
		try{
			
			
			Cursor c= ourDatabase.query(DATABASE_TABLE, 
					new String[]{KEY_ROWID, KEY_NAME, KEY_SURNAME, KEY_EMBG,KEY_DATEBIRTH, KEY_STREET, KEY_CITY, KEY_USERNAME,KEY_PASSWORD, KEY_PHONEID}
					,"persons_username" +" like "+"'"+username+"'", null, null, null, null);
			c.moveToFirst();
	        i = c.getCount(); 
	        c.close(); 
	        return i;
		}catch (Exception e){
			e.printStackTrace();
		}
		return i;

	}
	
	
}

