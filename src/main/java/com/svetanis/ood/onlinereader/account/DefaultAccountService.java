package com.svetanis.ood.onlinereader.account;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.collect.ImmutableMap;
import com.svetanis.java.base.dao.Dao;
import com.svetanis.ood.onlinereader.model.Account;

public final class DefaultAccountService implements AccountService {

  public DefaultAccountService(Dao<Integer, Account> dao) {
    this.dao = checkNotNull(dao, "dao");
  }

  private final Dao<Integer, Account> dao;

  @Override
  public Account get(int key) {
    return dao.get(key);
  }

  @Override
  public ImmutableMap<Integer, Account> getAll(Iterable<Integer> keys) {
    return dao.getAll(keys);
  }

  @Override
  public Account create(int key, Account account) {
    return dao.add(key, account);
  }

  @Override
  public Account update(int key, Account account) {
    close(key);
    return create(key, account);
  }

  @Override
  public Account close(int key) {
    return dao.delete(key);
  }

}
