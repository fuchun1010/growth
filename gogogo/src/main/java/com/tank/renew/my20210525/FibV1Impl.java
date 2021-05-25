package com.tank.renew.my20210525;

import lombok.NonNull;
import lombok.val;
import lombok.var;

/**
 * @author tank198435163.com
 */
public class FibV1Impl implements Fib {

  @Override
  public int calculate(@NonNull Integer num) {
    val fixedValue = 2;
    if (num == 0 || num == 1) {
      return 1;
    }
    val result = new int[num + 1];
    for (int i = 0; i < fixedValue; i++) {
      result[i] = 1;
    }
    for (var index = fixedValue; index <= num; index++) {
      result[index] = result[index - 1] + result[index - 2];
    }
    return result[num - 1];
  }
}
