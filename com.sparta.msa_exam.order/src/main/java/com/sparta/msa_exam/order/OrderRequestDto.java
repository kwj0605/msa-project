package com.sparta.msa_exam.order;

import java.util.List;
import lombok.Data;

@Data
public class OrderRequestDto {
  private List<Long> productIds;
}
