package com.tank.renew.my20210524;

import cn.hutool.core.util.StrUtil;
import io.vavr.collection.Stream;
import lombok.NonNull;
import lombok.val;

import java.io.PrintStream;
import java.util.List;

/**
 * @author tank198435163.com
 */
public class MaxDiscountV1 implements MaxDiscount {
  @Override
  public int maxAvailableProfit(@NonNull List<Goods> goods,
                                @NonNull List<Discount> discounts) {

    CONSOLE.println(StrUtil.format("goods = [{}], discounts = [{}] ", goods.size(), discounts.size()));

    if (goods.isEmpty()) {
      return 0;
    }

    if (discounts.isEmpty()) {
      return 0;
    }

    int firstProfit = 0;

    val remainingGoods = Stream.ofAll(goods).tail().toJavaList();
    List<Discount> remainingDiscount = Stream.ofAll(discounts).tail().toJavaList();

    val left = this.maxAvailableProfit(goods, remainingDiscount);

    val firstGoods = Stream.ofAll(goods).head();

    for (Discount discount : discounts) {
      if (!discount.getSku().contains(firstGoods.getSku())) {
        continue;
      }
      if (firstGoods.getPrice() > discount.getThreshold()) {
        firstProfit = Math.max(firstProfit, discount.getSubValue());
      }
    }
//    val selectFirstDiscount = Stream.ofAll(discounts).head();
//    val isOk = selectFirstDiscount.getSku().contains(firstGoods.getSku())
//            && selectFirstDiscount.getThreshold() < firstGoods.getPrice();
//    if (isOk) {
//      firstProfit = selectFirstDiscount.getSubValue();
//    }

    System.out.println("firstProfit========>" + firstProfit);
    val right = this.maxAvailableProfit(remainingGoods, remainingDiscount) + firstProfit;

    return Math.max(left, right);
  }

  private static final PrintStream CONSOLE = System.out;
}
