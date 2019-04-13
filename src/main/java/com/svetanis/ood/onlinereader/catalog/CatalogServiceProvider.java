package com.svetanis.ood.onlinereader.catalog;

import javax.inject.Provider;

import com.svetanis.java.base.dao.Dao;
import com.svetanis.ood.onlinereader.model.Book;

public final class CatalogServiceProvider implements Provider<CatalogService> {

  @Override
  public CatalogService get() {
    Dao<String, Book> dao = new SimpleCatalogDao();
    return new DefaultCatalogService(dao);
  }

}
