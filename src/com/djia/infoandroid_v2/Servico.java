package com.djia.infoandroid_v2;


import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;

public class Servico extends IntentService {
	private Save save;

	public Servico() {
		super("Servi�oTeste");
		// TODO Auto-generated constructor stub
	}

	/*Como esse servi�o � chamado usando o m�todo startService() na classe InternetReceiver
	 * ele eh inicializado aqui, ent�o aqui fa�o o que quero, que neste caso eh salvar o instante da conex�o
	 * no m�todo gravar() da classe Save, verifico se realmente h� conex�o com a internet , j� que o receive do sistema
	 * diz apenas que ocorreu uma mudan�a no estado da conectividade, e s� depois
	 * escrevo no arquivo*/
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub	
		save = new Save(this);
		save.gravar();
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		stopSelf();
	}

}
