package com.tank.renew.my20210522;

import lombok.val;
import org.junit.jupiter.api.*;

/**
 * @author tank198435163.com
 */
@DisplayName("挖矿")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GoldWorkerProfitTest {

  @Test
  @DisplayName("实际最大获取金矿")
  void maxProfit() {
    val maxProfit = this.goldWorkerProfit.maxProfit(w, n, p, g);
    Assertions.assertEquals(900, maxProfit);
  }


  @BeforeEach
  void init() {
    this.goldWorkerProfit = new DefaultGoldWorkerProfit();
  }

  private GoldWorkerProfit goldWorkerProfit;

  private int n = 5;
  private int w = 10;
  private int[] p = {5, 5, 3, 4, 3};
  private int[] g = {400, 500, 200, 300, 350};
}