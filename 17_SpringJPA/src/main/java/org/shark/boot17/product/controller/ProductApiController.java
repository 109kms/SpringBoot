package org.shark.boot17.product.controller;

import java.util.Map;

import org.shark.boot17.product.dto.ProductDTO;
import org.shark.boot17.product.dto.response.ApiResponseDTO;
import org.shark.boot17.product.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductApiController {

  private final ProductService productService;
  
  //----- 등록 요청
  @PostMapping
  public ResponseEntity<ApiResponseDTO> save(ProductDTO dto) {
    ApiResponseDTO apiResponseDTO = ApiResponseDTO.builder()
                                        .code("201")
                                        .message("제품 등록 성공")
                                        .results(Map.of("savedProduct", productService.saveProduct(dto)))
                                        .build();
    return ResponseEntity.status(HttpStatus.CREATED).body(apiResponseDTO);
  }
  
  //----- 수정 요청
  
  
}
