package com.tank.spike;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Queues;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Queue;
import java.util.stream.IntStream;

@DisplayName("斐波拉契数列计算")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FibonacciTest {

  @Test
  @DisplayName("第一个测试用例")
  void calculate1() {
    Queue<Integer> queue = Queues.newArrayDeque();
    IntStream.rangeClosed(1, 100).forEach(queue::add);
    val result = this.accumulate(queue);
    Assertions.assertEquals(5050, result);
  }

  @Test
  @DisplayName("2的整数次冥")
  void power2() {
    val result = this.tableCapacity(7);
    Assertions.assertEquals(8, result);
  }

  @Test
  @DisplayName("明天日期")
  void tomorrow() {
    val tomorrow = DateUtil.date().offsetNew(DateField.MONTH, 1);
    val tomorrowStr = DateUtil.format(tomorrow, DatePattern.NORM_DATE_PATTERN);
    val expireDateTime = StrUtil.format("{} {}", tomorrowStr, "04:00:58");
    Assertions.assertNotNull(expireDateTime);
    System.out.println(tomorrowStr);
  }

  @Test
  @DisplayName("过期分钟数")
  void expiredMinutes() {
    val tomorrow = DateUtil.date().offsetNew(DateField.MONTH, 1);
    val tomorrowStr = DateUtil.format(tomorrow, DatePattern.NORM_DATE_PATTERN);
    val expireDateStr = StrUtil.format("{} {}", tomorrowStr, "04:00:58");
    val differMinutes = DateUtil.between(DateUtil.date(), DateUtil.parseDate(expireDateStr), DateUnit.MINUTE);
    Assertions.assertTrue(differMinutes > 0);
    System.out.println(differMinutes);
  }

  int tableCapacity(int capacity) {
    capacity |= capacity >>> 1;
    capacity |= capacity >>> 2;
    capacity |= capacity >>> 4;
    capacity |= capacity >>> 8;
    capacity |= capacity >>> 16;
    capacity = capacity < 0 ? 1 : capacity + 1;
    return capacity;
  }


  int accumulate(Queue<Integer> data) {
    return data.isEmpty() ? 0 : data.poll() + accumulate(data);
  }


}
