package com.djia.infoandroid_v2;

import android.content.Context;
import android.location.Location;

public class Dados implements Infos {
	private Observador obs;
	private Local local;

	public Dados(Context c) {
		obs = new Observador(c);
		local = new Local(c);
	}

	@Override
	public Sinal getSinal() {
		return obs.getSinal();
	}

	@Override
	public Location local() {
		return local.getLocation();

	}

	@Override
	public void atualizar() {
		// TODO Auto-generated method stub
		obs.observar();
		local.atualizaLocal();
	}

}
