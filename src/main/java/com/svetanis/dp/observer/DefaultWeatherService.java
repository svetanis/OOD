package com.svetanis.dp.observer;

import static java.lang.String.format;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Provider;

public final class DefaultWeatherService implements WeatherService {

	private static final SecureRandom RAND = new SecureRandom();

	public DefaultWeatherService() {
		this.observers = new ArrayList<>();
	}

	private final List<WeatherObserver> observers;

	@Override
	public void addObserver(WeatherObserver observer) {
		this.observers.add(observer);
	}

	@Override
	public void removeObserver(WeatherObserver observer) {
		this.observers.remove(observer);
	}

	@Override
	public void timePasses() {
		WeatherType type = wtp().get();
		System.out.println(format("The weather changed to %s", type));
		notifyObservers(type);
	}

	private Provider<WeatherType> wtp() {
		return new WeatherTypeProvider(RAND.nextInt(100));
	}

	private void notifyObservers(WeatherType type) {
		for (WeatherObserver observer : observers) {
			observer.update(type);
		}
	}
}