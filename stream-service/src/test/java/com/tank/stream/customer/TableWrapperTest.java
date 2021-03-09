package com.tank.stream.customer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

/**
 * @author tank198435163.com
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TableWrapperTest {

  @Test
  void run() {
    Assertions.assertNotNull(this.tableWrapper);
    this.tableWrapper.run();
  }


  @BeforeEach
  void init() {
    this.tableWrapper = new TableWrapper();
  }


  private TableWrapper tableWrapper;

}