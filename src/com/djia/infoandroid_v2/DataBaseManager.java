package com.djia.infoandroid_v2;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DataBaseManager {

	private SQLiteDatabase database;
	private String[] columns = { DataBaseSave.COLUMN_LATITUDE,
			DataBaseSave.COLUMN_LONGITUDE, DataBaseSave.COLUMN_SINAL,
			DataBaseSave.COLUMN_OPERADORA };
	private DataBaseSave db;
	private Context c;

	public DataBaseManager(Context c) {
		this.c = c;
		db = new DataBaseSave(c);
	}

	public void open() throws SQLException {
		database = db.getWritableDatabase();
	}

	public void close() {
		db.close();
	}

	public void novoDado() {
		Dados d = new Dados(c);
		d.atualizar();
		ContentValues values = new ContentValues();
		values.put(DataBaseSave.COLUMN_LATITUDE, d.local().getLatitude());
		values.put(DataBaseSave.COLUMN_LONGITUDE, d.local().getLongitude());
		values.put(DataBaseSave.COLUMN_SINAL, d.getSinal().getdBm());
		values.put(DataBaseSave.COLUMN_OPERADORA, d.getOperadora());
		database.insert(DataBaseSave.TABLE_INFO, null, values);
	}

	public List<NovoDado> getDados() {
		List<NovoDado> dados = new ArrayList<NovoDado>();
		Cursor cursor = database.query(DataBaseSave.TABLE_INFO,columns,null,null,null,null,null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
		NovoDado d = new NovoDado();
		d.setLatitude(cursor.getDouble(0));
		d.setLongitude(cursor.getDouble(1));
		d.setSinal(cursor.getInt(2));
		d.setOperadora(cursor.getString(3));
		dados.add(d);
		cursor.moveToNext();
		}
		cursor.close();
		return dados;
	}
}
