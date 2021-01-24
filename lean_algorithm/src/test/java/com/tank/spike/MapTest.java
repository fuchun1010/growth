package com.tank.spike;

import io.vavr.Tuple2;
import io.vavr.collection.List;
import io.vavr.collection.Stream;
import io.vavr.control.Try;
import lombok.Getter;
import lombok.Setter;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

/**
 * @author tank198435163.com
 */
@DisplayName("hash测试")
@TestInstance(PER_CLASS)
class MapTest {

  @Test
  @DisplayName("测试创建map")
  void createMapTest() {
    val map = Collections.synchronizedMap(new HashMap<String, String>());
    map.put("jack", "programmer");
    map.put("john", "driver");
    Assertions.assertEquals(2, map.size());
  }

  @Test
  @DisplayName("测试clone对象")
  void cloneTest() {
    val driver = new Driver();
    List<Driver> drivers = Stream.ofAll(Arrays.asList("jack", "john")).map(name -> {
      val result = Try.of(driver::clone).get();
      result.setName(name);
      return result;
    }).toList();
    Assertions.assertFalse(drivers.isEmpty());
    Assertions.assertTrue("jack".equalsIgnoreCase(drivers.get(0).getName()));
    Assertions.assertTrue("john".equalsIgnoreCase(drivers.get(1).getName()));
  }

  @Test
  @DisplayName("计算索引值")
  void indexTest() {
    final int[] data = {3, 19, 35};
    val result = Stream.ofAll(data).map(d -> d & 15).reduce(Integer::sum);
    Assertions.assertEquals(9, (int) result);
  }

  @Test
  @DisplayName("hash扰动函数测试")
  void hashIndexTest() {
    val driver = new Driver();
    driver.setName("jack");
    int cap = 4;
    int h;
    val hashCode = (h = driver.hashCode()) < 0 ? 0 : h ^ (h >>> 16);
    Assertions.assertTrue(hashCode > 0);
    val index = hashCode & (cap - 1);
    Assertions.assertEquals(2, index);
  }

  @Test
  @DisplayName("hash index分布")
  void hashDistributionTest() {
    int end = 500;
    int star = 1;
    val groups = IntStream.rangeClosed(star, end)
            .boxed()
            .map(index -> index ^ (index >>> 16))
            .map(result -> {
              val index = result & 15;
              final Tuple2<Integer, Integer> tuple = new Tuple2<>(index, result);
              return tuple;
            })
            .collect(Collectors.groupingBy(Tuple2::_1));

    val groupsV2 = IntStream
            .rangeClosed(star, end)
            .boxed()
            .map(index -> {
              val result = index & 15;
              final Tuple2<Integer, Integer> tuple = new Tuple2<>(index, result);
              return tuple;
            }).collect(Collectors.groupingBy(Tuple2::_1));

    Assertions.assertFalse(groups.isEmpty());
    Assertions.assertFalse(groupsV2.isEmpty());

  }


  @Test
  @DisplayName("比较大小")
  void compareMax() {
    val r1 = 1 << 30;
    val rs = Integer.MAX_VALUE;
    Assertions.assertNotEquals(r1, rs);
  }

  @Getter
  @Setter
  private static class Driver implements Cloneable {
    @Override
    protected Driver clone() throws CloneNotSupportedException {

      return ((Driver) super.clone());
    }

    private String name;
  }
}
