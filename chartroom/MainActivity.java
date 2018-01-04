package com.example.administrator.myapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.drawable.Drawable;//可绘图
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.widget.Button;
import android.widget.TextView;//文本框
import android.telephony.TelephonyManager;
import android.widget.Toast;
import android.view.View;

import org.w3c.dom.Text;

import java.security.MessageDigest;


public class MainActivity extends AppCompatActivity implements OnClickListener,OnLongClickListener{
    private TextView tv_bbs;
    private TextView tv_control;

    @override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actiyity_bbs);
        tv_control = (TextView)findViewById(R.id.tv_control);
        tv_control.setOnClickListener(this);
        tv_control.setOnLongClickListener(this);
        tv_bbs = (TextView)findViewById(R.id.tv_bbs);
        tv_bbs.setOnClickListener(this);
        tv_bbs.setOnLongClickListener(this);
        tv_bbs.setGravity(Gravity.LEFT|Gravity.BOTTOM);
        tv_bbs.setLines(8);
        tv_bbs.setMaxLines(8);
        tv_bbs.setMovementMethod(new ScrollingMovementMethod());
    }
    private String[] mCharStr = {"1","2","3","4"};

    @override
    public void onClick(View v)
    {
        if(v.getId() == R.id.tv_control || v.getId() == R.id.tv_bbs)
        {
            int random = (int)(Math.random()*10)%5;
            String newStr = String.format("%s\n%s%s",tv_bbs.getText().toString(),DateUtils.getNowTime(),mCharStr[random]);
            tv_bbs.setText(newStr);
        }
    }

    @override
    public boolean onLongClick(view v)
    {
        if(v.getId() == R.id.tv_control || v.getId() == R.id.tv_bbs)
        {
            tv_bbs.setText("");
        }
        return true;
    }
}
