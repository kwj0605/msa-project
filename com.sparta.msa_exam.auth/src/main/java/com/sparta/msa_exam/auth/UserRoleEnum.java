package com.sparta.msa_exam.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRoleEnum {
  CUSTOMER("ROLE_COSTOMER"),
  MANAGER("ROLE_MANAGER");

  private final String role;
}
