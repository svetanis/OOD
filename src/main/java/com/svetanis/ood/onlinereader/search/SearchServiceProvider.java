package com.svetanis.ood.onlinereader.search;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.inject.Provider;

import com.svetanis.ood.onlinereader.catalog.CatalogService;

public final class SearchServiceProvider implements Provider<SearchService> {

  public SearchServiceProvider(CatalogService library) {
    this.library = checkNotNull(library, "library");
  }

  private final CatalogService library;

  @Override
  public SearchService get() {
    return new DefaultSearchService(library);
  }

}
