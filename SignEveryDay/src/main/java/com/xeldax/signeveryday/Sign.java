package com.xeldax.signeveryday;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.xeldax.signeveryday.Controller.signController;
import com.xeldax.signeveryday.dataHelper.SharedHelper;

public class Sign extends AppCompatActivity {

    private SignFragment sign_fragment;
    private HomeFragment home_fragment;
    private NoticeFragment notice_fragment;
    private Fragment[] fragments;
    private int last_fragment_id; //用于记录上一个碎片id
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if(last_fragment_id!=0){
                        switchFragment(last_fragment_id,0);
                        last_fragment_id = 0;
                        SharedHelper helper = new SharedHelper(getApplicationContext());
                        TextView fail_counts = (TextView)findViewById(R.id.fail_count);
                        TextView sign_dates = (TextView)findViewById(R.id.sign_date);
                        TextView long_dates = (TextView)findViewById(R.id.long_date);
                        fail_counts.setText(helper.read_fail_count()+"天");
                        sign_dates.setText(helper.read_sign_time()+"天");
                        long_dates.setText(helper.read_top_sign_time()+"天");
                    }
                    return true;
                case R.id.navigation_dashboard:
                    if(last_fragment_id!=1){
                        switchFragment(last_fragment_id,1);
                        last_fragment_id = 1;
                    }
                    return true;
                case R.id.navigation_notifications:
                    if(last_fragment_id!=2){
                        switchFragment(last_fragment_id,2);
                        last_fragment_id=2;
                    }
                    return true;
            }
            return false;
        }

    };

    private void initFragment(){
        sign_fragment = new SignFragment();
        home_fragment = new HomeFragment();
        notice_fragment = new NoticeFragment();
        fragments = new Fragment[]{home_fragment,sign_fragment,notice_fragment};
        last_fragment_id = 0;
        getSupportFragmentManager().beginTransaction().replace(R.id.main_lay,home_fragment).show(home_fragment).commit();
        // navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
    private void switchFragment(int lastFragment, int index){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(fragments[lastFragment]);
        if(!fragments[index].isAdded()){
            transaction.add(R.id.main_lay,fragments[index]);
        }
        transaction.show(fragments[index]).commitAllowingStateLoss();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        signController.init(getApplicationContext());
        initFragment();
    }

}
