package com.tank.renew.my20210522;

/**
 * @author tank198435163.com
 */
public class DefaultGoldWorkerProfit implements GoldWorkerProfit {

  @Override
  public int maxProfit(int w, int n, int[] p, int[] gold) {
    if (w == 0) {
      return 0;
    }

    if (n == 0) {
      return 0;
    }

    if (w < p[n - 1]) {
      return this.maxProfit(w, n - 1, p, gold);
    }

    return Math.max(maxProfit(w, n - 1, p, gold), maxProfit(w - p[n - 1], n - 1, p, gold) + gold[n - 1]);
  }
}
