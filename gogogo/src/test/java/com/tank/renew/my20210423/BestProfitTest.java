package com.tank.renew.my20210423;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@DisplayName("动态规划")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BestProfitTest {

  @Test
  @DisplayName("动态获取最大黄金数量")
  void bestObtainGold() {
    val profit = new BestProfit();
    val result = profit.bestObtainGold(w, g.length, p, g);
    Assertions.assertEquals(result, 900);
  }

  private int w = 10;
  private int[] p = {5, 5, 3, 4, 3};
  private int[] g = {400, 500, 200, 300, 350};
}