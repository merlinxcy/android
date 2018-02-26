package com.example.administrator.myapplication;

import android.app.Activity;
import android.bluetooth.BluetoothHealthAppConfiguration;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.drawable.Drawable;//可绘图
import android.text.InputFilter;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.content.Intent;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;//文本框
import android.telephony.TelephonyManager;
import android.widget.Toast;
import android.view.View;
import android.util.Log;
import org.w3c.dom.Text;
import android.widget.*;
import android.os.Bundle;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.util.Date;
import java.text.SimpleDateFormat;

//broadcast的使用
/*public class MainActivity extends AppCompatActivity{
    public final static String EVENT ="";
    private String[] mColorNmaeArray;
    private int[] mColorArray;
    class ColorSelectListener implements AdapterView.OnItemSelectedListener{
        public void onItemSelect(AdapterView<?> arg0,View arg1, int arg2,long arg3){
            Intent intent = new Intent(MainActivity.EVENT);
            intent.putExtra("color",mColorArray);
            LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
        }
        public void onNothingSelect(AdapterView<?> arg0){}
    }
    @Override
    public void onStart(){
        super.onStart();
        bgChangeReceiver = new BgChangeReceiver();
        IntentFilter filter = new IntentFilter(MainActivity.EVENT);
        LocalBroadcastManager.getInstance(this).registerReceiver(bgChangeReceiver,filter);
    }
    @Override
    public void onStop(){
        LocalBroadcastManager.getInstance(this).unregisterReceiver(bgChangeReceiver);
        super.onStop();
    }
    private BgChangeReceiver bgChangeReceiver;
    private class BgChangeReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent){
            if(intent!=null){
                int color = intent.getIntExtra("color",Color.WHITE);
                ll_brd_temp.setBackgroundColor(color);
            }
        }
    }
}*/
//////////////FRAGMNET
/*public class FragmentDynamicActivity extends FagmentActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView();

    }
}*/
/*public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public static void saveText(String path,String txt)
    {
        try{
            FileOutputStream fos = new FileOutputStream(path);
            fos.write(txt.getBytes());
            fos.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public static String openText(String path){
        String readStr = "";
        try{
            FileInputStream fis = new FileInputStream(path);
            byte[] b = new byte[fis.available()];
            fis.read();
            readStr = new String(b);
            fis.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return readStr;
    }

}*/

/*//app数据存储
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqllite);
        View connectDB_button = findViewById(R.id.connectDB);
        View insertDB_button  = findViewById(R.id.insertDB);
        View deleteDB_button = findViewById(R.id.deleteDB);
        //设置监听器
        connectDB_button.setOnClickListener(this);
        insertDB_button.setOnClickListener(this);
        deleteDB_button.setOnClickListener(this);
    }
    @Override
    public void onClick(View v)
    {
        if(v.getId() == R.id.connectDB)
        {
            SQLiteDatabase db = getApplicationContext().openOrCreateDatabase("test.db",Context.MODE_PRIVATE,null);

        }
        if(v.getId() == R.id.insertDB)
        {

        }
        if(v.getId() == R.id.deleteDB){
            getApplication().deleteDatabase("test.db");
        }
    }
}*/

/*public class MainActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        Log.d("test","start");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sps = getSharedPreferences("share",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sps.edit();
        editor.putString("name","Mr Lee");
        editor.putInt("age",30);
        editor.putBoolean("married",true);
        editor.putFloat("weight",100f);
        editor.commit();
        String name = sps.getString("name","");
        Log.d("name",name);
        Toast.makeText(this,"1",Toast.LENGTH_SHORT);
    }
}*/
/*public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent in = new Intent();
        in.setClass(this, ActRequestActivity.class);
        startActivity( in );
    }

    public static class ActRequestActivity extends AppCompatActivity implements View.OnClickListener {
        private EditText et_request;
        private TextView tv_request;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_act_request);
            findViewById(R.id.button2).setOnClickListener(this);
            et_request = (EditText) findViewById(R.id.editText3);
            tv_request = (TextView) findViewById(R.id.textView);
        }

        @Override
        public void onClick(View v)
        {
            if(v.getId() == R.id.button2){
                Intent intent = new Intent();
                intent.setClass(this,MainActivity.ActResponseActivity.class);
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                intent.putExtra("request_time",df.format(new Date()));
                intent.putExtra("request_content","122");
                startActivityForResult(intent,0);
            }
        }

        @Override
        protected void onActivityResult(int requestCode,int resultCode,Intent data)
        {
            if(data != null)
            {
                String response_time = data.getStringExtra("response_time");
                String response_content = data.getStringExtra("response_content");
                String desc = String.format("收到返回的消息： \n应答时间为%s \n应答内容为:%s",response_time,response_content);
                tv_request.setText(desc);
            }
        }
    }

    public static class ActResponseActivity extends AppCompatActivity implements View.OnClickListener{
        private EditText et_response;
        private TextView tv_response;

        @Override
        protected void onCreate(Bundle saveInstanceState)
        {
            super.onCreate(saveInstanceState);
            setContentView(R.layout.activity_act_response);
            findViewById(R.id.button3).setOnClickListener(this);
            et_response = (EditText)findViewById(R.id.editText4);
            tv_response = (TextView)findViewById(R.id.textView2);
            Bundle bundle = getIntent().getExtras();
            String request_time = bundle.getString("request_time");
            String request_content = bundle.getString("request_content");
            String desc = String.format("%s,%s",request_time,request_content);
            tv_response.setText(desc);
        }

        @Override
        public void onClick(View v)
        {
            if(v.getId() == R.id.button3)
            {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                bundle.putString("response_time",df.format(new Date()));
                intent.putExtras(bundle);
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        }
    }
}*/

//反射api调用键盘
/*public class MainActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.hideAllInputMethod(this);
    }

    //反射api 获取最大长度
    public static int getMaxLength(EditText et)
    {
        int length=0;
        try{
            InputFilter[] inputFilters = et.getFilters();
            for(InputFilter filter : inputFilters){
                Class<?> c = filter.getClass();
                if(c.getName().equals("android.text.InputFilter$LengthFilter"));{
                    Field[] f= c.getDeclaredFields();
                    for(Field field:f)
                    {
                        if(field.getName().equals("mMax")){
                            field.setAccessible(true);
                            length=(Integer)field.get(filter);
                        }
                    }
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return length;
    }

    //开关软键盘
    public static void hideAllInputMethod(Activity act){
        InputMethodManager imm = (InputMethodManager)act.getSystemService(Context.INPUT_METHOD_SERVICE);
        if(imm.isActive() == true){
            imm.toggleSoftInput(0,InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
    public static void hideOneInputMethod(Activity act,View v){
        InputMethodManager imm = (InputMethodManager)act.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(),0);

    }
}*/

/*public class MainActivity extends AppCompatActivity{
    private String[] starArray={"1","2","3"};
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayAdapter<String> starAdapter = new ArrayAdapter<String>(this,R.layout.activity_main,starArray);
        starAdapter.setDropDownViewResource(R.layout.activity_main);
        Spinner sp = (Spinner)findViewById(R.id.spinner);
        sp.setAdapter(starAdapter);
        sp.setSelection(0);
        sp.setOnItemClickListener(new MySelectListener());
    }
    private class MySelectListener implements AdapterView.OnItemSelectedListener{
        public void onItemSelected(AdapterView<?>arg0,View arg1,int arg2,long arg3)
        {
            Toast.makeText(MainActivity.this,"1",Toast.LENGTH_LONG).show();
        }
        public void onNothingSelected(AdapterView<?> arg0)
        {

        }
    }

}*/
//复选框checkbox按钮
/*public class MainActivity extends  AppCompatActivity {
    private CheckBox tv_check;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_check = (CheckBox)findViewById(R.id.checkBox_aaa);
        tv_check.setOnCheckedChangeListener(new CheckListener());
    }

    private class CheckListener implements CompoundButton.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(CompoundButton buttonView,boolean isChecked){
            String desc = String.format("你勾选了控件%d,状态为%b",buttonView.getId(),isChecked);
            if(isChecked)
            {
                Log.i("1","1");
            }
            else
            {
                Log.i("2","2");
            }
            Toast.makeText(MainActivity.this,desc,Toast.LENGTH_LONG).show();
        }
    }
}*/

/*public class MainActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setOnclickListen(this)
        
    }
}*/

/*public class MainActivity extends AppCompatActivity implements View.OnClickListener,View.OnLongClickListener{
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
}*/
/*
public class MainActivity extends AppCompatActivity implements  View.OnClickListener {
    private TextView txtOne;
    private String getMobileID() throws Exception
    {
        return ((TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
    }
    private String generateHash() throws Exception
    {
        Object localobject1 = getMobileID();
        Object localobject2 = MessageDigest.getInstance("md5");
        return null;
    }
    private void getTestIMEI()
    {
        try {
            Toast.makeText(this, getMobileID().toString(), Toast.LENGTH_SHORT).show();
        }
        catch(Exception a)
        {

        }

    }

    public void onClick(View paramView)
    {
        try
        {
            getTestIMEI();
        }
        catch (Exception paramString)
        {
            paramString.printStackTrace();
        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtOne = (TextView)findViewById(R.id.txtOne);
        try {
            txtOne.setText("11");
            getTestIMEI();
        }
        catch (Exception paramString)
        {
            paramString.printStackTrace();

        }
        ((Button)findViewById(R.id.button)).setOnClickListener(this);
    }
}
*/


/*
public class Utils{
        public static int dip2px(Content context,float dbValue){
            final float scale = context.getResources().getDisplayMetrics().density;
            return (int)(dpValue*scale+0.5f);
        }
        }*/
/*public class DisplayUtil{
    public static int getSreenWidth(Context ctx) throws Exception{
        WindowsManager wm = (WindowsManager)ctx.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetric();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;

    }
}*/











