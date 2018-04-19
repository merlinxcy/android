package com.example.xcy_m.devicetest;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "LocationActivity";
    private TextView tv_location;
    private String mLocation = "";
    private LocationManager mLocationMgr;
    private Criteria mCriteria = new Criteria();
    private Handler mHandler = new Handler();
    private boolean bLocationEnable = false;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DeviceConfig.setGpsStatus(getApplicationContext(),false);
        initWidget();
        initLocation();
        mHandler.postDelayed(mRefresh,100);
    }
    private void initWidget(){

        tv_location = (TextView)findViewById(R.id.textView);
        mLocationMgr = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        mCriteria.setAccuracy(Criteria.ACCURACY_FINE);
        mCriteria.setAltitudeRequired(true);
        mCriteria.setBearingRequired(true);
        mCriteria.setCostAllowed(true);
        mCriteria.setPowerRequirement(Criteria.POWER_LOW);
    }
    private void initLocation(){
        String bestProvider = mLocationMgr.getBestProvider(mCriteria, true);
        if(bestProvider == null){
            bestProvider = LocationManager.NETWORK_PROVIDER;
        }
        if(mLocationMgr.isProviderEnabled(bestProvider)){
            tv_location.setText("正在获取: " + bestProvider);
            mLocation = String.format("定位类型=%s",bestProvider);
            beginLocation(bestProvider);
            bLocationEnable = true;
        }
        else{
            tv_location.setText("\n"+bestProvider);
            bLocationEnable = false;
        }
    }
    private void setLocationText(Location location){
        if(location != null){
            String desc = String.format("%s\n定位对象信息如下："+"\n\t其中时间："+"\n\t其中时间:%s"+"\n\t其中精度:%f,纬度:%f"+"\n\t其中高度:%d米,进度:%d米",
                    mLocation,"1",location.getLongitude(),location.getLatitude(),Math.round(location.getAltitude()),Math.round(location.getAccuracy()));
            Log.d(TAG, desc);
            tv_location.setText(desc);
        }
        else{
            tv_location.setText(mLocation+"\n暂未获取定位对象");
        }
    }
    private void beginLocation(String method){
        mLocationMgr.requestLocationUpdates(method,300,0,mLocationListener);
        Location location = mLocationMgr.getLastKnownLocation(method);//不能直接使用location.get,一定要有监听器，GPRS不能直接获取
        setLocationText(location);
    }
    private LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            setLocationText(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };
    private Runnable mRefresh = new Runnable() {
        @Override
        public void run() {
            if(bLocationEnable == false){
                initLocation();
                mHandler.postDelayed(this,1000);
            }
        }
    };

//    @Override
//    protected void onDestory(){
//        if(mLocationMgr != null){
//            mLocationMgr.removeUpdates(mLocationListener);
//        }
//        super.onDestroy();
//    }
}

