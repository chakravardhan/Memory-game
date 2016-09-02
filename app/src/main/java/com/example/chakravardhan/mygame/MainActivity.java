package com.example.chakravardhan.mygame;

import android.graphics.Bitmap;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.chakravardhan.mygame.common.Shared;
import com.example.chakravardhan.mygame.utils.Utils;

public class MainActivity extends FragmentActivity {

    private ImageView mBackgroundImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Shared.context = getApplicationContext();

        setContentView(R.layout.activity_main);
        mBackgroundImage = (ImageView) findViewById(R.id.background_image);

        Shared.activity = this;

        // set background
        setBackgroundImage();

        // set menu
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    private void setBackgroundImage() {
        Bitmap bitmap = Utils.scaleDown(R.drawable.background, Utils.screenWidth(), Utils.screenHeight());
        bitmap = Utils.crop(bitmap, Utils.screenHeight(), Utils.screenWidth());
        bitmap = Utils.downscaleBitmap(bitmap, 2);
        mBackgroundImage.setImageBitmap(bitmap);
    }
}