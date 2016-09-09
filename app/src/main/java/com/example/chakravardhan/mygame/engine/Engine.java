package com.example.chakravardhan.mygame.engine;


import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.widget.ImageView;

import com.example.chakravardhan.mygame.R;
import com.example.chakravardhan.mygame.common.Shared;
import com.example.chakravardhan.mygame.engine.ScreenController.Screen;
import com.example.chakravardhan.mygame.events.EventObserverAdapter;

import com.example.chakravardhan.mygame.events.ui.BackGameEvent;
import com.example.chakravardhan.mygame.events.ui.DifficultySelectedEvent;
import com.example.chakravardhan.mygame.events.ui.FlipCardEvent;
import com.example.chakravardhan.mygame.events.ui.NextGameEvent;
import com.example.chakravardhan.mygame.events.ui.ResetBackgroundEvent;
import com.example.chakravardhan.mygame.events.ui.StartEvent;
import com.example.chakravardhan.mygame.events.ui.ThemeSelectedEvent;

import com.example.chakravardhan.mygame.themes.Theme;
import com.example.chakravardhan.mygame.themes.Themes;

import com.example.chakravardhan.mygame.utils.Utils;

public class Engine extends EventObserverAdapter {

	private static Engine mInstance = null;
	private ScreenController mScreenController;
	private Theme mSelectedTheme;
	private ImageView mBackgroundImage;
	private Handler mHandler;

	private Engine() {
		mScreenController = ScreenController.getInstance();
		mHandler = new Handler();
	}

	public static Engine getInstance() {
		if (mInstance == null) {
			mInstance = new Engine();
		}
		return mInstance;
	}

	public void start() {
		Shared.eventBus.listen(DifficultySelectedEvent.TYPE, this);
		Shared.eventBus.listen(FlipCardEvent.TYPE, this);
		Shared.eventBus.listen(StartEvent.TYPE, this);
		Shared.eventBus.listen(ThemeSelectedEvent.TYPE, this);
		Shared.eventBus.listen(BackGameEvent.TYPE, this);
		Shared.eventBus.listen(NextGameEvent.TYPE, this);
		Shared.eventBus.listen(ResetBackgroundEvent.TYPE, this);;
	}

	public void stop() {
		mBackgroundImage.setImageDrawable(null);
		mBackgroundImage = null;
		mHandler.removeCallbacksAndMessages(null);
		mHandler = null;

		Shared.eventBus.unlisten(DifficultySelectedEvent.TYPE, this);
		Shared.eventBus.unlisten(FlipCardEvent.TYPE, this);
		Shared.eventBus.unlisten(StartEvent.TYPE, this);
		Shared.eventBus.unlisten(ThemeSelectedEvent.TYPE, this);
		Shared.eventBus.unlisten(BackGameEvent.TYPE, this);
		Shared.eventBus.unlisten(NextGameEvent.TYPE, this);
		Shared.eventBus.unlisten(ResetBackgroundEvent.TYPE, this);

		mInstance = null;
	}

	@Override
	public void onEvent(ResetBackgroundEvent event) {
		Drawable drawable = mBackgroundImage.getDrawable();
		if (drawable != null) {
			((TransitionDrawable) drawable).reverseTransition(2000);
		} else {
			new AsyncTask<Void, Void, Bitmap>() {

				@Override
				protected Bitmap doInBackground(Void... params) {
					Bitmap bitmap = Utils.scaleDown(R.drawable.background, Utils.screenWidth(), Utils.screenHeight());
					return bitmap;
				}

				protected void onPostExecute(Bitmap bitmap) {
					mBackgroundImage.setImageBitmap(bitmap);
				};

			}.execute();
		}
	}

	@Override
	public void onEvent(StartEvent event) {
		mScreenController.openScreen(Screen.THEME_SELECT);
	}
	@Override
	public void onEvent(BackGameEvent event) {
//		PopupManager.closePopup();
		mScreenController.openScreen(Screen.DIFFICULTY);
	}
	@Override
	public void onEvent(ThemeSelectedEvent event) {
		mSelectedTheme = event.theme;
		mScreenController.openScreen(Screen.DIFFICULTY);
		AsyncTask<Void, Void, TransitionDrawable> task = new AsyncTask<Void, Void, TransitionDrawable>() {

			@Override
			protected TransitionDrawable doInBackground(Void... params) {
				Bitmap bitmap = Utils.scaleDown(R.drawable.background, Utils.screenWidth(), Utils.screenHeight());
				Bitmap backgroundImage = Themes.getBackgroundImage(mSelectedTheme);
				backgroundImage = Utils.crop(backgroundImage, Utils.screenHeight(), Utils.screenWidth());
				Drawable backgrounds[] = new Drawable[2];
				backgrounds[0] = new BitmapDrawable(Shared.context.getResources(), bitmap);
				backgrounds[1] = new BitmapDrawable(Shared.context.getResources(), backgroundImage);
				TransitionDrawable crossfader = new TransitionDrawable(backgrounds);
				return crossfader;
			}

			@Override
			protected void onPostExecute(TransitionDrawable result) {
				super.onPostExecute(result);
				mBackgroundImage.setImageDrawable(result);
				result.startTransition(2000);
			}
		};
		task.execute();
	}

	public void setBackgroundImageView(ImageView backgroundImage) {
		mBackgroundImage = backgroundImage;
	}


}
