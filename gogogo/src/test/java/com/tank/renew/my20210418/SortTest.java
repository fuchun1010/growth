package com.tank.renew.my20210418;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

/**
 * @author tank198435163.com
 */
@DisplayName("桶排序")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SortTest {

  @Test
  void sorted() {
    this.v1.sorted(new int[]{1, 3, 2, 8, 5, 7, 6, 89, 11});
  }

  @BeforeEach
  void initialize() {
    this.v1 = new BucketSortingImpl();
  }

  private Sort v1;
}