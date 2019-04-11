package com.svetanis.ood.onlinereader.library;

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
import com.svetanis.ood.onlinereader.model.Book;

public final class SimpleLibraryDao implements Dao<String, Book> {

  public SimpleLibraryDao() {
    this.map = newHashMap();
  }

  private Map<String, Book> map;

  @Override
  public Book get(String key) {
    return checkedGet(map, key);
  }

  @Override
  public ImmutableMap<String, Book> getAll(Iterable<String> keys) {
    return newMap(filterKeys(map, in(newSet(keys))));
  }

  @Override
  public ImmutableMap<String, Book> getAll() {
    return newMap(map);
  }

  @Override
  public Book add(String key, Book book) {
    return checkedPut(map, key, book);
  }

  @Override
  public ImmutableMap<String, Book> addAll(Iterable<String> keys, Iterable<Book> values) {
    map = combine(map, asMap(asPairs(newList(keys), newList(values))));
    return newMap(map);
  }

  @Override
  public Book delete(String key) {
    return checkedDelete(map, key);
  }

}
