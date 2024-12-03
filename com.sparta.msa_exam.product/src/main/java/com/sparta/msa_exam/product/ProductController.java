package com.sparta.msa_exam.product;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

  private final ProductService productService;

  @PostMapping
  public ProductResponseDto createProduct(@RequestBody ProductRequestDto productRequestDto) {
     return productService.createProduct(productRequestDto);
  }

  @GetMapping
  public Page<ProductResponseDto> getProduct(Pageable pageable) {
    return productService.getProduct(pageable);
  }
}
