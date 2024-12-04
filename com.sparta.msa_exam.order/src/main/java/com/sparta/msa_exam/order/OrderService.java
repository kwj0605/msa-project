package com.sparta.msa_exam.order;

import com.sparta.msa_exam.order.client.ProductClient;
import com.sparta.msa_exam.order.client.ProductResponseDto;
import com.sparta.msa_exam.order.exception.OrderServiceException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;

  @Transactional
  @CircuitBreaker(name = "failedCreateOrder", fallbackMethod = "fallbackCreateOrder")
  public OrderResponseDto createOrder(OrderRequestDto requestDto) {

    Order order = Order.createOrder(requestDto.getProductIds());
    Order savedOrder = orderRepository.save(order);
    return toResponseDto(savedOrder);
  }

  public OrderResponseDto fallbackCreateOrder(OrderRequestDto requestDto, Throwable t) {
    throw new OrderServiceException("잠시 후에 주문 추가를 요청 해주세요.");
  }

  @Transactional
  public OrderResponseDto additionalOrder(Long orderId, Long productId) {
    Order order = orderRepository.findById(orderId).orElseThrow(() ->
    new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));

    List<Long> productIds = order.getProductIds();
    productIds.add(productId);

    order.additionalOrder(productIds);
    Order updatedOrder = orderRepository.save(order);

    return toResponseDto(updatedOrder);
  }

  private OrderResponseDto toResponseDto(Order order) {
    return new OrderResponseDto(
        order.getId(),
        order.getProductIds()
    );
  }
}
