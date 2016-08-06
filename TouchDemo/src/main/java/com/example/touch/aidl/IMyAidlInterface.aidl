// IMyAidlInterface.aidl
package com.example.touch.aidl;

// Declare any non-default types here with import statements

interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void setName(String name);

    String getName();
}
