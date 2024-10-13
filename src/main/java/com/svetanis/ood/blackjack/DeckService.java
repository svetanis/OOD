package com.svetanis.ood.blackjack;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.svetanis.ood.blackjack.model.Hand;
import com.svetanis.ood.blackjack.model.PlayingCard;

public interface DeckService<T extends PlayingCard> {

	Optional<T> dealCard();

	Optional<Hand<T>> dealHand(int id, int number);

	ImmutableList<T> shuffle(Iterable<T> cards);

}
