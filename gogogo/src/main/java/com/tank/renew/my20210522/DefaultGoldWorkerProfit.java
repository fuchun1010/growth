package com.tank.renew.my20210522;

/**
 * @author tank198435163.com
 */
public class DefaultGoldWorkerProfit implements GoldWorkerProfit {

  /**
   * this method will return max profit
   *
   * @param w: 一共w个worker
   * @param n: n堆金矿
   * @param p: 每个金矿需要的人手
   * @param g: 每个金矿的含金量
   * @return max gold
   */
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

    int f1 = maxProfit(w, n - 1, p, gold);
    int f2 = maxProfit(w - p[n - 1], n - 1, p, gold) + gold[n - 1];
    return Math.max(f1, f2);
  }
}
