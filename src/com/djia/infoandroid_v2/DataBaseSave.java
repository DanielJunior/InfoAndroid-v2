package com.djia.infoandroid_v2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseSave extends SQLiteOpenHelper {
	public static final String TABLE_INFO = "INFO";
	public static final String COLUMN_LATITUDE = "LATITUDE";
	public static final String COLUMN_LONGITUDE = "LONGITUDE";
	public static final String COLUMN_SINAL = "SINAL";

	private static final String DATABASE_NAME = "info.db";
	private static final int DATABASE_VERSION = 1;

	private static final String DATABASE_CREATE = "create table" + TABLE_INFO
			+ " ( " + COLUMN_LATITUDE + " real not null, " + 
			COLUMN_LONGITUDE+ " real not null, " +
			COLUMN_SINAL + " integer,"
			+ "CONSTRAINT pk_chave primary key(LATITUDE,LONGITUDE,SINAL);";

	public DataBaseSave(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		// TODO Auto-generated method stub
		database.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub
		db.execSQL(" DROP TABLE IF EXISTS " + TABLE_INFO);
		onCreate(db);
	}

}
