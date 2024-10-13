package com.svetanis.ood.blackjack;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.svetanis.ood.blackjack.model.BlackJackCard;
import com.svetanis.ood.blackjack.model.Hand;

public final class DefaultBlackJackHandService implements BlackJackHandService {

	private static int MIN = MIN_VALUE;
	private static int MAX = MAX_VALUE;

	@Override
	public Hand<BlackJackCard> add(Hand<BlackJackCard> hand, BlackJackCard card) {
		List<BlackJackCard> cards = newArrayList(hand.getCards());
		cards.add(card);
		return Hand.build(hand.getId(), cards);
	}

	@Override
	public int score(Hand<BlackJackCard> hand) {
		int maxUnder = MIN;
		int minOver = MAX;
		List<Integer> scores = possibleScores(hand);
		for (int score : scores) {
			if (score > 21 && score < minOver) {
				minOver = score;
			} else if (score <= 21 && score > maxUnder) {
				maxUnder = score;
			}
		}
		return maxUnder == MIN ? minOver : maxUnder;
	}

	private ImmutableList<Integer> possibleScores(Hand<BlackJackCard> hand) {
		if (hand.getCards().isEmpty()) {
			return newList();
		}
		List<Integer> scores = newArrayList();
		for (BlackJackCard card : hand.getCards()) {
			addCardToScoreList(card, scores);
		}
		return newList(scores);
	}

	private void addCardToScoreList(BlackJackCard card, List<Integer> scores) {
		if (scores.size() == 0) {
			scores.add(0);
		}
		int n = scores.size();
		for (int i = 0; i < n; ++i) {
			int score = scores.get(i);
			scores.set(i, score + card.getMin());
			if (card.getMin() != card.getMax()) {
				scores.add(score + card.getMax());
			}
		}
	}

	@Override
	public boolean busted(Hand<BlackJackCard> hand) {
		return score(hand) > 21;
	}

	@Override
	public boolean is21(Hand<BlackJackCard> hand) {
		return score(hand) == 21;
	}

	@Override
	public boolean isBlackJack(Hand<BlackJackCard> hand) {
		List<BlackJackCard> cards = hand.getCards();
		if (cards.size() != 2) {
			return false;
		}
		BlackJackCard first = cards.get(0);
		BlackJackCard second = cards.get(1);
		boolean one = first.isAce() && second.isInrank();
		boolean two = second.isAce() && first.isInrank();
		return one || two;
	}
}