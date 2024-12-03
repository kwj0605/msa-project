package com.sparta.msa_exam.order;

import com.sparta.msa_exam.order.client.ProductClient;
import com.sparta.msa_exam.order.client.ProductResponseDto;
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
  public OrderResponseDto createOrder(OrderRequestDto requestDto) {

    Order order = Order.createOrder(requestDto.getOrderItemIds());
    Order savedOrder = orderRepository.save(order);
    return toresponseDto(savedOrder);
  }

  private OrderResponseDto toresponseDto(Order order) {
    return new OrderResponseDto(
        order.getId(),
        order.getOrderItemIds()
    );
  }

}
