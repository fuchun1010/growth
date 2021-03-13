package com.tank.stream.cdc;

import cn.hutool.json.JSONUtil;
import lombok.val;
import org.junit.jupiter.api.*;

import java.util.concurrent.CountDownLatch;

/**
 * @author tank198435163.com
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TableCdcStreamTest {

  @Test
  @DisplayName("测试sql dcd的stream")
  void processSqlEvent() {
    Assertions.assertNotNull(this.tableCdcStream);
    val latch = new CountDownLatch(1);

    try {
      this.tableCdcStream.processSqlEvent(value -> System.out.println(JSONUtil.toJsonStr(value)));
      latch.await();
    } catch (Exception e) {
      e.printStackTrace();
      latch.countDown();
    }

  }

  @Test
  @DisplayName("测试目录")
  void testDir() {
    val dir = System.getProperty("user.dir");
    Assertions.assertNotNull(dir);
    System.out.println(dir);
  }

  @BeforeEach
  void init() {
    this.tableCdcStream = new TableCdcStream();
  }

  private TableCdcStream tableCdcStream;


}