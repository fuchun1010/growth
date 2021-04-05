package com.tank.renew.my20210405;

import org.junit.jupiter.api.*;

@DisplayName("测试CAS和有锁差别")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class IncrementerDefTest {

  @Test
  @DisplayName("有锁测试效果:{cost:[14027] ,result:[335544320]}")
  void incrementWithLock() {
    Assertions.assertNotNull(this.incrementerDef);
    this.incrementerDef.increment();
    Assertions.assertEquals(this.incrementerDef.getCounter(), expect);
  }

  @Test
  @DisplayName("CAS测试效果:{cost:[15802] ,result:[335544320]}")
  void incrementWithCAS() {
    IncrementerDef incrementerDef = new IncrementWithCas();
    Assertions.assertNotNull(incrementerDef);
    incrementerDef.increment();
    Assertions.assertEquals(incrementerDef.getCounter(), expect);
  }

  @BeforeEach
  void initialize() {
    this.incrementerDef = new IncrementerWithLock();

  }

  private IncrementerDef incrementerDef;

  private final int expect = 335544320;
}