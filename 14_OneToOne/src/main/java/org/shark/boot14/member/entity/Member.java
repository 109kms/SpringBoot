package org.shark.boot14.member.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "members")

@Getter
@Setter
public class Member {
  
  //----- 1:1 연관관계 주인 (외래키 보유)

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "mid")
  private Long id;
  
  private String name;
  
  private String mobile;
  
  //----- 외래키 (Long lockerId)
  @OneToOne(                       //----- 1:1 연관관계
      cascade = CascadeType.PERSIST  //----- 영속성 전이
                                     //  1. 부모 엔티티의 영속성 상태 변화를 자식 엔티티에게 전이시키는 기능입니다.
                                     //  2. 종류
                                     //    1) ALL     : 모든 영속성 상태 전이 (부모와 자식의 라이프 사이클이 동일할 때 사용)
                                     //    2) PERSIST : 영속화 시에만 전이 (자식도 함께 저장)
                                     //    3) REMOVE  : 삭제 시에만 전이 (자식도 함께 삭제)
  )  
  @JoinColumn(name = "locker_id")  // 외래키의 칼럼명
  private Locker locker;
  
  protected Member() {}
  
  public static Member createMember(String name, String mobile) {
    Member member = new Member();
    member.name = name;
    member.mobile = mobile;
    return member;
  }
  
  //----- 연관관계 설정을 위한 비즈니스 메소드
  
  // Locker 배정
  public void assignLocker(Locker locker) {
    this.locker = locker;
  }
  
  // Locker 해제
  public void removeLocker() {
    if (this.locker != null) {
      this.locker = null;
    }
  }

  @Override
  public String toString() {
    return "Member [id=" + id + ", name=" + name + ", mobile=" + mobile + ", locker=" + locker + "]";
  }
  
  
}
