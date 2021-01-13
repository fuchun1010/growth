package com.tank.algorithm;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TwoIntAddingTest {

  @Test
  void indexForAddingSuccess() {
    Assertions.assertNotNull(this.twoIntAdding);
    val data = new Integer[]{1, 2, 3, 9, 7, 8};
    val target = 5;
    val result = this.twoIntAdding.indexForAdding(target, data);
    Assertions.assertNotNull(result);
    Assertions.assertEquals(2, result.length);
    Assertions.assertNotNull(result[0]);
    Assertions.assertNotNull(result[1]);
  }

  @Test
  void indexForAddingFailure() {
    Assertions.assertNotNull(this.twoIntAdding);
    val data = new Integer[]{1, 2, 3, 9, 7, 8};
    val target = 20;
    val result = this.twoIntAdding.indexForAdding(target, data);
    Assertions.assertNull(result[0]);
  }

  @BeforeAll
  void initialize() {
    this.twoIntAdding = new TwoIntAdding();
  }

  private TwoIntAdding twoIntAdding = null;
}