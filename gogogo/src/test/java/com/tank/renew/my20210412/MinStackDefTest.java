package com.tank.renew.my20210412;

import io.vavr.collection.Stream;
import lombok.val;
import lombok.var;
import org.junit.jupiter.api.*;

/**
 * @author tank198435163.com
 */
@DisplayName("栈中push,pop,min均是O(1)获取")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MinStackDefTest {

  @Test
  @DisplayName("出栈")
  void pop() {
    val value = this.v1.pop();
    Assertions.assertNotNull(value);
    Assertions.assertEquals(value.intValue(), 1);
  }

  @Test
  @DisplayName("最小值")
  void min() {
    var minValue = this.v1.min();
    Assertions.assertNotNull(minValue);
    Assertions.assertEquals(minValue.intValue(), 1);
  }

  @BeforeEach
  void initStack() {
    this.v1 = new MinStackV1<>(5, Integer.class);
    final Integer[] arr = {5, 10, 3, 7, 9, 1};
    Stream.of(arr).forEach(this.v1::push);
    System.out.println("init ok");
  }

  private MinStackDef<Integer> v1;
}