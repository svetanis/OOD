package com.svetanis.ood.blackjack.model;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.validate.Validation.checkAllPositives;
import static com.svetanis.java.base.validate.Validation.checkNoBlanks;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.collect.ImmutableList;
import com.svetanis.java.base.ProvidingBuilder;

@JsonDeserialize(builder = Player.Builder.class)
public final class Player<T> {

	private final int id;
	private final ImmutableList<Hand<T>> hands;

	private Player(Builder<T> builder) {
		this.id = builder.id;
		this.hands = newList(builder.hands);
	}

	public static final <T> Player<T> build(int id, List<Hand<T>> hands) {
		Builder<T> builder = builder();
		builder.withId(id);
		builder.withHands(hands);
		return builder.build();
	}

	public static final <T> Builder<T> builder() {
		return new Builder<>();
	}

	public static class Builder<T> implements ProvidingBuilder<Player<T>> {

		private int id = -1;
		private List<Hand<T>> hands = newArrayList();

		public final Builder<T> withId(int id) {
			this.id = id;
			return this;
		}

		public final Builder<T> withHands(List<Hand<T>> hands) {
			this.hands = hands;
			return this;
		}

		@Override
		public Player<T> build() {
			return validate(new Player<T>(this));
		}

		@Override
		public Player<T> get() {
			return build();
		}

		private static <T> Player<T> validate(Player<T> instance) {
			checkNoBlanks(instance);
			checkAllPositives(instance);
			checkArgument(instance.getHands().size() == 2, "only 2 cards allowed");
			return instance;
		}

	}

	public int getId() {
		return id;
	}

	public ImmutableList<Hand<T>> getHands() {
		return hands;
	}

}