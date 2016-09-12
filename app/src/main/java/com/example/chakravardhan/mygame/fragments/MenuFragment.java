package com.example.chakravardhan.mygame.fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.chakravardhan.mygame.R;
//import com.snatik.matches.common.Music;
import com.example.chakravardhan.mygame.common.Shared;
import com.example.chakravardhan.mygame.events.EventBus;
//import com.snatik.matches.ui.PopupManager;
import com.example.chakravardhan.mygame.events.ui.StartEvent;
import com.example.chakravardhan.mygame.utils.Utils;

public class MenuFragment extends Fragment {

	private ImageView mTitle;
	private ImageView mStartGameButton;
	private ImageView mStartButtonLights;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.menu_fragment, container, false);
		mTitle = (ImageView) view.findViewById(R.id.title);
		Log.e("title","title ha set");
		mStartGameButton = (ImageView) view.findViewById(R.id.start_game_button);

		mStartButtonLights = (ImageView) view.findViewById(R.id.start_game_button_lights);

		mStartGameButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.e("click","clicked");

					Shared.eventBus.notify(new StartEvent());


				// animate title from place and navigation buttons from place
				animateAllAssetsOff(new AnimatorListenerAdapter() {
					@Override
					public void onAnimationEnd(Animator animation) {
					}
				});
			}
		});
		startLightsAnimation();

		return view;
	}

	protected void animateAllAssetsOff(AnimatorListenerAdapter adapter) {
		// title
		// 120dp + 50dp + buffer(30dp)
		ObjectAnimator titleAnimator = ObjectAnimator.ofFloat(mTitle, "translationY", Utils.px(-200));
		titleAnimator.setInterpolator(new AccelerateInterpolator(2));
		titleAnimator.setDuration(300);

	// lights
		ObjectAnimator lightsAnimatorX = ObjectAnimator.ofFloat(mStartButtonLights, "scaleX", 0f);
		ObjectAnimator lightsAnimatorY = ObjectAnimator.ofFloat(mStartButtonLights, "scaleY", 0f);





		// start button
		ObjectAnimator startButtonAnimator = ObjectAnimator.ofFloat(mStartGameButton, "translationY", Utils.px(130));
		startButtonAnimator.setInterpolator(new AccelerateInterpolator(2));
		startButtonAnimator.setDuration(300);
		AnimatorSet animatorSet = new AnimatorSet();
		animatorSet.playTogether(titleAnimator, lightsAnimatorX, lightsAnimatorY,startButtonAnimator);
		animatorSet.addListener(adapter);
		animatorSet.start();
	}


	private void startLightsAnimation() {
		ObjectAnimator animator = ObjectAnimator.ofFloat(mStartButtonLights, "rotation", 0f, 360f);
		animator.setInterpolator(new AccelerateDecelerateInterpolator());
		animator.setDuration(6000);
		animator.setRepeatCount(ValueAnimator.INFINITE);
		mStartButtonLights.setLayerType(View.LAYER_TYPE_HARDWARE, null);
		animator.start();
	}

}
