package com.example.chakravardhan.mygame.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chakravardhan.mygame.R;
import com.example.chakravardhan.mygame.common.Shared;
import com.example.chakravardhan.mygame.events.engine.FlipDownCardsEvent;
import com.example.chakravardhan.mygame.events.engine.GameWonEvent;
import com.example.chakravardhan.mygame.events.engine.HidePairCardsEvent;

public class GameFragment extends BaseFragment {

	private TextView mTime;
	private ImageView mTimeImage;
	private LinearLayout ads;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		ViewGroup view = (ViewGroup) inflater.inflate(R.layout.game_fragment, container, false);
		view.setClipChildren(false);
		((ViewGroup) view.findViewById(R.id.game_board)).setClipChildren(false);
		mTime = (TextView) view.findViewById(R.id.time_bar_text);
		mTimeImage = (ImageView) view.findViewById(R.id.time_bar_image);
		FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.game_container);
		frameLayout.setClipChildren(false);

		// build board
		//buildBoard();
		Shared.eventBus.listen(FlipDownCardsEvent.TYPE, this);
		Shared.eventBus.listen(HidePairCardsEvent.TYPE, this);
		Shared.eventBus.listen(GameWonEvent.TYPE, this);

		return view;
	}

	@Override
	public void onDestroy() {
		Shared.eventBus.unlisten(FlipDownCardsEvent.TYPE, this);
		Shared.eventBus.unlisten(HidePairCardsEvent.TYPE, this);
		Shared.eventBus.unlisten(GameWonEvent.TYPE, this);
		super.onDestroy();
	}


	private void setTime(int time) {
		int min = time / 60;
		int sec = time - min * 60;
		mTime.setText(" " + String.format("%02d", min) + ":" + String.format("%02d", sec));
	}
}
