package com.sparta.msa_exam.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {
  private Long id;
  private String name;
  private Integer supplyPrice;
}
