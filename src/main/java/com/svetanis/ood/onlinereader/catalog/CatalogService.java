package com.svetanis.ood.onlinereader.catalog;

import com.google.common.collect.ImmutableMap;
import com.svetanis.ood.onlinereader.model.Book;

public interface CatalogService {

  Book get(String key);

  ImmutableMap<String, Book> getAll(Iterable<String> keys);

  ImmutableMap<String, Book> getAll();

  Book add(String key, Book book);

  ImmutableMap<String, Book> addAll(Iterable<String> keys, Iterable<Book> books);

  Book delete(String key);

}