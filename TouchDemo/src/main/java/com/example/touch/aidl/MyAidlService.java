package com.example.touch.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.example.touch.IMyAidlInterface;

/**
 * Created by liuzhe on 2016/8/2.
 */
public class MyAidlService extends Service{

    private String mName = "liuzhe";

    private Binder binder = new IMyAidlInterface.Stub(){

        @Override
        public void setName(String name) throws RemoteException {
            mName = name;
        }

        @Override
        public String getName() throws RemoteException {
            return mName;
        }
    };


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}
