package com.example.chakravardhan.mygame.events.engine;

import com.example.chakravardhan.mygame.events.AbstractEvent;
import com.example.chakravardhan.mygame.events.EventObserver;
import com.example.chakravardhan.mygame.model.GameState;

/**
 * When the 'back to menu' was pressed.
 */
public class GameWonEvent extends AbstractEvent {

	public static final String TYPE = GameWonEvent.class.getName();
	public GameState gameState;
	public GameWonEvent(GameState gameState) {
		this.gameState = gameState;
	}
	@Override
	protected void fire(EventObserver eventObserver) {
		eventObserver.onEvent(this);
	}

	@Override
	public String getType() {
		return TYPE;
	}

}
