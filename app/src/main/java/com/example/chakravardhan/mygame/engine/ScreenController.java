package com.example.chakravardhan.mygame.engine;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.chakravardhan.mygame.*;
import com.example.chakravardhan.mygame.common.Shared;
//import com.snatik.matches.events.ui.ResetBackgroundEvent;
//import com.snatik.matches.fragments.DifficultySelectFragment;
//import com.snatik.matches.fragments.GameFragment;
import com.example.chakravardhan.mygame.events.ui.DifficultySelectedEvent;
import com.example.chakravardhan.mygame.events.ui.ResetBackgroundEvent;
import com.example.chakravardhan.mygame.fragments.DifficultySelectFragment;
import com.example.chakravardhan.mygame.fragments.GameFragment;
import com.example.chakravardhan.mygame.fragments.MenuFragment;
import com.example.chakravardhan.mygame.fragments.ThemeSelectFragment;

public class ScreenController {

	private static ScreenController mInstance = null;
	private static List<Screen> openedScreens = new ArrayList<Screen>();
	private FragmentManager mFragmentManager;

	private ScreenController() {
	}

	public static ScreenController getInstance() {
		if (mInstance == null) {
			mInstance = new ScreenController();
		}
		return mInstance;
	}

	public static enum Screen {
		MENU,
		GAME,
		DIFFICULTY,
		THEME_SELECT
	}
	

	public void openScreen(Screen screen) {
		mFragmentManager = Shared.activity.getSupportFragmentManager();
		
		if (screen == Screen.GAME && openedScreens.get(openedScreens.size() - 1) == Screen.GAME) {
			openedScreens.remove(openedScreens.size() - 1);
		} else if (screen == Screen.DIFFICULTY && openedScreens.get(openedScreens.size() - 1) == Screen.GAME) {
			openedScreens.remove(openedScreens.size() - 1);
			openedScreens.remove(openedScreens.size() - 1);
		}
		Fragment fragment = getFragment(screen);
		FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
		fragmentTransaction.replace(R.id.fragment_container, fragment);
		fragmentTransaction.commit();
		openedScreens.add(screen);
	}
	public boolean onBack() {
		if (openedScreens.size() > 0) {
			Screen screenToRemove = openedScreens.get(openedScreens.size() - 1);
			openedScreens.remove(openedScreens.size() - 1);
			if (openedScreens.size() == 0) {
				return true;
			}
			Screen screen = openedScreens.get(openedScreens.size() - 1);
			openedScreens.remove(openedScreens.size() - 1);
			openScreen(screen);
			if ((screen == Screen.THEME_SELECT || screen == Screen.MENU) &&
					(screenToRemove == Screen.DIFFICULTY || screenToRemove == Screen.GAME)) {
				Shared.eventBus.notify(new ResetBackgroundEvent());
			}
			return false;
		}
		return true;
	}



	private Fragment getFragment(Screen screen) {
		switch (screen) {
			case MENU:
				return new MenuFragment();
			case THEME_SELECT:
				return new ThemeSelectFragment();
			case DIFFICULTY:
				return new DifficultySelectFragment();
			case GAME:
				return new GameFragment();
		default:
			break;
		}
		return null;
	}
}
