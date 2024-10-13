package com.svetanis.ood.blackjack.model;

import static com.svetanis.java.base.Enums.asEnumValue;
import static com.svetanis.java.base.Enums.fromEnumValues;

import com.google.common.collect.ImmutableMap;

public enum Suit {

	HEART, //
	SPADE, //
	CLUB, //
	DIAMOND; //

	private static final ImmutableMap<String, Suit> MAP = fromEnumValues(values());

	public static Suit asSuit(String s) {
		return asEnumValue(MAP, s);
	}
}