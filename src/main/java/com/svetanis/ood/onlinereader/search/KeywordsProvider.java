package com.svetanis.ood.onlinereader.search;

import static com.google.common.base.Predicates.notNull;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Multimaps.invertFrom;
import static com.svetanis.java.base.Optionals.toNullFromAbsent;
import static com.svetanis.java.base.Splitters.splitOnAny;
import static com.svetanis.java.base.Strings.lcase;
import static com.svetanis.java.base.collect.Lists.concat;
import static com.svetanis.java.base.collect.Lists.filter;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.collect.Lists.transform;
import static com.svetanis.java.base.collect.Maps.asMap;
import static com.svetanis.java.base.collect.Multimaps.asMultimap;
import static com.svetanis.java.base.collect.Multimaps.newMultimap;

import java.util.List;

import javax.inject.Provider;

import com.google.common.base.Function;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.svetanis.ood.onlinereader.model.Book;

public final class KeywordsProvider implements Provider<ImmutableMultimap<String, String>> {

  public KeywordsProvider(Iterable<Book> books) {
    this.books = newList(books);
  }

  private ImmutableList<Book> books;

  @Override
  public ImmutableMultimap<String, String> get() {
    Multimap<String, String> mm = asMultimap(asMap(books, b -> b.getIsbn(), KeywordFunction.INSTANCE));
    return newMultimap(invertFrom(mm, ArrayListMultimap.create()));
  }

  private enum KeywordFunction implements Function<Book, ImmutableList<String>> {
    INSTANCE;

    @Override
    public ImmutableList<String> apply(Book input) {
      List<String> list = newArrayList();
      list.add(input.getTitle());
      list.add(toNullFromAbsent(input.getSubject()));
      list.add(toNullFromAbsent(input.getPublisher()));
      list.addAll(transform(input.getAuthors(), a -> a.getName()));
      //list.addAll(transform(input.getAuthors(), a -> toNullFromAbsent(a.getDescription())));
      List<List<String>> splitted = transform(filter(list, notNull()), s -> splitOnAny(" ,.:;", s));
      // TODO: filter out stop-words
      return transform(concat(splitted), s -> lcase(s));
    }
  }

}
