package com.example.touch.jni;

/**
 * Created by XH on 2016/7/29.
 */
public class JniTest {

    static {
        System.loadLibrary("hello");
    }

    public native String get();

    public native void set(String str);



}
