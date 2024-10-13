package com.svetanis.ood.blackjack;

import com.google.common.collect.ImmutableList;
import com.svetanis.ood.blackjack.model.BlackJackCard;
import com.svetanis.ood.blackjack.model.Hand;

public interface BlackJackGameService extends GameService<BlackJackCard> {

	ImmutableList<Integer> getBlackJacks(Iterable<Hand<BlackJackCard>> hands);

}
