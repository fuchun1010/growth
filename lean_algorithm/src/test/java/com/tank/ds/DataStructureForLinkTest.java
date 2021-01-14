package com.tank.ds;

import io.vavr.collection.Stream;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DataStructureForLinkTest {

  @Test
  void add() {
    Stream.of(this.data).forEach(this.link::add);
    Assertions.assertTrue(this.link.size() > 0);
    Assertions.assertEquals((Integer) this.data.length, this.link.size());
  }

  @Test
  void traversal() {
    Stream.of(this.data).forEach(this.link::add);
    Assertions.assertNotNull(this.link);
    Assertions.assertTrue(this.link.size() > 0);
    Assertions.assertEquals((Integer) this.data.length, this.link.size());
    this.link.traversal();
  }

  @Test
  void deleteHeader() {
    Stream.of(this.data).forEach(this.link::add);
    val result = delete(1);
    Assertions.assertTrue(result > 0);
  }

  private int delete(int i) {
    val result = this.link.delete(i);
    Assertions.assertEquals(2, (int) this.link.size());
    this.link.traversal();
    return result;
  }

  @Test
  void deleteMiddle() {
    Stream.of(this.data).forEach(this.link::add);
    delete(2);
  }

  @Test
  void deleteTail() {
    Stream.of(this.data).forEach(this.link::add);
    delete(3);
  }

  @Test
  void size() {
    Stream.of(this.data).forEach(this.link::add);
    Assertions.assertEquals((Integer) this.data.length, this.link.size());
  }

  @BeforeEach
  void initialize() {
    this.data = new Integer[]{1, 2, 3};
    this.link = new DataStructureForLink<>();
  }

  private Integer[] data = null;

  private DataStructureForLink<Integer> link;
}