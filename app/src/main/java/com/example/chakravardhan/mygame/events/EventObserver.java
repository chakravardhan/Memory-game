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

public interface EventObserver {


	void onEvent(StartEvent event);

	void onEvent(ThemeSelectedEvent event);
	void onEvent(BackGameEvent event);
	void onEvent(DifficultySelectedEvent event);
	void onEvent(FlipCardEvent event);
	void onEvent(NextGameEvent event);
	void onEvent(ResetBackgroundEvent event);
	void onEvent(GameWonEvent event);
	void onEvent(HidePairCardsEvent event);

	void onEvent(FlipDownCardsEvent event);

}
