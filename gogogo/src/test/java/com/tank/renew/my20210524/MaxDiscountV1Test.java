package com.tank.renew.my20210524;

import io.vavr.collection.Stream;
import lombok.val;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.Collections;

@DisplayName("最大收益")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MaxDiscountV1Test {

  @Test
  @DisplayName("截取链表")
  void testSubList() {
    val list = Arrays.asList("hello", "this", "is", "the", "world");
    val result = Stream.ofAll(list).tail().toJavaList();
    Assertions.assertEquals(result.size(), 4);
  }

  @Test
  @DisplayName("1商品3活动")
  void disCount1() {
    val gd1 = new Goods().setSku("xyz").setPrice(6);
    val d1 = new Discount().setSku("xyz").setThreshold(0).setSubValue(6);
    val d2 = new Discount().setSku("x").setThreshold(0).setSubValue(5);
    val d3 = new Discount().setSku("y").setThreshold(0).setSubValue(4);
    val result = this.maxDiscount.maxAvailableProfit(
            Collections.singletonList(gd1),
            Arrays.asList(d1, d2, d3));
    val expected = 6;
    Assertions.assertEquals(expected, result);
  }


  @Test
  @DisplayName("4商品4活动")
  void discount5() {
    /**
     * ```json
     * carts = "xxyz"
     * priceOffs = [
     *     ["x", 4, 0],
     *     ["x", 20, 48],
     *     ["y", 5, 0],
     *     ["xyz", 6, 0],
     * ]
     * ```
     * ### 输出
     * `25`
     */
    val gd1 = new Goods().setSku("x").setPrice(4);
    val gd2 = new Goods().setSku("x").setPrice(20);
    val gd3 = new Goods().setSku("y").setPrice(5);
    val gd4 = new Goods().setSku("xyz").setPrice(6);

    val d1 = new Discount().setSku("x").setThreshold(0).setSubValue(4);
    val d2 = new Discount().setSku("x").setThreshold(48).setSubValue(20);
    val d3 = new Discount().setSku("y").setThreshold(0).setSubValue(5);
    val d4 = new Discount().setSku("xyz").setThreshold(0).setSubValue(6);

    val result = this.maxDiscount.maxAvailableProfit(
            Arrays.asList(gd1, gd2, gd3, gd4),
            Arrays.asList(d1, d2, d3, d4));
    Assertions.assertEquals(25, result);
  }

  @BeforeEach
  void init() {
    this.maxDiscount = new MaxDiscountV1();
  }

  private MaxDiscount maxDiscount;

}