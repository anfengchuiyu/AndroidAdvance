package com.example.touch.jni;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.widget.TextView;

/**
 * Created by XH on 2016/7/29.
 */
public class JniTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView tv = new TextView(this);
        tv.setPadding(20, 20, 20, 20);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        JniTest jniTest = new JniTest();
        tv.setText(jniTest.get());

        setContentView(tv);
    }
}
