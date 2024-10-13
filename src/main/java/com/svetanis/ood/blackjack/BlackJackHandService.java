package com.svetanis.ood.blackjack;

import com.svetanis.ood.blackjack.model.BlackJackCard;
import com.svetanis.ood.blackjack.model.Hand;

public interface BlackJackHandService extends HandService<BlackJackCard> {

	boolean busted(Hand<BlackJackCard> hand);

	boolean is21(Hand<BlackJackCard> hand);

	boolean isBlackJack(Hand<BlackJackCard> hand);

}
