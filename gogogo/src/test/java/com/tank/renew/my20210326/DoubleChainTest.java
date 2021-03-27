package com.tank.renew.my20210326;

import cn.hutool.core.util.StrUtil;
import lombok.val;
import org.junit.jupiter.api.*;

import java.util.stream.IntStream;

@DisplayName("双向链表测试")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DoubleChainTest {

  @Test
  @DisplayName("添加缓存节点")
  void addCacheNode() {
    val endInclusive = 10;
    IntStream.rangeClosed(1, endInclusive)
            .boxed()
            .forEach(index -> this.doubleChain.addCacheNode(index, StrUtil.format("hello{}", index)));
    Assertions.assertEquals(this.doubleChain.size(), endInclusive);
  }

  @Test
  @DisplayName("删除头节点")
  void delHead() {
    val endInclusive = 10;
    IntStream.rangeClosed(1, endInclusive)
            .boxed()
            .forEach(index -> this.doubleChain.addCacheNode(index, StrUtil.format("hello{}", index)));
    this.doubleChain.delHead();
    Assertions.assertEquals(this.doubleChain.size(), endInclusive - 1);
  }


  @BeforeEach
  void initialize() {
    this.doubleChain = new DoubleChain<>();
  }

  private DoubleChain<Integer, String> doubleChain;
}