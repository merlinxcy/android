package com.xeldax.signeveryday.dataHelper;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by xeldax on 2018/9/2.
 */

public class SharedHelper {
    private Context mContext;
    public SharedHelper(){
    }
    public SharedHelper(Context mContext){
        this.mContext = mContext;
    }
    //保存init状态
    public void save_init(int init_flag){
        SharedPreferences sp = mContext.getSharedPreferences("myinfo",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("init_flag", init_flag);
        editor.commit();
    }
    //读取sqlite状态
    public int read_init(){
        SharedPreferences sp =mContext.getSharedPreferences("myinfo", Context.MODE_PRIVATE);
        int flag = sp.getInt("init_flag",-1);
        return flag;
    }
    //保存连续签到时间
    public void save_sign_time(int sign_time_count) {
        SharedPreferences sp = mContext.getSharedPreferences("myinfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("sign_time_count", sign_time_count);
        editor.commit();
    }
    //读取连续签到时间
    public int read_sign_time(){
        SharedPreferences sp = mContext.getSharedPreferences("myinfo", Context.MODE_PRIVATE);
        int sign_time_count = sp.getInt("sign_time_count",0);
        return sign_time_count;
    }
    //保存失败次数
    public void save_fail_count(int fail_count){
        SharedPreferences sp = mContext.getSharedPreferences("myinfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("fail_count", fail_count);
        editor.commit();
    }
    //读取失败次数
    public int read_fail_count(){
        SharedPreferences sp = mContext.getSharedPreferences("myinfo", Context.MODE_PRIVATE);
        int fail_count = sp.getInt("fail_count", 0);
        return fail_count;
    }
    //保存最长连续签到时间
    public void save_top_sign_time(int time){
        SharedPreferences sp = mContext.getSharedPreferences("myinfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("top_sign_time", time);
        editor.commit();
    }
    //读取最长连续签到时间
    public int read_top_sign_time(){
        SharedPreferences sp = mContext.getSharedPreferences("myinfo", Context.MODE_PRIVATE);
        int top_sign_time = sp.getInt("top_sign_time", 0);
        return top_sign_time;
    }
    //保存上一次签到时间
    public void save_last_sign_time(long time){
        SharedPreferences sp = mContext.getSharedPreferences("myinfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putLong("last_sign_time", time);
        editor.commit();
    }
    //读取上一次签到时间
    public long read_last_sign_time(){
        SharedPreferences sp = mContext.getSharedPreferences("myinfo", Context.MODE_PRIVATE);
        long last_sign_time = sp.getLong("last_sign_time", 0);
        return last_sign_time;
    }
}
