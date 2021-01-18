package com.tank.sorting;

import io.vavr.collection.Stream;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BubbleTest {

  @Test
  void sortAsc() {
    val result = this.bubble.sortAsc();
    Assertions.assertNotNull(result);
    Assertions.assertTrue(result[0] < result[1]);
  }

  @BeforeEach
  void initialize() {
    final Integer[] array = {13, 11, 12, 26, 9, 24, 30, 28, 7, 6, 29, 17, 18, 16, 10};
    this.bubble = new Bubble<Integer>(Stream.of(array).toJavaList(), Integer.class);
  }

  private Bubble<Integer> bubble;
}