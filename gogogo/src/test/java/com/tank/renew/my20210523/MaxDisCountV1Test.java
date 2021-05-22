package com.tank.renew.my20210523;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Arrays;

@DisplayName("second")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MaxDisCountV1Test {

  @Test
  @DisplayName("3商品3活动")
  void discount() {
    val maxDisCountV1 = new MaxDisCountV1();
    val goods = Arrays.asList("x", "y", "xyz");
    val c = new int[]{0, 0, 0};
    val v = new int[]{4, 5, 6};
    val result = maxDisCountV1.discount(goods, c, v);
    Assertions.assertEquals(result, 11);
  }

  @Test
  @DisplayName("2商品3活动")
  void discount2() {
    val maxDisCountV1 = new MaxDisCountV1();
    val goods = Arrays.asList("x", "y");
    val c = new int[]{0, 0, 0};
    val v = new int[]{4, 5, 6};
    val result = maxDisCountV1.discount(goods, c, v);
    Assertions.assertEquals(result, 9);
  }

  @Test
  @DisplayName("1商品3活动")
  void discount3() {
    val maxDisCountV1 = new MaxDisCountV1();
    val goods = Arrays.asList("y");
    val c = new int[]{0, 0, 0};
    val v = new int[]{4, 5, 6};
    val result = maxDisCountV1.discount(goods, c, v);
    Assertions.assertEquals(result, 6);
  }

  @Test
  @DisplayName("3商品2活动")
  void discount4() {
    val maxDisCountV1 = new MaxDisCountV1();
    val goods = Arrays.asList("x", "y", "xyz");
    val c = new int[]{0, 0};
    val v = new int[]{4, 5};
    val result = maxDisCountV1.discount(goods, c, v);
    Assertions.assertEquals(result, 10);
  }


  private MaxDiscount maxDiscount;
}