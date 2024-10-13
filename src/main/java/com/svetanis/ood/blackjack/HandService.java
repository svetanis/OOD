package com.svetanis.ood.blackjack;

import com.svetanis.ood.blackjack.model.Hand;
import com.svetanis.ood.blackjack.model.PlayingCard;

public interface HandService<T extends PlayingCard> {

	Hand<T> add(Hand<T> hand, T card);

	int score(Hand<T> hand);

}
