package com.djia.infoandroid_v2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import android.telephony.gsm.GsmCellLocation;

public class Antena {

	private GsmCellLocation cellLocation;
	// cid = CellID (id da torre) lac = Location Area Code
	private int myLatitude, myLongitude, cid, lac;
	private boolean result;
	private float antenaLatitude, antenaLongitude;

	public Antena(GsmCellLocation gcl) {
		cellLocation = gcl;
		cid = cellLocation.getCid();
		lac = cellLocation.getLac();
	}

	// esse método achei na net , com ele que eh possível converter o cid e lac
	// para latitude e longitude
	// ele faz conexão com a internet
	private void conversao() {
		result = false;
		String urlmmap = "http://www.google.com/glm/mmap";
		try {
			URL url = new URL(urlmmap);
			URLConnection conn = url.openConnection();
			HttpURLConnection httpConn = (HttpURLConnection) conn;
			httpConn.setRequestMethod("POST");
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);
			httpConn.connect();

			OutputStream outputStream = httpConn.getOutputStream();
			WriteData(outputStream, cid, lac);

			InputStream inputStream = httpConn.getInputStream();
			DataInputStream dataInputStream = new DataInputStream(inputStream);

			dataInputStream.readShort();
			dataInputStream.readByte();
			int code = dataInputStream.readInt();
			if (code == 0) {
				myLatitude = dataInputStream.readInt();
				myLongitude = dataInputStream.readInt();
				result = true;
				antenaLatitude = (float) myLatitude / 1000000;
				antenaLongitude = (float) myLongitude / 1000000;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void WriteData(OutputStream out, int cid, int lac)
			throws IOException {
		DataOutputStream dataOutputStream = new DataOutputStream(out);
		dataOutputStream.writeShort(21);
		dataOutputStream.writeLong(0);
		dataOutputStream.writeUTF("pt-br");
		dataOutputStream.writeUTF("Android");
		dataOutputStream.writeUTF("1.0");
		dataOutputStream.writeUTF("Web");
		dataOutputStream.writeByte(27);
		dataOutputStream.writeInt(0);
		dataOutputStream.writeInt(0);
		dataOutputStream.writeInt(3);
		dataOutputStream.writeUTF("");

		dataOutputStream.writeInt(cid);
		dataOutputStream.writeInt(lac);

		dataOutputStream.writeInt(0);
		dataOutputStream.writeInt(0);
		dataOutputStream.writeInt(0);
		dataOutputStream.writeInt(0);
		dataOutputStream.flush();
	}

	public boolean isResult() {
		conversao();
		return result;
	}

	public int getCid() {
		return cid;
	}

	public int getLac() {
		return lac;
	}

	public float getAntenaLatitude() {
		return antenaLatitude;
	}

	public float getAntenaLongitude() {
		return antenaLongitude;
	}

}
