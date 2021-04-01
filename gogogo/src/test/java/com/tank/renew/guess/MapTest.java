package com.tank.renew.guess;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Maps;
import io.vavr.collection.Stream;
import lombok.*;
import lombok.experimental.Accessors;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.platform.commons.util.Preconditions;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author tank198435163.com
 */
@DisplayName("test for map")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MapTest {

  @DisplayName("参数化")
  @ParameterizedTest
  @ValueSource(strings = {"hello", "world"})
  void add(@NonNull final String str) {
    Assertions.assertTrue(str.length() > 0);
  }


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
  @DisplayName("测试table-size")
  void testTableSize() {
    val index = this.tableSize(9);
    Assertions.assertEquals(index, 16);
  }

  @DisplayName("将天转为日期")
  @ParameterizedTest
  @ValueSource(ints = {52, 53})
  void calculateDateFromDay(int offsetDay) {
    val DEFAULT_ERROR = "-";
    val startOfYear = Stream.of(DateTime.now().year())
            .map(String::valueOf)
            .map(year -> year.concat("-01").concat("-01")).findLast(year -> year.trim().length() > 0)
            .getOrElse(DEFAULT_ERROR);
    Assertions.assertNotEquals(DEFAULT_ERROR, startOfYear);
    val dateStr = DateTime.of(startOfYear, DatePattern.NORM_DATE_PATTERN)
            .offset(DateField.DAY_OF_YEAR, offsetDay)
            .toDateStr();
    System.out.println("date = " + dateStr);
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

  @Test
  @DisplayName("debug hash map implements")
  void testPut() {
    val raw = new HashMap<Integer, String>(1 << 4);
    IntStream.rangeClosed(1, 592)
            .forEach(index -> raw.put(index, String.valueOf(index)));
    val mappWrapper = Maps.newHashMap(raw);
    Assertions.assertFalse(raw.isEmpty());
  }

  @Test
  @DisplayName("测试数组链表")
  void testAddForPointerArr() {
    val list = IntStream.rangeClosed(1, 20)
            .boxed()
            .map(String::valueOf)
            .map("hello"::concat)
            .collect(Collectors.toList());
    Assertions.assertFalse(list.isEmpty());
    val pointerArr = new PointerArr();
    list.forEach(pointerArr::add);
    this.pointerArr.print();
  }

  @BeforeEach
  void initialize() {
    this.mappings = Maps.newConcurrentMap();
    this.pointerArr = new PointerArr();
    IntStream.rangeClosed(1, 10)
            .boxed()
            .forEach(index -> this.mappings.put(index, String.valueOf(index)));

  }

  private int tableSize(int cap) {
    var result = cap - 1;
    for (Integer offset : Stream.of(1, 2, 4, 8, 16)) {
      result |= result >>> offset;
    }
    val capacity = 1 << 30;
    return result < 0 ? 1 : (result > capacity ? result : result + 1);
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
        TimeUnit.SECONDS.sleep(1);
        System.out.println(StrUtil.format("Thread: [{}], value:[{}]", Thread.currentThread().getName(), next.getValue()));
      }
    }

    private final Map<Integer, String> mappings;
  }


  private static class PointerArr {

    public void add(@NonNull String data) {
      Preconditions.condition(StrUtil.isNotBlank(data), "not allowed empty string");
      if (ArrayUtil.isEmpty(tab)) {
        tab = new Node[1 << 2];
      }
      int index = Math.abs(Objects.hash(data)) & (tab.length - 1);
      if (Objects.isNull(tab[index])) {
        tab[index] = new Node(data, null);
      } else {
        val backNode = tab[index];
        tab[index] = new Node(data, null);
        tab[index].next = backNode;
      }
    }


    public void print() {
      if (Objects.isNull(this.tab)) {
        return;
      }
      for (Node node : this.tab) {
        while (node != null) {
          System.out.println(node.getData());
          node = node.next;
        }
      }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    static class Node {

      private String data;

      private Node next;

    }

    private static Node[] tab;
  }

  @Getter
  @Setter
  @Accessors(chain = true)
  private static class Order {
    private String orderNo;

    private String channel;
  }

  private Map<Integer, String> mappings;

  private PointerArr pointerArr;
}
