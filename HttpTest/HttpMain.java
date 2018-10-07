package com.example.xcy_m.test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by xcy_m on 2018/8/8.
 */

public class HttpMain extends AppCompatActivity{
    private TextView txtMenu, txtShow;
    private ImageView imgPic;
    private WebView webView;
    private ScrollView scroll;
    private Bitmap bitmap;
    private String detail = "";
    private boolean flag = false;
    private final static String PIC_URL = "http://ww2.sinaimg.cn/large/7a8aed7bgw1evshgr5z3oj20hs0qo0vq.jpg";
    private final static String HTML_URL = "http://www.baidu.com";

    private Handler handler = new Handler(){
        public void handleMessage(android.os.Message msg){
            switch(msg.what){
                case 0x001:
                    hideAllWidget();
                    imgPic.setVisibility(View.VISIBLE);
                    imgPic.setImageBitmap(bitmap);
                    Toast.makeText(HttpMain.this,"图片加载完成", Toast.LENGTH_SHORT).show();
                    break;
                case 0x002:
                    hideAllWidget();
                    scroll.setVisibility(View.VISIBLE);
                    txtShow.setText(detail);
                    Toast.makeText(HttpMain.this, "HTML加载完成", Toast.LENGTH_SHORT).show();
                    break;
                case 0x003:
                    hideAllWidget();
                    webView.setVisibility(View.VISIBLE);
                    webView.loadDataWithBaseURL("",detail,"text/html","UTF-8","");
                    Toast.makeText(HttpMain.this, "网页加载完毕", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.http_show);
        setViews();
    }

    private void setViews(){
        txtMenu = (TextView)findViewById(R.id.txtMenu);
        txtShow = (TextView)findViewById(R.id.txtshow);
        imgPic = (ImageView)findViewById(R.id.imgPic);
        webView = (WebView)findViewById(R.id.webView);
        scroll = (ScrollView)findViewById(R.id.scroll);
        registerForContextMenu(txtMenu);
    }

    private void hideAllWidget(){
        imgPic.setVisibility(View.GONE);
        scroll.setVisibility(View.GONE);
        webView.setVisibility(View.GONE);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
//        MenuInflater inflator = new MenuInflater(this);
//        inflator.inflate(R.menu.menus,menu);
        menu.add(0, Menu.FIRST, 0,"1");
        menu.add(0, Menu.FIRST + 1 ,0, "2");
        menu.add(0, Menu.FIRST + 2, 0, "3");
        super.onCreateContextMenu(menu,v,menuInfo);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item){
        switch(item.getItemId()){
            case 1:
                new Thread(){
                   public void run(){
                       try{
                           byte[] data = HttpUtils.getImage(PIC_URL);
                           bitmap = BitmapFactory.decodeByteArray(data,0, data.length);
                       }
                       catch(Exception e){
                           e.printStackTrace();
                       }
                       handler.sendEmptyMessage(0x001);
                   }

                }.start();
                break;
            case 2:
                new Thread(){
                    public void run(){
                        try{
                            detail = HttpUtils.getHtml(HTML_URL);

                        }
                        catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                }.start();
                break;
            case 3:
                if(detail.equals("")){
                    Toast.makeText(HttpMain.this, "get html first", Toast.LENGTH_SHORT).show();
                }
                else{
                    handler.sendEmptyMessage(0x003);
                }
                break;
        }
        return true;
    }

}
