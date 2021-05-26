package com.tank.renew.my20210526;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tank.renew.my20210524.Discount;
import com.tank.renew.my20210524.Goods;
import io.vavr.collection.Stream;
import lombok.NonNull;
import lombok.val;
import lombok.var;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author tank198435163.com
 */
public class PaymentProfit {

  /**
   * return max profit for customers
   *
   * @param discounts
   * @param goods
   * @return
   */
  public int maxProfit(@NonNull final List<Goods> goods,
                       @NonNull final List<Discount> discounts) {

    if (discounts.isEmpty()) {
      return 0;
    }

    if (goods.isEmpty()) {
      return 0;
    }

    var rv = 0;
    var totalPrice = 0;
    val lastDiscount = Stream.ofAll(discounts).last();
    var currentSkuFromGoods = Maps.<String, Integer>newHashMap();
    val priceMap = Maps.<String, Integer>newHashMap();
    val remainingDiscount = discounts.subList(0, discounts.size() - 1);
    for (Goods good : goods) {
      priceMap.put(good.getSku(), good.getPrice());
      val sku = good.getSku();
      if (currentSkuFromGoods.containsKey(sku)) {
        currentSkuFromGoods.computeIfPresent(sku, (key, v) -> ++v);
      } else {
        currentSkuFromGoods.put(sku, 1);
      }
    }

    val discountForSku = Stream.of(lastDiscount.getSku().split("")).toList();

    for (val sku : discountForSku) {
      var isOk = currentSkuFromGoods.containsKey(sku)
              && currentSkuFromGoods.get(sku).compareTo(0) > 0;
      if (isOk) {
        var v = currentSkuFromGoods.get(sku);
        val expectMinus = Math.max(new BigDecimal(lastDiscount.getThreshold())
                .divide(new BigDecimal(priceMap.get(sku)), BigDecimal.ROUND_FLOOR)
                .intValue(), 1);
        if (v >= expectMinus) {
          totalPrice += expectMinus * priceMap.get(sku);
          v -= expectMinus;
          currentSkuFromGoods.put(sku, v);
        } else {
          totalPrice = -1;
          break;
        }
        continue;
      }
      totalPrice = -1;
      break;
    }

    val remainingGoods = Lists.<Goods>newArrayList();

    for (Map.Entry<String, Integer> entry : currentSkuFromGoods.entrySet()) {
      if (entry.getValue() == 0) {
        continue;
      }
      if (totalPrice == -1) {
        continue;
      }
      for (int index = 0; index < entry.getValue(); index++) {
        val sku = entry.getKey();
        val price = priceMap.get(sku);
        val gd = new Goods().setSku(sku).setPrice(price);
        remainingGoods.add(gd);
      }
    }

    rv = totalPrice >= lastDiscount.getThreshold() ? lastDiscount.getSubValue() : 0;

    val left = this.maxProfit(remainingGoods, remainingDiscount) + rv;
    val right = this.maxProfit(goods, remainingDiscount);
    return Math.max(left, right);

  }

}
