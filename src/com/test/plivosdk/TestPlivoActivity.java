package com.test.plivosdk;

import java.util.LinkedHashMap;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.plivo.helper.android.attributes.CallAttributes;
import com.plivo.helper.android.exception.AndroidClientException;
import com.plivo.helper.android.rest.HttpFactory;

public class TestPlivoActivity extends Activity {

	private String AUTH_ID = "";	// AUTH_ID
	private String AUTH_TOKEN = "";	// AUTH_TOKEN
	
	private HttpFactory mHttp;
	private LinkedHashMap<String, String> params;

	private String FROM = "";		// <FROM>
	private String TO = "";			// <TO>
	private String ANSWER_URL = "";	// <ANSWER_URL>
		
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mHttp = new HttpFactory(AUTH_ID, AUTH_TOKEN);

		params = new LinkedHashMap<String, String>();
		params.put(CallAttributes.FROM, FROM);
		params.put(CallAttributes.TO, TO);
		params.put(CallAttributes.ANSWER_URL, ANSWER_URL);

		Thread callThread = new Thread(callRunnable);
		callThread.start();
	}

	Runnable callRunnable = new Runnable() {

		@Override
		public void run() {

			try {
				mHttp.makeCall(params);
			} catch (AndroidClientException e) {
				e.printStackTrace();
				Log.e("plivosdk", "Error: " + e.getMessage());
			}

		}
	};

}
