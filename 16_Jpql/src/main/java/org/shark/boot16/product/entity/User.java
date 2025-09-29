package org.shark.boot16.product.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")

@Setter
@Getter
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Integer userId;
  
  @Column(nullable = false, length = 50)
  private String username;
  
  @Embedded
  private Contact contact;
  
  @Column(length = 200)
  private String address;
  
  @Column(name = "birth_date")
  private LocalDate birthDate;
  
  @Column(name = "join_date")
  private LocalDateTime joinDate;
  
  @Column(name = "member_grade")
  private String memberGrade;
  
  @Column(name = "withdraw_yn", nullable = false)
  private Boolean withdrawYn = false;
  
//  @OneToMany(mappedBy = "user")
//  private List<Order> orders;
//  
//  @OneToMany(mappedBy = "user")
//  private List<Payment> payments;
  
  protected User() {}
  
  public static User createUser(String username, String address, String memberGrade, Boolean withdrawYn) {
    User u = new User();
    u.username = username;
    u.address = address;
    u.memberGrade = memberGrade;
    u.withdrawYn = withdrawYn;
    u.birthDate = LocalDate.now();
    u.joinDate = LocalDateTime.now();
    return u;
  }

  @Override
  public String toString() {
    return "User [userId=" + userId + ", username=" + username 
        + ", address=" + address + ", birthDate=" + birthDate + ", joinDate=" + joinDate + ", memberGrade="
        + memberGrade + ", withdrawYn=" + withdrawYn + "]";
  }
  
  
}
