package com.example.chakravardhan.mygame;

import android.graphics.Bitmap;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.chakravardhan.mygame.common.Shared;
import com.example.chakravardhan.mygame.engine.Engine;
import com.example.chakravardhan.mygame.engine.ScreenController;
import com.example.chakravardhan.mygame.events.EventBus;
import com.example.chakravardhan.mygame.events.ui.BackGameEvent;
import com.example.chakravardhan.mygame.ui.PopupManager;
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

    @Override
    public void onBackPressed() {
        Log.e("main activity","on back pressed is called");
        Log.e("main activity","screens: "+ ScreenController.getLastScreen());

        if (PopupManager.isShown()) {
            Log.e("main activity","middle");
            PopupManager.closePopup();
            if (ScreenController.getLastScreen() == ScreenController.Screen.GAME) {
                Shared.eventBus.notify(new BackGameEvent());
            }

        } else if (ScreenController.getInstance().onBack()) {
            Log.e("main activity","didn't go");
            super.onBackPressed();
        }

    }

    private void setBackgroundImage() {
        Bitmap bitmap = Utils.scaleDown(R.drawable.background, Utils.screenWidth(), Utils.screenHeight());
        bitmap = Utils.crop(bitmap, Utils.screenHeight(), Utils.screenWidth());
        bitmap = Utils.downscaleBitmap(bitmap, 2);
        mBackgroundImage.setImageBitmap(bitmap);
    }
}