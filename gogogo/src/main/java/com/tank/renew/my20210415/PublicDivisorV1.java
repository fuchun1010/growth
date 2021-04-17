package com.tank.renew.my20210415;

import lombok.NonNull;
import lombok.val;

/**
 * @author tank198435163.com
 */
public class PublicDivisorV1 implements PublicDivisor {
  @Override
  public int divisor(@NonNull Integer a, @NonNull Integer b) {
    if (a.compareTo(b) == 0) {
      return a;
    }
    val max = a.compareTo(b) > 0 ? a : b;
    val min = a.compareTo(b) < 0 ? a : b;
    val result = max % min;
    if (result == 0) {
      return min;
    }
    return divisor(result, min);
  }
}
