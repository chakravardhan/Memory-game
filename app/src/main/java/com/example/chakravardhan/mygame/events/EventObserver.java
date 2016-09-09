package com.example.chakravardhan.mygame.events;


import com.example.chakravardhan.mygame.events.ui.StartEvent;
import com.example.chakravardhan.mygame.events.ui.ThemeSelectedEvent;

public interface EventObserver {


	void onEvent(StartEvent event);

	void onEvent(ThemeSelectedEvent event);


}
