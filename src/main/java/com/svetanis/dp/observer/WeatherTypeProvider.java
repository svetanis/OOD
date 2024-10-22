package com.svetanis.dp.observer;

import static java.util.Arrays.asList;

import java.util.List;

import javax.inject.Provider;

public final class WeatherTypeProvider implements Provider<WeatherType> {

	public WeatherTypeProvider(int offset) {
		this.offset = offset;
	}

	private final int offset;

	@Override
	public WeatherType get() {
		List<WeatherType> list = asList(WeatherType.values());
		int index = (offset + 1) % list.size();
		return list.get(index);
	}

}