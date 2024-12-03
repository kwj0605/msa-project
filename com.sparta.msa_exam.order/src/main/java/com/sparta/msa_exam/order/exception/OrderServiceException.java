package com.sparta.msa_exam.order.exception;

public class OrderServiceException extends RuntimeException {
  public OrderServiceException(String message) {
    super(message);
  }
}