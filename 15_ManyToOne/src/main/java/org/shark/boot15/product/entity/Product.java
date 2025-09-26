package org.shark.boot15.product.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "products")

@Getter
@Setter
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "product_id")
  private Long id;
  
  @Column(name = "product_name")
  private String productName;
  
  private Double price;
  
  private Integer stock;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id")
  private Category category;
  
  protected Product() {}

  public static Product createdProduct(String productName, Double price, Integer stock) {
    Product product = new Product();
    product.productName = productName;
    product.price = price;
    product.stock = stock;
    return product;
  }

  @Override
  public String toString() {
    return "Product [id=" + id + ", productName=" + productName + ", price=" + price + ", stock=" + stock
        + ", category=" + category + "]";
  }
  
  
}
