package com.tank.renew.my20210524;

import lombok.val;
import org.junit.jupiter.api.*;

import java.util.Arrays;

/**
 * @author tank198435163.com
 */
@DisplayName("v2")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MaxDiscountV2Test {

  @Test
  @DisplayName("4商品4活动")
  void maxAvailableProfit1() {
    val gd1 = new Goods().setSku("x").setPrice(24);
    val gd2 = new Goods().setSku("x").setPrice(24);
    val gd3 = new Goods().setSku("y").setPrice(25);
    val gd4 = new Goods().setSku("z").setPrice(26);

    val d1 = new Discount().setSku("x").setThreshold(0).setSubValue(4);
    val d2 = new Discount().setSku("x").setThreshold(48).setSubValue(20);
    val d3 = new Discount().setSku("y").setThreshold(0).setSubValue(5);
    val d4 = new Discount().setSku("xyz").setThreshold(0).setSubValue(6);

    val result = this.maxDiscount.maxAvailableProfit(
            Arrays.asList(gd1, gd2, gd3, gd4),
            Arrays.asList(d1, d2, d3, d4));

    Assertions.assertEquals(result, 25);
  }

  @BeforeEach
  void init() {
    this.maxDiscount = new MaxDiscountV2();
  }

  private MaxDiscount maxDiscount;
}