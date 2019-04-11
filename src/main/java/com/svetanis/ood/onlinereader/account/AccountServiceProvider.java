package com.svetanis.ood.onlinereader.account;

import javax.inject.Provider;

import com.svetanis.java.base.dao.Dao;
import com.svetanis.ood.onlinereader.model.Account;

public final class AccountServiceProvider implements Provider<AccountService> {

  @Override
  public AccountService get() {
    Dao<Integer, Account> dao = new SimpleAccountDao();
    return new DefaultAccountService(dao);
  }

}
