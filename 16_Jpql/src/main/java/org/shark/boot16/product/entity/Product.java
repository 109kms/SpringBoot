package org.shark.boot16.product.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "product_id", nullable = false)
  private Integer productId;
  
  @Column(name = "product_name", nullable = false)
  private String productName;
  
  @Column(name = "product_price", nullable = false)
  private Integer productPrice;
  
  @Column(name = "stock_quantity", nullable = false)
  private Integer stockQuantity;
  
  @Column(name = "sale_status_yn", nullable = false)
  private Boolean saleStatusYn;
  
  @Column(name = "product_description", columnDefinition = "TEXT")
  private String productDescription;
  
  @Column(name = "register_date", nullable = false)
  private LocalDateTime registerDate;
  
  // 하나의 카테고리에는 여러 개의 제품이 들어감. 1:N
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id")
  private Category category;
  
  protected Product() {}
  
  public static Product createProduct(String productName, Integer productPrice, Integer stockQuantity, Boolean saleStatusYn, String productDescription) {
    Product p = new Product();
    p.productName = productName;
    p.productPrice = productPrice;
    p.stockQuantity = stockQuantity;
    p.saleStatusYn = saleStatusYn;
    p.productDescription = productDescription;
    p.registerDate = LocalDateTime.now();
    return p;
  }

  @Override
  public String toString() {
    return "Product [productId=" + productId + ", productName=" + productName + ", productPrice=" + productPrice
        + ", stockQuantity=" + stockQuantity + ", saleStatusYn=" + saleStatusYn + ", productDescription="
        + productDescription + ", registerDate=" + registerDate + ", category=" + category + "]";
  }
  
  
  
}
