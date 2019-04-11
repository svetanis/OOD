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

@JsonDeserialize(builder = Account.Builder.class)
public final class Account implements Comparable<Account> {

  private final int id;
  private final AccountStatus status;
  private final User user;
  @IgnoreValidation
  private final int hash;

  private Account(Builder builder) {
    this.id = builder.id;
    this.status = builder.status;
    this.user = builder.user;
    this.hash = hash(id, status, user);
  }

  public static final Builder builder() {
    return new Builder();
  }

  public static class Builder extends BuildingAdapter<Builder, Account> {

    private int id = -1;
    private AccountStatus status;
    private User user;

    public final Builder withId(int id) {
      this.id = id;
      return this;
    }

    public final Builder withStatus(AccountStatus status) {
      this.status = status;
      return this;
    }

    public final Builder withUser(User user) {
      this.user = user;
      return this;
    }

    @Override
    public Account build() {
      return validate(new Account(this));
    }

    @Override
    public Account get() {
      return build();
    }

    private static Account validate(Account instance) {
      checkNoBlanks(instance);
      checkAllPositives(instance);
      return instance;
    }

  }

  public int getId() {
    return id;
  }

  public AccountStatus getStatus() {
    return status;
  }

  public User getUser() {
    return user;
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
    helper.add("id", id);
    helper.add("status", status);
    helper.add("user", user);
    return helper.toString();
  }

  @Override
  public int compareTo(Account other) {
    ComparisonChain chain = ComparisonChain.start();
    chain = chain.compare(id, other.id);
    chain = chain.compare(status, other.status);
    chain = chain.compare(user, other.user);
    return chain.result();
  }

}