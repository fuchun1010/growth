package com.tank.spike;

import cn.hutool.core.util.StrUtil;
import io.vavr.Tuple2;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author tank198435163.com
 */
class HashTest {

  @Test
  @DisplayName("取余")
  void testMod() {
    val seed = 8;
    val result = 9 & (seed - 1);
    assertEquals(1, result);
  }

  @Test
  @DisplayName("hash集合")
  void testHashGroup() {
    Long[] seed = {18623377391L};
    int bullet = 32;
    val bulletResult = IntStream.rangeClosed(1, 1000).boxed().map(index -> {
      ++seed[0];
      return new Tuple2<Long, String>(seed[0], StrUtil.format("jack_{}", index));
    }).map(result -> {
      Long hash = result._1() & (bullet - 1);
      return new Tuple2<Long, String>(hash, result._2());
    }).collect(Collectors.groupingBy(Tuple2::_1));
    Assertions.assertFalse(bulletResult.isEmpty());


  }


}
