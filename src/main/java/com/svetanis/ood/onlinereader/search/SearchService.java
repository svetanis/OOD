package com.svetanis.ood.onlinereader.search;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.svetanis.ood.onlinereader.model.Author;
import com.svetanis.ood.onlinereader.model.Book;

public interface SearchService {

  Optional<Book> search(String title);

  ImmutableList<Book> search(Author author);

  ImmutableList<Book> search(Iterable<String> keywords);

}
