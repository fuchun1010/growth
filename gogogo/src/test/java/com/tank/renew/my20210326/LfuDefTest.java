package com.tank.renew.my20210326;

import lombok.val;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@DisplayName("lfu测试")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LfuDefTest {

  @Test
  @DisplayName("lfu查找成功")
  void find() {
    val node = this.lfuDef.find(2);
    Assertions.assertNotNull(node);
    Assertions.assertTrue(node.isPresent());
    Assertions.assertEquals(node.get().getValue(), "2");
  }

  @Test
  @DisplayName("lfu查找不存在的")
  void notFind() {
    val node = this.lfuDef.find(200);
    Assertions.assertNotNull(node);
    Assertions.assertFalse(node.isPresent());
  }

  @BeforeEach
  @DisplayName("初始化")
  @SuppressWarnings("unchecked")
  void initialize() {
    this.lfuDef = new LfuDefImpl<>();
    final List<CacheNode<Integer, String>> cacheNodes = IntStream.rangeClosed(1, 10)
            .boxed()
            .map(index -> new CacheNode<>(index, String.valueOf(index)))
            .collect(Collectors.toList());
    CacheNode<Integer, String>[] arr = new CacheNode[cacheNodes.size()];
    cacheNodes.toArray(arr);
    this.lfuDef.init(arr);
  }

  private LfuDef<Integer, String> lfuDef;
}