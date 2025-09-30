package org.shark.boot17.product.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.shark.boot17.product.dto.ProductDTO;
import org.shark.boot17.product.entity.Category;
import org.shark.boot17.product.entity.Product;
import org.shark.boot17.product.repository.CategoryRepository;
import org.shark.boot17.product.repository.ProductRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;
  private final CategoryRepository categoryRepository;
  
  @Override
  public ProductDTO saveProduct(ProductDTO dto) {
    // categoryId에 해당하는 Category 정보가 필요
    Category foundCategory = categoryRepository.findById(dto.getCategoryId())
                                                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 카테고리입니다."));
    // ProductDTO dto -> Product product
    Product product = dto.toEntity(foundCategory);
    Product savedProduct = productRepository.save(product);
    // Product savedProduct -> ProductDTO
    return ProductDTO.toDTO(savedProduct);
  }

  @Override
  public ProductDTO updateProduct(ProductDTO dto) {
    // findById 메소드 호출 결과는 영속 컨텍스트에 저장됩니다.
    Product foundProduct = productRepository.findById(dto.getProductId())
                                             .orElseThrow(() -> new NoSuchElementException("존재하지 않는 제품입니다."));
    // 영속 컨텍스트에 저장된 foundProduct의 내용을 수정하면 변경감지(Dirty Check)에 의해서 자동으로 수정됩니다.
    foundProduct.setProductName("아이폰 17 Pro mini");
    foundProduct.setProductPrice(200);
    foundProduct.setStockQuantity(5);
    foundProduct.setSaleStatusYn(false);
    foundProduct.setProductDescription("조마조마한 성능의 아이폰 17 프로 미니");
    // 수정된 엔티티 foundProduct을 ProductDTO로 변환해서 반환
    return ProductDTO.toDTO(foundProduct);
  }

  @Override
  public void deleteProduct(Integer productId) {
    // TODO Auto-generated method stub

  }

  @Transactional(readOnly = true)
  @Override
  public ProductDTO findProductById(Integer productId) {
    // TODO Auto-generated method stub
    return null;
  }

  @Transactional(readOnly = true)
  @Override
  public List<ProductDTO> findProductList(Pageable pageable) {
    // TODO Auto-generated method stub
    return null;
  }

}
