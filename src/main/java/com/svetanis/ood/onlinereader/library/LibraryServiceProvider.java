package com.svetanis.ood.onlinereader.library;

import javax.inject.Provider;

import com.svetanis.java.base.dao.Dao;
import com.svetanis.ood.onlinereader.model.Book;

public final class LibraryServiceProvider implements Provider<LibraryService> {

  @Override
  public LibraryService get() {
    Dao<String, Book> dao = new SimpleLibraryDao();
    return new DefaultLibraryService(dao);
  }

}
