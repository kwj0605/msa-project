package com.sparta.msa_exam.product;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCustom {

  private final JPAQueryFactory queryFactory;

  @Override
  public Page<ProductResponseDto> getProduct(Pageable pageable) {
    QProduct product = QProduct.product;

    List<Product> results = queryFactory
        .selectFrom(product)
        .orderBy(product.id.asc())
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetch();

    long total = queryFactory
        .selectFrom(product)
        .fetchCount();

    List<ProductResponseDto> responseDtos = results.stream()
        .map(p -> new ProductResponseDto(p.getId(), p.getName(), p.getSupplyPrice()))
        .collect(Collectors.toList());

    return new PageImpl<>(responseDtos, pageable, total);
  }
}
