package com.svetanis.ood.onlinereader.search;

import static com.google.common.base.Functions.identity;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Multimaps.filterKeys;
import static com.google.common.collect.Multimaps.invertFrom;
import static com.svetanis.java.base.Strings.lcase;
import static com.svetanis.java.base.collect.Lists.binarySearch;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.collect.Maps.asMap;
import static com.svetanis.java.base.collect.Multimaps.asMultimap;
import static com.svetanis.java.base.collect.Sets.in;
import static com.svetanis.java.base.collect.Sets.newSet;

import java.util.List;

import com.google.common.base.Optional;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Multimap;
import com.svetanis.ood.onlinereader.catalog.CatalogService;
import com.svetanis.ood.onlinereader.model.Author;
import com.svetanis.ood.onlinereader.model.Book;

public final class DefaultSearchService implements SearchService {

  public DefaultSearchService(CatalogService library) {
    this.library = checkNotNull(library, "library");
  }

  private final CatalogService library;

  @Override
  public Optional<Book> search(String title) {
    List<Book> list = getAll(library);
    return binarySearch(list, b -> b.getTitle(), lcase(title));
  }

  @Override
  public ImmutableList<Book> search(Author author) {
    List<Book> list = getAll(library);
    Multimap<Book, Author> mm = asMultimap(asMap(list, identity(), b -> b.getAuthors()));
    Multimap<Author, Book> inverse = invertFrom(mm, ArrayListMultimap.create());
    return newList(inverse.get(author));
  }

  @Override
  public ImmutableList<Book> search(Iterable<String> keywords) {
    Multimap<String, String> mm = new KeywordsProvider(getAll(library)).get();
    List<String> keys = newList(filterKeys(mm, in(newSet(keywords))).values());
    return newList(library.getAll(keys).values());
  }

  private ImmutableList<Book> getAll(CatalogService library) {
    return newList(library.getAll().values());
  }
}
