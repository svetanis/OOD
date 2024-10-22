package com.svetanis.dp.observer;

import static java.lang.String.format;

public final class Hobbit implements WeatherObserver {

	private static final String MSG = "The hobbits are facing %s weather now.";

	@Override
	public void update(WeatherType type) {
		System.out.println(format(MSG, type.name()));
	}

}