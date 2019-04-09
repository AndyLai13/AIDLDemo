// ILoginAidlInterface.aidl
package com.andylai.aidlserver;

// Declare any non-default types here with import statements
import com.andylai.aidlserver.customize.UserInfo;

interface ILoginAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
    void login(String userName, int password);
    void addUserInfo(in UserInfo userInfo);
}
