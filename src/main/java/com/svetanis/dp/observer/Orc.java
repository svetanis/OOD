package com.svetanis.dp.observer;

import static java.lang.String.format;

public final class Orc implements WeatherObserver {

	private static final String MSG = "The orcs are facing %s weather now.";

	@Override
	public void update(WeatherType type) {
		System.out.println(format(MSG, type.name()));
	}

}