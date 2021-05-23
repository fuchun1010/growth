package com.tank.renew.my20210524;

import lombok.NonNull;

import java.util.List;

/**
 * @author tank198435163.com
 */
public interface MaxDiscount {

  int maxAvailableProfit(@NonNull final List<Goods> goods,
                         @NonNull final List<Discount> discounts);
}
