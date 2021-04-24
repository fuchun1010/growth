package com.tank.renew.my20210423;

/**
 * @author tank198435163.com
 */
public class BestProfit {

  /**
   * @param w 工人数量
   * @param n 金矿数量
   * @param p 挖矿需要的人数
   * @param g 黄金含量
   * @return
   */
  public int bestObtainGold(int w, int n, int[] p, int[] g) {

    if (w == 0) {
      return 0;
    }

    if (n == 0) {
      return 0;
    }

    if (w < p[n - 1]) {
      return bestObtainGold(w, n - 1, p, g);
    }

    return Math.max(bestObtainGold(w, n - 1, p, g), bestObtainGold(w - p[n - 1], n - 1, p, g) + g[n - 1]);

  }
}
