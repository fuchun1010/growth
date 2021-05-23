package com.tank.renew.my20210524;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.vavr.collection.Stream;
import lombok.NonNull;
import lombok.val;

import java.util.List;
import java.util.Map;

/**
 * @author tank198435163.com
 */
public class MaxDiscountV2 implements MaxDiscount {

  @Override
  public int maxAvailableProfit(@NonNull List<Goods> goods, @NonNull List<Discount> discounts) {
    if (goods.isEmpty()) {
      return 0;
    }

    if (discounts.isEmpty()) {
      return 0;
    }

    val lastDiscount = Stream.ofAll(discounts).last();
    val fullGood = Stream.ofAll(goods).map(Goods::getSku).toJavaList();
    Map<String, Integer> fullGoodsMap = Maps.newHashMap();
    for (val g : fullGood) {
      if (fullGoodsMap.containsKey(g)) {
        int v = fullGoodsMap.get(g);
        fullGoodsMap.put(g, ++v);
      } else {
        fullGoodsMap.put(g, 1);
      }
    }
    int totalPrice = 0;

    val priceMap = Maps.<String, Integer>newHashMap();
    for (Goods good : goods) {
      priceMap.put(good.getSku(), good.getPrice());
    }

    for (String a : lastDiscount.getSku().split("")) {
      if (fullGoodsMap.containsKey(a) && fullGoodsMap.get(a) >= 1) {
        int v = fullGoodsMap.get(a);
        int expectMinus = (int) Math.ceil((double) lastDiscount.getThreshold() / priceMap.get(a));
        expectMinus = Math.max(expectMinus, 1);
        if (v >= expectMinus) {
          v -= expectMinus;
          fullGoodsMap.put(a, v);
          totalPrice += expectMinus * priceMap.get(a);
          continue;
        }
      }
      fullGoodsMap = Maps.newHashMap();
      for (val g : fullGood) {
        if (fullGoodsMap.containsKey(g)) {
          int v = fullGoodsMap.get(g);
          fullGoodsMap.put(g, ++v);
        } else {
          fullGoodsMap.put(g, 1);
        }
      }
      totalPrice = -1;
      break;
    }
    //  remaining
    val remainingGood = Lists.<Goods>newArrayList();
    for (Map.Entry<String, Integer> entry : fullGoodsMap.entrySet()) {
      val loop = entry.getValue();
      for (int index = 0; index < loop; index++) {
        remainingGood.add(new Goods().setSku(entry.getKey()).setPrice(priceMap.get(entry.getKey())));
      }
    }

    val remainingDiscount = discounts.subList(0, discounts.size() - 1);

    val rv = totalPrice >= lastDiscount.getThreshold() ? lastDiscount.getSubValue() : 0;

    val left = this.maxAvailableProfit(remainingGood, remainingDiscount) + rv;
    val right = this.maxAvailableProfit(goods, remainingDiscount);

    return Math.max(left, right);
  }

}
