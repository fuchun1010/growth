package com.tank.renew.my20210401;

import lombok.NonNull;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * @author tank198435163.com
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LifeCycleChainTest {

  @ParameterizedTest
  @ValueSource(strings = {"A", "B", "C"})
  @DisplayName("测试用例")
  void nextLifeNodeValue(@NonNull final String node) {
    val nextValueOpt = this.chain.nextLifeNodeValue(node);
    Assertions.assertTrue(nextValueOpt.isDefined());
  }


  @BeforeEach
  void initialize() {
    this.chain = new LifeCycleChain<>();
    this.chain.init(v -> v, "A", "B", "C", "D", "E");
  }

  private LifeCycleChain<String, String> chain;

}