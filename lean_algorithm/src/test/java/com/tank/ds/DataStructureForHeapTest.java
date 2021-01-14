package com.tank.ds;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DataStructureForHeapTest {

  @Test
  void add() throws IllegalAccessException {
    Assertions.assertNotNull(this.dataStructureForHeap);
    for (Integer data : values) {
      this.dataStructureForHeap.add(data);
    }
    val result = this.dataStructureForHeap.obtainArray();
    Assertions.assertNotNull(result);
  }

  @Test
  void add2() throws IllegalAccessException {
    this.values = new Integer[]{6, 5, 7, 9, 8, 10, 11, 12};
    for (Integer data : values) {
      this.dataStructureForHeap.add(data);
    }
    val result = this.dataStructureForHeap.obtainArray();
    Assertions.assertNotNull(result);
  }


  @BeforeEach
  void initializer() {
    this.dataStructureForHeap = new DataStructureForHeap<>(Integer.class);
  }

  private DataStructureForHeap<Integer> dataStructureForHeap;

  private Integer[] values = {1, 3, 2, 7, 8, 9, 10, 0};
}