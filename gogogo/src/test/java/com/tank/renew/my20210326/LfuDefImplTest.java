package com.tank.renew.my20210326;

import lombok.val;
import org.junit.jupiter.api.*;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author tank198435163.com
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LfuDefImplTest {

  @Test
  @DisplayName("查询命中")
  void find() {
    val result = this.lfuDef.find(1);
    Assertions.assertEquals("1", result);
  }

  @BeforeEach
  @DisplayName("初始化LfuDef实例")
  @SuppressWarnings("unchecked")
  void init() {
    this.lfuDef = new LfuDefImpl<>();
    val collect = IntStream.rangeClosed(1, 10)
            .boxed()
            .map(index -> {
              val cacheNode = new CacheNode<Integer, String>();
              cacheNode.setKey(index);
              cacheNode.setValue(String.valueOf(index));
              return cacheNode;
            })
            .collect(Collectors.toList());

    for (CacheNode<Integer, String> node : collect) {
      this.lfuDef.init(node);
    }
  }


  private LfuDef<Integer, String> lfuDef;
}