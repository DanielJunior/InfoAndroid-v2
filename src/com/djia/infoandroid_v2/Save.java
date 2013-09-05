package com.djia.infoandroid_v2;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

public class Save {
	private RandomAccessFile raf;
	private File file;
	private Date data;
	private String info;
	private ConexoesInternet conexoes;
	private Context c;

	public Save(Context c) {
		this.c = c;
		conexoes = new ConexoesInternet();
	}

	@SuppressLint("SimpleDateFormat")
	public void gravar() {
		//verifico se realmente estou conectado a internet
		if (conexoes.isOnline(c)) {
			Toast.makeText(c, "Serviço iniciado!", Toast.LENGTH_SHORT).show();
			try {
				//verifico se tenho um sdcard
				if (isExternalStorageWritable()) {
					//crio o arquivo ou abro se ele jah existe
					file = new File(Environment.getExternalStorageDirectory(),
							"logConexões.txt");
					raf = new RandomAccessFile(file, "rw");
					data = new Date();
					//formato da data
					SimpleDateFormat formatadorBrasil = new SimpleDateFormat(
							"dd-MM-yyyy HH:mm:ss");
					//string que irei escrever no arquivo
					info = "Conexão estabelecida em: "
							+ formatadorBrasil.format(data);
					//vou para o final do arquivo
					raf.seek(raf.length());
					//escrevo no arquivo
					raf.writeBytes(info + "\n");
					// raf.writeUTF(info + "\n");
					//fecho o arquivo
					raf.close();
					Toast.makeText(c, "Escrevi!", Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(c, "SDCard indiponível...",
							Toast.LENGTH_SHORT).show();
				}
			} catch (IOException e) {
				Log.d("ERRO_DE_ESCRITA", e.toString());
			}
		}
	}

	/* Checks if external storage is available for read and write */
	public boolean isExternalStorageWritable() {
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)) {
			return true;
		}
		return false;
	}

	/* Checks if external storage is available to at least read */
	public boolean isExternalStorageReadable() {
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)
				|| Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
			return true;
		}
		return false;
	}
}
