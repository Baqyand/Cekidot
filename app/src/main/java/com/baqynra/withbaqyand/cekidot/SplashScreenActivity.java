package com.baqynra.withbaqyand.cekidot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wang.avi.AVLoadingIndicatorView;

public class SplashScreenActivity extends AppCompatActivity {
    //pembuatan variabel untuk linearlayout
    ImageView GambarGif;
    private int waktu_loading=5000; // 2000 = 2 detik
    private AVLoadingIndicatorView avi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        GambarGif = (ImageView)findViewById(R.id.imgGif);
        avi= (AVLoadingIndicatorView) findViewById(R.id.avi);
        avi.setIndicator("BallClipRotateMultipleIndicator");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent pindah=new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(pindah);
                finish();
            }
        },waktu_loading);




    }
}


