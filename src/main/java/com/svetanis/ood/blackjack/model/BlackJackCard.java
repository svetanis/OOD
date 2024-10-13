package com.svetanis.ood.blackjack.model;

import static com.svetanis.java.base.validate.Validation.checkAllPositives;
import static com.svetanis.java.base.validate.Validation.checkNoBlanks;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.svetanis.java.base.serialize.jaxb.BuildingAdapter;

@JsonDeserialize(builder = BlackJackCard.Builder.class)
public final class BlackJackCard extends AbstractCard {

	// number or face that is on card - a number 2 through 10,
	// or 11 for Jack, 12 for Queen, 13 for King, or 1 for Ace
	private final int value;
	private final int min;
	private final int max;
	private final boolean ace;
	private final boolean inrank;

	private BlackJackCard(Builder builder) {
		super(builder.rank, builder.suit);
		this.value = builder.value;
		this.max = builder.max;
		this.min = builder.min;
		this.ace = builder.ace;
		this.inrank = builder.inrank;
	}

	public static final Builder builder() {
		return new Builder();
	}

	public static class Builder extends BuildingAdapter<Builder, BlackJackCard> {

		private int rank = -1;
		private Suit suit;
		private int value = -1;
		private int min = -1;
		private int max = -1;
		private boolean ace;
		private boolean inrank;

		public final Builder withRank(int rank) {
			this.rank = rank;
			return this;
		}

		public final Builder withSuit(Suit suit) {
			this.suit = suit;
			return this;
		}

		public final Builder withValue(int value) {
			this.value = value;
			return this;
		}

		public final Builder withMin(int min) {
			this.min = min;
			return this;
		}

		public final Builder withMax(int max) {
			this.max = max;
			return this;
		}

		public final Builder withAce(boolean ace) {
			this.ace = ace;
			return this;
		}

		public final Builder withInrank(boolean inrank) {
			this.inrank = inrank;
			return this;
		}

		@Override
		public BlackJackCard build() {
			return validate(new BlackJackCard(this));
		}

		@Override
		public BlackJackCard get() {
			return build();
		}

		private static BlackJackCard validate(BlackJackCard instance) {
			checkNoBlanks(instance);
			checkAllPositives(instance);
			return instance;
		}

	}

	public int getValue() {
		return value;
	}

	public int getMin() {
		return min;
	}

	public int getMax() {
		return max;
	}

	public boolean isAce() {
		return ace;
	}

	public boolean isInrank() {
		return inrank;
	}

}