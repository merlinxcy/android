package com.xeldax.signeveryday;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.xeldax.signeveryday.dataHelper.SharedHelper;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SignFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SignFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_sign,container,false);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final SharedHelper helper = new SharedHelper(getActivity().getApplicationContext());
        Button button = (Button)getActivity().findViewById(R.id.sign_button);
        Button fail_button = (Button)getActivity().findViewById(R.id.fail_button);
        fail_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.save_fail_count(helper.read_fail_count()+1);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long last_sign_time = helper.read_last_sign_time();
                long time_stamp = System.currentTimeMillis();
                Log.d("time_stamp",String.valueOf(time_stamp));
                Log.d("time_stamp",String.valueOf(last_sign_time));
                Log.d("time_stamp",String.valueOf(time_stamp-last_sign_time));
                if((time_stamp-last_sign_time)>12*60*60*1000){
                    Log.d("sign","true");
                    helper.save_sign_time(helper.read_sign_time()+1);
                    int top_sign_time = helper.read_top_sign_time();
                    if(top_sign_time < helper.read_sign_time() + 1){
                        helper.save_top_sign_time(helper.read_sign_time());
                    }
                    helper.save_last_sign_time(time_stamp);
                    Toast.makeText(getActivity().getApplicationContext(),"签到成功", Toast.LENGTH_SHORT).show();
                }
                else if((time_stamp-last_sign_time)>12*60*60*1000*2){
                    helper.save_sign_time(1);
                    int top_sign_time = helper.read_top_sign_time();
                    if(top_sign_time < helper.read_sign_time() + 1){
                        helper.save_top_sign_time(helper.read_sign_time());
                    }
                    helper.save_last_sign_time(time_stamp);
                    Toast.makeText(getActivity().getApplicationContext(),"签到成功", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(getActivity().getApplicationContext(),"已经签到", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
