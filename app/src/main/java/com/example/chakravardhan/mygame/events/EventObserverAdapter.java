package com.example.chakravardhan.mygame.events;

import com.example.chakravardhan.mygame.events.engine.FlipDownCardsEvent;
import com.example.chakravardhan.mygame.events.engine.GameWonEvent;
import com.example.chakravardhan.mygame.events.engine.HidePairCardsEvent;
import com.example.chakravardhan.mygame.events.ui.BackGameEvent;
import com.example.chakravardhan.mygame.events.ui.DifficultySelectedEvent;
import com.example.chakravardhan.mygame.events.ui.FlipCardEvent;
import com.example.chakravardhan.mygame.events.ui.NextGameEvent;
import com.example.chakravardhan.mygame.events.ui.ResetBackgroundEvent;
import com.example.chakravardhan.mygame.events.ui.StartEvent;
import com.example.chakravardhan.mygame.events.ui.ThemeSelectedEvent;


public class EventObserverAdapter implements EventObserver {

	public void onEvent(FlipCardEvent event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(DifficultySelectedEvent event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(HidePairCardsEvent event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(FlipDownCardsEvent event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(StartEvent event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(ThemeSelectedEvent event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(GameWonEvent event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(BackGameEvent event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(NextGameEvent event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(ResetBackgroundEvent event) {
		throw new UnsupportedOperationException();
	}
}
