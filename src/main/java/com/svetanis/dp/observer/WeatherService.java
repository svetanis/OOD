package com.svetanis.dp.observer;

public interface WeatherService {

	void addObserver(WeatherObserver observer);

	void removeObserver(WeatherObserver observer);

	void timePasses();

}