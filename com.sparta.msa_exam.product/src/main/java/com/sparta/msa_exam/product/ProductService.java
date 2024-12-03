package com.sparta.msa_exam.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;

  @Transactional
  public ProcductResponseDto createProduct(ProductRequestDto requestDto) {
    Product product = Product.createProduct(requestDto);
    return toResponseDto(productRepository.save(product));
  }

  private ProcductResponseDto toResponseDto(Product product) {
    return new ProcductResponseDto(
        product.getId(),
        product.getName(),
        product.getSupplyPrice()
    );
  }
}
