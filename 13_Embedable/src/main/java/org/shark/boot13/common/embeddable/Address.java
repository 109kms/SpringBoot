package org.shark.boot13.common.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Address {

  private String postcode;
  
  private String city;
  
  @Column(name = "street_addr")
  private String streetAddr;
  
  protected Address() {}
  
  public static Address createAddress(String postcode, String city, String streetAddr) {
    Address addr = new Address();
    addr.postcode = postcode;
    addr.city = city;
    addr.streetAddr = streetAddr;
    return addr;
  }

  @Override
  public String toString() {
    return "Address [postcode=" + postcode + ", city=" + city + ", streetAddr=" + streetAddr + "]";
  }
  
  
}
