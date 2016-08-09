package com.example.touch.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.touch.R;

/**
 * Created by liuzhe on 2016/8/2.
 */
public class MyAidlActivity extends AppCompatActivity {

    private static final String TAG = "MyAidlActivity";

    private TextView textView;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            IMyAidlInterface myAidlInterface = IMyAidlInterface.Stub.asInterface(service);

            try {
                myAidlInterface.setName("hello world!!!");//客户端设置一个字符串
                String nameStr = myAidlInterface.getName();//从服务端取设置的字符串

                Person person = new Person();
                person.setName("zhe");
                person.setAge(23);
                myAidlInterface.setPerson(person);


                textView.append("\nService返回的数据： " + nameStr);
                textView.append("\nService返回的person数据： " + myAidlInterface.getPerson().toString());
                Log.d(TAG, nameStr);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myaidl);
        textView = (TextView) findViewById(R.id.textview);

        Intent intent = new Intent(this, MyAidlService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);

        Person p = new Person();
    }

    @Override
    protected void onDestroy() {
        unbindService(connection);
        super.onDestroy();
    }
}
