package com.djia.infoandroid_v2;

import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;

public class Sinal extends PhoneStateListener {
private int asu,dBm;

@Override
	public void onSignalStrengthsChanged(SignalStrength signalStrength) {
		// TODO Auto-generated method stub
		super.onSignalStrengthsChanged(signalStrength);
		asu = signalStrength.getGsmSignalStrength();
		dBm = -113 + (2 * asu);
	}

public int getAsu() {
	return asu;
}

public int getdBm() {
	return dBm;
}
}
