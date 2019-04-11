package com.svetanis.ood.onlinereader.search;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.inject.Provider;

import com.svetanis.ood.onlinereader.library.LibraryService;

public final class SearchServiceProvider implements Provider<SearchService> {

  public SearchServiceProvider(LibraryService library) {
    this.library = checkNotNull(library, "library");
  }

  private final LibraryService library;

  @Override
  public SearchService get() {
    return new DefaultSearchService(library);
  }

}
