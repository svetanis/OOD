package com.svetanis.ood.blackjack;

import com.google.common.base.Function;
import com.svetanis.ood.blackjack.model.BlackJackCard;
import com.svetanis.ood.blackjack.model.Card;

public enum BlackJackCardFunction implements Function<Card, BlackJackCard> {
	INSTANCE;

	@Override
	public BlackJackCard apply(Card input) {
		int rank = input.getRank();
		BlackJackCard.Builder builder = BlackJackCard.builder();
		builder.withRank(rank);
		builder.withSuit(input.getSuit());
		builder.withValue(value(rank));
		builder.withMin(min(rank));
		builder.withMax(max(rank));
		builder.withAce(isAce(rank));
		builder.withInrank(isRank(rank));
		return builder.build();
	}

	private int value(int rank) {
		if (isAce(rank)) {
			return 1;
		} else if (isRank(rank)) {
			return 10;
		} else {
			return rank;
		}
	}

	private int min(int rank) {
		return isAce(rank) ? 1 : value(rank);
	}

	private int max(int rank) {
		return isAce(rank) ? 11 : value(rank);
	}

	private boolean isAce(int rank) {
		return rank == 1;
	}

	private boolean isRank(int rank) {
		return rank >= 11 && rank <= 13;
	}

}