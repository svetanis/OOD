package com.svetanis.ood.blackjack.model;

import static com.svetanis.java.base.validate.Validation.checkAllPositives;
import static com.svetanis.java.base.validate.Validation.checkNoBlanks;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.svetanis.java.base.serialize.jaxb.BuildingAdapter;

@JsonDeserialize(builder = Card.Builder.class)
public final class Card extends AbstractCard {

	// number or face that is on card - a number 2 through 10,
	// or 11 for Jack, 12 for Queen, 13 for King, or 1 for Ace

	private Card(Builder builder) {
		super(builder.rank, builder.suit);
	}

	public static final Card build(int rank, Suit suit) {
		Builder builder = builder();
		builder.withRank(rank);
		builder.withSuit(suit);
		return builder.build();
	}

	public static final Builder builder() {
		return new Builder();
	}

	public static class Builder extends BuildingAdapter<Builder, Card> {

		private int rank = -1;
		private Suit suit;

		public final Builder withRank(int rank) {
			this.rank = rank;
			return this;
		}

		public final Builder withSuit(Suit suit) {
			this.suit = suit;
			return this;
		}

		@Override
		public Card build() {
			return validate(new Card(this));
		}

		@Override
		public Card get() {
			return build();
		}

		private static Card validate(Card instance) {
			checkNoBlanks(instance);
			checkAllPositives(instance);
			return instance;
		}
	}

}