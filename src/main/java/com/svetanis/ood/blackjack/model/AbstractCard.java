package com.svetanis.ood.blackjack.model;

import static com.google.common.base.MoreObjects.toStringHelper;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.svetanis.java.base.Objects.equalByComparison;
import static com.svetanis.java.base.Preconditions.checkMin;
import static java.util.Objects.hash;

import com.google.common.base.MoreObjects.ToStringHelper;
import com.google.common.collect.ComparisonChain;

public abstract class AbstractCard implements PlayingCard, Comparable<PlayingCard> {

	public AbstractCard(int rank, Suit suit) {
		this.rank = checkMin(rank, 1, "rank");
		this.suit = checkNotNull(suit, "suit");
		this.hash = hash(rank, suit);
	}

	private final int rank;
	private final Suit suit;
	private final int hash;

	@Override
	public int getRank() {
		return rank;
	}

	@Override
	public Suit getSuit() {
		return suit;
	}

	@Override
	public int compareTo(PlayingCard other) {
		ComparisonChain chain = ComparisonChain.start();
		chain = chain.compare(rank, other.getRank());
		chain = chain.compare(suit, other.getSuit());
		return chain.result();
	}

	@Override
	public int hashCode() {
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		return equalByComparison(this, object);
	}

	@Override
	public String toString() {
		ToStringHelper helper = toStringHelper(this);
		helper.add("rank", rank);
		helper.add("suit", suit);
		return helper.toString();
	}

}
