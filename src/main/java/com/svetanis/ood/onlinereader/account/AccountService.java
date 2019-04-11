package com.svetanis.ood.onlinereader.account;

import com.google.common.collect.ImmutableMap;
import com.svetanis.ood.onlinereader.model.Account;

public interface AccountService {

  Account get(int key);

  ImmutableMap<Integer, Account> getAll(Iterable<Integer> keys);

  Account create(int key, Account account);
  
  Account update(int key, Account account);

  Account close(int key);

}