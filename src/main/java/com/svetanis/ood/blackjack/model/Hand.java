package com.svetanis.ood.blackjack.model;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.validate.Validation.checkAllPositives;
import static com.svetanis.java.base.validate.Validation.checkNoBlanks;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.collect.ImmutableList;
import com.svetanis.java.base.ProvidingBuilder;

@JsonDeserialize(builder = Hand.Builder.class)
public final class Hand<T> {

	private final int id;
	private final ImmutableList<T> cards;

	private Hand(Builder<T> builder) {
		this.id = builder.id;
		this.cards = newList(builder.cards);
	}

	public static final <T> Hand<T> build(int id, List<T> cards) {
		Builder<T> builder = builder();
		builder.withId(id);
		builder.withCards(cards);
		return builder.build();
	}

	public static final <T> Builder<T> builder() {
		return new Builder<>();
	}

	public static class Builder<T> implements ProvidingBuilder<Hand<T>> {

		private int id = -1;
		private List<T> cards = newArrayList();

		public final Builder<T> withId(int id) {
			this.id = id;
			return this;
		}

		public final Builder<T> withCards(List<T> cards) {
			this.cards = cards;
			return this;
		}

		@Override
		public Hand<T> build() {
			return validate(new Hand<T>(this));
		}

		@Override
		public Hand<T> get() {
			return build();
		}

		private static <T> Hand<T> validate(Hand<T> instance) {
			checkNoBlanks(instance);
			checkAllPositives(instance);
			return instance;
		}

	}

	public int getId() {
		return id;
	}

	public ImmutableList<T> getCards() {
		return cards;
	}

}