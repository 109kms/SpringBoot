package org.shark.boot16.product.entity;

import java.time.LocalDate;
import java.time.LocalTime;

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
@Table(name = "orders")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "order_id")
  private Integer orderId;
  
  @Column(name = "order_date")
  private LocalDate orderDate;
  
  @Column(name = "order_time")
  private LocalTime orderTime;
  
  @Column(name = "total_order_amount")
  private Integer totalOrderAmount;
  
  @Column(name = "order_status", length = 20)
  private String orderStatus;
  
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id")
  private User user;
  
  protected Order() {}
  
  public static Order createOrder(Integer totalOrderAmount, String orderStatus) {
    Order o = new Order();
    o.totalOrderAmount = totalOrderAmount;
    o.orderStatus = orderStatus;
    return o;
  }
  
}
