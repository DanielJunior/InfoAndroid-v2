package com.djia.infoandroid_v2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
//import android.os.Vibrator;

/*Essa classe que recebe a notificação do sistema que a mudança de conectividade aconteceu
 * (não necessariamente que há conectividade com a internet)
 * no método onReceive faço o que quero, neste caso inicio o Servico*/
public class InternetReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent arg1) {
		// TODO Auto-generated method stub
		String qual = arg1.getAction();
		//verifico quem chamou o receiver
		if (qual.equals("android.net.conn.CONNECTIVITY_CHANGE")
				|| qual.equals("android.net.wifi.WIFI_STATE_CHANGED")) {
			Intent intent = new Intent(context, Servico.class);
			context.startService(intent);
		}else{
			Observador ob = new Observador(context);
			ob.observar();
			Antena a = ob.getAntena();
			Save save = new Save(context);
			if(a!=null){
				String ifs = "CID: "+a.getCid()+" LAC: "+a.getLac();
				save.gravarMudancaRede(ifs);
			}else{
				save.gravarMudancaRede("Informação indiponível...");
			}
			/*Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);			 
			// Vibrate for 300 milliseconds
			v.vibrate(1000);*/

		}
	}
}
