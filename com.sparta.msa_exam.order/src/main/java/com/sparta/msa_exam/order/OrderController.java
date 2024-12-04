package com.sparta.msa_exam.order;

import com.sparta.msa_exam.order.exception.OrderServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService;

  @PostMapping
  public ResponseEntity<?> createOrder(@RequestBody OrderRequestDto requestDto) {
    try {
      OrderResponseDto responseDto = orderService.createOrder(requestDto);
      return ResponseEntity.ok(responseDto);
    } catch (OrderServiceException e) {
      return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
    }

  }

  @PutMapping("/{orderId}")
  public OrderResponseDto additionalOrder(@PathVariable Long orderId,
      @RequestBody Long productId) {
    return orderService.additionalOrder(orderId, productId);
  }

  @GetMapping("/{orderId}")
  public OrderResponseDto getOrderById(@PathVariable Long orderId) {
    return orderService.getOrderById(orderId);
  }
}
