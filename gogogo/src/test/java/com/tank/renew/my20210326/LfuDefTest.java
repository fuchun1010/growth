package com.tank.renew.my20210326;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@DisplayName("lfu测试")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LfuDefTest {

  @Test
  @DisplayName("lfu查找")
  void findAny() {

  }

  @BeforeEach
  @DisplayName("初始化")
  void initialize() {
    this.lfuDef = new LfuDefImpl<>();
  }

  private LfuDef<String, String> lfuDef;
}