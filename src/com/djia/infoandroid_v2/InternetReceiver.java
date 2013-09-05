package com.djia.infoandroid_v2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/*Essa classe que recebe a notifica��o do sistema que a mudan�a de conectividade aconteceu
 * (n�o necessariamente que h� conectividade com a internet)
 * no m�todo onReceive fa�o o que quero, neste caso inicio o Servico*/
public class InternetReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent arg1) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(context, Servico.class);
        context.startService(intent);
	}

}
