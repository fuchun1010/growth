package com.tank.algorithm;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

/**
 * @author tank198435163.com
 */
class SimpleArrayTest {

  @Test
  @DisplayName("数组测试添加")
  void add() {
    this.array.add(1);
    this.array.add(2);
    val size = this.array.size();
    Assertions.assertEquals(2, size);
  }

  @Test
  @DisplayName("删除数组最后1个元素测试")
  void delete1() {
    this.array.add(1);
    this.array.add(2);
    val size = this.array.size();
    Assertions.assertEquals(2, size);
    this.array.delete(2);
    Assertions.assertEquals(1, this.array.size());
  }

  @Test
  @DisplayName("删除数组中间1个元素测试")
  void delete2() {
    this.array.add(1);
    this.array.add(2);
    this.array.add(3);
    val size = this.array.size();
    Assertions.assertEquals(3, size);
    this.array.delete(2);
    Assertions.assertEquals(2, this.array.size());
  }

  @Test
  @DisplayName("删除数组中间1个元素测试")
  void delete3() {
    this.array.add(1);
    this.array.add(2);
    this.array.add(3);
    val size = this.array.size();
    Assertions.assertEquals(3, size);
    Assertions.assertTrue(this.array.delete(1));
    Assertions.assertEquals(2, this.array.size());
  }

  @Test
  @DisplayName("数组扩展")
  void resize() {
    IntStream.rangeClosed(1, 5).forEach(this.array::add);
    Assertions.assertEquals(5, this.array.size());
  }

  @Test
  @DisplayName("获取index对应的值")
  void obtain() {
    IntStream.rangeClosed(1, 5).forEach(this.array::add);
    val result = this.array.obtain(1);
    Assertions.assertEquals(2, (int) result);
  }

  @BeforeEach
  void initialize() {
    this.array = new SimpleArray<>(5, Integer.class);
  }

  private SimpleArray<Integer> array = null;
}