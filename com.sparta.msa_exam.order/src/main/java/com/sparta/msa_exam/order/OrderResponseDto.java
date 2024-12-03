package com.sparta.msa_exam.order;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {
  private Long id;
  private List<Long> orderItemIds;

}
