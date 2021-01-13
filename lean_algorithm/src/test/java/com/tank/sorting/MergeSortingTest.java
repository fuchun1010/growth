package com.tank.sorting;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.TestInstance.Lifecycle;

/**
 * @author tank198435163.com
 */
@TestInstance(Lifecycle.PER_CLASS)
class MergeSortingTest {

  @Test
  void sort() {
    Assertions.assertNotNull(this.mergeSorting);
    val result = this.mergeSorting.sort(new int[]{2, 1, 8});
    Assertions.assertNotNull(result);
    Assertions.assertTrue(result.length > 0);
  }

  @BeforeEach
  void initialize() {
    this.mergeSorting = new MergeSorting();
  }

  private MergeSorting mergeSorting;
}