package com.example.touch.touch_delegate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.touch.R;

/**
 * Created by liuzhe on 2016/7/27.
 *
 * 触摸区域的扩展，通过父布局对子view的touch代理来完成
 * 这里展示了两种实现方式
 */
public class TouchDelegateActivity extends AppCompatActivity implements View.OnClickListener{

    Toast toast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_delegate);

        toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                toast.setText("button 1");
                break;
            case R.id.button2:
                toast.setText("button 2");
                break;
        }
        toast.show();
    }
}
