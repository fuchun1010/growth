package com.tank.renew.my20210523;

import com.google.common.collect.Lists;
import lombok.NonNull;
import lombok.val;

import java.util.List;

/**
 * @author tank198435163.com
 */
public class MaxDisCountV1 implements MaxDiscount {

  @Override
  public int discount(@NonNull List<String> goods, @NonNull int[] c, @NonNull int[] values) {

    System.out.println("f(goods = " + goods + ", c = " + c.length + ")");
    if (goods.isEmpty() || c.length == 1) {
      return 0;
    }
    int[] rc = new int[c.length - 1];
    System.arraycopy(c, 0, rc, 0, rc.length);
    int lastIndex = goods.size() - 1;
    //最后1个商品不参加活动
    List<String> remainGoods = Lists.newArrayList();
    for (int i = 0; i < lastIndex; i++) {
      remainGoods.add(goods.get(i));
    }
    val remainValue = lastIndex >= values.length ? 0 : values[lastIndex];
    val left = this.discount(goods, rc, values);
    val right = this.discount(remainGoods, rc, values) + remainValue;
    System.out.println("left = " + left + " right = " + right);
    return Math.max(left, right);
  }

}
