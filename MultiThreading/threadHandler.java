package com.example.xcy_m.ndktest;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity{
    //初始化参数
    private int MSG_1 = 0;
    private int MSG_2 = 1;
    TextView windowsText;
    Button mButton;
    //结束参数
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        windowsText = (TextView)findViewById(R.id.textView);
        mButton = (Button)findViewById(R.id.button);
        // Example of a call to a native method
        //TextView tv = (TextView) findViewById(R.id.sample_text);
        //tv.setText(sad());
        mButton.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                if(v.getId() == R.id.button){
                    mThread obj = new mThread();
                    obj.run();
                }
            }
        });
    }

    // handler
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            if(msg.what == MSG_1){
                windowsText.setText("MSG_1 create");
            }
            else if(msg.what == MSG_2){
                windowsText.setText("MSG_2 create");
            }
            else{
                windowsText.setText("MSG else");
            }
        }
    };
    // thread
    private class mThread extends Thread{
        @Override
        public void run(){
            mHandler.sendEmptyMessage(0);
        }
    }

}
