package com.andylai.aidlserver;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.andylai.aidlserver.aidl.AIDLService;

public class MainActivity extends AppCompatActivity implements AIDLService.OnLoginListener {

	Handler mHandler = new Handler();
	TextView mUserInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mUserInfo = findViewById(R.id.userInfo);
		Intent intent = new Intent(this, AIDLService.class);
		bindService(intent, mAIDLConnection, BIND_AUTO_CREATE);
	}

	private ServiceConnection mAIDLConnection = new ServiceConnection() {
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			AIDLService.MyBinder binder = (AIDLService.MyBinder) service;
			AIDLService aidlService = binder.getService();
			aidlService.setOnLoginListener(MainActivity.this);
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {

		}
	};

	@Override
	public void login(final String userName, final int password) {
		mHandler.post(new Runnable() {
			@Override
			public void run() {
				mUserInfo.setText(userName + ", " + password);
				Toast.makeText(MainActivity.this, "login success", Toast.LENGTH_SHORT).show();
			}
		});
	}
}