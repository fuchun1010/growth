package com.tank.renew.my20210405;

/**
 * Note implements
 * add lock for concurrent modify variate
 */

/**
 * @author tank198435163.com
 */
public class IncrementerWithLock extends IncrementerDef {

  @Override
  protected synchronized void request() {
    counter++;
  }
}
