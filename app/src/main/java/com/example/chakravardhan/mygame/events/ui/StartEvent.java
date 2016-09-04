package com.example.chakravardhan.mygame.events.ui;

import com.example.chakravardhan.mygame.events.EventBus;
import com.example.chakravardhan.mygame.events.*;

/**
 * When the 'back to menu' was pressed.
 */
public class StartEvent extends AbstractEvent {

	public static final String TYPE = StartEvent.class.getName();

	@Override
	protected void fire(EventObserver eventObserver) {
		eventObserver.onEvent(this);
	}

	@Override
	public String getType() {
		return TYPE;
	}

}
