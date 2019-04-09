package com.andylai.aidlserver.customize;

import android.os.Parcel;
import android.os.Parcelable;

public class UserInfo implements Parcelable {

	String userName;
	int password;

	public UserInfo(String userName, int password) {
		this.userName = userName;
		this.password = password;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.userName);
		dest.writeInt(this.password);
	}

	protected UserInfo(Parcel in) {
		this.userName = in.readString();
		this.password = in.readInt();
	}

	public static final Creator<UserInfo> CREATOR = new Creator<UserInfo>() {
		@Override
		public UserInfo createFromParcel(Parcel source) {
			return new UserInfo(source);
		}

		@Override
		public UserInfo[] newArray(int size) {
			return new UserInfo[size];
		}
	};
}
