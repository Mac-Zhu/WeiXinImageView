package com.mac.chu;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.mac.chu.weixinimagview.Contstant;
import com.mac.chu.weixinimagview.ImagViewDetailActivity;
import com.mac.chu.weixinimagview.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {


    ImageView iv_test;
    String url;
    String urltype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏

        setContentView(R.layout.activity_main);
        urltype = Contstant.IMAGEURLTYPE_URL;
        url = "http://clubimg.club.vmall.com/data/attachment/album/201411/11/162124fwwqv04ft7j45fd4.jpg";
        iv_test = (ImageView) findViewById(R.id.iv_test);

        Picasso.get().load(url).into(iv_test);
        iv_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ImagViewDetailActivity.class);
                intent.putExtra(Contstant.IMAGEURL, url);
                intent.putExtra(Contstant.IMAGEURLTYPE, urltype);
                int[] location = new int[2];
                iv_test.getLocationOnScreen(location);
                intent.putExtra(Contstant.LOCATIONX, location[0]);
                intent.putExtra(Contstant.LOCATIONY, location[1]);
                intent.putExtra(Contstant.WIDTH, iv_test.getWidth());
                intent.putExtra(Contstant.HEIGHT, iv_test.getHeight());
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
    }
}
