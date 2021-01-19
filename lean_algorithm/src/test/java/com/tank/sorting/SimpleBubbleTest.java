package com.tank.sorting;

import com.google.common.collect.Lists;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

/**
 * @author tank198435163.com
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SimpleBubbleTest {

  @Test
  void sort() {
    Assertions.assertNotNull(this.simpleBubble);
    val result = this.simpleBubble.sort(Integer::compareTo);
    Assertions.assertNotNull(result[0]);
    Assertions.assertEquals(1, (int) result[0]);
  }

  @Test
  void sort2() {
    val result = this.simpleBubble.sort((a, b) -> b.compareTo(a));
    Assertions.assertNotNull(result[0]);
    Assertions.assertEquals(6, (int) result[0]);
  }

  @Test
  void sort3() {
    this.simpleBubble = new SimpleBubble<>(Lists.newArrayList(5, 8, 6, 39, 2, 1, 7), Integer.class);
    val result = this.simpleBubble.sort(Integer::compareTo);
    val compareTimes = this.simpleBubble.compareTimes();
    Assertions.assertNotNull(result[0]);
    Assertions.assertTrue(compareTimes > 0);
    Assertions.assertEquals(1, (int) result[0]);
  }

  @Test
  void sort4() {
    this.simpleBubble = new SimpleBubble<>(Lists.newArrayList(13, 11, 12, 26, 9, 24, 30, 28, 7, 6, 29, 17, 18, 16, 10), Integer.class);
    val result = this.simpleBubble.sort(Integer::compareTo);
    val compareTimes = this.simpleBubble.compareTimes();
    Assertions.assertNotNull(result[0]);
    Assertions.assertTrue(compareTimes > 0);
    Assertions.assertEquals(6, (int) result[0]);
  }

  @Test
  void highPerformanceSort1() {
    this.simpleBubble = new SimpleBubble<>(Lists.newArrayList(13, 11, 12, 26, 9, 24, 30, 28, 7, 6, 29, 17, 18, 16, 10), Integer.class);
    Assertions.assertNotNull(this.simpleBubble);
    val result = this.simpleBubble.highPerformanceSort(Integer::compareTo);
    val times = this.simpleBubble.compareTimes();
    Assertions.assertTrue(times > 0);
    Assertions.assertEquals(1, (int) result[0]);
    Assertions.assertEquals(2, (int) result[1]);
    Assertions.assertEquals(3, (int) result[2]);
    Assertions.assertEquals(4, (int) result[3]);
    Assertions.assertEquals(5, (int) result[4]);
    Assertions.assertEquals(6, (int) result[5]);
    Assertions.assertEquals(7, (int) result[6]);
    Assertions.assertEquals(8, (int) result[7]);
  }

  @BeforeEach
  void initialize() {
    this.simpleBubble = new SimpleBubble<>(Lists.newArrayList(5, 1, 6, 4), Integer.class);
  }

  private SimpleBubble<Integer> simpleBubble;
}