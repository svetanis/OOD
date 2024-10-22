package com.svetanis.dp.observer;

public final class WeatherObserverClient {

	public static void main(String[] args) {
		WeatherService ws = new DefaultWeatherService();
		ws.addObserver(new Orc());
		ws.addObserver(new Hobbit());
		ws.timePasses();
		ws.timePasses();
		ws.timePasses();
		ws.timePasses();
	}
}
