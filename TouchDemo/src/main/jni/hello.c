//
// Created by XH on 2016/7/29.
//
#include "com_example_touch_jni_JniTest.h"

JNIEXPORT jstring JNICALL Java_com_example_touch_jni_JniTest_get(JNIEnv *env, jobject obj){
    return (*env)->NewStringUTF(env,"刘哲 软件工程 Android");
}
