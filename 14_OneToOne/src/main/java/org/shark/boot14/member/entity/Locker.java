package org.shark.boot14.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "lockers")

@Getter
@Setter
public class Locker {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "lid")
  private Long id;
  
  private String location;
  
  protected Locker() {}
  
  public static Locker createLocker(String location) {
    Locker locker = new Locker();
    locker.setLocation(location);
    return locker;
  }

  @Override
  public String toString() {
    return "Locker [id=" + id + ", location=" + location + "]";
  }
  
  
}
