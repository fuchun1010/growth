package com.tank.renew.my20210502;

import lombok.val;
import org.junit.jupiter.api.*;

/**
 * @author tank198435163.com
 */
@DisplayName("简单的范围扫描")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SimpleRangeScannerTest {

  @Test
  @DisplayName("测试数据放入桶")
  void put() {
    val result = this.simpleRangeScanner.put();
    Assertions.assertEquals(result.size(), 3);
    for (Bucket bucket : result) {
      Assertions.assertFalse(bucket.getCollections().isEmpty());
    }
  }

  @BeforeEach
  void initialize() {
    this.simpleRangeScanner = new SimpleRangeScanner(3, 1, 3, 8, 12, 18, 21, 27, 36);
  }

  private SimpleRangeScanner simpleRangeScanner;

}