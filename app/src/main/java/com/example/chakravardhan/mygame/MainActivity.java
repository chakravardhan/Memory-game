package com.example.chakravardhan.mygame;

import android.graphics.Bitmap;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.chakravardhan.mygame.common.Shared;
import com.example.chakravardhan.mygame.engine.Engine;
import com.example.chakravardhan.mygame.engine.ScreenController;
import com.example.chakravardhan.mygame.events.EventBus;
import com.example.chakravardhan.mygame.utils.Utils;


public class MainActivity extends FragmentActivity {

    private ImageView mBackgroundImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Shared.context = getApplicationContext();
        Shared.eventBus = EventBus.getInstance();

        Shared.engine = Engine.getInstance();
        setContentView(R.layout.activity_main);
        mBackgroundImage = (ImageView) findViewById(R.id.background_image);

        Shared.activity = this;
        Shared.engine.start();
        Shared.engine.setBackgroundImageView(mBackgroundImage);
        // set background
        setBackgroundImage();

        // set menu
        ScreenController.getInstance().openScreen(ScreenController.Screen.MENU);
    }

    @Override
    protected void onDestroy() {
        Shared.engine.stop();
        super.onDestroy();
    }

    private void setBackgroundImage() {
        Bitmap bitmap = Utils.scaleDown(R.drawable.background, Utils.screenWidth(), Utils.screenHeight());
        bitmap = Utils.crop(bitmap, Utils.screenHeight(), Utils.screenWidth());
        bitmap = Utils.downscaleBitmap(bitmap, 2);
        mBackgroundImage.setImageBitmap(bitmap);
    }
}