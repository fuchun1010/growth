package com.tank.stream;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

/**
 * @author tank198435163.com
 */
@TestInstance(PER_CLASS)
class ReducerV1Test {

  @Test
  @DisplayName("测试聚合")
  void sum() {
    val reducerV1 = new ReducerV1();
    Assertions.assertNotNull(reducerV1);
    reducerV1.sum(1, 10);
  }

}