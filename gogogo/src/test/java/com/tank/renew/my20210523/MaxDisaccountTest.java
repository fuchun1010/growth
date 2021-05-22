package com.tank.renew.my20210523;

import com.google.common.collect.Lists;
import lombok.val;
import org.junit.jupiter.api.*;

import java.util.List;

/**
 * 门店售卖的商品有26种，为`abcdefghijklmnopqrstuvwxyz`，a价格为1，b价格为2，c价格为3...x的价格为24，y的价格为25，z的价格为26。
 * <p>
 * 店员可以配置多种优惠活动，活动的结构为 `[商品组合,优惠收益,价格门槛]`，比如：
 * 1. ['abc', 2, 0] 代表 商品a、b、c组合购买时，总体价格减少2元，无价格门槛
 * 2. ['x', 10, 23] 代表 商品x达到门槛23元时，价格减少10元
 * <p>
 * ### 输入
 * ```json
 * carts = "xyyz"
 * priceOffs = [
 * ["x", 4, 0],
 * ["y", 5, 0],
 * ["xyz", 6, 0],
 * ]
 * ```
 * ### 输出
 * `11`
 * ### 说明
 * 匹配到最优活动组合 `["y", 5, 0]`和 `["xyz", 6, 0]`
 */

/**
 * @author tank198435163.com
 */
@DisplayName("活动价钱")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MaxDiscountTest {

  @Test
  @DisplayName("最大折扣")
  void maxDiscount() {
    val result = this.maxDiscount.discount(this.goods, this.c, this.values);
    Assertions.assertEquals(11, result);
  }

  @Test
  @DisplayName("简单1")
  void maxDiscount_1() {
    List<String> goods = Lists.newArrayList("x", "y", "xyz");
    int[] c = {0};
    int[] values = {4};
    val result = this.maxDiscount.discount(goods, c, values);
    Assertions.assertEquals(result, 4);
  }

  @Test
  @DisplayName("简单2")
  void maxDiscount_2() {
    List<String> goods = Lists.newArrayList("y", "xyz");
    val result = this.maxDiscount.discount(goods, this.c, this.values);
    Assertions.assertEquals(result, 11);
  }

  @BeforeEach
  void init() {
    this.maxDiscount = new DefaultMaxDiscount();
  }

  private final List<String> goods = Lists.newArrayList("x", "y", "xyz");

  private final int[] c = {0, 0, 0};

  private final int[] values = {4, 5, 6};

  private MaxDiscount maxDiscount;


}