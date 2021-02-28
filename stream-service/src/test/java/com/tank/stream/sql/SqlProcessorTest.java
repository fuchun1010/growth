package com.tank.stream.sql;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

/**
 * @author tank198435163.com
 */
@DisplayName("测试简单sql统计")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SqlProcessorTest {

  @Test
  @DisplayName("测试获取文本信息=>table=>print")
  void process() {
    this.textProcessor.process();
  }

  @BeforeEach
  void init() {
    this.textProcessor = new TextProcessor();
  }

  private TextProcessor textProcessor;
}