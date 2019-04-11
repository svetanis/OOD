package com.svetanis.ood.onlinereader.account;

import static com.google.common.collect.Maps.filterKeys;
import static com.google.common.collect.Maps.newHashMap;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.collect.Maps.asMap;
import static com.svetanis.java.base.collect.Maps.asPairs;
import static com.svetanis.java.base.collect.Maps.checkedDelete;
import static com.svetanis.java.base.collect.Maps.checkedGet;
import static com.svetanis.java.base.collect.Maps.checkedPut;
import static com.svetanis.java.base.collect.Maps.combine;
import static com.svetanis.java.base.collect.Maps.newMap;
import static com.svetanis.java.base.collect.Sets.in;
import static com.svetanis.java.base.collect.Sets.newSet;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.svetanis.java.base.dao.Dao;
import com.svetanis.ood.onlinereader.model.Account;

public final class SimpleAccountDao implements Dao<Integer, Account> {

  public SimpleAccountDao() {
    this.map = newHashMap();
  }

  private Map<Integer, Account> map;

  @Override
  public Account get(Integer key) {
    return checkedGet(map, key);
  }

  @Override
  public ImmutableMap<Integer, Account> getAll(Iterable<Integer> keys) {
    return newMap(filterKeys(map, in(newSet(keys))));
  }

  @Override
  public ImmutableMap<Integer, Account> getAll() {
    return newMap(map);
  }

  @Override
  public Account add(Integer key, Account account) {
    return checkedPut(map, key, account);
  }

  @Override
  public ImmutableMap<Integer, Account> addAll(Iterable<Integer> keys, Iterable<Account> values) {
    map = combine(map, asMap(asPairs(newList(keys), newList(values))));
    return newMap(map);
  }

  @Override
  public Account delete(Integer key) {
    return checkedDelete(map, key);
  }

}
