package com.tank.renew.my20210525;

import lombok.NonNull;

/**
 * @author tank198435163.com
 */
public class DefaultFibImpl implements Fib {

  @Override
  public int calculate(@NonNull Integer num) {

    return num < 2 ? 1 : this.calculate(num - 1) + this.calculate(num - 2);
  }
}
