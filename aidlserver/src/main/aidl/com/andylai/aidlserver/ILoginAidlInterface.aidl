// ILoginAidlInterface.aidl
package com.andylai.aidlserver;

import com.andylai.aidlserver.IAIDLCallback;

interface ILoginAidlInterface {
    void login(String userName, int password);
    oneway void setCallback(IAIDLCallback callback);
}
