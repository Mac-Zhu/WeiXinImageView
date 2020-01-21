package com.mac.chu.weixinimagview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.io.File;

/**
 * Created by Mac on 2020/1/20
 * .
 */
public class ImagViewDetailActivity extends Activity implements View.OnClickListener, WeiXinImagView.TransformListener {
    private WeiXinImagView imageView = null;
    private String imageUrl;
    private String imageUrlType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent mIntent = getIntent();
        imageUrl = mIntent.getStringExtra(Contstant.IMAGEURL);
        imageUrlType = mIntent.getStringExtra(Contstant.IMAGEURLTYPE);
        int mLocationX = mIntent.getIntExtra(Contstant.LOCATIONX, 0);
        int mLocationY = mIntent.getIntExtra(Contstant.LOCATIONY, 0);
        int mWidth = mIntent.getIntExtra(Contstant.WIDTH, 0);
        int mHeight = mIntent.getIntExtra(Contstant.HEIGHT, 0);

        imageView = new WeiXinImagView(this);

        imageView.setOriginalInfo(mWidth, mHeight, mLocationX, mLocationY);
        imageView.transformIn();
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        imageView.enable();
        setContentView(imageView);
        if (imageUrlType.contentEquals(Contstant.IMAGEURLTYPE_FILE)) {
            File file = new File(imageUrl);
            Picasso.get().load(file).into(imageView);
        } else if (imageUrlType.contentEquals(Contstant.IMAGEURLTYPE_URL)) {
            Picasso.get().load(imageUrl).into(imageView);

        }
        setListener();


    }


    private void setListener() {
        imageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == imageView.getId()) {
            onBackPressed();
        }
    }


    @Override
    public void onTransformComplete(int mode) {
        if (mode == 2) {
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        imageView.setOnTransformListener(this);
        imageView.transformOut();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isFinishing()) {
            overridePendingTransition(0, 0);
        }
    }
}
