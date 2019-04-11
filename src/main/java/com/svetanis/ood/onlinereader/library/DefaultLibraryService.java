package com.svetanis.ood.onlinereader.library;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.collect.ImmutableMap;
import com.svetanis.java.base.dao.Dao;
import com.svetanis.ood.onlinereader.model.Book;

public final class DefaultLibraryService implements LibraryService {

  public DefaultLibraryService(Dao<String, Book> dao) {
    this.dao = checkNotNull(dao, "dao");
  }

  private final Dao<String, Book> dao;

  @Override
  public Book get(String key) {
    return dao.get(key);
  }

  @Override
  public ImmutableMap<String, Book> getAll(Iterable<String> keys) {
    return dao.getAll(keys);
  }

  @Override
  public ImmutableMap<String, Book> getAll() {
    return dao.getAll();
  }

  @Override
  public Book add(String key, Book account) {
    return dao.add(key, account);
  }

  @Override
  public ImmutableMap<String, Book> addAll(Iterable<String> keys, Iterable<Book> books) {
    return dao.addAll(keys, books);
  }

  @Override
  public Book delete(String key) {
    return dao.delete(key);
  }

}
