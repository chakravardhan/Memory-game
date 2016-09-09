package com.example.chakravardhan.mygame.events.engine;

import com.example.chakravardhan.mygame.events.AbstractEvent;
import com.example.chakravardhan.mygame.events.EventObserver;

/**
 * When the 'back to menu' was pressed.
 */
public class GameWonEvent extends AbstractEvent {

	public static final String TYPE = GameWonEvent.class.getName();

	@Override
	protected void fire(EventObserver eventObserver) {
		eventObserver.onEvent(this);
	}

	@Override
	public String getType() {
		return TYPE;
	}

}
