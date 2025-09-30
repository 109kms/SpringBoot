package org.shark.boot17.product.service;

import java.util.List;

import org.shark.boot17.product.dto.ProductDTO;
import org.springframework.data.domain.Pageable;

public interface ProductService {
  ProductDTO saveProduct(ProductDTO dto);
  ProductDTO updateProduct(ProductDTO dto);
  void deleteProduct(Integer productId);
  ProductDTO findProductById(Integer productId);
  List<ProductDTO> findProductList(Pageable pageable);
}
