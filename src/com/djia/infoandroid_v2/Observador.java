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

	// cid = cell id
	// lac = location area code
	public Observador(Context c) {
		//esse manager eh responsável por fornecer o IMEI , operadora e obter o ID da antena e o LAC
		tel = (TelephonyManager) c.getSystemService(Context.TELEPHONY_SERVICE);
		sinal = new Sinal();
		antena = new Antena((GsmCellLocation) tel.getCellLocation());
		operadora = tel.getSimOperatorName();
		ID = tel.getDeviceId();
		observar();
	}
//recupero os dados atuais da antena que estou conectado e potencia de sinal, tbm tento atualizar a latitude e longitude da antena
	//que só será possível com a conexão com a internet
	public void observar() {
		antena = new Antena((GsmCellLocation) tel.getCellLocation());
		tel.listen(sinal, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
		antena.isResult();
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
