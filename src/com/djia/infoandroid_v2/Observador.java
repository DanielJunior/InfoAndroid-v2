package com.djia.infoandroid_v2;

import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;

public class Observador {
	private TelephonyManager tel;
	private String operadora, ID;
	private Sinal sinal;
	private Antena antena;
	private static final String SEM_SINAL = "INDISPON�VEL";

	// cid = cell id
	// lac = location area code
	public Observador(Context c) {
		// esse manager eh respons�vel por fornecer o IMEI , operadora e obter o
		// ID da antena e o LAC
		tel = (TelephonyManager) c.getSystemService(Context.TELEPHONY_SERVICE);
		sinal = new Sinal();
		ID = tel.getDeviceId();
		operadora = tel.getSimOperatorName();
		//se tiver sinal...
		if (tel.getCellLocation() != null) {
			antena = new Antena((GsmCellLocation) tel.getCellLocation());
			observar();
		} else {
			antena = null;
			operadora = SEM_SINAL;
		}
	}

	// recupero os dados atuais da antena que estou conectado e potencia de
	// sinal, tbm tento atualizar a latitude e longitude da antena
	// que s� ser� poss�vel com a conex�o com a internet
	public void observar() {
		if (tel.getCellLocation() != null) {
			antena = new Antena((GsmCellLocation) tel.getCellLocation());
			antena.isResult();
		}
		tel.listen(sinal, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
	}

	public void parar() {
			// para o listener para economizar bateria
			tel.listen(sinal, PhoneStateListener.LISTEN_NONE);
	}

	public String getOperadora() {
		return operadora;
	}

	public String getID() {
		return ID;
	}

	public Sinal getSinal() {
		return sinal;
	}

	public Antena getAntena() {
		return antena;
	}
}
