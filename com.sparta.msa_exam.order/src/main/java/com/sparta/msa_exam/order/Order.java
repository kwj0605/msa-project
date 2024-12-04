package com.sparta.msa_exam.order;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "orders")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ElementCollection
  @CollectionTable(name = "product_ids", joinColumns = @JoinColumn(name = "product_id"))
  @Column(name = "product_id")
  private List<Long> productIds;

  public static Order createOrder(List<Long> productIds) {
    return Order.builder()
        .productIds(productIds)
        .build();
  }

  // 상품 추가
  public void additionalOrder(List<Long> productIds) {
    this.productIds = productIds;
  }
}
