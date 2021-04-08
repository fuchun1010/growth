package com.tank.renew.my20210409;

import lombok.val;
import lombok.var;
import org.junit.jupiter.api.*;

/**
 * @author tank198435163.com
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BooleanArrayTest {

  @Test
  void hit() {
  }

  @Test
  void set() {
    this.booleanArray.set(11, true);
    val hited = this.booleanArray.hit(11);
    Assertions.assertTrue(hited);
    Assertions.assertFalse(this.booleanArray.hit(0));
  }

  @Test
  @DisplayName("右移")
  void rightMove() {
    val d1 = this.tableSize(8) >> 3;
    //1000 >>3 => 0001
    val d2 = this.tableSize(9) >> 3;
    //10000 >>3 => 00010
    Assertions.assertEquals(d1, 1);
    Assertions.assertEquals(d2, 2);
  }

  @Test
  @DisplayName("左移")
  void leftMove() {
    val d1 = this.tableSize(8) << 3;
    System.out.println(d1);
  }


  @Test
  void testTableSize() {
    val size = this.tableSize(25);
    Assertions.assertEquals(size, 32);
  }


  @BeforeEach
  void init() {
    this.booleanArray = new BooleanArray(25);
  }

  private int tableSize(int capacity) {
    var result = capacity - 1;
    result |= result >>> 1;
    result |= result >>> 2;
    result |= result >>> 4;
    result |= result >>> 8;
    result |= result >>> 16;
    return result < 0 ? 1 : result > (1 << 30) ? 1 << 30 : result + 1;
  }

  private BooleanArray booleanArray;
}