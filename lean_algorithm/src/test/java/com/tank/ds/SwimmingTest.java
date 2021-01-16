package com.tank.ds;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.PriorityQueue;

/**
 * @author tank198435163.com
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SwimmingTest {

  @Test
  void testSwimming1() {
    int[] arr = {7, 8, 18, 5, 6};
    for (int index = arr.length - 1; index >= 0; index--) {
      this.swim(arr, index);
    }
    Assertions.assertEquals(arr[0], 18);
  }

  @Test
  void testSwimming2() {
    int[] arr = {1, 2, 6, 4, 10};
    for (int index = arr.length - 1; index / 2 >= 0; index--) {
      this.swim(arr, index);
    }

    Assertions.assertEquals(arr[0], 10);
  }

  @Test
  void testSwimming3() {
    int[] arr = {7, 8, 6, 5, 18};
    for (int index = arr.length - 1; index >= 0; index--) {
      this.swim(arr, index);
    }
    Assertions.assertEquals(arr[0], 18);
  }

  @Test
  void testSwimming4() {
    val priorityQueue = new PriorityQueue<Integer>();
    int[] arr = {7, 8, 18, 5, 6, 9};
    for (int data : arr) {
      priorityQueue.add(data);
    }
    Assertions.assertNotNull(priorityQueue);
    while (!priorityQueue.isEmpty()) {
      val result = priorityQueue.poll();
      System.out.println(result);
    }
  }


  private void swim(int arr[], int index) {
    while (index > 0 && arr[index] > arr[parentIndex(index)]) {
      int tmp = arr[index];
      arr[index] = arr[parentIndex(index)];
      arr[parentIndex(index)] = tmp;
      index = parentIndex(index);
    }
  }


  private int parentIndex(int index) {
    return (index - 1) >>> 1;
  }

}
