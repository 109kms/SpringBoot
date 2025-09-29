package org.shark.boot16.product.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Contact {

  @Column(unique = true, length = 100)
  private String email;
  
  @Column(name = "phone_number", length = 20)
  private String phoneNumber;
  
  protected Contact() {}
  
  public static Contact createContact(String email, String phoneNumber) {
    Contact c = new Contact();
    c.email = email;
    c.phoneNumber = phoneNumber;
    return c;
  }

  @Override
  public String toString() {
    return "Contact [email=" + email + ", phoneNumber=" + phoneNumber + "]";
  }
  
  
}
