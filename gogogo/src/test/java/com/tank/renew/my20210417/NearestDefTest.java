package com.tank.renew.my20210417;

import lombok.val;
import org.junit.jupiter.api.*;

@DisplayName("字段序算法")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class NearestDefTest {

  @Test
  void nearestArray() {
    int[] array = {1, 2, 3, 5, 4};
    val result = this.v1.nearestArray(array);
    Assertions.assertNotNull(result);
    for (int d : result) {
      System.out.print(d);
    }
  }

  @Test
  void nearestArray2() {
    int[] array = {1, 3, 2, 8, 5, 7, 6};
    val result = this.v1.nearestArray(array);
    Assertions.assertNotNull(result);
    for (int d : result) {
      System.out.print(d);
    }
  }


  @BeforeEach
  void initialize() {
    this.v1 = new NearestDefImpl();
  }

  private NearestDef v1;

}