package com.andylai.aidlclient;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.andylai.aidlserver.IAIDLCallback;
import com.andylai.aidlserver.ILoginAidlInterface;

public class MainActivity extends AppCompatActivity {

    private ILoginAidlInterface mLoginService;
    private Button btnLogin;
    private TextView textMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin = findViewById(R.id.login);
        textMessage = findViewById(R.id.message);

        Intent intent = new Intent();
        intent.setAction("com.andylai.aidlservice");
        intent.setPackage("com.andylai.aidlserver");
        bindService(intent, mServiceConnection, BIND_AUTO_CREATE);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mLoginService = ILoginAidlInterface.Stub.asInterface(service);
            retrieveMessage();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mLoginService = null;
        }
    };

    private void login() {
        if (mLoginService != null) {
            try {
                mLoginService.login("Andy", 123456);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    void retrieveMessage() {
        try {
            mLoginService.setCallback(new IAIDLCallback.Stub() {
                @Override
                public void handleMessage(String message) throws RemoteException {
                    textMessage.setText(message);
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
