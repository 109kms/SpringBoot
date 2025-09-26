package org.shark.boot14;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.shark.boot14.common.util.JpaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.persistence.EntityManager;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
class ApplicationTests {
  
  @Autowired
  private JpaUtil jpaUtil;

  private EntityManager em;

  //----- 전체 테스트 시작 전 : 엔티티 매니저 팩토리 생성
  @BeforeAll
  void setUpBeforeClass() {
    jpaUtil.initFactory();
  }

  //----- 각 테스트 시작 전 : 엔티티 매니저 생성
  @BeforeEach
  void setUp() {
    em = jpaUtil.getEntityManager();
  }

  //----- 각 테스트 종료 후 : 엔티티 매니저 소멸
  @AfterEach
  void tearDown() {
    if (em != null && em.isOpen()) em.close();
  }

  //----- 전체 테스트 종료 휴 : 엔티티 매니저 팩토리 소멸
  @AfterAll
  void tearDownAfterClass() {
    jpaUtil.closeFactory();
  }

	@Test
	void contextLoads() {
	}

}
