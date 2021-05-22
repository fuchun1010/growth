package com.tank.renew.my20210522;

/**
 * @author tank198435163.com
 */
public interface GoldWorkerProfit {

  /**
   * this method will return max profit
   *
   * @param w: 一共w个worker
   * @param n: n堆金矿
   * @param p: 每个金矿需要的人手
   * @param g: 每个金矿的含金量
   * @return max gold
   */
  int maxProfit(int w, int n, final int[] p, final int[] gold);
}
