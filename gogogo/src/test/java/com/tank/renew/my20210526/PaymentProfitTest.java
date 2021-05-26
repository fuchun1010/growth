package com.tank.renew.my20210526;

import cn.hutool.core.util.StrUtil;
import com.tank.renew.my20210524.Discount;
import com.tank.renew.my20210524.Goods;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Arrays;

/**
 * @author tank198435163.com
 */
@DisplayName("购买商品最大优惠额度")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PaymentProfitTest {

  @Test
  @DisplayName("最大优惠额度计算")
  void maxProfit() {

    val paymentProfit = new PaymentProfit();

    val gd1 = new Goods().setSku("x").setPrice(24);
    val gd2 = new Goods().setSku("x").setPrice(24);
    val gd3 = new Goods().setSku("y").setPrice(25);
    val gd4 = new Goods().setSku("z").setPrice(26);

    val d1 = new Discount().setSku("x").setThreshold(0).setSubValue(4);
    val d2 = new Discount().setSku("x").setThreshold(48).setSubValue(20);
    val d3 = new Discount().setSku("y").setThreshold(0).setSubValue(5);
    val d4 = new Discount().setSku("xyz").setThreshold(0).setSubValue(6);

    val discounts = Arrays.asList(d1, d2, d3, d4);
    val goods = Arrays.asList(gd1, gd2, gd3, gd4);
    val actual = paymentProfit.maxProfit(goods, discounts);
    System.out.println(StrUtil.format("max profit = [{}]", actual));
  }
}