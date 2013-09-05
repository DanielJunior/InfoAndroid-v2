package com.djia.infoandroid_v2;


import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;

public class Servico extends IntentService {
	private Save save;

	public Servico() {
		super("ServiçoTeste");
		// TODO Auto-generated constructor stub
	}

	/*Como esse serviço é chamado usando o método startService() na classe InternetReceiver
	 * ele eh inicializado aqui, então aqui faço o que quero, que neste caso eh salvar o instante da conexão
	 * no método gravar() da classe Save, verifico se realmente há conexão com a internet , já que o receive do sistema
	 * diz apenas que ocorreu uma mudança no estado da conectividade, e só depois
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
