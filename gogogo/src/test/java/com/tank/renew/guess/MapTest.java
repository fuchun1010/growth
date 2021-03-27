package com.tank.renew.guess;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Maps;
import io.vavr.collection.Stream;
import lombok.*;
import lombok.experimental.Accessors;
import org.junit.jupiter.api.*;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author tank198435163.com
 */
@DisplayName("test for map")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MapTest {

  @Test
  @DisplayName("遍历过程中删除")
  void testRemove() {
    val iterator = this.mappings.entrySet().iterator();

    while (iterator.hasNext()) {
      val next = iterator.next();
      iterator.remove();
      Assertions.assertNotNull(next);
      Assertions.assertNotNull(next.getValue());
      System.out.println(StrUtil.format("value: [{}]", next.getValue()));
    }

    Assertions.assertEquals(this.mappings.size(), 0);
  }

  @Test
  @SneakyThrows
  @DisplayName("并发遍历并且删除")
  void testConcurrentRemove() {
    Assertions.assertTrue("start".length() > 0);
    val taskA = new MapOperatorTask(this.mappings);
    val taskB = new MapOperatorTask(this.mappings);
    val latch = new CountDownLatch(1);

    Stream.of(taskA, taskB).map(Thread::new).forEach(Thread::start);
    latch.await();
  }

  @Test
  @DisplayName("hashCode for order")
  void testHashCodeForOrder() {
    val order = new Order().setOrderNo("s0001").setChannel("meituan");
    val result = Objects.hashCode(order.getOrderNo()) ^ Objects.hashCode(order.getChannel());
    System.out.println(result);
    Assertions.assertTrue(result > 0);
  }

  @Test
  @DisplayName("测试数组获取头元素")
  void testFirstElement() {
    String[] arr = {"hello", "this", "is"};
    val len = arr.length;
    val index = (len - 1) & Objects.hash("is");
    Assertions.assertNotNull(arr[index]);
    Assertions.assertEquals("hello", arr[index]);
    System.out.println(arr[index]);


  }

  @BeforeEach
  void initialize() {
    this.mappings = Maps.newConcurrentMap();
    IntStream.rangeClosed(1, 10)
            .boxed()
            .forEach(index -> this.mappings.put(index, String.valueOf(index)));
  }

  private static class MapOperatorTask implements Runnable {

    public MapOperatorTask(@NonNull final Map<Integer, String> mappings) {
      this.mappings = mappings;
    }

    @Override
    @SneakyThrows
    public void run() {
      val iterator = this.mappings.entrySet().iterator();
      while (iterator.hasNext()) {
        val next = iterator.next();
        iterator.remove();
        Assertions.assertNotNull(next);
        TimeUnit.SECONDS.sleep(1);
        Assertions.assertNotNull(next.getValue());
        System.out.println(StrUtil.format("Thread: [{}], value:[{}]", Thread.currentThread().getName(), next.getValue()));
      }
    }

    private Map<Integer, String> mappings;
  }

  @Getter
  @Setter
  @Accessors(chain = true)
  private static class Order {
    private String orderNo;

    private String channel;
  }

  private Map<Integer, String> mappings;
}
