package org.shark.boot16.product.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class OrderProductId implements Serializable {

  @Column(name = "order_id")
  private Integer orderId;
  
  @Column(name = "product_id")
  private Integer productId;
  
  protected OrderProductId() {}
  
  public static OrderProductId createOrderProductId(Integer orderId, Integer productId) {
    OrderProductId id = new OrderProductId();
    id.orderId = orderId;
    id.productId = productId;
    return id;
  }

  @Override
  public String toString() {
    return "OrderProductId [orderId=" + orderId + ", productId=" + productId + "]";
  }
  
  
}
