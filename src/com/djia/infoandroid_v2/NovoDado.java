package com.djia.infoandroid_v2;

public class NovoDado {
	private String operadora;
	private double latitude, longitude;
	private int sinal;

	public NovoDado(String operadora, double latitude, double longitude,
			int sinal) {
		super();
		this.operadora = operadora;
		this.latitude = latitude;
		this.longitude = longitude;
		this.sinal = sinal;
	}

	public NovoDado() {
	}

	public String getOperadora() {
		return operadora;
	}

	public void setOperadora(String operadora) {
		this.operadora = operadora;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public int getSinal() {
		return sinal;
	}

	public void setSinal(int sinal) {
		this.sinal = sinal;
	}
}
