package com.djia.infoandroid_v2;

import android.location.Location;

public interface Infos {
	public Sinal getSinal();
	public Location local();
	public void atualizar();
	public String getOperadora();
}
