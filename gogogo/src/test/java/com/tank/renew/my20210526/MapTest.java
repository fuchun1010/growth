package com.tank.renew.my20210526;

import com.google.common.collect.Maps;
import lombok.val;
import org.junit.jupiter.api.*;

import java.util.Map;

/**
 * @author tank198435163.com
 */
@DisplayName("map测试")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MapTest {


  @Test
  @DisplayName("computeIf测试")
  void testComputeIfAbsent() {
    this.map.computeIfAbsent("apple", key -> 1);
    val actual = this.map.get("apple");
    Assertions.assertNotNull(actual);
    Assertions.assertEquals(actual.intValue(), 1);
  }

  @Test
  @DisplayName("computePresent测试")
  void testComputePresent() {
    this.map.computeIfAbsent("apple", key -> 1);
    this.map.computeIfPresent("apple", (key, v) -> --v);
    val actual = this.map.get("apple");
    Assertions.assertNotNull(actual);
    Assertions.assertEquals(actual.intValue(), 0);
  }

  @BeforeEach
  void init() {
    this.map = Maps.newHashMap();
  }

  private Map<String, Integer> map;
}
