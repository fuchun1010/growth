package com.tank.stream.sql;

import com.tank.stream.sql.mysql.MysqlStreamProcessor;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

/**
 * @author tank198435163.com
 */
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class MysqlStreamProcessorTest {

  @Test
  void process() {
    val processor = new MysqlStreamProcessor();
    processor.process();
  }
}