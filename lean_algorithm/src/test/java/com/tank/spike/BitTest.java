package com.tank.spike;

import lombok.Getter;
import lombok.Setter;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.Serializable;

/**
 * @author tank198435163.com
 */
@DisplayName("移位测试")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BitTest {

  @Test
  @DisplayName("移位操作")
  void bitForHash() {
    val student = new Student();
    student.setNo("s0001");
    student.setGender(1);
    student.setName("jack");
    int code = student.hashCode();
    Assertions.assertTrue(code > 0);
    int result = code ^ (code >>> 16);
    Assertions.assertNotEquals(code, result);
  }

  @Test
  @DisplayName("无符号右移动")
  void bitForHash2() {
    val num = 1 << 16;
    val result = num >>> 16;
    Assertions.assertEquals(1, result);
  }

  @Test
  @DisplayName("连续无符号右移动3")
  void bitForHash3() {
    int cap = 17;
    val result = tableSizeFor(cap);
    Assertions.assertEquals(0, (result & (result - 1)));
  }

  @Test
  @DisplayName("连续无符号右移动3")
  void bitForHash4() {
    int cap = 4;
    val result = tableSizeFor(cap);
    Assertions.assertEquals(0, (result & (result - 1)));
  }

  @Test
  @DisplayName("算hash下标")
  void bitForHash5() {
    val cap = 4;
    val r1 = 7 & (cap - 1);
    val r2 = 9 & (cap - 1);
    Assertions.assertTrue((r1 & r2) > 0);
  }


  int tableSizeFor(int cap) {
    int n = cap - 1;
    n |= n >>> 1;
    n |= n >>> 2;
    n |= n >>> 4;
    n |= n >>> 8;
    n |= n >>> 16;
    return (n < 0) ? 1 : (n >= Integer.MAX_VALUE) ? Integer.MAX_VALUE : n + 1;
  }

  @Getter
  @Setter
  static class Student implements Serializable {

    private String no;

    private String name;

    private int gender;
  }

}
