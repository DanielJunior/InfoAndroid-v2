package com.djia.infoandroid_v2;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

public class Bateria {
	private int nivel;
	private Context c;

	public Bateria(Context c) {
		this.c = c;
		atualizaBateria();
	}

	public void atualizaBateria() {
		// crio um filtro pra intent
		IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
		// uma intent que recupera a mudança de valor da bateria
		Intent estadoDaBateria = c.registerReceiver(null, ifilter);
		nivel = estadoDaBateria.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
	}

	public int getNivel() {
		return nivel;
	}
}
