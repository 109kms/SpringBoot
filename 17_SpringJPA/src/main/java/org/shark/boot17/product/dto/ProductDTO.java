package org.shark.boot17.product.dto;

import java.time.LocalDateTime;

import org.shark.boot17.product.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProductDTO {
  
  private Integer productId;
  private String productName;
  private Integer productPrice;
  private Integer stockQuantity;
  private Boolean saleStatusYn;
  private String productDescription;
  private LocalDateTime registerDate;
  private Integer categoryId;
  private String categoryName;
  
  // Entity -> DTO
  public static ProductDTO toDTO(Product product) {
    ProductDTO dto = new ProductDTO();
    dto.setProductId(product.getProductId());
    dto.setProductName(product.getProductName());
    dto.setProductPrice(product.getProductPrice());
    dto.setStockQuantity(product.getStockQuantity());
    dto.setSaleStatusYn(product.getSaleStatusYn());
    dto.setProductDescription(product.getProductDescription());
    dto.setRegisterDate(product.getRegisterDate());
    if (product.getCategory() != null) {
      dto.setCategoryId(product.getCategory().getCategoryId());
      dto.setCategoryName(product.getCategory().getCategoryName());
    }
    return dto;
  }
  
}
