package com.djia.infoandroid_v2;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class ConexoesInternet {

	/*Método responsável por checar a conexão real com a internet*/
	public boolean hasActiveInternetConnection() {
		try {
			HttpURLConnection urlc = (HttpURLConnection) (new URL(
					"http://www.google.com").openConnection());
			urlc.setRequestProperty("User-Agent", "Test");
			urlc.setRequestProperty("Connection", "close");
			urlc.setConnectTimeout(1500);
			urlc.connect();
			return (urlc.getResponseCode() == 200);
		} catch (IOException e) {
			Log.e("NO_CONNECTION", "Error checking internet connection", e);
		}
		return false;
	}
/*Método responsável por checar a conexão real com a internet*/
	public boolean isOnline(Context con) {
		ConnectivityManager cm;
		boolean connected = false;
		try {
			cm = (ConnectivityManager) con
					.getSystemService(Context.CONNECTIVITY_SERVICE);

			NetworkInfo networkInfo = cm.getActiveNetworkInfo();
			connected = networkInfo != null && networkInfo.isAvailable()
					&& networkInfo.isConnected();
			return connected;

		} catch (Exception e) {
			System.out
					.println("CheckConnectivity Exception: " + e.getMessage());
			Log.v("connectivity", e.toString());
		}
		return connected;
	}
}
