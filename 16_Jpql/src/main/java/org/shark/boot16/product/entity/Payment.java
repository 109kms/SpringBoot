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
@Table(name = "payments")
public class Payment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "payment_id")
  private Integer paymentId;
  
  @Column(name = "payment_date")
  private LocalDate paymentDate;
  
  @Column(name = "payment_time")
  private LocalTime paymentTime;
  
  @Column(name = "payment_amount")
  private Integer paymentAmount;
  
  @Column(name = "payment_type", length = 20)
  private String paymentType;
  
  @Column(name = "payment_status", length = 20)
  private String paymentStatus;
  
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id")
  private User user;
  
  protected Payment() {}
  
  public static Payment createPayment(Integer paymentAmount, String paymentType, String paymentStatus) {
    Payment p = new Payment();
    p.paymentAmount = paymentAmount;
    p.paymentType = paymentType;
    p.paymentStatus = paymentStatus;
    return p;
  }

  @Override
  public String toString() {
    return "Payment [paymentId=" + paymentId + ", paymentDate=" + paymentDate + ", paymentTime=" + paymentTime
        + ", paymentAmount=" + paymentAmount + ", paymentType=" + paymentType + ", paymentStatus=" + paymentStatus
        + "]";
  }
  
  
}
