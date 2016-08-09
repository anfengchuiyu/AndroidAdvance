package com.example.touch.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

/**
 * Created by liuzhe on 2016/8/2.
 */
public class MyAidlService extends Service{

    private volatile String mName = "liuzhe";
    private volatile Person mPerson = new Person();

    private Binder binder = new IMyAidlInterface.Stub(){

        @Override
        public void setName(String name) throws RemoteException {
            mName = name;
        }

        @Override
        public String getName() throws RemoteException {
            return mName;
        }

        @Override
        public Person getPerson() throws RemoteException {
            return mPerson;
        }

        @Override
        public void setPerson(Person person) throws RemoteException {
            mPerson.setName(person.getName());
            mPerson.setAge(person.getAge());

        }
    };


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}
