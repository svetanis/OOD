package com.svetanis.ood.blackjack;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.svetanis.ood.blackjack.model.Hand;

public interface GameService<T> {

	ImmutableList<Hand<T>> dealInit(int players);

	ImmutableList<Hand<T>> playAllHands(List<Hand<T>> hands);

	ImmutableList<Integer> getWinners(Iterable<Hand<T>> hands);

	ImmutableMap<Integer, Integer> getScores(Iterable<Hand<T>> hands);

}
