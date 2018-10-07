package com.xeldax.signeveryday.Controller;

import android.content.Context;
import android.widget.Toast;

import com.xeldax.signeveryday.dataHelper.SharedHelper;
/**
 * Created by xeldax on 2018/9/2.
 */

public class signController {
    public static void init(Context mContext){
        SharedHelper helper = new SharedHelper(mContext);
        int flag = helper.read_init();
        if(flag != 1){
            helper.save_fail_count(0);
            helper.save_last_sign_time(0);
            helper.save_sign_time(0);
            helper.save_top_sign_time(0);
            helper.save_init(1);
            Toast.makeText(mContext,"Welcome",Toast.LENGTH_LONG).show();
        }
    }
}
