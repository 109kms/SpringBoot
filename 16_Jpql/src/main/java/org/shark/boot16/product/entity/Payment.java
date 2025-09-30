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
@Table(name = "payments")

@Getter
@Setter
public class Payment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "payment_id")
  private Integer paymentId;

  @Column(name = "payment_date", nullable = false)
  private LocalDate paymentDate;

  @Column(name = "payment_time", nullable = false)
  private LocalTime paymentTime;

  @Column(name = "payment_amount", nullable = false)
  private Integer paymentAmount;

  @Column(name = "payment_type", nullable = false, length = 20)
  private String paymentType;

  @Column(name = "payment_status", nullable = false, length = 20)
  private String paymentStatus;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;
  
  @OneToMany(mappedBy = "payment")
  private List<OrderPayment> orderPayments;
  
  protected Payment() {}

  public static Payment createPayment(LocalDate paymentDate, 
                                      LocalTime paymentTime, 
                                      Integer paymentAmount,
                                      String paymentType, 
                                      String paymentStatus, 
                                      User user) {
    Payment payment = new Payment();
    payment.setPaymentDate(paymentDate);
    payment.setPaymentTime(paymentTime);
    payment.setPaymentAmount(paymentAmount);
    payment.setPaymentType(paymentType);
    payment.setPaymentStatus(paymentStatus);
    payment.setUser(user);
    return payment;
  }

  @Override
  public String toString() {
    return "Payment [paymentId=" + paymentId + ", paymentDate=" + paymentDate + ", paymentTime=" + paymentTime
        + ", paymentAmount=" + paymentAmount + ", paymentType=" + paymentType + ", paymentStatus=" + paymentStatus
        + ", user=" + user + "]";
  }
  
}