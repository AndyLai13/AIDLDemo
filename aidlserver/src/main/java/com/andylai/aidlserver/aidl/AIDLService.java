package com.andylai.aidlserver.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.andylai.aidlserver.IAIDLCallback;
import com.andylai.aidlserver.ILoginAidlInterface;
import com.andylai.aidlserver.customize.UserInfo;

public class AIDLService extends Service {

    public OnLoginListener mOnLoginListener;

    public AIDLService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    public class MyBinder extends ILoginAidlInterface.Stub {

        @Override
        public void login(String userName, int password) throws RemoteException {
            Log.d("Andy", "userName = " + userName);
            Log.d("Andy", "password = " + password);
        }

        public AIDLService getService() {
            return AIDLService.this;
        }

        @Override
        public void setCallback(final IAIDLCallback callback) throws RemoteException {
            Log.d("Andy", "out callback message");
            callback.handleMessage("callback message");
        }
    }

    public void setOnLoginListener(OnLoginListener onLoginListener) {
        mOnLoginListener = onLoginListener;
    }

    public interface OnLoginListener {
        void login(String userName, int password);
    }
}
