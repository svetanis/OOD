package com.svetanis.dp.strategy;

import static com.google.common.base.Stopwatch.createStarted;
import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.Formats.getTime;
import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Random;

import com.google.common.base.Stopwatch;

public final class DefaultSorting implements Sorting {

	@Override
	public <C extends Comparable<C>> void sort(List<C> list) {
		if (list.size() <= 1000) {
			sort(new InsertionSort(), list);
		}
		sort(new QuickSort(), list);
	}

	public <C extends Comparable<C>> void sort(Sorting strategy, List<C> list) {
		strategy.sort(list);
	}

	public static void main(String[] args) {
		Random random = new Random();
		int size = 100000;
		int bound = 1000000;
		List<Integer> list = random.ints(size, 0, bound).boxed().collect(toList());
		List<Integer> clone1 = newArrayList(list);
		List<Integer> clone2 = newArrayList(list);

		List<Integer> list2 = random.ints(1000, 0, bound).boxed().collect(toList());

		DefaultSorting df = new DefaultSorting();

		Stopwatch swq = createStarted();
		df.sort(list);
		System.out.println("Quick--> " + getTime(swq));

		Stopwatch swh = createStarted();
		df.sort(new HeapSort(), clone1);
		System.out.println("Heap --> " + getTime(swh));

		Stopwatch sws = createStarted();
		df.sort(list2);
		System.out.println("Insertion Small --> " + getTime(sws));

		Stopwatch swi = createStarted();
		df.sort(new InsertionSort(), clone2);
		System.out.println("Insertion --> " + getTime(swi));

		// Quick--> 35ms
		// Heap --> 36ms
		// Insertion Small --> 8ms
		// Insertion --> 9.281s
	}
}