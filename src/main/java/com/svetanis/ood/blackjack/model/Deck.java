package com.svetanis.ood.blackjack.model;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.validate.Validation.checkAllPositives;
import static com.svetanis.java.base.validate.Validation.checkNoBlanks;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.collect.ImmutableList;
import com.svetanis.java.base.ProvidingBuilder;

@JsonDeserialize(builder = Deck.Builder.class)
public final class Deck<T> {

	private final int deal;
	private final ImmutableList<T> cards;

	private Deck(Builder<T> builder) {
		this.deal = builder.deal;
		this.cards = newList(builder.cards);
	}

	public static final <T> Deck<T> build(int index, List<T> cards) {
		Builder<T> builder = builder();
		builder.withDeal(index);
		builder.withCards(cards);
		return builder.build();
	}

	public static final <T> Builder<T> builder() {
		return new Builder<>();
	}

	public static class Builder<T> implements ProvidingBuilder<Deck<T>> {

		private int deal = -1;
		private List<T> cards = newArrayList();

		public final Builder<T> withDeal(int deal) {
			this.deal = deal;
			return this;
		}

		public final Builder<T> withCards(List<T> cards) {
			this.cards = cards;
			return this;
		}

		@Override
		public Deck<T> build() {
			return validate(new Deck<T>(this));
		}

		@Override
		public Deck<T> get() {
			return build();
		}

		private static <T> Deck<T> validate(Deck<T> instance) {
			checkNoBlanks(instance);
			checkAllPositives(instance);
			return instance;
		}

	}

	public int getDeal() {
		return deal;
	}

	public ImmutableList<T> getCards() {
		return cards;
	}

}