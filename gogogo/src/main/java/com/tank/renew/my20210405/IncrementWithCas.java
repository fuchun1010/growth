package com.tank.renew.my20210405;

/**
 * @author tank198435163.com
 */
public class IncrementWithCas extends IncrementerDef {

  @Override
  protected void request() {
    while (!compareAndSet(counter, counter + 1)) ;
  }

  private synchronized boolean compareAndSet(int expected, int newValue) {
    if (counter == expected) {
      counter = newValue;
      return true;
    }
    return false;
  }


}
