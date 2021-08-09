// IAIDLCallback.aidl
package com.andylai.aidlserver;

// Declare any non-default types here with import statements

interface IAIDLCallback {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void handleMessage(String message);
}