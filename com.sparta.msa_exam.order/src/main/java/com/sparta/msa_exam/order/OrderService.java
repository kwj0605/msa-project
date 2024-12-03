package com.sparta.msa_exam.order;

import com.sparta.msa_exam.order.client.ProductClient;
import com.sparta.msa_exam.order.client.ProductResponseDto;
import com.sparta.msa_exam.order.exception.OrderServiceException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;

  @Transactional
  @CircuitBreaker(name = "failedCreateOrder", fallbackMethod = "fallbackCreateOrder")
  public OrderResponseDto createOrder(OrderRequestDto requestDto) {

    Order order = Order.createOrder(requestDto.getOrderItemIds());
    Order savedOrder = orderRepository.save(order);
    return toresponseDto(savedOrder);
  }

  public OrderResponseDto fallbackCreateOrder(OrderRequestDto requestDto, Throwable t) {
    throw new OrderServiceException("잠시 후에 주문 추가를 요청 해주세요.");
  }

  private OrderResponseDto toresponseDto(Order order) {
    return new OrderResponseDto(
        order.getId(),
        order.getOrderItemIds()
    );
  }

}
