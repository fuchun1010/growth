package com.tank.renew.my20210523;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import lombok.NonNull;
import lombok.val;

import java.io.PrintStream;
import java.util.List;

/**
 * @author tank198435163.com
 */
public class MaxDisCountV1 implements MaxDiscount {

  @Override
  public int discount(@NonNull List<String> sku,
                      @NonNull Integer[] c,
                      @NonNull Integer[] values,
                      @NonNull List<Goods> map) {

    //CONSOLE.println(StrUtil.format("sku {}, values:{}", sku, values));

    if (sku.isEmpty()) {
      return 0;
    }

    if (values.length == 0) {
      return 0;
    }

    Integer[] dis = new Integer[values.length - 1];
    System.arraycopy(values, 0, dis, 0, dis.length);

    val left = this.discount(sku, c, dis, map);
    CONSOLE.println(StrUtil.format("sku {}, values:{}", sku, values));
    val lastGood = map.get(sku.size() - 1);
    int lastProfit = 0;

    for (val tmp : map) {
      if (!tmp.getSku().contains(lastGood.getSku())) {
        continue;
      }
      if (tmp.getC() > lastGood.getValue()) {
        continue;
      }
      lastProfit = Math.max(lastProfit, tmp.getValue());
    }

    val right = this.discount(sku.subList(0, sku.size() - 1), c, dis, map) + lastProfit;
    return Math.max(left, right);
  }

  private static final PrintStream CONSOLE = System.out;

}
