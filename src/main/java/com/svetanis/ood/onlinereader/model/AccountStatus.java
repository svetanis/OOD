package com.svetanis.ood.onlinereader.model;

import static com.svetanis.java.base.Enums.asEnumValue;
import static com.svetanis.java.base.Enums.fromEnumValues;

import com.google.common.collect.ImmutableMap;

public enum AccountStatus {
  
  ACTIVE, //
  CLOSED, //
  CANCELED, //
  BLACK_LISTED; //

  private static final ImmutableMap<String, AccountStatus> MAP = fromEnumValues(values());

  public static AccountStatus asAccountStatus(String s) {
    return asEnumValue(MAP, s);
  }
}