package com.djia.infoandroid_v2;

import android.os.Bundle;
import android.app.Activity;
import android.content.res.Configuration;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Bateria bateria;
	private Local local;
	private Observador observador;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bateria = new Bateria(this);
		local = new Local(this);
		observador = new Observador(this);
		observador.observar();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		observador.parar();
	}

	public void atualizar(View view) {
		Toast.makeText(this, "Aguarde um instante por favor...",
				Toast.LENGTH_SHORT).show();
		observador.observar();
		local.atualizaLocal();
		bateria.atualizaBateria();
		TextView disp = (TextView) findViewById(R.id.dispositivo);
		disp.setText("ID: " + observador.getID());
		TextView bat = (TextView) findViewById(R.id.bateria);
		bat.setText("Nível de bateria: " + bateria.getNivel() + "%");
		TextView lat = (TextView) findViewById(R.id.lat);
		TextView longi = (TextView) findViewById(R.id.longi);
		TextView antena = (TextView) findViewById(R.id.antena_id);
		TextView lac = (TextView) findViewById(R.id.lac);
		if ((observador.getAntena() == null)) {
			lat.setText("Latitude : INDISPONÍVEL (SEM CONEXÃO)");
			longi.setText("Longitude: INDISPONÍVEL (SEM CONEXÃO)");
			antena.setText("ID Antena: INDISPONÍVEL (SEM SINAL)");
			lac.setText("Location Area Code(LAC): INDISPONÍVEL (SEM SINAL)");

		} else if (!observador.getAntena().isResult()) {
			lat.setText("Latitude : INDISPONÍVEL (SEM CONEXÃO)");
			longi.setText("Longitude: INDISPONÍVEL (SEM CONEXÃO)");
			antena.setText("ID Antena: " + observador.getAntena().getCid());
			lac.setText("Location Area Code(LAC): "
					+ observador.getAntena().getLac());
		} else {
			lat.setText("Latitude : "
					+ observador.getAntena().getAntenaLatitude());
			longi.setText("Longitude: "
					+ observador.getAntena().getAntenaLongitude());
			antena.setText("ID Antena: " + observador.getAntena().getCid());
			lac.setText("Location Area Code(LAC): "
					+ observador.getAntena().getLac());
		}
		TextView op = (TextView) findViewById(R.id.operadora);
		op.setText("Operadora: " + observador.getOperadora());
		TextView sinal = (TextView) findViewById(R.id.sinal);
		sinal.setText("Nível de sinal: " + observador.getSinal().getdBm()
				+ "dBm");

	}
}
