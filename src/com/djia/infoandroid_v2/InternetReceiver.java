package com.djia.infoandroid_v2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/*Essa classe que recebe a notificação do sistema que a mudança de conectividade aconteceu
 * (não necessariamente que há conectividade com a internet)
 * no método onReceive faço o que quero, neste caso inicio o Servico*/
public class InternetReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent arg1) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(context, Servico.class);
        context.startService(intent);
	}

}
