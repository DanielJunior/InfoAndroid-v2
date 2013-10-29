package com.djia.infoandroid_v2;

import android.content.Context;
import android.location.Location;

public class Dados implements Infos {
	private Observador obs;
	private Local local;
	private Location l;
	private Sinal s;
	private String o;

	public Dados(Context c) {
		obs = new Observador(c);
		local = new Local(c);
	}

	public Dados(){
	}

	@Override
	public Sinal getSinal() {
		return s;
	}

	@Override
	public Location local() {
		return l;

	}

	@Override
	public void atualizar() {
		// TODO Auto-generated method stub
		obs.observar();
		local.atualizaLocal();
		l = local.getLocation();
		s = obs.getSinal();
		o = obs.getOperadora();
	}

	@Override
	public String getOperadora() {
		// TODO Auto-generated method stub
		return o;
	}

}
