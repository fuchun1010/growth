package com.tank.stream.cdc;

import org.junit.jupiter.api.*;

/**
 * @author tank198435163.com
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CdcFlowTest {

  @Test
  @DisplayName("测试事件流")
  void processEventFlow() {
    Assertions.assertNotNull(this.cdcFlow);
    this.cdcFlow.processEventFlow();
  }

  @BeforeEach
  void init() {
    this.cdcFlow = new CdcFlow();
  }

  private CdcFlow cdcFlow;
}