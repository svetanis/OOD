package com.svetanis.dp.strategy;

import java.util.List;

public interface Sorting {

	public <C extends Comparable<C>> void sort(List<C> list);


}
