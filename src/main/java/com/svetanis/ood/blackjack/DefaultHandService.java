package com.svetanis.ood.blackjack;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.transform;

import java.util.List;

import com.svetanis.ood.blackjack.model.Hand;
import com.svetanis.ood.blackjack.model.PlayingCard;

public final class DefaultHandService<T extends PlayingCard> implements HandService<T> {

	@Override
	public Hand<T> add(Hand<T> hand, T card) {
		List<T> cards = newArrayList(hand.getCards());
		cards.add(card);
		return Hand.build(hand.getId(), cards);
	}

	@Override
	public int score(Hand<T> hand) {
		List<Integer> values = transform(hand.getCards(), c -> c.getRank());
		return values.stream().reduce(0, Integer::sum);
	}

}
