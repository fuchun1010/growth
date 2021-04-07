package com.tank.stream.my20210407;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

/**
 * @author tank198435163.com
 */

@DisplayName("topN")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TopNStreamTest {

  @Test
  void top5() {
    val topNStream = new TopStream();
    Assertions.assertNotNull(topNStream);
    topNStream.process();
  }
}