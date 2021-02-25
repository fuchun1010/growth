package com.tank.stream.common;

import com.google.common.collect.Lists;
import lombok.val;
import org.junit.jupiter.api.*;

import java.util.List;

@DisplayName("props工具")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PropsTest {

  @Test
  @DisplayName("添加属性")
  void addProps() {
    Assertions.assertTrue(this.props.size() > 0);
    Assertions.assertTrue(this.props.value("topics").isDefined());
    val isListClass = this.props.value("topics").get() instanceof List;
    Assertions.assertTrue(isListClass);
  }


  @Test
  @DisplayName("属性转换")
  void toProps() {
    Assertions.assertTrue(this.props.value("topics").isDefined());
  }

  @BeforeEach
  void init() {
    this.props = new PropsBuilder();
    val list = Lists.<String>newLinkedList();
    list.add("member");
    list.add("orders");
    val result = this.props.addProps("topics", list);
    Assertions.assertNotNull(result);
  }

  private PropsBuilder props;
}