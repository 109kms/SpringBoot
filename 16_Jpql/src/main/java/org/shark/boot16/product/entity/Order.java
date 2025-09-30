package org.shark.boot16.product.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "orders")

@Getter
@Setter
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "order_id")
  private Integer orderId;

  @Column(name = "order_date", nullable = false)
  private LocalDate orderDate;

  @Column(name = "order_time", nullable = false)
  private LocalTime orderTime;

  @Column(name = "total_order_amount", nullable = false)
  private Integer totalOrderAmount;

  @Column(name = "order_status", nullable = false, length = 20)
  private String orderStatus;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;
  
  @OneToMany(mappedBy = "order")
  private List<OrderPayment> orderPayments;
  
  @OneToMany(mappedBy = "order")
  private List<OrderProduct> orderProducts;
  
  protected Order() { }
  
  public static Order createOrder(LocalDate orderDate, 
                                  LocalTime orderTime, 
                                  Integer totalOrderAmount, 
                                  String orderStatus, 
                                  User user) {
    Order order = new Order();
    order.setOrderDate(orderDate);
    order.setOrderTime(orderTime);
    order.setTotalOrderAmount(totalOrderAmount);
    order.setOrderStatus(orderStatus);
    order.setUser(user);
    return order;
  }

  @Override
  public String toString() {
    return "Order [orderId=" + orderId + ", orderDate=" + orderDate + ", orderTime=" + orderTime + ", totalOrderAmount="
        + totalOrderAmount + ", orderStatus=" + orderStatus + ", user=" + user + "]";
  }
  
}