package com.tank.renew.my20210523;

import lombok.NonNull;

import java.util.List;

/**
 * @author tank198435163.com
 */
public interface MaxDiscount {

  /**
   * this method will return max discount
   *
   * @param goods:  商品
   * @param c:      条件
   * @param values: 折扣额度
   * @return
   */
  int discount(@NonNull final List<String> goods,
               @NonNull final int[] c,
               @NonNull final int[] values);
}
