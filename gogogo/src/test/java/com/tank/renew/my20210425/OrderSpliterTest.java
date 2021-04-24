package com.tank.renew.my20210425;

import com.google.common.collect.Lists;
import com.google.common.collect.Queues;
import lombok.val;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Queue;
import java.util.stream.Stream;

@DisplayName("切分订单")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OrderSpliterTest {

  @Test
  void splitted() {
    val result = this.orderSpliter.splitted(this.items, this.queue);
    Assertions.assertFalse(result.isEmpty());
  }

  @Test
  void splitted2() {
    this.items = Lists.newArrayList();
    val item01 = new Item().setName("A").setChannel(1).setRepository("code");
    val item02 = new Item().setName("B").setChannel(1).setRepository("code");
    val item03 = new Item().setName("C").setChannel(1).setRepository("warn");
    val item04 = new Item().setName("D").setChannel(2).setRepository("warn");
    Stream.of(item01, item02, item03, item04).forEach(this.items::add);
    this.queue = Queues.newArrayDeque();
    Stream.of("channel", "repository").forEach(this.queue::offer);
    val result = this.orderSpliter.splitted(this.items, this.queue);
    Assertions.assertFalse(result.isEmpty());
  }


  @BeforeEach
  void init() {
    this.orderSpliter = new OrderSpliter();
    this.items = Lists.newArrayList();
    val item01 = new Item().setName("A").setChannel(1).setRepository("code");
    val item02 = new Item().setName("B").setChannel(1).setRepository("code");
    val item03 = new Item().setName("C").setChannel(1).setRepository("warn");
    Stream.of(item01, item02, item03).forEach(this.items::add);
    this.queue = Queues.newArrayDeque();
    Stream.of("channel", "repository").forEach(this.queue::offer);
  }

  private OrderSpliter orderSpliter;
  private List<Item> items;
  private Queue<String> queue;
}