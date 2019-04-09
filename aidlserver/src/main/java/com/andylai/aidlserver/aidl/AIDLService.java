package com.andylai.aidlserver.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

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
		public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

		}

		@Override
		public void login(String userName, int password) throws RemoteException {
			if(mOnLoginListener != null)
				mOnLoginListener.login(userName, password);
		}

		@Override
		public void addUserInfo(UserInfo userInfo) throws RemoteException {

		}

		public AIDLService getService() {
			return AIDLService.this;
		}
	}

	public void setOnLoginListener(OnLoginListener onLoginListener) {
		mOnLoginListener = onLoginListener;
	}

	public interface OnLoginListener {
		void login(String userName, int password);
	}
}
