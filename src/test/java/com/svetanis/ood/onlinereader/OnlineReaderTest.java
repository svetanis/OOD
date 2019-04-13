package com.svetanis.ood.onlinereader;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.Pairs.left;
import static com.svetanis.java.base.Pairs.right;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.collect.Lists.transform;
import static com.svetanis.java.base.serialize.json.Printing.COMPACT;
import static java.util.Arrays.asList;

import java.util.List;

import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.svetanis.java.base.Pair;
import com.svetanis.java.base.serialize.json.DefaultJsonSerializer;
import com.svetanis.java.base.serialize.json.JsonSerializer;
import com.svetanis.ood.onlinereader.catalog.CatalogService;
import com.svetanis.ood.onlinereader.catalog.CatalogServiceProvider;
import com.svetanis.ood.onlinereader.model.Author;
import com.svetanis.ood.onlinereader.model.Book;
import com.svetanis.ood.onlinereader.search.SearchService;
import com.svetanis.ood.onlinereader.search.SearchServiceProvider;

public class OnlineReaderTest {

  private final JsonSerializer json = new DefaultJsonSerializer(COMPACT);

  @Test
  public void test() {
    try {
      List<Book> list = getBooks();
      List<Pair<String, Book>> pairs = transform(list, b -> Pair.build(b.getIsbn(), b));
      CatalogService library = new CatalogServiceProvider().get();
      library.addAll(left(pairs), right(pairs));
      SearchService search = new SearchServiceProvider(library).get();
      List<Book> books = search.search(asList("java", "algorithms"));
      System.out.println(json.write(books));
    } catch (Throwable e) {
      e.printStackTrace();
      throw e;
    }
  }

  private ImmutableList<Book> getBooks() {
    List<Book> list = newArrayList();
    list.add(Book.build("QWERT", "Effective Java", Author.build("Joshua Bloch")));
    list.add(Book.build("YUIOP", "Concurrent Programming in Java", Author.build("Doug Lea")));
    list.add(Book.build("ASDFG", "Introduction to Algorithms", Author.build("Cormen")));
    list.add(Book.build("AHYUI", "Algorithms", Author.build("Sedgewick")));
    list.add(Book.build("LKJHG", "Think in Java", Author.build("Downey")));
    list.add(Book.build("MNBVC", "Design Patterns", Author.build("Fowler")));
    list.add(Book.build("ZXCVB", "Data Structures", Author.build("Weiss")));
    list.add(Book.build("ZPUYE", "Computer Networking", Author.build("Kurose Ross")));
    return newList(list);
  }
  
  /*
   * [
   *   { "authors" : [ { "description" : null, "name" : "joshua bloch" } ], "isbn" : "qwert", "language" : null, "pages" : null, "publisher" : null, "subject" : null, "title" : "effective java" },
   *   { "authors" : [ { "description" : null, "name" : "doug lea" } ], "isbn" : "yuiop", "language" : null, "pages" : null, "publisher" : null, "subject" : null, "title" : "concurrent programming in java" },
   *   { "authors" : [ { "description" : null, "name" : "cormen" } ], "isbn" : "asdfg", "language" : null, "pages" : null, "publisher" : null, "subject" : null, "title" : "introduction to algorithms" },
   *   { "authors" : [ { "description" : null, "name" : "sedgewick" } ], "isbn" : "ahyui", "language" : null, "pages" : null, "publisher" : null, "subject" : null, "title" : "algorithms" },
   *   { "authors" : [ { "description" : null, "name" : "downey" } ], "isbn" : "lkjhg", "language" : null, "pages" : null, "publisher" : null, "subject" : null, "title" : "think in java" }
   * ]
   */

}
