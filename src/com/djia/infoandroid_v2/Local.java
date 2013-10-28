package com.djia.infoandroid_v2;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;

public class Local {
	private double altitude, latitude, longitude;
	private float precisao;
	private String provedor;
	private LocationManager LM;
	private Context c;
	private Location l;

	public Local(Context c) {
		this.c = c;
	}

	// pego a localização por um critério que verá o melhor provedor a ser usado
	public void atualizaLocal() {
		LM = (LocationManager) c.getSystemService(Context.LOCATION_SERVICE);
		String bestProvider = LM.getBestProvider(new Criteria(), true);
		l = LM.getLastKnownLocation(bestProvider);
		if (disponivel()) {
			precisao = l.getAccuracy();
			altitude = l.getAltitude();
			latitude = l.getLatitude();
			longitude = l.getLongitude();
			provedor = l.getProvider();
		}
	}

	public boolean disponivel() {
		if (l != null)
			return true;
		return false;
	}

	public double getAltitude() {
		return altitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public float getPrecisao() {
		return precisao;
	}

	public String getProvedor() {
		return provedor;
	}

	public Location getLocation() {
		return l;
	}
}