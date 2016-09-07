package com.example.chakravardhan.mygame.events.ui;

import com.example.chakravardhan.mygame.events.AbstractEvent;
import com.example.chakravardhan.mygame.events.EventObserver;
import com.example.chakravardhan.mygame.themes.Theme;

public class ThemeSelectedEvent extends AbstractEvent {

	public static final String TYPE = ThemeSelectedEvent.class.getName();
	public final Theme theme;

	public ThemeSelectedEvent(Theme theme) {
		this.theme = theme;
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
