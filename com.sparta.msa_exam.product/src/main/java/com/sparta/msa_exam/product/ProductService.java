package com.sparta.msa_exam.product;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;

  @Transactional
  public ProductResponseDto createProduct(ProductRequestDto requestDto) {
    Product product = Product.createProduct(requestDto);
    return toResponseDto(productRepository.save(product));
  }

  private ProductResponseDto toResponseDto(Product product) {
    return new ProductResponseDto(
        product.getId(),
        product.getName(),
        product.getSupplyPrice()
    );
  }

  @Transactional(readOnly = true)
  public Page<ProductResponseDto> getProduct(Pageable pageable) {
    return productRepository.getProduct(pageable);
  }
}
