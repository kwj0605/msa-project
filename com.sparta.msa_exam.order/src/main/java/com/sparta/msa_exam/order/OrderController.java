package com.sparta.msa_exam.order;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService;

  @PostMapping
  public OrderResponseDto createOrder(@RequestBody OrderRequestDto requestDto) {
    return orderService.createOrder(requestDto);
  }

}
