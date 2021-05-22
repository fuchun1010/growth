package com.tank.renew.my20210523;

import com.google.common.collect.Lists;
import lombok.NonNull;
import lombok.val;

import java.util.List;
import java.util.Map;

/**
 * @author tank198435163.com
 */
public class MaxDisCountV1 implements MaxDiscount {

  @Override
  public int discount(@NonNull List<String> goods,
                      @NonNull int[] c,
                      @NonNull int[] values,
                      @NonNull Map<String, Goods> map) {

    System.out.println("f(goods = " + goods + ", c = " + values.length + ")");
    if (goods.isEmpty()) {
      return 0;
    }

    if (values.length == 1) {
      return 0;
    }

    if (goods.size() == 1) {
      int max = 0;
      for (Map.Entry<String, Goods> entry : map.entrySet()) {
        for (String good : goods) {
          val existed = entry.getValue().getSku().contains(good);
          if (existed ) {
            max = Math.max(max, entry.getValue().getValue());
          }
        }
      }
      return max;
    }

    int[] v = new int[values.length - 1];
    System.arraycopy(values, 0, v, 0, v.length);
    int lastIndex = goods.size() - 1;
    //最后1个商品不参加活动
    List<String> remainGoods = Lists.newArrayList();
    for (int i = 0; i < lastIndex; i++) {
      remainGoods.add(goods.get(i));
    }
    val remainValue = lastIndex >= values.length ? 0 : values[lastIndex];
    val left = this.discount(goods, c, v, map);
    val right = this.discount(remainGoods, c, v, map) + remainValue;
    System.out.println("left = " + left + " right = " + right);
    return Math.max(left, right);
  }

}
