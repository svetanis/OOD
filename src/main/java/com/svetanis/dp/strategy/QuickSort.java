package com.svetanis.dp.strategy;

import static com.svetanis.java.base.utils.Random.randomIndex;
import static com.svetanis.java.base.utils.Swap.swap;

import java.util.List;

public final class QuickSort implements Sorting {

	@Override
	public <C extends Comparable<C>> void sort(List<C> list) {
		sort(list, 0, list.size() - 1);
	}

	private <C extends Comparable<C>> void sort(List<C> list, int left, int right) {
		if (left < right) {
			int pivot = randomizedPartition(list, left, right);
			sort(list, left, pivot - 1);
			sort(list, pivot + 1, right);
		}
	}

	private <C extends Comparable<C>> int randomizedPartition(List<C> list, int left, int right) {
		int index = randomIndex(left, right);
		return partition(list, left, right, index);
	}

	// smaller elements to the left
	private <C extends Comparable<C>> int partition(List<C> list, int left, int right, int index) {
		int i = left - 1;
		swap(list, right, index);
		C pivot = list.get(right);
		for (int j = left; j < right; ++j) {
			if (list.get(j).compareTo(pivot) < 1) {
				i++;
				swap(list, i, j);
			}
		}
		// move pivot to its final place
		swap(list, i + 1, right);
		return i + 1;
	}
}
