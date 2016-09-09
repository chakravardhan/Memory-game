package com.example.chakravardhan.mygame.events;

import com.example.chakravardhan.mygame.events.ui.StartEvent;
import com.example.chakravardhan.mygame.events.ui.ThemeSelectedEvent;


public class EventObserverAdapter implements EventObserver {


	@Override
	public void onEvent(StartEvent event) {
		throw new UnsupportedOperationException();
	}


	@Override
	public void onEvent(ThemeSelectedEvent event) {
		throw new UnsupportedOperationException();
	}
}
