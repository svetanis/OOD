package com.svetanis.ood.onlinereader.model;

import static com.google.common.base.MoreObjects.toStringHelper;
import static com.svetanis.java.base.Objects.equalByComparison;
import static com.svetanis.java.base.validate.Validation.checkAllPositives;
import static com.svetanis.java.base.validate.Validation.checkNoBlanks;
import static java.util.Objects.hash;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.MoreObjects.ToStringHelper;
import com.google.common.collect.ComparisonChain;
import com.svetanis.java.base.serialize.jaxb.BuildingAdapter;
import com.svetanis.java.base.validate.IgnoreValidation;

@JsonDeserialize(builder = Profile.Builder.class)
public final class Profile implements Comparable<Profile> {

  private final int page;
  private final Book book;
  private final Account account;
  @IgnoreValidation
  private final int hash;

  private Profile(Builder builder) {
    this.page = builder.page;
    this.book = builder.book;
    this.account = builder.account;
    this.hash = hash(page, book, account);
  }

  public static Profile build(Account account, Book book, int page) {
    Builder builder = builder();
    builder.withPage(page);
    builder.withBook(book);
    builder.withAccount(account);
    return builder.build();
  }

  public static final Builder builder() {
    return new Builder();
  }

  public static class Builder extends BuildingAdapter<Builder, Profile> {

    private int page = -1;
    private Book book;
    private Account account;

    public final Builder withPage(int page) {
      this.page = page;
      return this;
    }

    public final Builder withBook(Book book) {
      this.book = book;
      return this;
    }

    public final Builder withAccount(Account account) {
      this.account = account;
      return this;
    }

    @Override
    public Profile build() {
      return validate(new Profile(this));
    }

    @Override
    public Profile get() {
      return build();
    }

    private static Profile validate(Profile instance) {
      checkNoBlanks(instance);
      checkAllPositives(instance);
      return instance;
    }

  }

  public int getPage() {
    return page;
  }

  public Book getBook() {
    return book;
  }

  public Account getAccount() {
    return account;
  }

  @Override
  public int hashCode() {
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    return equalByComparison(this, object, true);
  }

  @Override
  public String toString() {
    ToStringHelper helper = toStringHelper(this);
    helper.add("account", account);
    helper.add("book", book);
    helper.add("page", page);
    return helper.toString();
  }

  @Override
  public int compareTo(Profile other) {
    ComparisonChain chain = ComparisonChain.start();
    chain = chain.compare(account, other.account);
    chain = chain.compare(book, other.book);
    chain = chain.compare(page, other.page);
    return chain.result();
  }

}