package com.example.chakravardhan.mygame.common;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.example.chakravardhan.mygame.events.EventBus;

import com.example.chakravardhan.mygame.engine.Engine;

public class Shared {

	public static Context context;
	public static FragmentActivity activity; // it's fine for this app, but better move to weak reference
	public static Engine engine;
	public static EventBus eventBus;
}
